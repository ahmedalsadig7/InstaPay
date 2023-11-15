import java.util.HashMap;
import java.util.Map;

// InstapaySystem class
public class InstapaySystem {
    private Map<String, User> userDatabase;
    public InstapaySystem() {
        this.userDatabase = new HashMap<>();
    }
    // Registration method
    public boolean registerUser(String username, String password, String mobileNumber, boolean isBankUser) {
        if (userDatabase.containsKey(username)) {
            // User already exists with this username
            return false;
        }
        User newUser = new User(username, password, mobileNumber,isBankUser);
        userDatabase.put(username, newUser);
        return true; // Registration successful
    }
    public boolean isUsernameTaken(String username) {
        return userDatabase.containsKey(username);
    }

    public User login(String username, String password,boolean isBankUer) {
        User user = userDatabase.get(username);
        if (user != null && user.getPassword().equals(password)&& user.isBankUser()) {
            return user; // Login successful
        }
        return null; // Login failed
    }

}
