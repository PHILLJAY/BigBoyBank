import com.philipBank.Account;
import com.philipBank.Bank;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        // Press Alt+Enter with your caret at the highlighted text to see how
        // IntelliJ IDEA suggests fixing it.
        System.out.print("Hello and welcome!");
        Bank bigBoyBank = new Bank();

        System.out.println(bigBoyBank.getID());
        System.out.println(bigBoyBank);

        //bigBoyBank.addUser("Philip", "Jasionowski", 100);

        //creating the account class
        Account firstAccount = new Account(100,0.02);
        }
    }
