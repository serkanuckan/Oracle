package com.mycompany.oracleveri;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Random;

public class OracleVeri {

    public static void main(String[] args) {
        String jdbcURL = "jdbc:oracle:thin:@localhost:1521/xe";
        String username = "sys as sysdba";
        String password = "your_password";

        try (Connection connection = DriverManager.getConnection(jdbcURL, username, password)) {
            if (connection != null) {
                System.out.println("Connected to the database");

                // Random kayıtları eklemek için döngü
                for (int i = 0; i < 100; i++) {
                    insertRandomBook(connection);
                }

                System.out.println("Random records inserted successfully!");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void insertRandomBook(Connection connection) throws SQLException {
        String insertSQL = "INSERT INTO BOOK (ID, NAME, ISBN) VALUES (BOOK_SEQ.NEXTVAL, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(insertSQL)) {
            // Random ID, NAME, ISBN oluşturma
            Random random = new Random();
            String name = "Book " + random.nextInt(1000);
            String isbn = "ISBN" + random.nextInt(1000);

            // Parametreleri set etme
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, isbn);

            // SQL sorgusunu çalıştırma
            preparedStatement.executeUpdate();
        }
    }
}
