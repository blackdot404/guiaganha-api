package com.blackworld.guiaganha.repositories;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import com.blackworld.guiaganha.entities.VariableIncome;

public interface VariableIncomeRepository extends JpaRepository<VariableIncome, UUID> {

}
