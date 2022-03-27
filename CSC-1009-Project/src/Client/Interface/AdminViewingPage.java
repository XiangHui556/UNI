package Client.Interface;

import Client.ServerComms.ClientToServerCommands;

import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Objects;

public class AdminViewingPage extends AdminPage{
    private static ArrayList<String> history;
    private static int arrSize = 0;
    private static final int numbersPerPage = 10;

    public static void adminViewPage(int page, boolean type, String input) throws IOException {
        ConsoleClear.clear();
        CreateTable table = new CreateTable();
        try {
            if(type){
                history = server.getAllAccounts();
                table.setHeaders(true);
            }
            else{
                history = server.getAdminTransactionHistory(input);
                table.setHeaders(false);
            }
            arrSize = history.size();
            System.out.println(title2);
            int pages = (arrSize/numbersPerPage);
            int test = arrSize%numbersPerPage;
            if(test != 0){
                pages += 1;
            }
            int displayNum = page * numbersPerPage;
            if(arrSize > (numbersPerPage + displayNum)){
                for(int i = arrSize - displayNum - 1; (arrSize - numbersPerPage - displayNum) <= i; i--){
                    table.addRow(history.get(i));
                }
            }
            else{
                for(int i = arrSize - displayNum - 1; 0 <= i ; i--){
                    table.addRow(history.get(i));
                }
            }
            table.print();
            System.out.println("---" + (page+1) + "/" + pages + "---");
            System.out.print("\nEnter a page number or 0 to go back: ");

            boolean selection = false;
            while(!selection){
                try{
                    String strChoice = sc.nextLine();
                    if(Objects.equals(strChoice, "back")){
                        selection = true;
                        adminPage();
                    }
                    else{
                        int choice = Integer.parseInt(strChoice);
                        if(choice <= 0){
                            selection = true;
                            adminPage();
                        }
                        else if(choice == page+1){
                            throw new IllegalArgumentException("Already on page " + page+1);
                        }
                        else if(choice <= pages){
                            selection = true;
                            adminViewPage(choice - 1, type, input);
                        }
                        else{
                            throw new IllegalArgumentException("Invalid Page Number");
                        }
                    }
                } catch (IllegalArgumentException | InterruptedException e){
                    System.out.println("Error: " + e.getMessage());
                    System.out.print("Please try again: ");
                }
            }
        } catch (InputMismatchException | IndexOutOfBoundsException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
