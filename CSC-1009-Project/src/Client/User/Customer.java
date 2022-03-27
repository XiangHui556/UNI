package Client.User;

import Client.ServerComms.ClientToServer;
import Client.ServerComms.ClientToServerCommands;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Customer extends AbstractUsers {
    private Date dateAndTime;
    private static final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/YYYY hh:mm:ss");

    public void createNewServerAccount() throws IOException { //Used to communicate with the server to create a new account for the customer
        try{
            ClientToServer client = new ClientToServer();
            client.connectToServer();
            String toSend = String.join(",", getAccountID(), getAccountPIN(), getAccountHolderName(), getAccountEmail(), getAccountCurrency(), getAccountType()); // data the server needs
            client.sendToServer("CreateNewAccount - " + toSend); // Command that will be sent to the server along with the data needed for the account creation
            String message = client.getFromServer(); // Server will return a message if the creation is successful
            if(message == null){
                throw new IllegalArgumentException("Failed to create the account(Might be server side)");
            }
            else{
                System.out.println(message);
            }
            client.disconnectFromServer();
        }
        catch (IllegalArgumentException e){
            System.out.println("Error: " + e.getMessage());
        }
    }

    @Override
    public void setAccount(String[] data){ // set all the account info for the customer
        setAccountMaxLimit(Integer.parseInt(data[7]));
        setAvailableBalance(Double.parseDouble(data[10]));
        setCurrentBalance(Double.parseDouble(data[9]));
        setAccountLimit(Integer.parseInt(data[8]));
        setCurrentLimit(Integer.parseInt(data[8]));
        setAccountHolderName(data[3]);
        setAccountCurrency(data[5]);
        setAccountNumber(data[0]);
        setAccountEmail(data[4]);
        setAccountType(data[6]);
        setAccountPIN(data[2]);
        setAccountID(data[1]);
    }

    public void depositOrWithdraw(Double amount, String memo, boolean type) throws IOException, InterruptedException { // used to deposit or withdraw for the customer
        dateAndTime = new Date();
        String dateString =sdf.format(dateAndTime);
        String accountNum = getAccountNumber();
        String curr = getAccountCurrency();
        boolean success;
        if(memo.isEmpty()){
            memo = "-";
        }
        if(type){
            success = comms.serverDeposit(amount, accountNum, dateString, curr, memo);
        }
        else{
            success = comms.serverWithdraw(amount, accountNum, dateString, curr, memo);
        }
        if(success){
            if(type){
                setAvailableBalance(getAvailableBalance()+amount);
                setCurrentBalance(getCurrentBalance()+amount);
                System.out.println("You have successfully deposited " + amount + curr + " to " + accountNum);
            }
            else{
                setAvailableBalance(getAvailableBalance()-amount);
                setCurrentBalance(getCurrentBalance()-amount);
                System.out.println("You have successfully withdrew " + amount + curr + " from " + accountNum);
            }
            System.out.println("You will be redirected in 2 seconds!");
            TimeUnit.SECONDS.sleep(2);

        }
        else{
            System.out.println("Oops! Something went wrong, Please try again!");
            TimeUnit.SECONDS.sleep(1);
        }
    }



    public void transferMoney(Double amount, String memo, String toAccount, String toCurr) throws IOException, InterruptedException { // used to deposit or withdraw for the customer
        dateAndTime = new Date();
        String dateString =sdf.format(dateAndTime);
        String accountNum = getAccountNumber();
        String curr = getAccountCurrency();
        boolean success;
        if(memo.isEmpty()){
            memo = "-";
        }
        success = comms.serverTransfer(amount, accountNum, dateString, curr, memo, toAccount, toCurr);
        if(success){
            setAvailableBalance(getAvailableBalance() - amount);
            setCurrentBalance(getCurrentBalance() - amount);
            setCurrentLimit(getCurrentLimit() - amount);
            System.out.println("You have successfully transferred " + amount + curr + " to " + toAccount + "(" + toCurr + ")");
            System.out.println("You will be redirected in 2 seconds!");
            TimeUnit.SECONDS.sleep(2);

        }
        else{
            System.out.println("Oops! Something went wrong, Please try again!");
            TimeUnit.SECONDS.sleep(1);
        }
    }

    public boolean changeTransferLimit(int newAmount) throws IOException {
        if(comms.changeLimit(getAccountNumber(), newAmount)){
            double amountUsed = getAccountLimit() - getCurrentLimit();
            setCurrentLimit(newAmount - amountUsed);
            setAccountLimit(newAmount);
            return true;
        }
        else{
            return false;
        }
    }

    @Override
    public boolean checkValidPIN(String pin){
        return Objects.equals(pin, getAccountPIN());
    }

    @Override
    public boolean changePIN(String newPIN, ArrayList<Customer> accounts) throws IOException {
        if(comms.changePIN(getAccountID(), newPIN)){
            for(Customer account: accounts){
                account.setAccountPIN(newPIN);
            }
            return true;
        }
        else{
            return false;
        }
    }

    @Override
    public boolean changeID(String newID, ArrayList<Customer> accounts) throws IOException {
        if(comms.changeID(getAccountID(), newID)){
            for(Customer account: accounts){
                account.setAccountID(newID);
            }
            return true;
        }
        else{
            return false;
        }
    }

    @Override
    public boolean emailAuthentication(Scanner sc){
        Authentication auth = new Authentication();
        String authCode = auth.getAuthentication();
        String[] mail = getAccountEmail().split("@");
        String first5Chars = mail[0].substring(0, 5);
        String backOfEmail = mail[mail.length - 1];
        System.out.println("An authentication code was sent to your email: " + first5Chars + "**********@" + backOfEmail);
        System.out.println(authCode);
        System.out.print("\nPlease enter the authentication code: ");
        String code = sc.nextLine();
        if(Objects.equals(code, authCode)){
            return true;
        }
        else{
            return false;
        }
    }

    @Override
    public boolean changeEmail(String newEmail) throws IOException {
        if(comms.changeEmail(getAccountNumber(), newEmail)){
            setAccountEmail(newEmail);
            return true;
        }
        else{
            return false;
        }
    }
}
