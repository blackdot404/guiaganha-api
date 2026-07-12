package com.blackworld.guiaganha.services;

import java.util.List;
import java.util.UUID;
import org.springframework.stereotype.Service;
import com.blackworld.guiaganha.entities.User;
import com.blackworld.guiaganha.entities.VariableExpense;
import com.blackworld.guiaganha.repositories.VariableExpenseRepository;
import com.blackworld.guiaganha.services.exceptions.ExpenseNotFoundException;

@Service
public class VariableExpenseService {

    private final VariableExpenseRepository repository;
    private final UserService service;

    public VariableExpenseService(VariableExpenseRepository repository, UserService service) {
        this.repository = repository;
        this.service = service;
    }

    // Metodo para registrar o gasto exporadico
    public VariableExpense registerExpense(UUID userId, VariableExpense expense) {
        User user = service.getUserById(userId);

        expense.setUser(user);

        return repository.save(expense);
    }

    // Metodo para buscar todos os gastos do usuario
    public List<VariableExpense> getExpensesByUser(UUID userId) {
        User user = service.getUserById(userId);
        return repository.findByUser(user);
    }

    // Metodo de delete do gasto, o id é o id do gasto
    public void deleteExpense(UUID id) {
        VariableExpense expense =
                repository.findById(id).orElseThrow(() -> new ExpenseNotFoundException(id));

        repository.delete(expense);
    }

}
