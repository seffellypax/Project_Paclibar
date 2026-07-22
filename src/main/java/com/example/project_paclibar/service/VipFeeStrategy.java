package com.example.project_paclibar.service;

public class VipFeeStrategy implements FeeCalculationStrategy {
    @Override
    public double calculateFee(double amount) {
        return 0.00;
    }
}