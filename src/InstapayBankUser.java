import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;
public class InstapayBankUser extends User {
    private String bankAccountNumber;
    private double balance;
    public InstapayBankUser(){
        this.balance = ThreadLocalRandom.current().nextDouble(1000, 5000);
    }
    public InstapayBankUser(String bankAccountNumber,double balance) {
        this.bankAccountNumber = bankAccountNumber;
        this.balance=balance;

    }

    public String getBankAccountNumber() {
        return bankAccountNumber;
    }

    public double getBalance() {
        return balance;
    }
    public void setBankAccountNumber(String bankaccountNumber){
        this.bankAccountNumber=bankaccountNumber;
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


}
