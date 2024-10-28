import java.io.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter 'D' for deposit or 'W' for withdrawal:");
        char operation = sc.next().charAt(0);

        System.out.println("Enter the amount:");
        int amount = sc.nextInt();

        File balanceFile = new File("balance.txt");
        Scanner fileScanner = new Scanner(balanceFile);

        int currentBalance = fileScanner.nextInt();
        fileScanner.close();  // Close the scanner once the reading is done.

        System.out.println("Current balance: " + currentBalance);

        if (operation == 'D' || operation == 'd') {
            currentBalance += amount;
            System.out.println("Deposited successfully.");
        } else if (operation == 'W' || operation == 'w') {
            if (amount > currentBalance) {
                System.out.println("Insufficient balance!");
                return;  // Exit the program if insufficient balance.
            }
            currentBalance -= amount;
            System.out.println("Withdrawal successful.");
        } else {
            System.out.println("Invalid operation!");
            return;  // Exit the program for invalid input.
        }

        System.out.println("Updated balance: " + currentBalance);

        // Write the updated balance back to the file
        try (FileOutputStream fos = new FileOutputStream("balance.txt")) {
            fos.write(Integer.toString(currentBalance).getBytes());
        }
    }
}
