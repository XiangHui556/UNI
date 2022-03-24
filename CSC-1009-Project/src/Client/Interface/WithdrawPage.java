package Client.Interface;

import java.io.IOException;
import java.util.InputMismatchException;

public class WithdrawPage extends HomePage{
    public static void withdrawPage() throws IOException, InterruptedException {
        ConsoleClear.clear();
        System.out.println(title2);
        System.out.print("\n1.Withdraw\n2.Back\nPlease Enter Your Choice: ");
        boolean selection = false;
        while(!selection){ //while loop in case of wrong choices and switch for choices
            String input =  sc.nextLine().toLowerCase();
            try{
                int intInput = Integer.parseInt(input);
                switch(intInput){
                    case 1:
                        selection = true;
                        //System.out.println("You choose Withdraw");
                        withdraw();
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
                    case "deposit":
                        selection = true;
                        //System.out.println("You choose Withdraw");
                        withdraw();
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
            } catch (IllegalArgumentException | IllegalStateException e) {
                System.out.print("Invalid Choice Please Choose Again: ");
            }
        }
    }

    public static void withdraw(){
        ConsoleClear.clear();
        System.out.println(title2);
        System.out.print("\nHow much do you want to withdraw(back to return)?: ");
        boolean selection = false;
        while(!selection) { //while loop in case of wrong choices and switch for choices
            try {
                String inputAmount = sc.nextLine();
                if (inputAmount.equalsIgnoreCase("back")) {
                    selection = true;
                    withdrawPage();
                }
                else {
                    double amount = Double.parseDouble(inputAmount);
                    if(amount <= 0){
                        throw new IllegalArgumentException("You have to withdraw more than 1"+currencyType);
                    }
                    else if(amount > aBalance){
                        throw new InsufficientFundsException(amount, aBalance, currencyType);
                    }
                    else{
                        System.out.print("Add a Memo (Press Enter If Non): ");
                        String memo = sc.nextLine();
                        account.depositOrWithdraw(amount, memo, false);
                        selection = true;

                        //Update balance in title
                        aBalance = account.getAvailableBalance();
                        cBalance = account.getCurrentBalance();
                        title2 = TITLE + "\nCurrently accessing (" + accountType + "): " + accountNum + "\nAvailable Balance: " + ConsoleColors.YELLOW_UNDERLINED + formatter.format(aBalance) + currencyType + ConsoleColors.RESET + "\nCurrent Balance: " + ConsoleColors.YELLOW_UNDERLINED + formatter.format(cBalance) + currencyType + ConsoleColors.RESET;
                        withdrawPage();
                    }
                }
            } catch (IllegalArgumentException | InputMismatchException | IOException | InterruptedException | InsufficientFundsException e) {
                System.out.println("Error: " + e.getMessage());
                System.out.print("Please try again: ");
            }
        }
    }
}
