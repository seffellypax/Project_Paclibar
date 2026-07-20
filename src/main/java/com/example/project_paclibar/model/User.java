package com.example.project_paclibar.model;

import java.io.Serializable; // Import this

public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    private int userId;
    private String username;
    private String password;
    private String name;
    private String email;
    private String phone;
    private String address;
    private String role;


    public User(int userId,
                String username,
                String password,
                String name,
                String email,
                String phone,
                String address,
                String role) {

        this.userId = userId;
        this.username = username;
        this.password = password;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.role = role;
    }

    public int getUserId() {
        return userId;
    }


    public String getUsername() {
        return username;
    }


    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }

    public String getRole() {
        return role;
    }
}