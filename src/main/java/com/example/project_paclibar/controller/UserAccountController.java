package com.example.project_paclibar.controller;

import com.example.project_paclibar.model.User;
import com.example.project_paclibar.model.Wallet;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class UserAccountController {
    @FXML private Label nameLabel;
    @FXML private Label emailLabel;
    @FXML private Label phoneLabel;
    @FXML private Label addressLabel;
    @FXML private Label accountNumberLabel;
    @FXML private Label walletIdLabel;
    @FXML private Label statusLabel;

    private User currentUser;
    private Wallet currentWallet;

    public void initData(User user, Wallet wallet) {

        this.currentUser = user;
        this.currentWallet = wallet;

        nameLabel.setText(user.getName());

        emailLabel.setText(
                "Email: " + user.getEmail()
        );
        phoneLabel.setText(
                "Phone: " + user.getPhone()
        );
        addressLabel.setText(
                "Address: " + user.getAddress()
        );
        accountNumberLabel.setText(
                "User ID: " + user.getUserId()
        );
        walletIdLabel.setText(
                "Username: " + user.getUsername()
        );
        statusLabel.setText(
                "Status: Active"
        );
    }

    @FXML
    public void handleBack() {
        try {
            FXMLLoader loader =
                    new FXMLLoader(
                            getClass().getResource("/dashboard.fxml")
                    );
            Parent root = loader.load();
            DashboardController controller =
                    loader.getController();

            controller.initData(
                    currentUser,
                    currentWallet
            );
            Stage stage =
                    (Stage) nameLabel
                            .getScene()
                            .getWindow();
            stage.setScene(
                    new Scene(root, 450, 750)
            );
            stage.setTitle(
                    "E-Wallet Dashboard"
            );
        } catch (Exception e) {
            e.printStackTrace();

        }
    }
    @FXML
    public void handleLogout() {

        com.example.project_paclibar.util.SessionManager.deleteSession();

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/login.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) nameLabel.getScene().getWindow();

            stage.setScene(new Scene(root, 450, 750));
            stage.setTitle("Login");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}