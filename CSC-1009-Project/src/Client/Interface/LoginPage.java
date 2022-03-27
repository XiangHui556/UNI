package Client.Interface;

import Client.User.Admin;
import Client.User.Customer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import static Client.Interface.AccountsPage.initAccountPage;

public class LoginPage implements Startup {
    public static void login() throws IOException, InterruptedException {
        ConsoleClear.clear();
        System.out.println(TITLE);
        ArrayList<String> datas = loginCheck();
        ArrayList<Customer> accounts = new ArrayList<Customer>();
        ArrayList<Admin> admins = new ArrayList<Admin>();
        if(datas != null){
            for(String data: datas){
                String[] arrData = data.split(",");
                if(Objects.equals(arrData[6], "Admin")){
                    Admin adminAccount = new Admin();
                    adminAccount.setAccount(arrData);
                    admins.add(adminAccount);
                }
                else{
                    Customer account = new Customer();
                    account.setAccount(arrData);
                    accounts.add(account);
                }
            }
            initAccountPage(accounts, admins);
        }
        else{
            ConsoleClear.clear();
            System.out.println(TITLE);
            System.out.println("Login Failed!\nGoing back to menu in 5 seconds!");
            TimeUnit.SECONDS.sleep(5);
            Startup.menu();
        }
    }


    public static ArrayList<String> loginCheck(){
        boolean selection = false;
        while(!selection){ //while loop in case of wrong choices
            try{
                ConsoleClear.clear();
                System.out.println(TITLE);
                System.out.print("\nPlease Enter Your Account ID: ");
                String id =  sc.nextLine();
                System.out.print("\nPlease Enter Your PIN number: ");
                char[] input = console.readPassword();
                String pin = String.valueOf(input);
                ArrayList<String> data = server.checkLoginDetails(id, pin);
                if(!data.isEmpty()) {
                    selection = true;
                    System.out.println("Welcome " + id + " you have successfully logged in");
                    return data;
                }
                else{
                    System.out.print("Your ID/PIN is wrong or your Account does not exist\nDo you want to try again?(Y/N): ");
                    String choice =  sc.nextLine().toLowerCase();
                    if(choice.equalsIgnoreCase("y")){
                        //Do nothing
                    }
                    else{
                        return null;
                    }
                }
            } catch(IllegalArgumentException e) {
                System.out.print(e.getMessage() + " Please Try Again:");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
