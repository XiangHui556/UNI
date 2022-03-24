package Client.Interface;

import Client.ServerComms.ClientToServerCommands;

import java.io.Console;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Scanner;
import java.util.*;

import static Client.Interface.RegistrationPage.CreationPage;
import static Client.Interface.LoginPage.login;

public interface Startup {
    Date dateAndTime = new Date();
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/YYYY");
    String dateString =sdf.format(dateAndTime);
    String TITLE = ConsoleColors.RED_UNDERLINED + "PTSD" + ConsoleColors.RESET +" Banking System " + dateString;
    ClientToServerCommands server = new ClientToServerCommands();
    Scanner sc = new Scanner(System.in);
    Console console = System.console();

    static void menu() throws IOException, InterruptedException {
        ConsoleClear.clear();
        System.out.print (TITLE + "\n\n1.Login\n2.Register\n3.Exit\n\nWelcome what do you want to?: ");
        boolean selection = false;
        while(!selection){
            String input =  sc.nextLine().toLowerCase();
            try{
                int intInput = Integer.parseInt(input);
                switch(intInput){
                    case 1:
                        selection = true;
                        //System.out.println("You choose login");
                        login();
                        break;

                    case 2:
                        selection = true;
                        //System.out.println("You choose register");
                        registerPage();
                        break;

                    case 3:
                        selection = true;
                        System.out.println("Exiting program");
                        sc.close();
                        break;

                    default:
                        System.out.print(ConsoleColors.RED + "Invalid Choice " + ConsoleColors.RESET + " Please Choose Again: ");
                        break;
                }
            }
            catch(NumberFormatException | IOException e){
                switch(input.toLowerCase()){
                    case "login":
                        selection = true;
                        //System.out.println("You choose login");
                        login();
                        break;

                    case "register":
                        selection = true;
                        //System.out.println("You choose register");
                        registerPage();
                        break;

                    case "exit":
                        selection = true;
                        System.out.println("Exiting program");
                        sc.close();
                        break;

                    default:
                        System.out.print(ConsoleColors.RED + "Invalid Choice " + ConsoleColors.RESET + " Please Choose Again: ");
                        break;
                }
            } catch (InterruptedException | IllegalArgumentException | IllegalStateException e) {
                e.printStackTrace();
            }
        }
    }

    static void registerPage() throws IOException, InterruptedException {
        ConsoleClear.clear();
        System.out.print (TITLE + "\n\n1.Savings Account\n2.Current Account\n3.Corporate/Business Account\n4.Back\n\nWhat Account do you want to create?: ");
        boolean selection = false;
        while(!selection){ //while loop in case of wrong choices and switch for choices
            String input =  sc.nextLine().toLowerCase();
            try{
                int intInput = Integer.parseInt(input);
                switch(intInput){
                    case 1:
                        selection = true;
                        //System.out.println("You choose Savings");
                        CreationPage(1);
                        break;

                    case 2:
                        selection = true;
                        //System.out.println("You choose Current");
                        CreationPage(2);
                        break;

                    case 3:
                        selection = true;
                        //System.out.println("You choose Corporate");
                        CreationPage(3);
                        break;

                    case 4:
                        selection = true;
                        //System.out.println("Returning To Menu");
                        menu();
                        break;

                    default:
                        System.out.print(ConsoleColors.RED + "Invalid Choice " + ConsoleColors.RESET + " Please Choose Again: ");
                        break;
                }
            }
            catch(NumberFormatException e){
                switch(input.toLowerCase()){
                    case "savings":
                        selection = true;
                        //System.out.println("You choose Savings");
                        CreationPage(1);
                        break;

                    case "current":
                        selection = true;
                        //System.out.println("You choose Current");
                        CreationPage(2);
                        break;

                    case "corporate":

                    case "business":
                        selection = true;
                        //System.out.println("You choose Corporate");
                        CreationPage(3);
                        break;
                    //System.out.println("You choose Business");

                    case "back":
                        selection = true;
                        //System.out.println("Returning To Menu");
                        menu();
                        break;

                    default:
                        System.out.print(ConsoleColors.RED + "Invalid Choice " + ConsoleColors.RESET + " Please Choose Again: ");
                        break;
                }
            } catch (InterruptedException | IllegalArgumentException e) {
                e.printStackTrace();
            }
        }
    }
}
