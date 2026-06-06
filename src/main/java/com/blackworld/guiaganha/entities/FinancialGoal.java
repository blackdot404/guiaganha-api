package com.blackworld.guiaganha.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;
import com.blackworld.guiaganha.entities.enums.GoalState;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_financial_goal")
public class FinancialGoal implements Serializable {
    public static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private BigDecimal targetAmount;

    @Column(nullable = false)
    private BigDecimal savedAmount;

    @Column(nullable = false)
    private LocalDate targetDate;

    @Column(nullable = false)
    private Integer currentState;

    public FinancialGoal() {

    }

    public FinancialGoal(UUID id, User user, String title, BigDecimal targetAmount,
            BigDecimal savedAmount, LocalDate targetDate, GoalState currentState) {
        this.id = id;
        this.user = user;
        this.title = title;
        this.targetAmount = targetAmount;
        this.savedAmount = savedAmount;
        this.targetDate = targetDate;
        setGoalState(currentState);
    }

    public GoalState getGoalState() {
        return GoalState.valueOf(currentState);
    }

    public void setGoalState(GoalState currentState) {
        if (currentState != null) {
            this.currentState = currentState.getCode();
        }
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public BigDecimal getTargetAmount() {
        return targetAmount;
    }

    public void setTargetAmount(BigDecimal targetAmount) {
        this.targetAmount = targetAmount;
    }

    public BigDecimal getSavedAmount() {
        return savedAmount;
    }

    public void setSavedAmount(BigDecimal savedAmount) {
        this.savedAmount = savedAmount;
    }

    public LocalDate getTargetDate() {
        return targetDate;
    }

    public void setTargetDate(LocalDate targetDate) {
        this.targetDate = targetDate;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        FinancialGoal other = (FinancialGoal) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }



}
