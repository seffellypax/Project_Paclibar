package com.example.project_paclibar.controller;


import com.example.project_paclibar.database.UserDAO;
import com.example.project_paclibar.model.User;

import javafx.fxml.FXML;
import javafx.scene.control.*;

public class RegisterController {
    @FXML private TextField usernameField;
    @FXML private PasswordField passwordField;
    @FXML private TextField nameField;
    @FXML private TextField emailField;
    @FXML private TextField phoneField;
    @FXML private TextField addressField;
    @FXML private Label statusLabel;

    private UserDAO userDAO = new UserDAO();

    @FXML
    public void handleRegister() {
        User user =
                new User(
                        0,
                        usernameField.getText(),
                        passwordField.getText(),
                        nameField.getText(),
                        emailField.getText(),
                        phoneField.getText(),
                        addressField.getText(),
                        "USER"
                );
        boolean success = userDAO.register(user);
        if(success) {
            statusLabel.setText(
                    "Registration successful!"
            );
            statusLabel.setStyle(
                    "-fx-text-fill:green;"
            );
        }
        else {
            statusLabel.setText(
                    "Registration failed."
            );
            statusLabel.setStyle(
                    "-fx-text-fill:red;"
            );
        }
    }
}