package com.yashbhalodiya.bank.repository;

import com.yashbhalodiya.bank.models.Account;

import java.util.HashMap;
import java.util.Map;

public class AccountRepository {
    private final Map<Integer, Account> accounts = new HashMap<>();

    public void saveAccount(Account account){
        accounts.put(account.getAccountNumber(), account);
    }

    public Account findAccount(int accountNumber){
        return accounts.get(accountNumber);
    }

    public void updateAccount(Account account){
        if (accounts.containsKey(account.getAccountNumber())){
            accounts.put(account.getAccountNumber(), account);
            System.out.println("Account updated successfully: " + account.getAccountNumber());
        }else{
            System.out.println("Account not found: " + account.getAccountNumber());
        }
    }

    public void closeAccount(int accountNumber){
        if (accounts.containsKey(accountNumber)){
            accounts.remove(accountNumber);
            System.out.println("Account Closed Successfully " + accountNumber);
        }else {
            System.out.println("Account not found: " + accountNumber);
        }
    }

    public Map<Integer, Account> getAllAccounts() {
        return new HashMap<>(accounts);
    }
}
