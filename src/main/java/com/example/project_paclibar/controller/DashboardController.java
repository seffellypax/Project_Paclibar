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
public class DashboardController {
    @FXML private Label balanceLabel;
    private Wallet currentWallet;
    private User currentUser;

    public void initData(User user, Wallet wallet) {
        this.currentUser = user;
        this.currentWallet = wallet;
        if(wallet != null) {
            balanceLabel.textProperty().bind(
                    wallet.balanceProperty()
                            .asString("PHP %.2f")
            );
        }
    }
    @FXML public void handleSendMoney() {
        navigateTo(
                "/transfer.fxml",
                "Transfer Money"
        );
    }
    @FXML public void handleDeposit() {
        navigateTo(
                "/deposit.fxml",
                "Deposit Money"
        );
    }

    @FXML public void handleAccount() {
        try {
            FXMLLoader loader =
                    new FXMLLoader(
                            getClass()
                                    .getResource("/userAccount.fxml")
                    );
            Parent root =
                    loader.load();
            UserAccountController controller =
                    loader.getController();
            controller.initData(
                    currentUser,
                    currentWallet
            );
            Stage stage =
                    (Stage) balanceLabel
                            .getScene()
                            .getWindow();
            stage.setScene(
                    new Scene(root,450,750)
            );
            stage.setTitle(
                    "My Account"
            );
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
    private void navigateTo(String fxmlPath, String title) {
        try {
            FXMLLoader loader =
                    new FXMLLoader(
                            getClass()
                                    .getResource(fxmlPath)
                    );

            Parent root =
                    loader.load();
            Object controller =
                    loader.getController();
            if(controller instanceof TransferController) {
                TransferController transferController =
                        (TransferController) controller;
                transferController.initData(
                        currentUser,
                        currentWallet
                );
            }
            else if(controller instanceof DepositController) {
                DepositController depositController =
                        (DepositController) controller;
                depositController.initData(
                        currentUser,
                        currentWallet
                );
            }
            Stage stage =
                    (Stage) balanceLabel
                            .getScene()
                            .getWindow();
            stage.setScene(
                    new Scene(root,450,750)
            );
            stage.setTitle(title);

        } catch(IOException e) {
            e.printStackTrace();
        }
    }
    @FXML public void handleViewHistory() {
        System.out.println(
                "Opening History..."
        );
    }


    public User getCurrentUser() {
        return currentUser;
    }
    public Wallet getCurrentWallet() {
        return currentWallet;
    }
}