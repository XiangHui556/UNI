package Client.Interface;

import Client.User.Admin;

import java.io.IOException;

import static Client.Interface.AdminViewingPage.adminViewPage;
import static Client.Interface.SettingsPage.settingsPage;

public class AdminPage extends AccountsPage{
    public static String accountName;
    public static String accountType;
    public static String accountNum;
    public static Admin account;

    public static void adminPage() throws IOException, InterruptedException {
        ConsoleClear.clear();
        account = adminAccounts.get(multiAccount);
        accountType = account.getAccountType();
        accountNum = account.getAccountNumber();
        accountName = account.getAccountHolderName();
        title2 = TITLE + "\nCurrently accessing (" + accountType + "): " + accountNum;
        System.out.println(title2);
        System.out.println("\n1.View Accounts\n2.View Transactions\n3.Settings");
        if(checkMultiple){
            System.out.println("4.Back\n5.Sign Out");
        }
        else{
            System.out.println("4.Sign Out");
        }
        System.out.print("Welcome "+ ConsoleColors.GREEN_BOLD + accountName + ConsoleColors.RESET +" what do you want to?: ");
        boolean selection = false;
        while(!selection){ //while loop in case of wrong choices and switch for choices
            String input =  sc.nextLine();
            try{
                int intInput = Integer.parseInt(input);
                switch(intInput){
                    case 1:
                        selection = true;
                        //System.out.println("You choose View Accounts");
                        viewAccounts();
                        break;

                    case 2:
                        selection = true;
                        //System.out.println("You choose View Transactions");
                        viewTransactions();
                        break;

                    case 3:
                        selection = true;
                        //System.out.println("You choose Settings");
                        settingsPage();
                        break;

                    case 4:
                        if(checkMultiple){
                            selection = true;
                            //System.out.println("You choose Back");
                            accountPage();
                        }
                        else{
                            selection = true;
                            Startup.menu();
                        }
                        break;

                    case 5:
                        if(checkMultiple){
                            selection = true;
                            Startup.menu();
                        }
                        else{
                            throw new IllegalArgumentException();
                        }
                        break;

                    default:
                        System.out.print("Invalid Choice Please Choose Again: ");
                        break;
                }
            }
            catch(NumberFormatException e){
                switch(input.toLowerCase()){
                    case "accounts":
                    case "account":
                        selection = true;
                        //System.out.println("You choose View Accounts");
                        viewAccounts();
                        break;

                    case "transaction":
                    case "transactions":
                        selection = true;
                        //System.out.println("You choose View Transactions");
                        viewTransactions();
                        break;

                    case "setting":
                    case "settings":
                        selection = true;
                        //System.out.println("You choose Settings");
                        settingsPage();
                        break;

                    case "back":
                        if(checkMultiple){
                            selection = true;
                            //System.out.println("You choose Back");
                            accountPage();
                        }
                        else{
                            throw new IllegalArgumentException();
                        }
                        break;

                    case "out":
                    case "signout":
                    case "sign out":
                        selection = true;
                        Startup.menu();
                        break;

                    default:
                        System.out.print("Invalid Choice Please Choose Again: ");
                        break;
                }
            } catch (IllegalArgumentException | IllegalStateException | IOException | InterruptedException e) {
                System.out.print("Invalid Choice Please Choose Again: ");
            }
        }
    }

    public static void viewAccounts() throws IOException {
        adminViewPage(0, true, "all");
    }

    public static void viewTransactions() throws IOException {
        ConsoleClear.clear();
        System.out.println(title2);
        System.out.print("\n\nPlease Enter The Accounts You Want To View Separate accounts via ',' (All to view everything)\nExample: X-100000001,X-100000002,.....\n> ");
        boolean selection = false;
        while(!selection){
            try{
                String input = sc.nextLine();
                String[] inputArr = input.split(",",2);
                if(input.isEmpty()){
                    throw new IllegalArgumentException();
                }
                else if(inputArr[0].equalsIgnoreCase("all")){
                    selection = true;
                    adminViewPage(0,false,"all");
                }
                else{
                    selection = true;
                    adminViewPage(0,false, input);
                }
            } catch (IllegalArgumentException e){
                System.out.print("Invalid Input Please Try Again\n>");
            }
        }
    }
}
