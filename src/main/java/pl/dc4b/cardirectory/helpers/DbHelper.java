package pl.dc4b.cardirectory.helpers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbHelper {
    private static final String URL = "jdbc:sqlite:car_directory.db";

    public static Connection getConnection() throws SQLException {
        setupDbDriver();
        return DriverManager.getConnection(URL);
    }
    private static void setupDbDriver() {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}