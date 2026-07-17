package com.example.project_paclibar.database;

import com.example.project_paclibar.model.Wallet;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class WalletDAO {
    public Wallet getWalletByUserId(int userId) {
        Wallet wallet = new Wallet();
        String sql =
                "SELECT balance FROM wallet WHERE user_id=?";

        try {
            Connection con =
                    DatabaseConnection.getConnection();
            PreparedStatement ps =
                    con.prepareStatement(sql);
            ps.setInt(1, userId);
            ResultSet rs =
                    ps.executeQuery();

            if(rs.next()) {
                wallet.addMoney(
                        rs.getDouble("balance")
                );
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
        return wallet;
    }

    public void updateBalance(int userId, double newBalance) {
        String sql =
                "UPDATE wallet SET balance=? WHERE user_id=?";
        try {
            Connection con =
                    DatabaseConnection.getConnection();
            PreparedStatement ps =
                    con.prepareStatement(sql);
            ps.setDouble(1, newBalance);
            ps.setInt(2, userId);
            ps.executeUpdate();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void createWallet(int userId) {
        String sql =
                "INSERT INTO wallet(user_id, balance) VALUES(?, ?)";
        try {
            Connection con =
                    DatabaseConnection.getConnection();
            PreparedStatement ps =
                    con.prepareStatement(sql);
            ps.setInt(1, userId);
            ps.setDouble(2, 0.00);
            ps.executeUpdate();

        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}