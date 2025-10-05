package mmallows.budget.DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import mmallows.budget.Models.ExpenseBucket;
import mmallows.budget.Models.ExpenseCategory;

public class ExpenseBucketDao extends BaseDao {
    @Override
    public String getTableName() {
        return new ExpenseCategory().getTableName();
    }

    public String getTableSql() {
        return "CREATE TABLE IF NOT EXISTS " + this.getTableName() + " (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "name TEXT NOT NULL, " +
                "valid BOOLEAN NOT NULL DEFAULT 1, " +
                ");";
    }

    @Override
    public ExpenseBucket mapRow(ResultSet rs) throws SQLException {
        ExpenseBucket expenseBucket = new ExpenseBucket();
        expenseBucket.setId(rs.getInt("id"));
        expenseBucket.setName(rs.getString("name"));
        expenseBucket.setValid(rs.getBoolean("valid"));
        return expenseBucket;
    }

    @Override
    public HashMap<String, Object> mapObject(ExpenseBucket expenseBucket) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("id", expenseBucket.getId());
        map.put("name", expenseBucket.getName());
        map.put("valid", expenseBucket.isValid());
        return map;
    }
}
