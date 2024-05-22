package myLibrary;

import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexiune {
    private static final String DATABASE_URL = "jdbc:h2:mem:poo-db;DB_CLOSE_DELAY=-1";
    private static final String DATABASE_USER = "root";
    private static final String DATABASE_PASSWORD = "password";

    public static java.sql.Connection getConnection() {
        java.sql.Connection connection;
        try {
            connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USER, DATABASE_PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return connection;
    }
}
