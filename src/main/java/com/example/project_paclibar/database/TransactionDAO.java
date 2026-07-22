package com.example.project_paclibar.database;

import com.example.project_paclibar.model.Transaction;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TransactionDAO {

    public void addTransaction(
            int userId,
            String type,
            double amount,
            String description
    ) {
        String sql =
                "INSERT INTO transactions " +
                        "(user_id,type,amount,description) " +
                        "VALUES (?,?,?,?)";

        try {
            Connection con =
                    DatabaseConnection.getConnection();
            PreparedStatement ps =
                    con.prepareStatement(sql);
            ps.setInt(1, userId);
            ps.setString(2, type);
            ps.setDouble(3, amount);
            ps.setString(4, description);
            ps.executeUpdate();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    public List<Transaction> getTransactionsByUserId(int userId) {
        List<Transaction> transactions = new ArrayList<>();
        String sql = "SELECT * FROM transactions WHERE user_id = ?";

        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Transaction transaction = new Transaction(
                        rs.getInt("transaction_id"),
                        rs.getString("type"),
                        rs.getDouble("amount"),
                        rs.getString("description")
                );
                transactions.add(transaction);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return transactions;
    }
}