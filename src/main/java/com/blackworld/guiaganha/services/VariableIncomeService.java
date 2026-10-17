package com.blackworld.guiaganha.services;

import java.util.List;
import java.util.UUID;
import org.springframework.stereotype.Service;
import com.blackworld.guiaganha.entities.User;
import com.blackworld.guiaganha.entities.VariableIncome;
import com.blackworld.guiaganha.repositories.VariableIncomeRepository;
import com.blackworld.guiaganha.services.exceptions.IncomeNotFoundException;

@Service
public class VariableIncomeService {

    private final VariableIncomeRepository repository;
    private final UserService service;

    public VariableIncomeService(VariableIncomeRepository repository, UserService service) {
        this.repository = repository;
        this.service = service;
    }

    // Metodo para cadastrar um novo ganho
    public VariableIncome registerIncome(UUID id, VariableIncome income) {
        User user = service.getUserById(id);

        income.setUser(user);

        return repository.save(income);
    }

    // Metodo para buscar todos os ganhos do usuario
    public List<VariableIncome> getIncomeByUser(UUID id) {
        User user = service.getUserById(id);

        return repository.findByUser(user);
    }

    // Metodo de delete do ganho, o id é o id do ganho
    public void deleteIncome(UUID id) {
        VariableIncome income =
                repository.findById(id).orElseThrow(() -> new IncomeNotFoundException(id));

        repository.delete(income);
    }

}
