package Client.User;

import Client.ServerComms.ClientToServerCommands;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

abstract class AbstractUsers {
    protected static final ClientToServerCommands comms = new ClientToServerCommands();
    private String accountType;
    private String accountNumber;
    private String accountHolderName;
    private String accountID;
    private String accountPIN;
    private String accountEmail;
    private String accountCurrency;
    private double availableBalance; //Should mostly be the same as current balance due to no 3rd party
    private double currentBalance; //Spendable total balance, main balance
    private int accountLimit;
    private int accountMaxLimit;
    private double currentLimit;

    public double getAccountLimit() {
        return accountLimit;
    }

    public double getCurrentLimit() {
        return currentLimit;
    }

    public void setCurrentLimit(double currentLimit) {
        this.currentLimit = currentLimit;
    }

    public void setAccountLimit(int accountLimit) {
        this.accountLimit = accountLimit;
    }

    public double getAccountMaxLimit() {
        return accountMaxLimit;
    }

    public void setAccountMaxLimit(int accountDefaultLimit) {
        this.accountMaxLimit = accountDefaultLimit;
    }

    public void setAccountPIN(String accountPIN) {
        this.accountPIN = accountPIN;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public void setAccountEmail(String accountEmail) {
        this.accountEmail = accountEmail;
    }

    public void setAccountID(String accountID) {
        this.accountID = accountID;
    }

    public void setAccountHolderName(String accountName) {
        this.accountHolderName = accountName;
    }

    public void setAccountCurrency(String accountCurrency) {
        this.accountCurrency = accountCurrency;
    }

    public void setAvailableBalance(double availableBalance) {
        this.availableBalance = availableBalance;
    }

    public void setCurrentBalance(double currentBalance) {
        this.currentBalance = currentBalance;
    }

    public String getAccountID() {
        return accountID;
    }

    protected String getAccountPIN() {
        return accountPIN;
    }

    protected String getAccountEmail() {
        return accountEmail;
    }

    public String getAccountCurrency() {
        return accountCurrency;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getAccountHolderName() {
        return accountHolderName;
    }

    public double getAvailableBalance() {
        return availableBalance;
    }

    public double getCurrentBalance() {
        return currentBalance;
    }

    public void setAccount(String[] data){}

    public boolean checkValidPIN(String pin) throws IOException {
        return false;
    }

    public boolean changePIN(String newPIN, ArrayList<Customer> accounts) throws IOException {return false;}

    public boolean changeID(String newID, ArrayList<Customer> accounts) throws IOException {return false;}

    public boolean emailAuthentication(Scanner sc) {return false;}

    public boolean changeEmail(String newEmail) throws IOException {return false;}

}
