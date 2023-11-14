public class Bill {
    private String billType;
    private double amount;

    // Constructor for Bill class
    public Bill(String billType, double amount) {
        this.billType = billType;
        this.amount = amount;
    }

    // Getters and Setters
    public String getBillType() {
        return billType;
    }

    public void setBillType(String billType) {
        this.billType = billType;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    // toString method for printing bill details in a readable format
    @Override
    public String toString() {
        return "Bill{" +
                "billType='" + billType + '\'' +
                ", amount=" + amount +
                '}';
    }
}
