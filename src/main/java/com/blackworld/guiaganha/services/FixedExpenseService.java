package com.blackworld.guiaganha.services;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.UUID;
import org.springframework.stereotype.Service;
import com.blackworld.guiaganha.entities.FixedExpense;
import com.blackworld.guiaganha.entities.User;
import com.blackworld.guiaganha.entities.enums.ExpenseFrequency;
import com.blackworld.guiaganha.repositories.FixedExpenseRepository;
import com.blackworld.guiaganha.services.exceptions.FixedNotFoundException;

@Service
public class FixedExpenseService {

    private final FixedExpenseRepository repository;
    private final UserService service;

    public FixedExpenseService(FixedExpenseRepository repository, UserService service) {
        this.repository = repository;
        this.service = service;
    }

    // Metodo para registrar o gasto fixo
    public FixedExpense registerExpense(UUID userId, FixedExpense expense) {
        User user = service.getUserById(userId);
        expense.setUser(user);

        return repository.save(expense);
    }

    // Metodo para buscar todos os gastos fixos do usuario
    public List<FixedExpense> getExpensesByUser(UUID userId) {
        User user = service.getUserById(userId);

        return repository.findByUser(user);
    }

    // Metodo de delete do gasto fixo, o id é o id do gasto
    public void deleteExpense(UUID id) {
        FixedExpense expense =
                repository.findById(id).orElseThrow(() -> new FixedNotFoundException(id));

        repository.delete(expense);
    }

    // Metodo para calcular o peso diário de todas as contas
    public BigDecimal calculateDailyExpenseFraction(UUID userId) {
        List<FixedExpense> expenses = getExpensesByUser(userId);
        BigDecimal totalDailyImpact = BigDecimal.ZERO;

        for (FixedExpense expense : expenses) {
            BigDecimal amount = expense.getAmount();
            ExpenseFrequency frequency = expense.getExpenseFrequency();

            if (frequency == ExpenseFrequency.DAILY) {
                totalDailyImpact = totalDailyImpact.add(amount);
            } else if (frequency == ExpenseFrequency.MONTHLY) {
                BigDecimal dailyFraction =
                        amount.divide(new BigDecimal("30"), 2, RoundingMode.HALF_UP);
                totalDailyImpact = totalDailyImpact.add(dailyFraction);
            } else if (frequency == ExpenseFrequency.YEARLY) {
                BigDecimal dailyFraction =
                        amount.divide(new BigDecimal("365"), 2, RoundingMode.HALF_UP);
                totalDailyImpact = totalDailyImpact.add(dailyFraction);
            }
        }

        return totalDailyImpact;
    }

}
