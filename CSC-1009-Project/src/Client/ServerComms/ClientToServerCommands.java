package Client.ServerComms;

import java.io.IOException;
import java.util.ArrayList;

public class ClientToServerCommands extends ClientToServerComms{
    // Check if the pin is correct or not according to the user's id
    public boolean checkLogin(String id, String pin) throws IOException {
        String command = "LoginCheck - " + id + "," +pin;
        String message = simpleCommand(command);
        if ("true".equals(message)) {
            return true;
        }
        return false;
    }

    // Check if the login details is correct for the user if true, server will send the client all the user's accounts
    public ArrayList<String> checkLoginDetails(String id, String pin) throws IOException {
        String command = "LoginDetails - " + id + "," +pin;
        return(arrayReturnCommand(command));
    }

    // Check weather the user's id is unique or not
    public boolean checkUnique(String id) throws IOException {
        String command = "CheckUniqueID - " + id;
        String message = simpleCommand(command);
        if ("true".equals(message)) {
            return true;
        }
        return false;
    }

    public String checkValidAccountNumber(String toCheckAccountNumber) throws IOException {
        String command = "CheckValidAccountNumber - " + toCheckAccountNumber;
        return simpleCommand(command);
    }

    public boolean changeLimit(String accountNum, int newAmount) throws IOException {
        String command = "ChangeLimit - " + accountNum + "," + newAmount;
        String message = simpleCommand(command);
        if ("true".equals(message)) {
            return true;
        }
        return false;
    }

    public boolean changeEmail(String accountNum, String newEmail) throws IOException {
        String command = "ChangeEmail - " + accountNum + "," + newEmail;
        String message = simpleCommand(command);
        if ("true".equals(message)) {
            return true;
        }
        return false;
    }

    public boolean changePIN(String accountID, String pin) throws IOException {
        String command = "ChangePIN - " + accountID + "," + pin;
        String message = simpleCommand(command);
        if ("true".equals(message)) {
            return true;
        }
        return false;
    }

    public boolean changeID(String oldID, String newID) throws IOException {
        String command = "ChangeID - " + oldID + "," + newID;
        String message = simpleCommand(command);
        if ("true".equals(message)) {
            return true;
        }
        return false;
    }

    // Check if the currency is valid or not by asking the server to check the internet
    public String checkCurrency(String curr) throws IOException {
        String command = "CheckCurrency - " + curr;
        return simpleCommand(command);
    }

    public String getExchangeRates(String toCurr, String fromCurr) throws IOException {
        String command = "GetExchangeRates - " + toCurr + "," + fromCurr;
        return simpleCommand(command);
    }

    // update the server's database with the deposit amount
    public boolean serverDeposit(double amount, String accountNum, String date, String curr, String memo) throws IOException{
        String command = "Deposit - " + accountNum + "," + amount + "," + date + "," + curr + "," + memo;
        String message = simpleCommand(command);
        if ("true".equals(message)) {
            return true;
        }
        return false;
    }

    // update the server's database with the withdrawal amount
    public boolean serverWithdraw(double amount, String accountNum, String date, String curr, String memo) throws IOException{
        String command = "Withdraw - " + accountNum + "," + amount + "," + date + "," + curr + "," + memo;
        String message = simpleCommand(command);
        if ("true".equals(message)) {
            return true;
        }
        return false;
    }

    // update the server's database with the transfer amount
    public boolean serverTransfer(double amount, String accountNum, String date, String curr, String memo, String toAccount, String toAccountCurr) throws IOException{
        String command = "Transfer - " + accountNum + "," + amount + "," + date + "," + curr + "," + memo + "," + toAccount + "," + toAccountCurr;
        String message = simpleCommand(command);
        if ("true".equals(message)) {
            return true;
        }
        return false;
    }

    // Used to check if the client needs a Transaction History update, this can be used to prevent the client from calling the server
    // everytime it views the transaction history
    public boolean checkTransactionHistory(String accountNum, int currentAmount) throws IOException{
        String command = "CheckTransUpdate - " + accountNum + "," + currentAmount;
        String message = simpleCommand(command);
        if ("true".equals(message)) {
            return true;
        }
        return false;
    }

    // Get all the user's transaction history from the server
    public ArrayList<String> getTransactionHistory(String accountNum) throws IOException{
        String command = "TransactionHistory - " + accountNum;
        return arrayReturnCommand(command);
    }
    public ArrayList<String> getAdminTransactionHistory(String listOfAccounts) throws IOException{
        String command = "AdminTransactionHistory - " + listOfAccounts;
        return arrayReturnCommand(command);
    }

    // Get all the accounts from the server
    public ArrayList<String> getAllAccounts() throws IOException{
        String command = "AllAccounts - " + "None";
        return arrayReturnCommand(command);
    }
}
