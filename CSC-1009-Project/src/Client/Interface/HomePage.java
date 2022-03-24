package Client.Interface;

import Client.User.Customer;

import java.io.IOException;

import static Client.Interface.DepositPage.depositPage;
import static Client.Interface.SettingsPage.settingsPage;
import static Client.Interface.TransferPage.transferPage;
import static Client.Interface.WithdrawPage.withdrawPage;

public class HomePage extends AccountsPage{
    public static String currencyType;
    public static String accountType;
    public static String accountNum;
    public static String accountName;
    public static double aBalance;
    public static double cBalance;
    public static double transLimit;
    public static double maxLimit;
    public static Customer account;

    public static void home() throws IOException, InterruptedException {
        ConsoleClear.clear();
        account = accounts.get(multiAccount);
        accountType = account.getAccountType();
        accountNum = account.getAccountNumber();
        currencyType = account.getAccountCurrency().toUpperCase();
        aBalance = account.getAvailableBalance();
        cBalance = account.getCurrentBalance();
        transLimit = account.getAccountLimit();
        maxLimit = account.getAccountMaxLimit();
        accountName = account.getAccountHolderName();
        title2 = TITLE + "\nCurrently accessing (" + accountType + "): " + accountNum + "\nAvailable Balance: " + ConsoleColors.YELLOW_UNDERLINED + formatter.format(aBalance) + currencyType + ConsoleColors.RESET + "\nCurrent Balance: " + ConsoleColors.YELLOW_UNDERLINED + formatter.format(cBalance) + currencyType + ConsoleColors.RESET;
        System.out.println(title2);
        System.out.println("\n1.Deposit\n2.Withdraw\n3.Transfer\n4.View Transactions\n5.Settings");
        if(checkMultiple){
            System.out.println("6.Back\n7.Sign Out");
        }
        else{
            System.out.println("6.Sign Out");
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
                        //System.out.println("You choose Deposit");
                        depositPage();
                        break;

                    case 2:
                        selection = true;
                        //System.out.println("You choose Withdraw");
                        withdrawPage();
                        break;

                    case 3:
                        selection = true;
                        //System.out.println("You choose Transfer");
                        transferPage();
                        break;

                    case 4:
                        selection = true;
                        //System.out.println("You choose View Transactions");
                        TransactionsPage transP = new TransactionsPage();
                        transP.transactionsPage(0);
                        break;

                    case 5:
                        selection = true;
                        //System.out.println("You choose Settings");
                        settingsPage();
                        break;

                    case 6:
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

                    case 7:
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
                    case "deposit":
                        selection = true;
                        //System.out.println("You choose Deposit");
                        depositPage();
                        break;

                    case "withdraw":
                        selection = true;
                        //System.out.println("You choose Withdraw");
                        withdrawPage();
                        break;

                    case "transfer":
                        selection = true;
                        //System.out.println("You choose Transfer");
                        transferPage();
                        break;

                    case "view transactions":

                    case "transaction":

                    case "transactions":

                    case "view":
                        selection = true;
                        //System.out.println("You choose View Transactions");
                        TransactionsPage transP = new TransactionsPage();
                        transP.transactionsPage(0);
                        break;

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
                    case "sign out":
                    case "signout":
                        selection = true;
                        Startup.menu();
                        break;

                    default:
                        System.out.print("Invalid Choice Please Choose Again: ");
                        break;
                }
            } catch (IllegalArgumentException | IllegalStateException | IOException | NoClassDefFoundError e) {
                System.out.print("Invalid Choice Please Choose Again: ");
            }
        }
    }
}
