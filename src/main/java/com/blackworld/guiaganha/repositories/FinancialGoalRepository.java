package com.blackworld.guiaganha.repositories;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import com.blackworld.guiaganha.entities.FinancialGoal;

public interface FinancialGoalRepository extends JpaRepository<FinancialGoal, UUID> {

}
