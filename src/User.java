import java.util.concurrent.ThreadLocalRandom;

public class User {
    private String username;
    private String password; // Store hashed passwords in a real application
    private String mobileNumber;
    private boolean isBankUser;
    private String bankAccountNumber; // Nullable for bank users
    private String walletProvider; // Nullable for wallet users
    private double balance;

    public User(String username, String password, String mobileNumber, boolean isBankUser, String bankAccountNumber, String walletProvider) {
        this.username = username;
        this.password = password;
        this.mobileNumber = mobileNumber;
        this.isBankUser = isBankUser;
        this.bankAccountNumber = bankAccountNumber;
        this.walletProvider = walletProvider;
        this.balance = ThreadLocalRandom.current().nextDouble(1000, 5000); // Assign a random balance
    }



    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public boolean isBankUser() {
        return isBankUser;
    }

    public String getBankAccountNumber() {
        return bankAccountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
        }
    }

    public boolean withdraw(double amount) {
        if (amount <= balance && amount > 0) {
            balance -= amount;
            return true;
        }
        return false;
    }

    // Utility method to display user's balance
    public void displayBalance() {
        System.out.printf("The balance for %s is: %.2f\n", getUsername(), getBalance());
    }

    // Method to pay a bill
    public boolean payBill(double billAmount) {
        if (balance >= billAmount) {
            balance -= billAmount; // Deduct the bill amount from the balance
            return true;
        }
        return false;
    }
}
