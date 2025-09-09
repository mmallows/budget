package mmallows.budget.Models;

public class ExpenseBucket {
    private int id;
    private String name;

    public ExpenseBucket(String name) {
        this.name = name;
    }

    public ExpenseBucket(int id) {
        // Lookup expense bucket by id
    }
}
