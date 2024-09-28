package com.yashbhalodiya.bank.services;

import com.yashbhalodiya.bank.models.Account;
import com.yashbhalodiya.bank.repository.AccountRepository;

import java.util.Map;

public class AccountServices {
    public AccountRepository accountRepository;

    public AccountServices(){
        this.accountRepository = new AccountRepository();
    }

    // To open account
    public void openAccount(Account account){
        accountRepository.saveAccount(account);
        accountRepository.saveDataToDatabase(account);
        System.out.println("Account Opened Successfully");
    }

    //To close account
    public void closeAccount(int accountNumber){
        Account accToClose = accountRepository.findAccount(accountNumber);
        if (accToClose != null){
            accountRepository.closeAccount(accountNumber);
            System.out.println("Account Closed Successfully " + accountNumber);
        }else {
            System.out.println("Account not found!");
        }
    }

    //To check balance in account
    public void checkBalance(int accountNumber){
        System.out.println("Checking balance for account number: " + accountNumber);
        Account account = accountRepository.findAccount(accountNumber);

        if (account != null) {
            System.out.println("Account found. Balance: " + account.getBalance());
        } else {
            System.out.println("Account not found for account number: " + accountNumber);
        }
    }

    //To deposit amount in account
    public void deposit(int accountNumber, double amount){
        Account deposit = accountRepository.findAccount(accountNumber);
        if (deposit != null && amount > 0){
            deposit.setBalance(deposit.getBalance() + amount);
            accountRepository.updateAccount(deposit);
            System.out.println("Rs " + amount + " deposited to account " + accountNumber);
        } else {
            System.out.println("Invalid account or amount!");
        }
    }

    //To withdraw amount from account
    public void withdraw(int accountNumber, double amount){
        Account withdraw = accountRepository.findAccount(accountNumber);
        if (withdraw != null && withdraw.getBalance() > amount){
            withdraw.setBalance(withdraw.getBalance() - amount);
            accountRepository.updateAccount(withdraw);
            System.out.println("Rs " + amount + " withdraw from account " + accountNumber);
        } else {
            System.out.println("Invalid account or insufficient fund in acc!");
        }
    }

    //To transfer funds between account
    public void transferFund(int fromAccountNo, int toAccountNo, double amount){
        Account fromAccount = accountRepository.findAccount(fromAccountNo);
        Account toAccount = accountRepository.findAccount(toAccountNo);
        if (fromAccount != null && toAccount != null && fromAccount.getBalance() > amount && amount > 0){
            fromAccount.setBalance(fromAccount.getBalance() - amount);
            toAccount.setBalance(toAccount.getBalance() + amount);
            accountRepository.updateAccount(fromAccount);
            accountRepository.updateAccount(toAccount);
            System.out.println("Transferred " + amount + " from account " + fromAccountNo + " to account " + toAccountNo);
        } else {
            System.out.println("Invalid account or insufficient fund");
        }
    }

    public void showAllAccounts(){
        Map<Integer, Account> accounts = accountRepository.getAllAccounts();
        if (!accounts.isEmpty()){
            for (Account account : accounts.values()) {
                System.out.println(account);
            }
        }else {
            System.out.println("No accounts available..");
        }
    }
}
