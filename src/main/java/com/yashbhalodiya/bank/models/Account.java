package com.yashbhalodiya.bank.models;

public class Account {
    private int accountNumber;
    private double balance;
    private String customerId;

    public Account(int accountNumber, double balance, String customerId){
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.customerId = customerId;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }
}
