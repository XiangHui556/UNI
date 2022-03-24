package Client.Interface;

import java.io.Console;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static Client.Interface.AdminPage.adminPage;

public class SettingsPage extends HomePage{
    public static void settingsPage() throws IOException {
        ConsoleClear.clear();
        System.out.println(title2);
        if(isAdmin){
            System.out.print("\n1.Change PIN\n2.Change Account ID\n3.Change Email\n4.Back\nPlease Enter Your Choice: ");
        }
        else{
            System.out.print("\n1.Change Transfer Limit("+ formatter.format(transLimit) + currencyType +")\n2.Change PIN\n3.Change Account ID\n4.Change Email\n5.Back\nPlease Enter Your Choice: ");
        }
        boolean selection = false;
        while(!selection){ //while loop in case of wrong choices and switch for choices
            String input =  sc.nextLine();
            try{
                int intInput = Integer.parseInt(input);
                if(isAdmin){
                    intInput += 1;
                }
                switch(intInput){
                    case 1:
                        selection = true;
                        //System.out.println("You choose Change Transfer Limit");
                        transferLimit();
                        break;

                    case 2:
                        selection = true;
                        //System.out.println("You choose Change PIN Number");
                        pin();
                        break;

                    case 3:
                        selection = true;
                        //System.out.println("You choose Change Account ID");
                        id();
                        break;

                    case 4:
                        selection = true;
                        //System.out.println("You choose Change Email");
                        email();
                        break;

                    case 5:
                        if(isAdmin){
                            selection = true;
                            //System.out.println("You choose Back");
                            adminPage();
                        }
                        else{
                            selection = true;
                            //System.out.println("You choose Back");
                            home();
                        }
                        break;

                    default:
                        System.out.print("Invalid Choice Please Choose Again: ");
                        break;
                }
            } catch (IllegalArgumentException | IllegalStateException | IOException | NoSuchElementException | InterruptedException e) {
                System.out.print("Invalid Choice Please Choose Again: ");
            }
        }
    }

    public static void transferLimit(){
        ConsoleClear.clear();
        System.out.println(TITLE);
        System.out.println("Your Account's Maximum Transfer Limit: " + formatter.format(maxLimit) + currencyType);
        System.out.println("Your Account's Current Transfer Limit: " + formatter.format(transLimit) + currencyType);
        System.out.print("\nDo you want to change your transfer limit?(Y/N): ");
        boolean selection = false;
        while(!selection){
            try{
                String choice = sc.nextLine().toLowerCase();
                if(choice.equals("y")){
                    System.out.print("\nEnter new transfer limit: ");
                    boolean selection2 = false;
                    while(!selection2){
                        try{
                            String strAmount = sc.nextLine();
                            int amount = Integer.parseInt(strAmount);
                            if(amount > maxLimit){
                                throw new IllegalArgumentException("Amount higher than maximum limit!");
                            }
                            else if(amount <= 0){
                                throw new IllegalArgumentException("Amount cannot be 0 or lower!");
                            }
                            else{
                                if(account.changeTransferLimit(amount)){
                                    selection = true;
                                    selection2 = true;
                                    System.out.println("New Transfer Limit Set: " + amount + currencyType);
                                    System.out.println("Redirecting back to Settings Page");
                                    TimeUnit.SECONDS.sleep(1);
                                    settingsPage();
                                }
                                else{
                                    selection = true;
                                    selection2 = true;
                                    System.out.println("Something Went Wrong!");
                                    System.out.println("Redirecting back to Settings Page");
                                    TimeUnit.SECONDS.sleep(1);
                                    settingsPage();
                                }
                            }
                        } catch (IllegalArgumentException | IOException e){
                            System.out.println("Error: " + e.getMessage());
                            System.out.println("Please Enter A Valid Amount: ");
                        }
                    }
                }
                else if(choice.equals("n")){
                    selection = true;
                    System.out.println("Redirecting back to Settings Page");
                    TimeUnit.SECONDS.sleep(1);
                    settingsPage();
                }
                else{
                    throw new IllegalArgumentException();
                }
            } catch (IllegalArgumentException | InterruptedException |IOException e){
                System.out.println("Invalid Choice Please Try Again: ");
            }
        }
    }

