package com.blackworld.guiaganha.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blackworld.guiaganha.entities.FinancialGoal;
import com.blackworld.guiaganha.entities.User;

public interface FinancialGoalRepository extends JpaRepository<FinancialGoal, UUID> {

    // SELECT Para buscar todos as metas do usuario
    List<FinancialGoal> findByUser(User user);
}
