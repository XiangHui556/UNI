package Client.Interface;

import Client.User.Customer;

import java.io.IOException;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegistrationPage implements Startup {
    public static void CreationPage(int type) throws IOException, InterruptedException {
        ConsoleClear.clear();
        Customer create = new Customer();
        switch(type){ //switch to display the title with the appropriate context
            case 1:
                System.out.println(TITLE + "\nSavings Account Creation");
                create.setAccountType("Savings");
                break;

            case 2:
                System.out.println(TITLE + "\nCurrent Account Creation");
                create.setAccountType("Current");
                break;

            case 3:
                System.out.println(TITLE + "\nCorporate/Business Account Creation");
                create.setAccountType("Business");
                break;

            default:
                System.out.println("Error\nReturning to Main Menu in 3 Seconds");
                TimeUnit.SECONDS.sleep(3);
                Startup.menu();
                break;
        }
        boolean check = idCondition(create);
        accountHolderName(create);
        if(!check){
            pinCondition(create);
        }
        currencyCondition(create);
        emailCondition(create);
        create.createNewServerAccount();
        System.out.println("You will be redirected to the Main Menu in 5 Seconds!");
        TimeUnit.SECONDS.sleep(5);
        Startup.menu();
    }

    public static void accountHolderName(Customer create){
        System.out.print("\nPlease Enter Your Full Name: ");
        boolean selection = false;
        while(!selection){
            try{
                String name =  sc.nextLine();
                if(Pattern.matches("[a-zA-Z ]+", name)) {
                    create.setAccountHolderName(name);
                    System.out.println("Name: " + name + " Saved");
                    selection = true;
                }
                else{
                    throw new IllegalArgumentException("Name Cannot Have Special Character or Number");
                }

            } catch(IllegalArgumentException e) {
                System.out.print(e.getMessage() + " Please Try Again:");
            }
        }
    }

    public static boolean askForPIN(String id, Customer create){
        System.out.print("\nPlease Enter Your PIN number: ");
        boolean selection = false;
        while(!selection){
            try{
                char[] input = console.readPassword();
                String pin = String.valueOf(input);
                boolean check = server.checkLogin(id, pin);
                if(check) {
                    create.setAccountID(id);
                    create.setAccountPIN(pin);
                    System.out.println("Welcome " + id + " you have successfully logged in");
                    selection = true;
                    return true;
                }
                else{
                    System.out.print("Wrong Pin Do you want to try again?(Y/N): ");
                    String choice =  sc.nextLine().toLowerCase();
                    if(choice.equalsIgnoreCase("y")){
                        System.out.print("\nPlease Enter Your PIN number: ");
                    }
                    else{
                        return false;
                    }
                }

            } catch(IllegalArgumentException e) {
                System.out.print(e.getMessage() + " Please Try Again:");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public static boolean idCondition(Customer create){
        System.out.print("\nPlease Enter Your AccountID: ");
        boolean selection = false;
        while(!selection){
            try{
                String id =  sc.nextLine();
                if(Pattern.matches("[0-9a-zA-Z]+", id)){
                    boolean check = server.checkUnique(id);
                    if(check){
                        create.setAccountID(id);
                        System.out.println("ID: " + id + " Saved");
                        selection = true;
                    }
                    else{
                        System.out.print("ID is already taken\nDo you want to use the same ID? (Y/N): ");
                        String choice =  sc.nextLine().toLowerCase();
                        if(choice.equalsIgnoreCase("y")){
                            if(askForPIN(id, create)){
                                return true;
                            }
                            else{
                                throw new IllegalArgumentException("Try another ID");
                            }
                        }
                        else{
                            throw new IllegalArgumentException("Try another ID");
                        }
                    }
                }
                else{
                    throw new IllegalArgumentException("Special Character Detected");
                }

            } catch(IllegalArgumentException | IOException e) {
                System.out.print(e.getMessage() + " Please Try Again: ");
            }
        }
        return false;
    }

    public static void emailCondition(Customer create){
        System.out.print("\nPlease Enter Your Email: ");
        boolean selection = false;
        Pattern p = Pattern.compile(".+@.+\\.[a-z]+");
        boolean matchFound;
        while(!selection){
            try{
                String email =  sc.nextLine();
                Matcher m = p.matcher(email);
                matchFound = m.matches();
                if (matchFound) {
                    create.setAccountEmail(email);
                    selection = true;
                }
                else{
                    throw new IllegalArgumentException();
                }
            } catch(IllegalArgumentException e) {
                System.out.print("Invalid Email Format Please Try Again: ");
            }
        }
    }

    public static void pinCondition(Customer create){
        System.out.print("\nPlease Enter A 6-Digit PIN Number: ");
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
                            create.setAccountPIN(String.valueOf(input));
                            System.out.println("PIN Set!");
                            selection2 = true;
                        } else {
                            System.out.print("PIN is not the same Please Try again: ");
                        }
                        //System.out.println(String.valueOf(input));
                        //System.out.println(String.valueOf(input2));
                    }
                }
            } catch(IllegalArgumentException e) {
                System.out.print("Input is Not a 6-Digit Number Please Try again: ");
            }
        }
    }

    public static void currencyCondition(Customer create){
        System.out.print("\nPlease Enter Your Account Currency Type(eg.SGD): ");
        boolean selection = false;
        while(!selection){
            try{
                String curr =  sc.nextLine().toLowerCase();
                    String currType = server.checkCurrency(curr);
                    if(!Objects.equals(currType, "")){
                        create.setAccountCurrency(curr);
                        System.out.println("Currency Type: " + currType + " Saved");
                        selection = true;
                    }
                    else{
                        throw new IllegalArgumentException("Invalid Currency Type");
                    }
            } catch(IllegalArgumentException e) {
                System.out.print(e.getMessage() + " Please Try Again:");
            } catch (IOException e) {
                //.printStackTrace();
            }
        }
    }
}
