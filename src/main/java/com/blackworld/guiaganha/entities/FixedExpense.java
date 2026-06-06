package com.blackworld.guiaganha.entities;

// Entidade de custos fixos, diarios, mensais e anuais.

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.UUID;
import com.blackworld.guiaganha.entities.enums.ExpenseFrequency;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_fixed_expenses")
public class FixedExpense implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private BigDecimal amount;

    private Integer dueDay;

    @Column(nullable = false)
    private Integer frequency;

    public FixedExpense() {

    }

    public FixedExpense(UUID id, User user, String name, BigDecimal amount, Integer dueDay,
            ExpenseFrequency frequency) {
        this.id = id;
        this.user = user;
        this.name = name;
        this.amount = amount;
        this.dueDay = dueDay;
        setExpenseFrequency(frequency);
    }

    public ExpenseFrequency getExpenseFrequency() {
        return ExpenseFrequency.valueOf(frequency);
    }

    public void setExpenseFrequency(ExpenseFrequency frequency) {
        if (frequency != null) {
            this.frequency = frequency.getCode();
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Integer getDueDay() {
        return dueDay;
    }

    public void setDueDay(Integer dueDay) {
        this.dueDay = dueDay;
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
        FixedExpense other = (FixedExpense) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }



}
