package mmallows.budget.Models;

import mmallows.budget.DAO.BaseDao;
import mmallows.budget.DAO.ExpenseBucketDao;

public class ExpenseBucket extends Entity {
    private int id;
    private String name;

    public ExpenseBucket() {
        super();
    }

    public ExpenseBucket(String name) {
        this.name = name;
    }

    public ExpenseBucket(int id) {
        // Lookup expense bucket by id
    }

    public String getTableName() {
        return "expense_bucket";
    }

    public BaseDao<?> getDao() {
        return new ExpenseBucketDao();
    }
}
