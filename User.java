import java.util.ArrayList;
import java.util.List;

public class User {
    private String username;
    private String password;
    private double balance;
    private List<String> transactionHistory;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.balance = 0.0;
        this.transactionHistory = new ArrayList<>();
    }

    public String getUsername() {
        return username;
    }

    public boolean checkPassword(String password) {
        return this.password.equals(password);
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        this.balance += amount;
        transactionHistory.add("Deposited: $" + amount);
    }

    public boolean withdraw(double amount) {
        if (amount <= balance) {
            balance -= amount;
            transactionHistory.add("Withdrew: $" + amount);
            return true;
        }
        return false;
    }

    public boolean transfer(User recipient, double amount) {
        if (amount <= balance) {
            balance -= amount;
            recipient.deposit(amount);
            transactionHistory.add("Transferred: $" + amount + " to " + recipient.getUsername());
            return true;
        }
        return false;
    }

    public List<String> getTransactionHistory() {
        return transactionHistory;
    }
}
