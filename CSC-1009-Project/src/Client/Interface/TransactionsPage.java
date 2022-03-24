package Client.Interface;

import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Objects;

public class TransactionsPage extends HomePage{
    private static ArrayList<String> history;
    private static int arrSize = 0;
    private static final int numbersPerPage = 10;

    public void transactionsPage(int page) throws IOException {
        ConsoleClear.clear();
        try {
            if(arrSize <= 0){
                history = server.getTransactionHistory(accountNum);
            }
            else{
                if(server.checkTransactionHistory(accountNum, arrSize)){
                    //Do nothing
                }
                else{
                    history = server.getTransactionHistory(accountNum);
                }
            }
            arrSize = history.size();
            System.out.println(title2);
            int pages = (arrSize/numbersPerPage);
            int test = arrSize%numbersPerPage;
            if(test != 0){
                pages += 1;
            }
            int displayNum = page * numbersPerPage;
            CreateTable table = new CreateTable();
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
            table.setHeaders(false);
            table.print();
            System.out.println("---" + (page+1) + "/" + pages + "---");
            System.out.print("\nEnter a page number or 0 to go back: ");

            boolean selection = false;
                while(!selection){
                    try{
                        String strChoice = sc.nextLine();
                        if(Objects.equals(strChoice, "back")){
                            selection = true;
                            home();
                        }
                        else{
                            int choice = Integer.parseInt(strChoice);
                            if(choice <= 0){
                                selection = true;
                                home();
                            }
                            else if(choice == page+1){
                                throw new IllegalArgumentException("Already on page " + page+1);
                            }
                            else if(choice <= pages){
                                selection = true;
                                transactionsPage(choice - 1);
                            }
                            else{
                                throw new IllegalArgumentException("Invalid Page Number");
                            }
                        }
                    } catch (IllegalArgumentException e){
                        System.out.println("Error: " + e.getMessage());
                        System.out.print("Please try again: ");
                    }
                }
        } catch (InputMismatchException | IndexOutOfBoundsException | InterruptedException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
