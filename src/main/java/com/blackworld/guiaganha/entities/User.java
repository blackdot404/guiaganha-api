package com.blackworld.guiaganha.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.security.Timestamp;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_users")
public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    private BigDecimal dailyRevenueLimit;

    @Column(nullable = false)
    private Timestamp createdAt;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<FixedExpense> fixedExpenses = new HashSet<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<VariableExpense> variableExpenses = new HashSet<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<VariableIncome> variableIncomes = new HashSet<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<FinancialGoal> financialGoals = new HashSet<>();

    public User() {

    }

    public User(UUID id, String name, String email, String password, BigDecimal dailyRevenueLimit,
            Timestamp createdAt) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.dailyRevenueLimit = dailyRevenueLimit;
        this.createdAt = createdAt;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public BigDecimal getDailyRevenueLimit() {
        return dailyRevenueLimit;
    }

    public void setDailyRevenueLimit(BigDecimal dailyRevenueLimit) {
        this.dailyRevenueLimit = dailyRevenueLimit;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Set<FixedExpense> getFixedExpenses() {
        return fixedExpenses;
    }

    public Set<VariableExpense> getVariableExpenses() {
        return variableExpenses;
    }

    public void addFixedExpense(FixedExpense expense) {
        this.fixedExpenses.add(expense);
        expense.setUser(this);
    }

    public void removeFixedExpense(FixedExpense expense) {
        this.fixedExpenses.remove(expense);
        expense.setUser(null);
    }

    public void addVariableExpense(VariableExpense expense) {
        this.variableExpenses.add(expense);
        expense.setUser(this);
    }

    public void removeVariableExpense(VariableExpense expense) {
        this.variableExpenses.remove(expense);
        expense.setUser(null);
    }

    public void addVariableIncome(VariableIncome expense) {
        this.variableIncomes.add(expense);
        expense.setUser(this);
    }

    public void removeVariableIncome(VariableIncome expense) {
        this.variableIncomes.remove(expense);
        expense.setUser(null);
    }

    public void addFinancialGoal(FinancialGoal expense) {
        this.financialGoals.add(expense);
        expense.setUser(this);
    }

    public void removeFinancialGoal(FinancialGoal expense) {
        this.financialGoals.remove(expense);
        expense.setUser(null);
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
        User other = (User) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

}
