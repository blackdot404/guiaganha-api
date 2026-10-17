package com.blackworld.guiaganha.repositories;

import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import com.blackworld.guiaganha.entities.User;
import com.blackworld.guiaganha.entities.VariableIncome;

public interface VariableIncomeRepository extends JpaRepository<VariableIncome, UUID> {

    // SELECT para buscar todos os ganhos do usuario
    List<VariableIncome> findByUser(User user);

}
