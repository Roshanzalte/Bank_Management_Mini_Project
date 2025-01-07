
package com.roshan.demo;
public class SalaryAccount extends Account {

    private static final double interestRate = 5.0;

    public SalaryAccount(int accountNumber, String accountHolderName, double balance) {
        super(accountNumber, accountHolderName, balance);
    }

    @Override
    public void deposit(double amount) {
        if (amount > 0) {
            setBalance(getBalance() + amount);
            addTransaction("Deposit: " + amount);
            System.out.println("Deposited " + amount + " into Salary Account.");
        } else {
            System.out.println("Invalid deposit amount.");
        }
    }

    @Override
    public void withdraw(double amount) {
        if (getBalance() >= amount) {
            setBalance(getBalance() - amount);
            addTransaction("Withdraw: " + amount);
            System.out.println("Withdrew " + amount + " from Salary Account.");
        } else {
            System.out.println("Insufficient balance.");
        }
    }

    @Override
    public void calculateInterest() {
        double interest = getBalance() * interestRate / 100;
        setBalance(getBalance() + interest);
        addTransaction("Interest: " + interest);
        System.out.println("Interest added: " + interest);
    }

    @Override
    public void displayAccountDetails() {
        System.out.println("Account Number: " + getAccountNumber());
        System.out.println("Account Holder: " + getAccountHolderName());
        System.out.println("Balance: " + getBalance());
        System.out.println("Interest Rate: " + interestRate + "%");
    }
}
