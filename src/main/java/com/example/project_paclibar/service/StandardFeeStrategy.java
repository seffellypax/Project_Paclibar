package com.example.project_paclibar.service;

public class StandardFeeStrategy implements FeeCalculationStrategy {
    @Override
    public double calculateFee(double amount) {
        return 10.00;
    }
}