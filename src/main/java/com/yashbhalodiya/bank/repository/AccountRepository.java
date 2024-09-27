package com.yashbhalodiya.bank.repository;

import com.yashbhalodiya.bank.models.Account;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class AccountRepository {
    private final Map<Integer, Account> accounts = new HashMap<>();

    public void saveAccount(Account account){
        accounts.put(account.getAccountNumber(), account);
    }

    BufferedWriter bw;
    public void saveDataToFile(String fileName){
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName, true))) {
            for (Account account : accounts.values()){
                bw.write(account.toString());
                bw.newLine();
            }
            System.out.println("Data Added Successfully!");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
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
