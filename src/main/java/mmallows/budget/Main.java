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
                        "name TEXT NOT NULL, " +
                        "valid BOOLEAN NOT NULL DEFAULT 1, " +
                        "date TEXT NOT NULL, " +
                        "apply_date TEXT, " +
                        "routing_number INTEGER, " +
                        "account_number INTEGER NOT NULL, " +
                        "display_name TEXT, " +
                        "amount REAL NOT NULL, " +
                        "expense_category_id INTEGER, " +
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