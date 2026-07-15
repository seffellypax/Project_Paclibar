package com.example.project_paclibar.model;

public class User {
    private int userID;
    private String fullName;
    private String email;
    private String phoneNo;
    private String password;

    // Composition: User owns one Wallet
    private Wallet wallet;

    public User(int userID, String fullName, String email, String phoneNo, String password) {
        this.userID = userID;
        this.fullName = fullName;
        this.email = email;
        this.phoneNo = phoneNo;
        this.password = password;
        // Initialize the wallet when the user is created
        this.wallet = new Wallet();
    }

    // Getters and Setters
    public Wallet getWallet() { return wallet; }
    public void setWallet(Wallet wallet) { this.wallet = wallet; }
}