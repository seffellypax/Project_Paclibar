package com.example.project_paclibar.model;


public class Wallet {
    private int walletID;
    private double balance;
    private String status;

    public void addMoney(double amount) { this.balance += amount; }
    public void withdrawMoney(double amount) { this.balance -= amount; }
    public double checkBalance() { return this.balance; }
}