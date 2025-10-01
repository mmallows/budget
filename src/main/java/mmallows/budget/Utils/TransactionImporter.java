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
                String date = (String) row.get("date");
                int routingNumber = Integer.parseInt((String) row.get("routing_number"));
                int accountNumber = Integer.parseInt((String) row.get("account_number"));
                String name = (String) row.get("name");
                double amount = Double.parseDouble((String) row.get("amount"));

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
