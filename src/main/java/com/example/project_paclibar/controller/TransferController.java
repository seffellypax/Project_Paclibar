package com.example.project_paclibar.controller;

import com.example.project_paclibar.database.WalletDAO;
import com.example.project_paclibar.database.TransactionDAO;
import com.example.project_paclibar.model.User;
import com.example.project_paclibar.model.Wallet;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;


public class TransferController {
    @FXML private TextField recipientField;
    @FXML private TextField amountField;
    @FXML private Label statusLabel;
    @FXML private Label balanceDisplayLabel;
    private Wallet currentWallet;
    private User currentUser;

    private WalletDAO walletDAO = new WalletDAO();
    private TransactionDAO transactionDAO = new TransactionDAO();


    public void initData(
            User user,
            Wallet wallet
    ) {

        this.currentUser = user;
        this.currentWallet = wallet;

        balanceDisplayLabel.textProperty().bind(
                wallet.balanceProperty()
                        .asString(
                                "Available Balance: PHP %.2f"
                        )
        );
    }
    @FXML public void handleTransfer() {
        try {
            String recipient =
                    recipientField.getText();
            double amount =
                    Double.parseDouble(
                            amountField.getText()
                    );
            if(recipient.isEmpty()) {
                statusLabel.setText(
                        "Enter recipient name."
                );
                statusLabel.setStyle(
                        "-fx-text-fill:red;"
                );
                return;
            }
            if(amount <= 0) {
                statusLabel.setText(
                        "Enter valid amount."
                );
                statusLabel.setStyle(
                        "-fx-text-fill:red;"
                );
                return;
            }

            if(amount > currentWallet.getBalance()) {
                statusLabel.setText(
                        "Insufficient balance."
                );
                statusLabel.setStyle(
                        "-fx-text-fill:red;"
                );
                return;
            }

            currentWallet.withdrawMoney(amount);

            walletDAO.updateBalance(
                    currentUser.getUserId(),
                    currentWallet.getBalance()
            );
            transactionDAO.addTransaction(
                    currentUser.getUserId(),
                    "TRANSFER",
                    amount,
                    "Transfer to " + recipient
            );

            statusLabel.setText(
                    "Transfer successful to "
                            + recipient
                            + "!"
            );

            statusLabel.setStyle(
                    "-fx-text-fill:green;"
            );

            recipientField.clear();
            amountField.clear();
        }
        catch(NumberFormatException e) {
            statusLabel.setText(
                    "Enter a valid number."
            );
            statusLabel.setStyle(
                    "-fx-text-fill:red;"
            );
        }
    }
    @FXML public void handleBack() {
        try {
            FXMLLoader loader =
                    new FXMLLoader(
                            getClass()
                                    .getResource("/dashboard.fxml")
                    );
            Parent root =
                    loader.load();
            DashboardController controller =
                    loader.getController();

            controller.initData(
                    currentUser,
                    currentWallet
            );

            Stage stage =
                    (Stage) recipientField
                            .getScene()
                            .getWindow();

            stage.setScene(
                    new Scene(root,450,750)
            );
            stage.setTitle(
                    "E-Wallet Dashboard"
            );
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
}