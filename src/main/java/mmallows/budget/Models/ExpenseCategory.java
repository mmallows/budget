package mmallows.budget.Models;

public class ExpenseCategory {
    private int id;
    private String name;
    private int idExpenseBucket;

    public ExpenseCategory(String name, int idExpenseBucket) {
        this.name = name;
        this.idExpenseBucket = idExpenseBucket;
    }

    public ExpenseCategory(int id) {
        // Lookup expense category by ID
    }
}
