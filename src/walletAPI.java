public class walletAPI {
    public boolean verifyWalletAccount(String mobileNumber,String walletProvider) {
        if (mobileNumber != null) {
            System.out.println("mobile number " + mobileNumber + " verified successfully!");
            return true;
        } else {
            System.out.println("Invalid mobile number: " + mobileNumber);
            return false;
        }
    }
}
