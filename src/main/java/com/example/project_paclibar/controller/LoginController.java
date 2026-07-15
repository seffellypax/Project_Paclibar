package com.example.project_paclibar.controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;

public class LoginController {
    @FXML private TextField emailField;
    @FXML private PasswordField passwordField;
    @FXML private Label errorLabel;

    // Hardcoded credentials for testing
    private final String TEST_EMAIL = "test@example.com";
    private final String TEST_PASSWORD = "password123";

    @FXML
    public void handleLogin() {
        String inputEmail = emailField.getText();
        String inputPassword = passwordField.getText();

        if (inputEmail.equals(TEST_EMAIL) && inputPassword.equals(TEST_PASSWORD)) {
            errorLabel.setText("Login Successful!");
            errorLabel.setStyle("-fx-text-fill: green;");
            System.out.println("Redirecting to Dashboard...");
            // We will add the screen transition logic here soon
        } else {
            errorLabel.setText("Invalid email or password.");
            errorLabel.setStyle("-fx-text-fill: red;");
        }
    }
}