package com.mycompany.oracleveri;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RetrieveData {

    public static void main(String[] args) {
        // Bağlantı bilgileri
        String jdbcURL = "jdbc:oracle:thin:@localhost:1521/xe";
        String username = "sys as sysdba";
        String password = "your_password";

        try {
            // JDBC sürücüsünü yükle
            Class.forName("oracle.jdbc.driver.OracleDriver");

            // Veritabanına bağlan
            try (Connection connection = DriverManager.getConnection(jdbcURL, username, password)) {
                if (connection != null) {
                    System.out.println("Connected to the database");

                    // Veritabanından kayıtları çekme
                    retrieveBooks(connection);
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    }

    private static void retrieveBooks(Connection connection) throws SQLException {
        String selectSQL = "SELECT * FROM BOOK";
        try (PreparedStatement preparedStatement = connection.prepareStatement(selectSQL);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            // Sonuçları ekrana yazma
            System.out.println("ID\t\tNAME\t\tISBN");
            while (resultSet.next()) {
                int id = resultSet.getInt("ID");
                String name = resultSet.getString("NAME");
                String isbn = resultSet.getString("ISBN");

                System.out.println(id + "\t\t" + name + "\t\t" + isbn);
            }
        }
    }
}
