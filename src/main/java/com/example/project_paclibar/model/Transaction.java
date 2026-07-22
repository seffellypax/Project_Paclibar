package com.example.project_paclibar.model;

import java.time.LocalDateTime;

public class Transaction {
    private int transactionID;
    private double amount;
    private LocalDateTime date;
    private String type;
    private String description;
    private String status;

    public Transaction(int transactionID, String type, double amount, String description) {
        this.transactionID = transactionID;
        this.type = type;
        this.amount = amount;
        this.description = description;
        this.date = LocalDateTime.now();
        this.status = "COMPLETED";
    }

    public int getTransactionID() {
        return transactionID;
    }
    public double getAmount() {
        return amount;
    }
    public LocalDateTime getDate() {
        return date;
    }
    public String getType() {
        return type;
    }
    public String getDescription() {
        return description;
    }
    public String getStatus() {
        return status;
    }
}