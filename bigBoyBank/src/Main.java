import com.philipBank.ATM;
import com.philipBank.Account;
import com.philipBank.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    private static List<User> users = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);
    private static ATM atm = new ATM(true);


    public static void main(String[] args) {
        while (true) {
            System.out.println("User Menu");
            System.out.println("1. Create user");
            System.out.println("2. Select user");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline left-over

            switch (choice) {
                case 1:
                    createUser();
                    break;
                case 2:
                    if (users.isEmpty()) {
                        System.out.println("No users available.");
                    } else {
                        selectUser();
                    }
                    break;
                case 3:
                    System.exit(0);
                case 10:
                    debugMenu();
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    /**
     * Displays a debug menu and performs actions based on user input.
     */
    private static void debugMenu() {
        while (true) {
            System.out.println("Debug menu");
            System.out.println("1. Exit");
            System.out.println("2. ATM working: " + atm.isWorking());
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline left-over
            if (choice == 2) {// Toggle the working state of the ATM
                atm.setWorking(!atm.isWorking());
                System.out.println("ATM is working: " + atm.isWorking());
            } else {
                System.out.println("Exiting debug menu.");
            }
            break;
        }
    }
    private static void createUser() {
        System.out.println("Create user");
        System.out.print("Enter first name: ");
        String firstName = scanner.nextLine();
        System.out.print("Enter last name: ");
        String lastName = scanner.nextLine();
        User user = new User(firstName, lastName);
        users.add(user);
        System.out.println("User created.");
    }

    private static void selectUser() {
        for (int i = 0; i < users.size(); i++) {
            User user = users.get(i);
            System.out.printf("%d. %s %s%n", i + 1, user.getFirstName(), user.getLastName());
        }
        System.out.print("Select a user: ");
        int index = scanner.nextInt() - 1;
        if (index < 0 || index >= users.size()) {
            System.out.println("Invalid choice.");
        } else {
            manageUser(users.get(index));
        }
    }

    private static void manageUser(User user) {
        while (true) {
            System.out.println("1. Create account");
            System.out.println("2. Select account");
            System.out.println("3. Back to main menu");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline left-over

            switch (choice) {
                case 1:
                    createAccount(user);
                    break;
                case 2:
                    if (user.getNumOfAccounts() == 0) {
                        System.out.println("No accounts available.");
                    } else {
                        selectAccount(user);
                    }
                    break;
                case 3:
                    return;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    private static void createAccount(User user) {
        System.out.print("Enter initial balance: ");
        double initialBalance = scanner.nextDouble();
        scanner.nextLine();  // Consume newline left-over
        if (initialBalance < 0) {
            System.out.println("Initial balance cannot be negative.");
            return;
        }
        Account account = new Account(initialBalance);  // Create an account with the specified initial balance
        user.addAccount(account);
        System.out.println("Account created with an initial balance of $" + initialBalance);
    }

    private static void selectAccount(User user) {
        System.out.println("Accounts");
        List<Account> accounts = user.getAccounts();
        for (int i = 0; i < accounts.size(); i++) {
            System.out.printf("%d. Account %d: $%.2f%n", i + 1, i + 1, accounts.get(i).getMoney());
        }
        System.out.print("Select an account: ");
        int index = scanner.nextInt() - 1;
        if (index < 0 || index >= accounts.size()) {
            System.out.println("Invalid choice.");
        } else {
            manageAccount(accounts.get(index));
        }
    }

    private static void manageAccount(Account account) {
        while (true) {
            System.out.println("Account " + account.getID());
            System.out.println("Balance: $" + account.getMoney());
            System.out.println("1. Deposit");
            System.out.println("2. Withdraw");
            System.out.println("3. Transfer");
            System.out.println("4. Back to user menu");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    deposit(account);
                    break;
                case 2:
                    withdraw(account);
                    break;
                case 3:
                    transfer(account);
                    break;
                case 4:
                    return;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    private static void transfer(Account account) {
        System.out.println("Enter amount to transfer: ");
        double amount = scanner.nextDouble();
        scanner.nextLine();
        try {
            account.transferMoney(account, amount);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void withdraw(Account account) {
        System.out.println("Enter amount to withdraw: ");
        double amount = scanner.nextDouble();
        scanner.nextLine();
        try {
            account.withdrawMoney(atm, amount);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void deposit(Account account) {
        System.out.println("Enter amount to deposit: ");
        double amount = scanner.nextDouble();
        scanner.nextLine();
        try {
            account.depositMoney(atm, amount);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

}