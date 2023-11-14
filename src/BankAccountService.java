public class BankAccountService implements BankAccountServiceProvider {
    public boolean verifyBankAccount(String bankAccountNumber) {
        if (bankAccountNumber != null) {
            System.out.println("Bank account number " + bankAccountNumber + " verified successfully!");
            return true;
        } else {
            System.out.println("Invalid bank account number: " + bankAccountNumber);
            return false;
        }
    }
}
