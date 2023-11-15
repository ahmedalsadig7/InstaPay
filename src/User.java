import java.util.Scanner;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
public class User implements Registration,Login {
    public static InstapaySystem instapaySystem=new InstapaySystem();
    public static InstapayBankUser instapayBankUser=new InstapayBankUser();
    public static InstapayWalletUser instapayWalletUser=new InstapayWalletUser();
    public static bankAPI bankAPI=new bankAPI();
    public static walletAPI walletAPI=new walletAPI();
    public static OTPService otpService=new OTPService();
    private String username;
    private String password; // Store hashed passwords in a real application
    private String mobileNumber;
    private boolean isBankUser;
    public User(){

    }
    public User(String username, String password, String mobileNumber, boolean isBankUser) {
        this.username = username;
        this.password = password;
        this.mobileNumber = mobileNumber;
        this.isBankUser = isBankUser;
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

    //User Registration
    public void registerUser(Scanner scanner){
        System.out.print("Enter username: ");
        String username = scanner.nextLine();

        if (instapaySystem.isUsernameTaken(username)) {

            System.out.println("Username is already taken. Please try again.");
            return;
        }
        System.out.println("*Password must be at least 8 characters long and include uppercase, lowercase, digits, and special characters.*");


        System.out.println("Enter password: ");
        String password = scanner.nextLine();

        if (!isPasswordComplex(password)) {
            System.out.println("Password is not complex enough. It must be at least 8 characters long and include uppercase, lowercase, digits, and special characters.");
            return;
        }

        System.out.print("Enter mobile number: ");
        String mobileNumber = scanner.nextLine();

        System.out.print("Will you be registering with a bank account? (yes/no): ");
        boolean isBankUser = "yes".equalsIgnoreCase(scanner.nextLine());
        if (isBankUser){
                System.out.print("Enter bank account number: ");
                String bankAccountNumber = scanner.nextLine();
                if (!bankAPI.verifyBankAccount(bankAccountNumber)) {
                    System.out.println("Bank account verification failed. Please check the number and try again.");
                    return;
                }
                instapayBankUser.setBankAccountNumber(bankAccountNumber);

        }
        else {
            System.out.print("Enter wallet provider name: ");
            String walletProvider = scanner.nextLine();
            if (!walletAPI.verifyWalletAccount(mobileNumber,walletProvider)) {
                System.out.println("Wallet account verification failed. Please check the number and try again.");
                return;
            }
            instapayWalletUser.setWalletProvider(walletProvider);
        }
        int otp = otpService.generateOTP(mobileNumber);
        System.out.print("Enter the OTP sent to your mobile number: ");
        int enteredOtp = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        if (otpService.verifyOTP(mobileNumber, enteredOtp)) {
            boolean registered = instapaySystem.registerUser(username, password, mobileNumber, isBankUser);
            if (registered) {
                System.out.println("Registration successful!");
            } else {
                System.out.println("Registration failed. Username may already be taken.");
            }
        } else {
            System.out.println("Incorrect OTP. Registration failed.");
        }


    }
    public boolean isPasswordComplex(String password){
        if (password.length() < 8) {
            return false; // Password length should be at least 8 characters
        }
        boolean hasUpper = false, hasLower = false, hasDigit = false, hasSpecial = false;
        for (char c : password.toCharArray()) {
            if (Character.isUpperCase(c)) hasUpper = true;
            else if (Character.isLowerCase(c)) hasLower = true;
            else if (Character.isDigit(c)) hasDigit = true;
            else hasSpecial = true;
        }
        return hasUpper && hasLower && hasDigit && hasSpecial;
    }
    public void loginUser(Scanner scanner) {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        System.out.print("your account type bank account(b)/ wallet(w)? : ");
        boolean isBankUser = "b".equalsIgnoreCase(scanner.nextLine());

        User user = instapaySystem.login(username, password,isBankUser);
        if (user != null) {
            System.out.println("Login successful!");
            BankuserActions(user, scanner);

        } else {
            System.out.println(" failed");
        }
    }
// need to be edited (how to difference between bank account and wallet account)
    private static void BankuserActions(User user, Scanner scanner) {
        boolean isRunning = true;
        while (isRunning) {
            System.out.println("\nUser: " + user.getUsername());
            System.out.println("1. Show Balance");
            System.out.println("2. Transfer Funds");
            System.out.println("3. Pay Bills");
            System.out.println("4. Logout");
            System.out.print("Choose an action: ");

            int action = scanner.nextInt();
            scanner.nextLine();

            switch (action) {
                case 1:
                    break;

                    user.displayBalance(); // this must be a balance to specific account
                    break;
                case 2:
                    performTransfer(scanner, user);
                    break;
                case 3:
                    payBills(scanner, user);
                    break;
                case 4:
                    isRunning = false;
                    System.out.println("You have been logged out.");
                    break;
                default:
                    System.out.println("Invalid action selected. Please try again.");
            }

            }
        }
    }









   /* public String getBankAccountNumber() {
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
    */
}
