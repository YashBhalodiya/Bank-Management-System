package com.yashbhalodiya.bank;

import com.yashbhalodiya.bank.models.Account;
import com.yashbhalodiya.bank.services.AccountServices;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        AccountServices accountServices = new AccountServices();
        Scanner sc = new Scanner(System.in);

        boolean exit = false;

        while (!exit){
            System.out.println("-----------------------------------");
            System.out.println("Welcome to Back Management System!");
            System.out.println("1. Open Account");
            System.out.println("2. Deposit Funds");
            System.out.println("3. Withdraw Funds");
            System.out.println("4. Transfer Funds Between Accounts");
            System.out.println("5. Check Balance");
            System.out.println("6. Close Account");
            System.out.println("7. Show All Accounts");
            System.out.println("8. Exit");
            System.out.println("-----------------------------------");
            System.out.println();

            System.out.print("Enter Your Choice: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice){
                case 1:
                    openAccount(sc, accountServices);
                    break;
                case 2:
                    depositFund(sc, accountServices);
                    break;
                case 3:
                    withdrawFund(sc, accountServices);
                    break;
                case 4:
                    transferBetweenAccount(sc, accountServices);
                    break;
                case 5:
                    checkBalance(sc, accountServices);
                    break;
                case 6:
                    closeAccount(sc, accountServices);
                    break;
                case 7:
                    accountServices.showAllAccounts();
                    break;
                case 8:
                    exit = true;
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid Choice!");
            }
        }
    }

    public static void openAccount(Scanner sc, AccountServices accountServices){
        System.out.print("Enter Account Number: ");
        int accountNumber = sc.nextInt();
        sc.nextLine();

        System.out.print("Enter Account Holder Name: ");
        String accountHolderName = sc.nextLine();

        System.out.print("Enter Account Type: ");
        String accountType = sc.nextLine();

        System.out.print("Enter Account Initial Balance: ");
        double accountInitialBalance = sc.nextDouble();
        sc.nextLine();

        System.out.print("Enter Date of Account Creation (dd/MM/yyyy): ");
        String dateInput = sc.nextLine();

        Date dateCreated;
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            dateFormat.setLenient(false);
            dateCreated = dateFormat.parse(dateInput);
        } catch (ParseException e) {
            System.out.println("Invalid date format! Setting today's date.");
            dateCreated = new Date();
        }

        System.out.print("Enter Account Branch Name: ");
        String accountBranchName = sc.nextLine();

        System.out.print("Enter Account Status: ");
        String accountStatus = sc.nextLine();

        Account account = new Account(accountNumber,accountHolderName,accountType,accountInitialBalance,dateCreated,accountBranchName,accountStatus);
        accountServices.openAccount(account);
    }

    public static void depositFund(Scanner sc, AccountServices accountServices){
        System.out.print("Enter Account Number: ");
        int accountNumber = sc.nextInt();
        sc.nextLine();

        System.out.print("Enter amount to deposit: ");
        double amount = sc.nextDouble();
        sc.nextLine();

        accountServices.deposit(accountNumber,amount);
    }

    public static void withdrawFund(Scanner sc, AccountServices accountServices){
        System.out.print("Enter Account Number: ");
        int accountNumber = sc.nextInt();
        sc.nextLine();

        System.out.print("Enter amount to withdraw: ");
        double amountToWithdraw = sc.nextDouble();
        sc.nextLine();

        accountServices.withdraw(accountNumber,amountToWithdraw);
    }

    public static void transferBetweenAccount(Scanner sc, AccountServices accountServices){
        System.out.print("Enter Source Account Number: ");
        int fromAccountNumber = sc.nextInt();
        sc.nextLine();

        System.out.print("Enter Target Account Number: ");
        int toAccountNumber = sc.nextInt();
        sc.nextLine();

        System.out.print("Enter Amount: ");
        double amount = sc.nextDouble();
        sc.nextLine();

        accountServices.transferFund(fromAccountNumber, toAccountNumber, amount);
    }

    public static void checkBalance(Scanner sc, AccountServices accountServices){
        System.out.print("Enter Account Number: ");
        int accountNumber = sc.nextInt();
        sc.nextLine();

        accountServices.checkBalance(accountNumber);
    }

    public static void closeAccount(Scanner sc, AccountServices accountServices){
        System.out.print("Enter Account Number: ");
        int accountNumber = sc.nextInt();
        sc.nextLine();

        accountServices.closeAccount(accountNumber);
    }

}