import java.util.HashMap;
import java.util.Map;

// InstapaySystem class
public class InstapaySystem {
    private Map<String, User> userDatabase;
    private OTPServiceProvider otpService;
    private BankAccountServiceProvider bankAccountService;

    // Constructor for InstapaySystem class
    public InstapaySystem(OTPServiceProvider otpService, BankAccountServiceProvider bankAccountService) {
        this.userDatabase = new HashMap<>();
        this.otpService = otpService;
        this.bankAccountService = bankAccountService;
    }

    // Registration method
    public boolean registerUser(String username, String password, String mobileNumber, boolean isBankUser, String bankAccountNumber, String walletProvider) {
        if (userDatabase.containsKey(username)) {
            // User already exists with this username
            return false;
        }
        User newUser = new User(username, password, mobileNumber, isBankUser, bankAccountNumber, walletProvider);
        userDatabase.put(username, newUser);
        return true; // Registration successful
    }

    // Login method
    public User login(String username, String password) {
        User user = userDatabase.get(username);
        if (user != null && user.getPassword().equals(password)) {
            return user; // Login successful
        }
        return null; // Login failed
    }


    public boolean requestOTP(String mobileNumber) {
        // In a real system, you'd check if the mobile number is valid here
        // and return false if it's not
        int otp = otpService.generateOTP(mobileNumber);
        return true; // OTP request successful
    }
    public boolean transferFunds(String fromUsername, String toUsername, double amount) {
        User fromUser = userDatabase.get(fromUsername);
        User toUser = userDatabase.get(toUsername);

        if (fromUser != null && toUser != null && fromUser.withdraw(amount)) {
            toUser.deposit(amount);
            return true; // Transfer successful
        }
        return false; // Transfer failed
    }


    public boolean transferToWallet(String fromUsername, String toMobileNumber, double amount) {
        User fromUser = userDatabase.get(fromUsername);
        // Assuming mobile number uniquely identifies a wallet user, not implemented here
        // Transfer logic would be similar to account transfer but may involve different rules or APIs

        if (fromUser != null && fromUser.withdraw(amount)) {
            // In a real system, you'd integrate with a mobile wallet API here
            System.out.printf("Transferred $%.2f from %s to wallet %s%n", amount, fromUsername, toMobileNumber);
            return true;
        }
        return false;
    }

    // Method overloading for transferring to another Instapay account using username
    public boolean transferToInstapayAccount(String fromUsername, String toUsername, double amount) {
        User fromUser = userDatabase.get(fromUsername);
        User toUser = userDatabase.get(toUsername);
        // Check if the recipient exists and the sender has enough balance
        if (toUser != null && fromUser != null && fromUser.withdraw(amount)) {
            toUser.deposit(amount);
            return true; // Transfer successful
        }
        return false; // Transfer failed
    }

    // User balance check
    public void printUserBalance(String username) {
        User user = userDatabase.get(username);
        if (user != null) {
            System.out.printf("%s's current balance: %.2f%n", username, user.getBalance());
        } else {
            System.out.println("User not found.");
        }
    }

        //Bill payment
        public boolean processBillPayment(String username, Bill bill) {
            User user = userDatabase.get(username);
            if (user != null && user.payBill(bill.getAmount())) {
                System.out.println(username + " paid " + bill.getBillType() + " bill of " + bill.getAmount());
                return true;
            }
            return false;
        }

    public boolean isUsernameTaken(String username) {
        return userDatabase.containsKey(username);
    }
}
