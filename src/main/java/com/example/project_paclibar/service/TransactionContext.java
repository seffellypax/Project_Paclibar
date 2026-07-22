package com.example.project_paclibar.service;

public class TransactionContext {
    private FeeCalculationStrategy strategy;

    public TransactionContext(FeeCalculationStrategy strategy) {
        this.strategy = strategy;
    }

    public void setStrategy(FeeCalculationStrategy strategy) {
        this.strategy = strategy;
    }

    public double executeFeeCalculation(double amount) {
        return strategy.calculateFee(amount);
    }
}