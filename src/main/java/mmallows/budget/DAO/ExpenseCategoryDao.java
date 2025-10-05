package mmallows.budget.DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import mmallows.budget.Models.ExpenseCategory;

public class ExpenseCategoryDao extends BaseDao<ExpenseCategory> {
    @Override
    public String getTableName() {
        return new ExpenseCategory().getTableName();
    }

    public String getTableSql() {
        return "CREATE TABLE IF NOT EXISTS " + this.getTableName() + " (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "name TEXT NOT NULL, " +
                "valid BOOLEAN NOT NULL DEFAULT 1, " +
                "expense_budget_id INTEGER, " +
                ");";
    }

    @Override
    public ExpenseCategory mapRow(ResultSet rs) throws SQLException {
        ExpenseCategory expenseCategory = new ExpenseCategory();
        expenseCategory.setId(rs.getInt("id"));
        expenseCategory.setName(rs.getString("name"));
        expenseCategory.setValid(rs.getBoolean("valid"));
        expenseCategory.setExpenseBucketId(rs.getInt("expense_bucket_id"));
        return expenseCategory;
    }

    @Override
    public HashMap<String, Object> mapObject(ExpenseCategory expenseCategory) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("id", expenseCategory.getId());
        map.put("name", expenseCategory.getName());
        map.put("valid", expenseCategory.isValid());
        map.put("expense_bucket_id", expenseCategory.getExpenseBucketId());
        return map;
    }
}
