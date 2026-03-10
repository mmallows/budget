package mmallows.budget.Models;

import mmallows.budget.DAO.BaseDao;
import mmallows.budget.DAO.ExpenseCategoryDao;

@Entity
public class ExpenseCategory extends Entity {
    private Long expense_bucket_id;

    public ExpenseCategory() {
        super();
    }

    public ExpenseCategory(String name, Long expense_bucket_id) {
        this.name = name;
        this.expense_bucket_id = expense_bucket_id;
    }

    public ExpenseCategory(Long id) {
        // Lookup expense category by ID
    }

    public String getTableName() {
        return "expense_category";
    }

    public BaseDao<?> getDao() {
        return new ExpenseCategoryDao();
    }

    public Long getExpenseBucketId() {
        return this.expense_bucket_id;
    }

    public void setExpenseBucketId(Long expense_bucket_id) {
        this.expense_bucket_id = expense_bucket_id;
    }
}
