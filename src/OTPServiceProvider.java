public interface OTPServiceProvider {
    int generateOTP(String mobileNumber);
    boolean verifyOTP(String mobileNumber, int otpToVerify);
}
