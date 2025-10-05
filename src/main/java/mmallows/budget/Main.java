package mmallows.budget;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.Scanner;
import java.util.HashMap;

import mmallows.budget.Utils.TransactionImporter;

public class Main {
    public static void main(String[] args) {

        System.out.println("Welcome to the mmallows Budget App!");
        System.out.println("Please input a file path to import CSV data:");
        String filePath = "C:/Users/Marcus/Downloads/transactions (3).csv";
        // try (Scanner input = new Scanner(System.in)) {
        // filePath = input.nextLine();
        // }

        TransactionImporter importer = new TransactionImporter(filePath);
        HashMap<String, Double> summary = importer.getTransactionTotalSummary();

        System.out.println("Transaction Summary:");
        System.out.println("--------------------");
        for (String category : summary.keySet()) {
            System.out.printf("%-20s : $%.2f%n", category, summary.get(category));
        }

        // String url = "jdbc:sqlite:budget.db"; // This will create budget.db in your
        // project folder

        // try (Connection conn = DriverManager.getConnection(url)) {
        // if (conn != null) {
        // System.out.println("Connected to SQLite database!");

        // // TODO: Write a more comprehensive database initialization routine
        // // To iterate over all my classes and create all my tables

        // try (Statement stmt = conn.createStatement()) {
        // stmt.execute(sql);
        // System.out.println("Table 'transaction' is ready to use.");
        // }
        // }
        // } catch (SQLException e) {
        // System.out.println("Database connection failed.");
        // e.printStackTrace();
        // }
    }
}