package com.example.project_paclibar.model;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

public class Wallet {
    private final DoubleProperty balance = new SimpleDoubleProperty(0.0);

    public DoubleProperty balanceProperty() {
        return balance;
    }
    public double getBalance() {
        return balance.get();
    }
    public void addMoney(double amount) {
        if (amount > 0) {
            balance.set(balance.get() + amount);
        }
    }
    public void withdrawMoney(double amount) {
        if (amount > 0 && amount <= balance.get()) {
            balance.set(balance.get() - amount);
        }
    }
}