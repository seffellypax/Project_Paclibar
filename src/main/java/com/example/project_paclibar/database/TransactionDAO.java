package com.example.project_paclibar.database;

import java.sql.Connection;
import java.sql.PreparedStatement;

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
}