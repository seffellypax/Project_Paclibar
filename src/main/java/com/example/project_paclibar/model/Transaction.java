package com.example.project_paclibar.model;
import java.time.LocalDateTime;

public class Transaction {
    private int transactionID;
    private double amount;
    private LocalDateTime date; // Modern Date API
    private String type;
    private String status;

    public Transaction(int transactionID, double amount, String type) {
        this.transactionID = transactionID;
        this.amount = amount;
        this.type = type;
        this.date = LocalDateTime.now(); // Captures the current timestamp
        this.status = "COMPLETED";
    }

    // Getters
    public int getTransactionID() { return transactionID; }
    public double getAmount() { return amount; }
    public LocalDateTime getDate() { return date; }
    public String getType() { return type; }
}