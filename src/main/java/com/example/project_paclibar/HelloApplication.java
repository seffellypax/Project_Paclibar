package com.example.project_paclibar;

import com.example.project_paclibar.model.User;
import com.example.project_paclibar.model.Wallet; // Ensure this is imported
import com.example.project_paclibar.database.WalletDAO;
import com.example.project_paclibar.util.SessionManager;
import com.example.project_paclibar.controller.DashboardController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application { // Make sure you extend Application

    @Override
    public void start(Stage stage) throws Exception {

        User savedUser = SessionManager.getSession();

        if (savedUser != null) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/dashboard.fxml"));
            Parent root = loader.load();
            DashboardController controller = loader.getController();

            WalletDAO walletDAO = new WalletDAO();
            Wallet wallet = walletDAO.getWalletByUserId(savedUser.getUserId());
            controller.initData(savedUser, wallet);

            stage.setScene(new Scene(root, 450, 750));
            stage.setTitle("E-Wallet Dashboard");
            stage.show();

        } else {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/login.fxml"));
            stage.setScene(new Scene(loader.load(), 450, 750));
            stage.setTitle("Login");
            stage.show();
        }
    }
    public static void main(String[] args) {
        launch(args);
    }
}