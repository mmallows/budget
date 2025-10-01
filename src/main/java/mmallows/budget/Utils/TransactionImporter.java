package mmallows.budget.Utils;

import java.util.HashMap;
import java.util.List;

import mmallows.budget.DAO.TransactionDao;
import mmallows.budget.Models.Transaction;

public class TransactionImporter extends CsvImporter {

    public TransactionImporter(String filePath) {
        super(filePath);
    }

    public void importTransactions() {
        for (HashMap<String, Object> row : this.data) {
            try {
                String date = (String) row.get("Date");
                int routingNumber = Integer.parseInt((String) row.get("Bank RTN"));
                int accountNumber = Integer.parseInt((String) row.get("Account Number"));
                String name = (String) row.get("Description");
                double debit = Double.parseDouble((String) row.get("Debit"));
                double credit = Double.parseDouble((String) row.get("Credit"));
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
