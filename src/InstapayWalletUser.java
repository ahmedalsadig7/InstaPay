public class InstapayWalletUser extends User {
    private String walletProvider;
    private double balance;
    public InstapayWalletUser(){

    }
    public InstapayWalletUser(String walletProvider,double balance){
        this.walletProvider=walletProvider;
        this.balance=balance;
    }


    public double getBalance() {
        return balance;
    }
    public String getWalletProvider() {
        return walletProvider;
    }
    public void setWalletProvider(String walletProvider){
        this.walletProvider=walletProvider;
    }
    public void deposit(double amount) { //interface
        if (amount > 0) {
            balance += amount;
        }
    }
    public boolean withdraw(double amount) { // interface
        if (amount <= balance && amount > 0) {
            balance -= amount;
            return true;
        }
        return false;
    }
}
