package mmallows.budget.Models;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import mmallows.budget.DAO.BaseDao;
import mmallows.budget.DAO.TransactionDao;

public class Transaction extends Entity {
    private LocalDate date;
    private LocalDate applyDate;
    private int routingNumber;
    private int accountNumber;
    // private int idAccount; TODO: Add an account model
    private String displayName;
    private double amount;
    private Integer expenseCategory_id;

    public Transaction() {
        super();
    }

    public Transaction(String dateInput, int routingNum, int acctNum, String nameInput, double amountInput) {
        this.setDate(dateInput);
        this.setAccount(routingNum, acctNum);
        this.setName(nameInput);
        this.setDisplayName(nameInput);
        // TODO: Look up transaction by date, account, name and pull existing data
        this.setAmount(amountInput);
        this.setExpenseCategory();
    }

    public Transaction(int id) {
        // Lookup transaction by id and return it
        super();
    }

    public String getTableName() {
        return "transaction";
    }

    public BaseDao<?> getDao() {
        return new TransactionDao();
    }

    public void setDate(String dateInput) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        this.date = LocalDate.parse(dateInput, formatter);
        this.setApplyDate(dateInput);
    }

    public LocalDate getDate() {
        return this.date;
    }

    public void setApplyDate(String dateInput) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        this.applyDate = LocalDate.parse(dateInput, formatter);
    }

    public LocalDate getApplyDate() {
        return this.applyDate;
    }

    public void setAccount(int routingNum, int accountNum) {
        this.routingNumber = routingNum;
        this.accountNumber = accountNum;
        // TODO: Search up account by account number and set the id
    }

    public int getRoutingNumber() {
        return this.routingNumber;
    }

    public int getAccountNumber() {
        return this.accountNumber;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return this.displayName;
    }

    public void setAmount(double amountInput) {
        this.amount = amountInput;
    }

    public double getAmount() {
        return this.amount;
    }

    public void setExpenseCategoryId(int categoryId) {
        this.expenseCategory_id = categoryId;
        // TODO: Look up the category based on past transactions
    }

    public void setExpenseCategory() {
        this.expenseCategory_id = -1;
        // TODO Lookup category based on past transactions
    }

    public Integer getExpenseCategoryId() {
        return this.expenseCategory_id;
    }
}
