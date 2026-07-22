package com.example.project_paclibar.controller;

import com.example.project_paclibar.database.IUserDAO;
import com.example.project_paclibar.database.UserDAO;
import com.example.project_paclibar.database.WalletDAO;
import com.example.project_paclibar.model.User;
import com.example.project_paclibar.model.Wallet;
import com.example.project_paclibar.util.SessionManager;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {
    @FXML private TextField emailField;
    @FXML private PasswordField passwordField;
    @FXML private Label errorLabel;

    private final IUserDAO userDAO = new UserDAO();
    private final WalletDAO walletDAO = new WalletDAO();

    @FXML
    public void handleLogin() {
        String username = emailField.getText();
        String password = passwordField.getText();

        User user = userDAO.login(username, password);

        if(user != null) {
            SessionManager.saveSession(user);

            Wallet wallet = walletDAO.getWalletByUserId(user.getUserId());
            navigateToDashboard(user, wallet);
        } else {
            errorLabel.setText("Invalid username or password.");
            errorLabel.setStyle("-fx-text-fill: red;");
        }
    }
    private void navigateToDashboard(User user, Wallet wallet) {
        try {
            FXMLLoader loader =
                    new FXMLLoader(
                            getClass()
                                    .getResource("/dashboard.fxml")
                    );
            Parent root = loader.load();
            DashboardController controller =
                    loader.getController();

            controller.initData(
                    user,
                    wallet
            );
            Stage stage =
                    (Stage) emailField
                            .getScene()
                            .getWindow();
            stage.setScene(
                    new Scene(root,450,750)
            );
            stage.setTitle(
                    "E-Wallet Dashboard"
            );
            stage.show();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    public void handleRegister() {
        try {
            FXMLLoader loader =
                    new FXMLLoader(
                            getClass()
                                    .getResource("/register.fxml")
                    );
            Parent root =
                    loader.load();
            Stage stage =
                    (Stage) emailField
                            .getScene()
                            .getWindow();

            stage.setScene(
                    new Scene(root,450,750)
            );
            stage.setTitle(
                    "Create Account"
            );
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}