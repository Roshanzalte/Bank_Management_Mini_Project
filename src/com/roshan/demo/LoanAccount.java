
package com.roshan.demo;
public class LoanAccount extends Account {

    private double loanAmount;
    private static final double INTEREST_RATE = 8.0;

    public LoanAccount(int accountNumber, String accountHolderName, double loanAmount) {
        super(accountNumber, accountHolderName, 0); // balance starts at 0
        this.loanAmount = loanAmount;
        setBalance(-loanAmount); // Negative balance to reflect loan
    }

    @Override
    public void deposit(double amount) {
        if (amount > 0) {
            setBalance(getBalance() + amount);
            addTransaction("Repayment: " + amount);
            System.out.println("Repayment made on Loan Account.");
        } else {
            System.out.println("Invalid deposit amount.");
        }
    }

    @Override
    public void withdraw(double amount) {
        System.out.println("Loans cannot be withdrawn directly.");
    }

    @Override
    public void calculateInterest() {
        double interest = loanAmount * INTEREST_RATE / 100;
        loanAmount += interest;
        setBalance(-loanAmount); // Reflect new loan balance after interest
        addTransaction("Interest: " + interest);
        System.out.println("Interest added to loan: " + interest);
    }

    @Override
    public void displayAccountDetails() {
        System.out.println("Account Number: " + getAccountNumber());
        System.out.println("Account Holder: " + getAccountHolderName());
        System.out.println("Loan Amount: " + loanAmount);
    }
}
