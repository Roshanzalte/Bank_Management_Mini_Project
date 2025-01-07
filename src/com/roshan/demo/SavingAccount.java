package com.roshan.demo;

public class SavingAccount extends Account {

    // Setting the minimum balance to ₹10,000
    private double minBalance = 10000;

    // Constructor
    public SavingAccount(int accountNumber, String accountHolderName, double balance, double minBalance) {
        super(accountNumber, accountHolderName, balance);
        // This constructor can still accept a minBalance, but we're overriding it to ₹10,000
        this.minBalance = 10000;
    }

    @Override
    public void deposit(double amount) {
        if (amount > 0) {
            setBalance(getBalance() + amount);
            addTransaction("Deposit: " + amount);
            System.out.println("Deposited " + amount + " into Savings Account.");
        } else {
            System.out.println("Invalid deposit amount.");
        }
    }

    @Override
    public void withdraw(double amount) {
        // Check if the withdrawal doesn't violate the minimum balance constraint
        if (getBalance() - amount >= minBalance) {
            setBalance(getBalance() - amount);
            addTransaction("Withdraw: " + amount);
            System.out.println("Withdrew " + amount + " from Savings Account.");
        } else {
            System.out.println("Insufficient funds! Minimum balance of ₹10,000 must be maintained.");
        }
    }

    @Override
    public void calculateInterest() {
        double interest = getBalance() * 0.04; // 4% interest rate
        setBalance(getBalance() + interest);
        addTransaction("Interest: " + interest);
        System.out.println("Interest added: " + interest);
    }

    @Override
    public void displayAccountDetails() {
        System.out.println("Account Number: " + getAccountNumber());
        System.out.println("Account Holder: " + getAccountHolderName());
        System.out.println("Balance: " + getBalance());
        System.out.println("Minimum Balance: ₹" + minBalance);
    }
}