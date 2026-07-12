package com.blackworld.guiaganha.repositories;

import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import com.blackworld.guiaganha.entities.FixedExpense;
import com.blackworld.guiaganha.entities.User;

public interface FixedExpenseRepository extends JpaRepository<FixedExpense, UUID> {

    // SELECT para buscar todos os ganhos do usuario
    List<FixedExpense> findByUser(User user);
}
