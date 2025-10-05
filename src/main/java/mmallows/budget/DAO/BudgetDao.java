package mmallows.budget.DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import mmallows.budget.Models.Budget;

public class BudgetDao extends BaseDao<Budget> {

    @Override
    public String getTableName() {
        return new Budget().getTableName();
    }

    public String getTableSql() {
        return "CREATE TABLE IF NOT EXISTS budget (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "name TEXT NOT NULL, " +
                "valid BOOLEAN NOT NULL DEFAULT 1, " +
                ");";
    }

    @Override
    public Budget mapRow(ResultSet rs) throws SQLException {
        Budget budget = new Budget();
        budget.setId(rs.getInt("id"));
        budget.setName(rs.getString("name"));
        return budget;
    }

    @Override
    public HashMap<String, Object> mapObject(Budget budget) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("id", budget.getId());
        map.put("name", budget.getName());
        return map;
    }
}