    public static void pin(){
        ConsoleClear.clear();
        System.out.println(TITLE);
        System.out.print("\nDo you want to change your PIN?(Y/N): ");
        boolean selection3 = false;
        while(!selection3){
            try{
                String choice = sc.nextLine().toLowerCase();
                if(choice.equals("y")){
                    if(askForOldPIN()){
                        System.out.print("\nPlease Enter Your New PIN: ");
                        Console console = System.console();
                        boolean selection = false;
                        boolean selection2 = false;
                        while(!selection){
                            char[] input = console.readPassword();
                            try{
                                int intInput = Integer.parseInt(String.valueOf(input)); //used to test if the PIN is numbers if not throw exception
                                if (input.length != 6) {
                                    throw new IllegalArgumentException();
                                }
                                else {
                                    selection = true;
                                    System.out.print("Please Enter the same PIN again: ");
                                    while (!selection2) {
                                        char[] input2 = console.readPassword();
                                        if (String.valueOf(input2).equals(String.valueOf(input))) {
                                            if(account.changePIN(String.valueOf(input), accounts)){
                                                System.out.println("New PIN Set!");
                                                selection2 = true;
                                                selection3 = true;
                                                System.out.println("Redirecting back to Settings Page");
                                                TimeUnit.SECONDS.sleep(1);
                                                settingsPage();
                                            }
                                            else{
                                                selection2 = true;
                                                selection3 = true;
                                                System.out.println("Something Went Wrong!");
                                                System.out.println("Redirecting back to Settings Page");
                                                TimeUnit.SECONDS.sleep(1);
                                                settingsPage();
                                            }
                                        } else {
                                            System.out.print("PIN is not the same Please Try again: ");
                                        }
                                    }
                                }
                            } catch(IllegalArgumentException e) {
                                System.out.print("Input is Not a 6-Digit Number Please Try again: ");
                            }
                        }
                    }
                    else{
                        selection3 = true;
                        System.out.println("Redirecting back to Settings Page");
                        TimeUnit.SECONDS.sleep(1);
                        settingsPage();
                    }
                }
                else if(choice.equals("n")){
                    selection3 = true;
                    System.out.println("Redirecting back to Settings Page");
                    TimeUnit.SECONDS.sleep(1);
                    settingsPage();
                }
                else{
                    throw new IllegalArgumentException();
                }
            } catch (IllegalArgumentException | InterruptedException |IOException e){
                System.out.println("Invalid Choice Please Try Again: ");
            }
        }
    }

    public static boolean askForOldPIN(){
        System.out.print("\nPlease Enter Your Old PIN: ");
        boolean selection = false;
        while(!selection){
            try{
                char[] input = console.readPassword();
                String pin = String.valueOf(input);
                if(account.checkValidPIN(pin)) {
                    selection = true;
                    return true;
                }
                else{
                    System.out.print("Wrong Pin Do you want to try again?(Y/N): ");
                    String choice =  sc.nextLine().toLowerCase();
                    if(choice.equalsIgnoreCase("y")){
                        System.out.print("\nPlease Enter Your Old PIN: ");
                    }
                    else{
                        return false;
                    }
                }

            } catch(IllegalArgumentException e) {
                System.out.print(e.getMessage() + " Please Try Again:");
            }
        }
        return false;
    }

