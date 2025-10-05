package mmallows.budget.Models;

import mmallows.budget.DAO.BaseDao;
import mmallows.budget.DAO.ExpenseCategoryDao;

public class ExpenseCategory extends Entity {
    private int id;
    private String name;
    private int expense_bucket_id;

    public ExpenseCategory() {
        super();
    }

    public ExpenseCategory(String name, int expense_bucket_id) {
        this.name = name;
        this.expense_bucket_id = expense_bucket_id;
    }

    public ExpenseCategory(int id) {
        // Lookup expense category by ID
    }

    public String getTableName() {
        return "expense_category";
    }

    public BaseDao<?> getDao() {
        return new ExpenseCategoryDao();
    }

    public int getExpenseBucketId() {
        return this.expense_bucket_id;
    }

    public void setExpenseBucketId(int expense_bucket_id) {
        this.expense_bucket_id = expense_bucket_id;
    }
}
