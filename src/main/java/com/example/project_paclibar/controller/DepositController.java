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

public class DepositController {
    @FXML private TextField depositAmountField;
    @FXML private TextField referenceNumberField;
    @FXML private TextArea notesField;
    @FXML private ComboBox<String> depositMethodComboBox;
    @FXML private Label statusLabel;
    @FXML private Label balanceDisplayLabel;


    private Wallet currentWallet;
    private User currentUser;

    private WalletDAO walletDAO = new WalletDAO();
    private TransactionDAO transactionDAO = new TransactionDAO();

    public void initData(User user, Wallet wallet) {
        this.currentUser = user;
        this.currentWallet = wallet;

        balanceDisplayLabel.textProperty().bind(
                wallet.balanceProperty()
                        .asString("Available Balance: PHP %.2f")
        );
        depositMethodComboBox.getItems().clear();
        depositMethodComboBox.getItems().addAll(
                "Bank Transfer",
                "Debit Card",
                "Credit Card",
                "GCash",
                "Maya",
                "Cash Deposit"
        );
    }
    @FXML public void handleDeposit() {
        try {
            double amount =
                    Double.parseDouble(
                            depositAmountField.getText()
                    );
            if (depositMethodComboBox.getValue() == null) {
                statusLabel.setText(
                        "Please select a deposit method."
                );
                statusLabel.setStyle(
                        "-fx-text-fill: red;"
                );
                return;
            }
            if (amount <= 0) {

                statusLabel.setText(
                        "Enter a valid amount."
                );
                statusLabel.setStyle(
                        "-fx-text-fill: red;"
                );
                return;
            }
            currentWallet.addMoney(amount);

            walletDAO.updateBalance(
                    currentUser.getUserId(),
                    currentWallet.getBalance()
            );
            transactionDAO.addTransaction(
                    currentUser.getUserId(),
                    "DEPOSIT",
                    amount,
                    "Deposit via " + depositMethodComboBox.getValue()
            );

            statusLabel.setText(
                    "Deposit successful via "
                            + depositMethodComboBox.getValue()
                            + "!"
            );
            statusLabel.setStyle(
                    "-fx-text-fill: green;"
            );

            depositAmountField.clear();
            referenceNumberField.clear();
            notesField.clear();
            depositMethodComboBox.setValue(null);

        } catch (NumberFormatException e) {
            statusLabel.setText(
                    "Please enter a valid amount."
            );
            statusLabel.setStyle(
                    "-fx-text-fill: red;"
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
            Parent root = loader.load();
            DashboardController controller = loader.getController();

            controller.initData(currentUser, currentWallet);
            Stage stage =
                    (Stage) depositAmountField
                            .getScene()
                            .getWindow();
            stage.setScene(
                    new Scene(root,450,750)
            );
            stage.setTitle(
                    "E-Wallet Dashboard"
            );
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}