    public static void id(){
        ConsoleClear.clear();
        System.out.println(TITLE);
        System.out.print("\nDo you want to change your Account ID?(Y/N): ");
        boolean selection = false;
        while(!selection){
            try{
                String choice = sc.nextLine().toLowerCase();
                if(choice.equals("y")) {
                    System.out.print("\nPlease Enter Your New Account ID: ");
                    boolean selection2 = false;
                    while (!selection2) {
                        try{
                            String newID = sc.nextLine();
                            if (Pattern.matches("[0-9a-zA-Z]+", newID)) {
                                if (server.checkUnique(newID)) {
                                    if (account.changeID(newID, accounts)) {
                                        System.out.println("ID changed to: " + newID);
                                        selection = true;
                                        selection2 = true;
                                        System.out.println("Redirecting back to Settings Page");
                                        TimeUnit.SECONDS.sleep(1);
                                        settingsPage();
                                    } else {
                                        selection = true;
                                        selection2 = true;
                                        System.out.println("Something Went Wrong!");
                                        System.out.println("Redirecting back to Settings Page");
                                        TimeUnit.SECONDS.sleep(1);
                                        settingsPage();
                                    }
                                } else {
                                    throw new IllegalArgumentException("ID is already taken");
                                }
                            } else {
                                throw new IllegalArgumentException("Special Character Detected");
                            }
                        }catch (IllegalArgumentException e){
                            System.out.print(e.getMessage() + " Please Try Again: ");
                        }
                    }
                }
                else if(choice.equals("n")){
                    selection = true;
                    System.out.println("Redirecting back to Settings Page");
                    TimeUnit.SECONDS.sleep(1);
                    settingsPage();
                }
                else{
                    throw new IllegalArgumentException();
                }
            } catch (IllegalArgumentException | InterruptedException | IOException e){
                System.out.print(e.getMessage() + " Please Try Again: ");
            }
        }
    }

    public static void email(){
        ConsoleClear.clear();
        System.out.println(TITLE);
        System.out.print("\nDo you want to change your Email?(Y/N): ");
        boolean selection = false;
        while(!selection){
            try{
                String choice = sc.nextLine().toLowerCase();
                if(choice.equals("y")){
                    if(account.emailAuthentication(sc)){
                        System.out.print("\nPlease Enter Your New Email: ");
                        boolean selection2 = false;
                        Pattern p = Pattern.compile(".+@.+\\.[a-z]+");
                        boolean matchFound;
                        while(!selection2){
                            try{
                                String newEmail = sc.nextLine();
                                Matcher m = p.matcher(newEmail);
                                matchFound = m.matches();
                                if (matchFound) {
                                    if(account.changeEmail(newEmail)){
                                        selection = true;
                                        selection2 = true;
                                        System.out.println("Email changed to: " + newEmail);
                                        System.out.println("Redirecting back to Settings Page");
                                        TimeUnit.SECONDS.sleep(1);
                                        settingsPage();
                                    }
                                    else{
                                        selection = true;
                                        selection2 = true;
                                        System.out.println("Something Went Wrong!");
                                        System.out.println("Redirecting back to Settings Page");
                                        TimeUnit.SECONDS.sleep(1);
                                        settingsPage();
                                    }
                                }
                                else{
                                    throw new IllegalArgumentException();
                                }
                            } catch(IllegalArgumentException e) {
                                System.out.print("Invalid Email Format Please Try Again: ");
                            }
                        }
                    }
                    else{
                        selection = true;
                        System.out.println("Email Authentication Failed");
                        System.out.println("Redirecting back to Settings Page");
                        TimeUnit.SECONDS.sleep(1);
                        settingsPage();
                    }
                }
                else if(choice.equals("n")){
                    selection = true;
                    System.out.println("Redirecting back to Settings Page");
                    TimeUnit.SECONDS.sleep(1);
                    settingsPage();
                }
                else{
                    throw new IllegalArgumentException();
                }
            } catch (IllegalArgumentException | InterruptedException | IOException e){
                System.out.println(e.getMessage() + " Please Try Again");
            } catch (NoSuchElementException e){}
        }

    }

}
