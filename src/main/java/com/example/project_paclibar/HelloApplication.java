package com.example.project_paclibar;

import com.example.project_paclibar.model.User;
import com.example.project_paclibar.model.Wallet;
import com.example.project_paclibar.database.WalletDAO;
import com.example.project_paclibar.service.*;
import com.example.project_paclibar.util.SessionManager;
import com.example.project_paclibar.controller.DashboardController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class HelloApplication extends Application {

    @Override
    public void start(Stage stage) throws Exception {

        System.out.println("========================================");
        System.out.println("TESTING ADAPTER PATTERN STARTING...");
        ThirdPartySmsSender thirdPartySms = new ThirdPartySmsSender();
        NotificationService notificationService = new SmsAdapter(thirdPartySms);
        notificationService.sendNotification("09123456789", "Adapter Test Successful!");
        System.out.println("TESTING ADAPTER PATTERN FINISHED!");
        System.out.println("========================================");

        double transferAmount = 1000.00;

        TransactionContext context = new TransactionContext(new StandardFeeStrategy());
        double standardFee = context.executeFeeCalculation(transferAmount);
        System.out.println("Standard Transfer Fee for ₱" + transferAmount + ": ₱" + standardFee);

        context.setStrategy(new VipFeeStrategy());
        double vipFee = context.executeFeeCalculation(transferAmount);
        System.out.println("VIP Transfer Fee for ₱" + transferAmount + ": ₱" + vipFee);

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