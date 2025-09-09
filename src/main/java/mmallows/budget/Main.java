package mmallows.budget;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {
    public static void main(String[] args) {
        String url = "jdbc:sqlite:budget.db"; // This will create budget.db in your project folder

        try (Connection conn = DriverManager.getConnection(url)) {
            if (conn != null) {
                System.out.println("Connected to SQLite database!");

                // TODO: Write a more comprehensive database initialization routine
                // To iterate over all my classes and create all my tables

                // Create a sample table if it doesn't exist
                String sql = "CREATE TABLE IF NOT EXISTS transaction (" +
                        "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "date TEXT NOT NULL, " +
                        "description TEXT NOT NULL, " +
                        "amount REAL NOT NULL" +
                        ");";

                try (Statement stmt = conn.createStatement()) {
                    stmt.execute(sql);
                    System.out.println("Table 'transaction' is ready to use.");
                }
            }
        } catch (SQLException e) {
            System.out.println("Database connection failed.");
            e.printStackTrace();
        }
    }
}