package com.kira.ira.expensetrackerapi.api.model;

import java.time.LocalDate;

public class Split {
    private Long id;
    private Long expenseId;
    private int splitMonths;
    private double monthlyAmount;
    private LocalDate startDate; // Start date of the split
    private LocalDate endDate; // End date of the split

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getExpenseId() {
        return expenseId;
    }

    public void setExpenseId(Long expenseId) {
        this.expenseId = expenseId;
    }

    public int getSplitMonths() {
        return splitMonths;
    }

    public void setSplitMonths(int splitMonths) {
        this.splitMonths = splitMonths;
    }

    public double getMonthlyAmount() {
        return monthlyAmount;
    }

    public void setMonthlyAmount(double monthlyAmount) {
        this.monthlyAmount = monthlyAmount;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }
}
