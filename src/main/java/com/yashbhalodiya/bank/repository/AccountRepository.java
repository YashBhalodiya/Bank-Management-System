package com.yashbhalodiya.bank.repository;

import com.yashbhalodiya.bank.database.DbConnection;
import com.yashbhalodiya.bank.models.Account;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class AccountRepository {
    private final Map<Integer, Account> accounts = new HashMap<>();

    public void saveAccount(Account account){
        accounts.put(account.getAccountNumber(), account);
    }

    BufferedWriter bw;
    public void saveDataToFile(Account account){
        String sql = "INSERT INTO bank(account_number,account_holder_name,account_type,balance,date_created,branch_name,status) VALUES(?,?,?,?,?,?,?)";
        try (Connection connection = DbConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setInt(1,account.getAccountNumber());
                preparedStatement.setString(2,account.getAccountHolderName());
                preparedStatement.setString(3,account.getAccountType());
                preparedStatement.setDouble(4,account.getBalance());
                preparedStatement.setDate(5, new java.sql.Date(account.getDateCreated().getTime()));
                preparedStatement.setString(6,account.getBranchName());
                preparedStatement.setString(7,account.getStatus());

                preparedStatement.execute();
            System.out.println("Account " + account.getAccountNumber() + " saved successfully!");
        } catch (SQLException e) {
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
        String sql = "SELECT * FROM bank";
        Map<Integer, Account> accountMap = new HashMap<>();
        try(Connection connection = DbConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery()){
            while (resultSet.next()){
                int accountNumber = resultSet.getInt("account_number");
                String accountHolderName = resultSet.getString("account_holder_name");
                String accountType = resultSet.getString("account_type");
                double accountBalance = resultSet.getDouble("balance");
                Date date_created = resultSet.getDate("date_created");
                String branch_name = resultSet.getString("branch_name");
                String status = resultSet.getString("status");

                Account account = new Account(accountNumber,accountHolderName,accountType,accountBalance,date_created,branch_name,status);
                accountMap.put(accountNumber,account);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return new HashMap<>(accountMap);
//        return accountMap;
    }
}
