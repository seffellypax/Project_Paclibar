package com.example.project_paclibar.controller;

import com.example.project_paclibar.database.WalletDAO;
import com.example.project_paclibar.database.TransactionDAO;
import com.example.project_paclibar.model.User;
import com.example.project_paclibar.model.Wallet;
import com.example.project_paclibar.service.FeeCalculationStrategy;
import com.example.project_paclibar.service.StandardFeeStrategy;
import com.example.project_paclibar.service.TransactionContext;
import com.example.project_paclibar.service.NotificationService;
import com.example.project_paclibar.service.SmsAdapter;
import com.example.project_paclibar.service.ThirdPartySmsSender;

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

    public void initData(User user, Wallet wallet) {
        this.currentUser = user;
        this.currentWallet = wallet;

        balanceDisplayLabel.textProperty().bind(
                wallet.balanceProperty()
                        .asString("Available Balance: PHP %.2f")
        );
    }
    @FXML public void handleTransfer() {
        try {
            String recipientUsername = recipientField.getText().trim();
            double amount = Double.parseDouble(amountField.getText());

            if(recipientUsername.isEmpty()) {
                statusLabel.setText("Enter recipient name.");
                statusLabel.setStyle("-fx-text-fill:red;");
                return;
            }
            if(amount <= 0) {
                statusLabel.setText("Enter valid amount.");
                statusLabel.setStyle("-fx-text-fill:red;");
                return;
            }
            if(recipientUsername.equals(currentUser.getUsername())) {
                statusLabel.setText("You cannot transfer to yourself.");
                statusLabel.setStyle("-fx-text-fill:red;");
                return;
            }
            com.example.project_paclibar.database.UserDAO userDAO = new com.example.project_paclibar.database.UserDAO();
            User recipientUser = userDAO.getUserByUsername(recipientUsername); // Ensure this method exists in your UserDAO

            if(recipientUser == null) {
                statusLabel.setText("Recipient user not found.");
                statusLabel.setStyle("-fx-text-fill:red;");
                return;
            }
            FeeCalculationStrategy feeStrategy = new StandardFeeStrategy();
            TransactionContext feeContext = new TransactionContext(feeStrategy);
            double fee = feeContext.executeFeeCalculation(amount);
            double totalDeduction = amount + fee;

            if(totalDeduction > currentWallet.getBalance()) {
                statusLabel.setText("Insufficient balance (Includes PHP " + fee + " fee).");
                statusLabel.setStyle("-fx-text-fill:red;");
                return;
            }
            currentWallet.withdrawMoney(totalDeduction);
            walletDAO.updateBalance(currentUser.getUserId(), currentWallet.getBalance());

            transactionDAO.addTransaction(
                    currentUser.getUserId(),
                    "TRANSFER",
                    amount,
                    "Transfer to " + recipientUsername + " (Fee: PHP " + fee + ")"
            );
            double recipientCurrentBalance = walletDAO.getBalanceByUserId(recipientUser.getUserId());
            double newRecipientBalance = recipientCurrentBalance + amount;

            walletDAO.updateBalance(recipientUser.getUserId(), newRecipientBalance);

            transactionDAO.addTransaction(
                    recipientUser.getUserId(),
                    "RECEIVED",
                    amount,
                    "Transfer from " + currentUser.getUsername()
            );

            String notificationMessage = "Successfully transferred PHP " + amount + " to " + recipientUsername + ". Fee: PHP " + fee;

            ThirdPartySmsSender smsSender = new ThirdPartySmsSender();
            NotificationService notificationService = new SmsAdapter(smsSender);
            notificationService.sendNotification("09123456789", notificationMessage);

            showAlert("Transfer Successful", "Notification Alert", notificationMessage);

            statusLabel.setText("Transfer successful to " + recipientUsername + "! (Fee: PHP " + fee + ")");
            statusLabel.setStyle("-fx-text-fill:green;");

            recipientField.clear();
            amountField.clear();
        }
        catch(NumberFormatException e) {
            statusLabel.setText("Enter a valid number.");
            statusLabel.setStyle("-fx-text-fill:red;");
        }
    }
    private void showAlert(String title, String header, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);

        DialogPane dialogPane = alert.getDialogPane();
        dialogPane.getStylesheets().add(
                getClass().getResource("/login.css").toExternalForm()
        );
        dialogPane.getStyleClass().add("custom-alert");
        alert.showAndWait();
    }
    @FXML public void handleBack() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/dashboard.fxml"));
            Parent root = loader.load();
            DashboardController controller = loader.getController();

            controller.initData(currentUser, currentWallet);

            Stage stage = (Stage) recipientField.getScene().getWindow();
            stage.setScene(new Scene(root, 450, 750));
            stage.setTitle("E-Wallet Dashboard");
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
}