package pl.dc4b.cardirectory.helpers;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class DbHelper {
    private static final String URL = "jdbc:sqlite:car_directory.db";

    public static Connection getConnection() throws SQLException {
        setupDbDriver();
        return DriverManager.getConnection(URL);
    }

    public static void executeSqlScript(String scriptFileName) {
        try (Connection connection = DbHelper.getConnection()) {
            // Load the SQL script from the resources folder
            InputStream inputStream = DbHelper.class.getClassLoader().getResourceAsStream(scriptFileName);
            if (inputStream == null) {
                throw new IllegalArgumentException("Script file not found: " + scriptFileName);
            }

            // Execute each SQL statement
            try (Scanner scanner = new Scanner(new InputStreamReader(inputStream))) {
                scanner.useDelimiter(";");
                while (scanner.hasNext()) {
                    String sqlStatement = scanner.next().trim();
                    if (!sqlStatement.isEmpty()) {
                        try (Statement statement = connection.createStatement()) {
                            statement.execute(sqlStatement);
                        }
                    }
                }
            }

            System.out.println("SQL script executed successfully.");

        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception appropriately
        }
    }
    private static void setupDbDriver() {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}