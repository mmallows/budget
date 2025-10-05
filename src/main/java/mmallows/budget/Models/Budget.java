package mmallows.budget.Models;

import mmallows.budget.DAO.BaseDao;
import mmallows.budget.DAO.BudgetDao;

public class Budget extends Entity {

    public Budget() {
        super();
    }

    public Budget(String name) {
        this.name = name;
    }

    public Budget(int id) {
        // Lookup the budget by the ID
    }

    protected BaseDao<?> getDao() {
        return new BudgetDao();
    }

    public String getTableName() {
        return "budget";
    }
}
