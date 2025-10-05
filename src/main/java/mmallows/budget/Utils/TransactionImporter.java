package mmallows.budget.Utils;

import java.util.HashMap;
import java.util.List;

import mmallows.budget.DAO.TransactionDao;
import mmallows.budget.Models.Transaction;

public class TransactionImporter extends CsvImporter {

    public TransactionImporter(String filePath) {
        super(filePath);
    }

    public HashMap<String, Double> getTransactionTotalSummary() {
        HashMap<String, Double> summary = new HashMap<>();
        for (HashMap<String, Object> row : this.data) {
            try {
                String debitStr = (String) row.getOrDefault("Debit", "0.0");
                String creditStr = (String) row.getOrDefault("Credit", "0.0");
                double debit = Double.parseDouble(debitStr == "" ? "0.0" : debitStr);
                double credit = Double.parseDouble(creditStr == "" ? "0.0" : creditStr);
                double total = credit - debit;
                summary.put("Debit Total", summary.getOrDefault("Debit Total", 0.0) + debit);
                summary.put("Credit Total", summary.getOrDefault("Credit Total", 0.0) + credit);
                summary.put("Net Total", summary.getOrDefault("Net Total", 0.0) + total);
            } catch (Exception e) {
                System.err.println("Error processing row for summary: " + row);
                e.printStackTrace();
            }
        }
        return summary;
    }

    public void importTransactions() {
        for (HashMap<String, Object> row : this.data) {
            try {
                String date = (String) row.get("Date");
                int routingNumber = Integer.parseInt((String) row.getOrDefault("Bank RTN", ""));
                int accountNumber = Integer.parseInt((String) row.get("Account Number"));
                String name = (String) row.get("Description");
                double debit = Double.parseDouble((String) row.getOrDefault("Debit", "0.0"));
                double credit = Double.parseDouble((String) row.getOrDefault("Credit", "0.0"));
                double amount = credit - debit;

                HashMap<String, Object> conditions = new HashMap<>();
                conditions.put("date", date);
                conditions.put("account_number", accountNumber);
                conditions.put("amount", amount);
                conditions.put("name", name);
                TransactionDao transactionDao = new TransactionDao();
                List<Transaction> matches = transactionDao.findByColumns(conditions, false);

                if (!matches.isEmpty()) {
                    System.out.println("Transaction already exists, skipping: " + row);
                    continue;
                }

                Transaction transaction = new Transaction(date, routingNumber, accountNumber, name, amount);
                transaction.save();
            } catch (Exception e) {
                System.err.println("Error processing row: " + row);
                e.printStackTrace();
            }
        }
    }
}
