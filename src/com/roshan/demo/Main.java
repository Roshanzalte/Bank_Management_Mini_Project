
package com.roshan.demo;
import java.util.Scanner;

public class Main {
    private Account[] accounts = new Account[100];
    private int accountCount = 0;
    private Scanner scanner = new Scanner(System.in);

    public void openBank() {
        while (true) {
            System.out.println("\n-- Banking System --");
            System.out.println("1. Over the Counter Activities");
            System.out.println("2. Account Lifecycle");
            System.out.println("3. Interest Calculation");
            System.out.println("4. End of Day Report for Daily Transactions");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            if (choice == 1) {
                overTheCounterActivities();
            } else if (choice == 2) {
                accountLifecycle();
            } else if (choice == 3) {
                interestCalculation();
            } else if (choice == 4) {
                endOfDayReport();
            } else if (choice == 5) {
                System.out.println("Exiting...");
                return;
            } else {
                System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void overTheCounterActivities() {
        System.out.println("1. Create Account");
        System.out.println("2. Delete Account");
        System.out.println("3. Search Account");
        System.out.println("4. Show All Accounts");
        System.out.print("Enter your choice: ");
        int choice = scanner.nextInt();

        if (choice == 1) {
            createAccount();
        } else if (choice == 2) {
            deleteAccount();
        } else if (choice == 3) {
            searchAccount();
        } else if (choice == 4) {
            showAllAccounts();
        } else {
            System.out.println("Invalid choice.");
        }
    }

    private void createAccount() {
        System.out.println("Enter Account Type (1. Savings, 2. Salary, 3. Current, 4. Loan): ");
        int accountType = scanner.nextInt();
        System.out.print("Enter Account Number: ");
        int accountNumber = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        System.out.print("Enter Account Holder Name: ");
        String accountHolderName = scanner.nextLine();
        System.out.print("Enter Initial Balance: ");
       
        double balance =0; 
        
       
        	balance=scanner.nextDouble();        

        Account account = null;
        if (accountType == 1) {
            account = new SavingAccount(accountNumber, accountHolderName, balance, 10000); // Assuming min balance
        } else if (accountType == 2) {
            account = new SalaryAccount(accountNumber, accountHolderName, balance);
        } else if (accountType == 3) {
            System.out.print("Enter Overdraft Limit: ");
            double overdraftLimit = scanner.nextDouble();
            account = new CurrentAccount(accountNumber, accountHolderName, balance, overdraftLimit);
        } else if (accountType == 4) {
            System.out.print("Enter Loan Amount: ");
            double loanAmount = scanner.nextDouble();
            account = new LoanAccount(accountNumber, accountHolderName, loanAmount);
        } else {
            System.out.println("Invalid account type.");
            return;
        }

        if (account != null) {
            accounts[accountCount] = account;
            accountCount++;
            System.out.println("Account created successfully.");
        }
    }

    private void deleteAccount() {
        System.out.print("Enter Account Number to delete: ");
        int accountNumber = scanner.nextInt();

        for (int i = 0; i < accountCount; i++) {
            if (accounts[i].getAccountNumber() == accountNumber) {
                for (int j = i; j < accountCount - 1; j++) {
                    accounts[j] = accounts[j + 1];
                }
                accounts[accountCount - 1] = null;
                accountCount--;
                System.out.println("Account deleted successfully.");
                return;
            }
        }
        System.out.println("Account not found.");
    }

    private void searchAccount() {
        System.out.print("Enter Account Number to search: ");
        int accountNumber = scanner.nextInt();

        for (int i = 0; i < accountCount; i++) {
            if (accounts[i].getAccountNumber() == accountNumber) {
                accounts[i].displayAccountDetails();
                return;
            }
        }
        System.out.println("Account not found.");
    }

    private void showAllAccounts() {
        if (accountCount == 0) {
            System.out.println("No accounts available.");
        } else {
            System.out.println("\nAll Accounts -");
            for (int i = 0; i < accountCount; i++) {
                System.out.println("Account Number: " + accounts[i].getAccountNumber() +
                        ", Account Holder: " + accounts[i].getAccountHolderName() +
                        ", Balance: " + accounts[i].getBalance());
            }
        }
    }

    private void accountLifecycle() {
        System.out.println("1. View Transaction History");
        System.out.println("2. Add Transaction (Deposit/Withdraw)");
        System.out.println("3. View Balance");
        System.out.print("Enter your choice: ");
        int choice = scanner.nextInt();

        if (choice == 1) {
            viewTransactionHistory();
        } else if (choice == 2) {
            addTransaction();
        } else if (choice == 3) {
            viewBalance();
        } else {
            System.out.println("Invalid choice.");
        }
    }

    private void viewTransactionHistory() {
        System.out.print("Enter Account Number to view transaction history: ");
        int accountNumber = scanner.nextInt();

        Account account = findAccount(accountNumber);
        if (account != null) {
            account.displayTransactions();  // This will display transaction history
        } else {
            System.out.println("Account not found.");
        }
    }

    private void addTransaction() {
        System.out.print("Enter Account Number for transaction: ");
        int accountNumber = scanner.nextInt();

        Account account = findAccount(accountNumber);
        if (account != null) {
            System.out.println("Choose Transaction Type: ");
            System.out.println("1. Deposit");
            System.out.println("2. Withdraw");
            System.out.print("Enter your choice: ");
            int transactionType = scanner.nextInt();
            System.out.print("Enter Transaction Amount: ");
            double amount = scanner.nextDouble();

            if (transactionType == 1) {
                account.deposit(amount);
            } else if (transactionType == 2) {
                account.withdraw(amount);
            } else {
                System.out.println("Invalid transaction type.");
            }
        } else {
            System.out.println("Account not found.");
        }
    }

    private void viewBalance() {
        System.out.print("Enter Account Number to view balance: ");
        int accountNumber = scanner.nextInt();

        Account account = findAccount(accountNumber);
        if (account != null) {
            System.out.println("Account Balance: " + account.getBalance());
        } else {
            System.out.println("Account not found.");
        }
    }

    private Account findAccount(int accountNumber) {
        for (int i = 0; i < accountCount; i++) {
            if (accounts[i].getAccountNumber() == accountNumber) {
                return accounts[i];
            }
        }
        return null;
    }

    private void interestCalculation() {
        System.out.print("Enter Account Number: ");
        int accountNumber = scanner.nextInt();

        Account account = findAccount(accountNumber);
        if (account != null) {
            account.calculateInterest();
        } else {
            System.out.println("Account not found.");
        }
    }

    private void endOfDayReport() {
        System.out.println("\nEnd of Day Report: Transactions Summary");
        for (int i = 0; i < accountCount; i++) {
            accounts[i].displayAccountDetails();
        }
    }

    public static void main(String[] args) {
        Main bank = new Main();
        bank.openBank();
    }
}
