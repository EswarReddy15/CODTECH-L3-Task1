import java.util.Scanner;

public class BankingSystem {
    private static Bank bank = new Bank();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("Welcome to the Online Banking System");
            System.out.println("1. Create Account");
            System.out.println("2. Login");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    createAccount();
                    break;
                case 2:
                    login();
                    break;
                case 3:
                    System.out.println("Thank you for using the Online Banking System.");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void createAccount() {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        if (bank.createUser(username, password)) {
            System.out.println("Account created successfully.");
        } else {
            System.out.println("Username already exists.");
        }
    }

    private static void login() {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        User user = bank.authenticateUser(username, password);
        if (user != null) {
            System.out.println("Login successful.");
            userMenu(user);
        } else {
            System.out.println("Invalid username or password.");
        }
    }

    private static void userMenu(User user) {
        while (true) {
            System.out.println("\nUser Menu");
            System.out.println("1. View Balance");
            System.out.println("2. Deposit Funds");
            System.out.println("3. Withdraw Funds");
            System.out.println("4. Transfer Funds");
            System.out.println("5. View Transaction History");
            System.out.println("6. Logout");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.println("Current Balance: $" + user.getBalance());
                    break;
                case 2:
                    depositFunds(user);
                    break;
                case 3:
                    withdrawFunds(user);
                    break;
                case 4:
                    transferFunds(user);
                    break;
                case 5:
                    viewTransactionHistory(user);
                    break;
                case 6:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void depositFunds(User user) {
        System.out.print("Enter amount to deposit: ");
        double amount = scanner.nextDouble();
        scanner.nextLine(); // Consume newline
        user.deposit(amount);
        System.out.println("Deposited $" + amount);
    }

    private static void withdrawFunds(User user) {
        System.out.print("Enter amount to withdraw: ");
        double amount = scanner.nextDouble();
        scanner.nextLine(); // Consume newline
        if (user.withdraw(amount)) {
            System.out.println("Withdrew $" + amount);
        } else {
            System.out.println("Insufficient balance.");
        }
    }

    private static void transferFunds(User user) {
        System.out.print("Enter recipient username: ");
        String recipientUsername = scanner.nextLine();
        User recipient = bank.getUser(recipientUsername);

        if (recipient == null) {
            System.out.println("Recipient not found.");
            return;
        }

        System.out.print("Enter amount to transfer: ");
        double amount = scanner.nextDouble();
        scanner.nextLine(); // Consume newline

        if (user.transfer(recipient, amount)) {
            System.out.println("Transferred $" + amount + " to " + recipientUsername);
        } else {
            System.out.println("Insufficient balance.");
        }
    }

    private static void viewTransactionHistory(User user) {
        System.out.println("Transaction History:");
        for (String transaction : user.getTransactionHistory()) {
            System.out.println(transaction);
        }
    }
}
