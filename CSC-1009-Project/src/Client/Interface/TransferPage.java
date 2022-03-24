package Client.Interface;

import java.io.IOException;
import java.util.InputMismatchException;

public class TransferPage extends HomePage{
    public static void transferPage() throws IOException, InterruptedException {
        ConsoleClear.clear();
        transLimit = account.getCurrentLimit();
        System.out.println(title2);
        System.out.print("\n1.Transfer\n2.Back\nPlease Enter Your Choice: ");
        boolean selection = false;
        while(!selection){ //while loop in case of wrong choices and switch for choices
            String input =  sc.nextLine().toLowerCase();
            try{
                int intInput = Integer.parseInt(input);
                switch(intInput){
                    case 1:
                        selection = true;
                        //System.out.println("You choose transfer");
                        transfer();
                        break;

                    case 2:
                        selection = true;
                        //System.out.println("You choose Back");
                        home();
                        break;

                    default:
                        System.out.print("Invalid Choice Please Choose Again: ");
                        break;
                }
            }
            catch(NumberFormatException e){
                switch(input.toLowerCase()){
                    case "transfer":
                        selection = true;
                        //System.out.println("You choose transfer");
                        transfer();
                        break;

                    case "back":
                        selection = true;
                        //System.out.println("You choose Back");
                        home();
                        break;

                    default:
                        System.out.print("Invalid Choice Please Choose Again: ");
                        break;
                }
            } catch (IllegalArgumentException | IllegalStateException | IOException | InterruptedException | NoClassDefFoundError e) {
                System.out.print("Invalid Choice Please Choose Again: ");
            }
        }
    }


    public static void transfer() {
        ConsoleClear.clear();
        System.out.println(title2);
        System.out.print("\nEnter the account number you want to transfer funds to: ");
        boolean selection = false;
        while (!selection) { //while loop in case of wrong choices and switch for choices
            try {
                String toAccount = sc.nextLine();
                if (toAccount.equalsIgnoreCase("back")) {
                    selection = true;
                    transferPage();
                }
                String toAccountCurrencyType = server.checkValidAccountNumber(toAccount);
                if (!toAccountCurrencyType.isEmpty()) {
                    boolean selection2 = false;
                    if(currencyType.toLowerCase() != toAccountCurrencyType.toLowerCase()){
                        System.out.println("\n"+ ConsoleColors.RED + "Notice!" + ConsoleColors.RESET + " Different Currency Type");
                        System.out.print(currencyType.toUpperCase() + ":" + toAccountCurrencyType.toUpperCase() + "\n" + server.getExchangeRates(currencyType.toLowerCase(), toAccountCurrencyType.toLowerCase()) +"\n(Type back to return to previous page)");
                    }
                    System.out.print("\nHow much do you want to Transfer?: ");
                    while (!selection2) {
                        String inputAmount = sc.nextLine();
                        if (inputAmount.equalsIgnoreCase("back")) {
                            selection = true;
                            selection2 = true;
                            transferPage();
                        } else {
                            double amount = Double.parseDouble(inputAmount);
                            try {
                                if (amount <= 0) {
                                    throw new IllegalArgumentException("You have to transfer more than 1" + currencyType);
                                }
                                else if(amount > transLimit){
                                    throw new IllegalArgumentException("Your cannot transfer more than your daily limit!");
                                }
                                else if (amount > aBalance) {
                                    throw new InsufficientFundsException(amount, aBalance, currencyType);
                                } else {
                                    System.out.print("Add a Memo (Press Enter If Non): ");
                                    String memo = sc.nextLine();
                                    account.transferMoney(amount, memo, toAccount, toAccountCurrencyType);
                                    selection = true;
                                    selection2 = true;

                                    //Update balance in title and transfer limit
                                    aBalance = account.getAvailableBalance();
                                    cBalance = account.getCurrentBalance();
                                    title2 = TITLE + "\nCurrently accessing (" + accountType + "): " + accountNum + "\nAvailable Balance: " + ConsoleColors.YELLOW_UNDERLINED + formatter.format(aBalance) + currencyType + ConsoleColors.RESET + "\nCurrent Balance: " + ConsoleColors.YELLOW_UNDERLINED + formatter.format(cBalance) + currencyType + ConsoleColors.RESET;
                                    transferPage();
                                }
                            } catch (IllegalArgumentException e) {
                                System.out.println("Error: " + e.getMessage());
                                System.out.print("Please try again('back' to go back): ");
                            }
                        }
                    }
                } else {
                    throw new IllegalArgumentException("No Such Account Number Found!");
                }
            } catch (IllegalArgumentException | InputMismatchException | IOException | InterruptedException | InsufficientFundsException e) {
                System.out.println("Error: " + e.getMessage());
                System.out.print("Please try again('back' to go back): ");
            }
        }
    }
}
