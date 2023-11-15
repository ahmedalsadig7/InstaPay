import java.util.Random;
import java.util.Scanner;

// InstapayApp class
public class InstapayApp {
public static User user=new User();

    // Main menu
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nWelcome to Instapay!");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");

            int option = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (option) {
                case 1:
                    user.registerUser(scanner);
                    break;
                case 2:
                    user.loginUser(scanner);
                    break;
                case 3:

                    return;
                default:
                    System.out.println("Invalid option selected. Please try again.");
            }
        }
    }

}