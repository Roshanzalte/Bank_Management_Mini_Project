package com.roshan.demo;


public abstract class Account {
    private int accountNumber;
    private String accountHolderName;
    private double balance;
    private String[] transactions = new String[100]; // Array to store transaction history
    private int transactionCount = 0; // Keeps track of the number of transactions

    public Account(int accountNumber, String accountHolderName, double balance) {
        this.accountNumber = accountNumber;
        this.accountHolderName = accountHolderName;
        this.balance = balance;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public String getAccountHolderName() {
        return accountHolderName;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void addTransaction(String transactionDetails) {
        if (transactionCount < transactions.length) {
            transactions[transactionCount] = transactionDetails;
            transactionCount++;
        }
    }

    public void displayTransactions() {
        if (transactionCount == 0) {
            System.out.println("No transactions found.");
        } else {
            System.out.println("Transaction History:");
            for (int i = 0; i < transactionCount; i++) {
                System.out.println(transactions[i]);
            }
        }
    }

    public abstract void deposit(double amount);

    public abstract void withdraw(double amount);

    public abstract void calculateInterest();

    public abstract void displayAccountDetails();
}
