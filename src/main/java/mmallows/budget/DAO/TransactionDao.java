package mmallows.budget.DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import mmallows.budget.Models.Transaction;

public class TransactionDao extends BaseDao<Transaction> {

    @Override
    public String getTableName() {
        return new Transaction().getTableName();
    }

    public String getTableSql() {
        return "CREATE TABLE IF NOT EXISTS transaction (" +
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
    }

    @Override
    public Transaction mapRow(ResultSet rs) throws SQLException {
        Transaction transaction = new Transaction();
        transaction.setId(rs.getInt("id"));
        transaction.setName(rs.getString("name"));
        transaction.setValid(rs.getBoolean("valid"));
        transaction.setDate(rs.getString("date"));
        transaction.setAccount(rs.getInt("routing_number"), rs.getInt("account_number"));
        transaction.setAmount(rs.getDouble("amount"));
        transaction.setExpenseCategoryId(rs.getInt("expense_category_id"));
        return transaction;
    }

    @Override
    public HashMap<String, Object> mapObject(Transaction transaction) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("id", transaction.getId());
        map.put("name", transaction.getName());
        map.put("valid", transaction.isValid());
        map.put("date", transaction.getDate());
        map.put("apply_date", transaction.getApplyDate());
        map.put("routing_number", transaction.getRoutingNumber());
        map.put("account_number", transaction.getAccountNumber());
        map.put("amount", transaction.getAmount());
        map.put("expense_category_id", transaction.getExpenseCategoryId());
        return map;
    }
}
