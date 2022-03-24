package Client.User;

import Client.ServerComms.ClientToServerCommands;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class Admin extends AbstractUsers {
// No way to set up first admin accounts other than adding them manually to accounts.csv

    @Override
    public void setAccount(String[] data){ // set all the account info for the admin
        setAccountHolderName(data[3]);
        setAccountNumber(data[0]);
        setAccountEmail(data[4]);
        setAccountType(data[6]);
        setAccountPIN(data[2]);
        setAccountID(data[1]);
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
