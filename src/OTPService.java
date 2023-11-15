import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class OTPService {
    private final Map<String, Integer> otpStorage = new HashMap<>();
    private final Random random = new Random();

    // Generate and store an OTP for a specific mobile number
    public int generateOTP(String mobileNumber) {
        int otp = 100000 + random.nextInt(900000); // Generate 6 digit OTP
        otpStorage.put(mobileNumber, otp);
        // In a real application, you would send the OTP to the user's mobile number here
        System.out.println("Generated OTP for " + mobileNumber + ": " + otp);
        return otp;
    }

    // Verify the OTP entered by the user against the stored value
    public boolean verifyOTP(String mobileNumber, int otpToVerify) {
        // Check if an OTP was generated for this mobile number
        if (!otpStorage.containsKey(mobileNumber)) {
            return false; // No OTP was generated for this number
        }

        // Check if the OTP matches the stored OTP
        int storedOtp = otpStorage.get(mobileNumber);
        if (storedOtp == otpToVerify) {
            otpStorage.remove(mobileNumber); // OTP is single-use, so remove after verification
            return true;
        } else {
            return false; // OTP mismatch
        }
    }
}