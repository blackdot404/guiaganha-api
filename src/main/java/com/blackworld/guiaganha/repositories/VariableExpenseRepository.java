package com.blackworld.guiaganha.repositories;

import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import com.blackworld.guiaganha.entities.User;
import com.blackworld.guiaganha.entities.VariableExpense;

public interface VariableExpenseRepository extends JpaRepository<VariableExpense, UUID> {

    // SELECT para buscar todos os gastos do usuario
    List<VariableExpense> findByUser(User user);
}
