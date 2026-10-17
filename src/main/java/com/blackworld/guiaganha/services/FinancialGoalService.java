package com.blackworld.guiaganha.services;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.blackworld.guiaganha.entities.FinancialGoal;
import com.blackworld.guiaganha.entities.User;
import com.blackworld.guiaganha.repositories.FinancialGoalRepository;
import com.blackworld.guiaganha.services.exceptions.FinancialGoalNotFoundException;
import com.blackworld.guiaganha.services.exceptions.InsufficientBalanceException;

@Service
public class FinancialGoalService {

    private final FinancialGoalRepository repository;
    private final UserService service;

    public FinancialGoalService(FinancialGoalRepository repository, UserService service) {
        this.repository = repository;
        this.service = service;
    }

    // Metodo para registrar uma meta financeira
    public FinancialGoal createGoal(UUID userId, FinancialGoal goal) {
        User user = service.getUserById(userId);
        goal.setUser(user);

        if (goal.getSavedAmount() == null) {
            goal.setSavedAmount(BigDecimal.ZERO);
        }

        return repository.save(goal);
    }

    // Metodos para consultar as metas por usuario
    public List<FinancialGoal> getGoalsByUser(UUID userId) {
        User user = service.getUserById(userId);

        return repository.findByUser(user);
    }

    // Metodo que guarda dinheiro na meta
    public FinancialGoal addFunds(UUID goalId, BigDecimal amountToAdd) {

        // Primeiro garantir que a meta exista no banco
        FinancialGoal goal = repository.findById(goalId)
                .orElseThrow(() -> new FinancialGoalNotFoundException(goalId));

        // Segundo garantir que o valor seja maior que zero
        if (amountToAdd == null || amountToAdd.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("The amount to add must be greater than zero");
        }

        // Terceiro fazemos a soma e setamos o novo resultado
        BigDecimal updatedAmount = goal.getSavedAmount().add(amountToAdd);
        goal.setSavedAmount(updatedAmount);

        return repository.save(goal);

    }

    // Metodos para saque de valores da meta
    public FinancialGoal withdrawFunds(UUID goalId, BigDecimal amountToWithdraw) {

        FinancialGoal goal = repository.findById(goalId)
                .orElseThrow(() -> new FinancialGoalNotFoundException(goalId));

        if (amountToWithdraw == null || amountToWithdraw.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("The amount to withdraw must be greater than zero.");
        }

        // Valida se o valor de saque é maior que o que tem guardado
        if (amountToWithdraw.compareTo(goal.getSavedAmount()) > 0) {
            throw new InsufficientBalanceException("Insufficient balance for this goal.");
        }

        // Caso o valor seja menor que o valor guardado continua a operação
        BigDecimal updatedAmount = goal.getSavedAmount().subtract(amountToWithdraw);
        goal.setSavedAmount(updatedAmount);

        return repository.save(goal);

    }

    public void deleteGoal(UUID goalId) {
        FinancialGoal goal = repository.findById(goalId)
                .orElseThrow(() -> new FinancialGoalNotFoundException(goalId));

        repository.delete(goal);

    }

}
