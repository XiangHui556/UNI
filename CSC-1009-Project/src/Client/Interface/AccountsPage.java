package Client.Interface;

import Client.User.Admin;
import Client.User.Customer;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;

import static Client.Interface.AdminPage.adminPage;
import static Client.Interface.HomePage.home;

public class AccountsPage extends LoginPage{
    static DecimalFormat formatter = new DecimalFormat("#,###.00 ");
    protected static ArrayList<Customer> accounts;
    protected static ArrayList<Admin> adminAccounts;
    protected static boolean checkMultiple;
    protected static int multiAccount;
    protected static boolean isAdmin;
    public static String title2;

    public static void accountPage() throws IOException {
        ConsoleClear.clear();
        System.out.println(TITLE + "\n" + ConsoleColors.GREEN_BOLD + "Account Selection:" + ConsoleColors.RESET);
        short counter = 0;
        if(adminAccounts.size() != 0){
            for(Admin adminAccount: adminAccounts){
                counter += 1;
                System.out.println(counter + "." + adminAccount.getAccountNumber() + " (" + adminAccount.getAccountType() + ")");
            }
        }
        if(accounts.size() != 0){
            for(Customer account: accounts){
                counter += 1;
                System.out.println(counter + "." + account.getAccountNumber() + " (" + account.getAccountType() + ")");
            }
        }
        System.out.println("\nHello, " + ConsoleColors.GREEN_BOLD + accounts.get(0).getAccountID() + ConsoleColors.RESET + "!");
        System.out.print("Please select the account you want to use: ");
        boolean selection = false;
        while(!selection){
            String input =  sc.nextLine();
            try{
                int intInput = Integer.parseInt(input);
                if(intInput > accounts.size() + adminAccounts.size()){
                    throw new IllegalArgumentException();
                }
                else{
                    selection = true;
                    if(intInput > adminAccounts.size()){
                        checkMultiple = true;
                        multiAccount = intInput - 1 - adminAccounts.size();
                        isAdmin = false;
                        home();
                    }
                    else{
                        checkMultiple = true;
                        multiAccount = intInput-1;
                        isAdmin = true;
                        adminPage();
                    }
                }
            } catch (IllegalArgumentException | IOException | InterruptedException e) {
               System.out.print("Invalid Input Please Try Again: ");
            }
        }
    }

    public static void initAccountPage(ArrayList<Customer> accounts2, ArrayList<Admin> admins) throws IOException, InterruptedException {
        accounts = accounts2;
        adminAccounts = admins;
        if(accounts.size() == 1){
            checkMultiple = false;
            multiAccount = 0;
            isAdmin = false;
            home();
        }
        else if(adminAccounts.size() == 1){
            checkMultiple = false;
            multiAccount = 0;
            isAdmin = true;
            adminPage();
        }
        else{
            accountPage();
        }
    }

}
