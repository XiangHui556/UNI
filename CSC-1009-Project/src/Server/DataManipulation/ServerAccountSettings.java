package Server.DataManipulation;

import javax.swing.*;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class ServerAccountSettings implements ServerCSV {
    private static final int DEFAULT_TRANSFER_LIMIT = 3000;
    private static final int MAX_TRANSFER_LIMIT = 200000;
    private static final int MAX_TRANSFER_LIMIT2 = 100000; // For conversion in cause the currency of the other country is too small

    public boolean loginCheck(String inData) throws IOException {
        try{
            String []data2 = inData.split(",");
            String id = data2[0];
            String pin = data2[1];
            BufferedReader csvReader = new BufferedReader(new FileReader(absolutePathAccount));
            String row;
            while ((row = csvReader.readLine()) != null) {
                String[] data = row.split(",");
                if(Objects.equals(data[1], id) && Objects.equals(data[2], pin)){
                    csvReader.close();
                    return true;
                }
            }
            csvReader.close();
        }
        catch (IOException | ArrayIndexOutOfBoundsException e) { System.out.println(e.getMessage()); }
        return false;
    }

    public void loginDetails(String inData, DataOutputStream out) throws IOException {
        try{
            String []data2 = inData.split(",");
            String id = data2[0];
            String pin = data2[1];
            BufferedReader csvReader = new BufferedReader(new FileReader(absolutePathAccount));
            String row;
            while ((row = csvReader.readLine()) != null) {
                String[] data = row.split(",");
                if(Objects.equals(data[1], id) && Objects.equals(data[2], pin)){
                    //System.out.println(row);
                    out.writeUTF(row);
                }
            }
            out.writeUTF("EndOfTransmission");
            csvReader.close();
        }
        catch (IOException | ArrayIndexOutOfBoundsException e) { System.out.println(e.getMessage()); }
    }

    // checks if the ID is unique
    public boolean checkUnique(String id) throws IOException {
        try{
            BufferedReader csvReader = new BufferedReader(new FileReader(absolutePathAccount));
            String row;
            while ((row = csvReader.readLine()) != null) {
                String[] data = row.split(",");
                if(Objects.equals(data[1], id)){
                    csvReader.close();
                    return true;
                }
            }
            csvReader.close();
        }
        catch (IOException e) { System.out.println(e.getMessage()); }
        return false;
    }

    // checks if account number exists
    public String checkValidAccountNumber(String num) throws IOException {
        try{
            BufferedReader csvReader = new BufferedReader(new FileReader(absolutePathAccount));
            String row;
            while ((row = csvReader.readLine()) != null) {
                String[] data = row.split(",");
                if(Objects.equals(data[0], num)){
                    csvReader.close();
                    return data[5];
                }
            }
            csvReader.close();
        }
        catch (IOException e) { System.out.println(e.getMessage()); }
        return "";
    }

    // Creates a new account for the user and saves in the database
    public void createNewAccountData(String accountData, DataOutputStream out) throws IOException {
        try{
            String[] arrData = accountData.split(",");
            String accountType = arrData[arrData.length-1];
            String currency = arrData[arrData.length-2];
            String accountNumberFront = "";
            long accountNumberBack = 100000000;
            switch(accountType){ // Generate a new unique account number for the user
                case "Admin":
                    accountNumberFront = "A-";
                    break;

                case "Savings":
                    accountNumberFront = "S-";
                    break;

                case "Current":
                    accountNumberFront = "C-";
                    break;

                case "Business":
                    accountNumberFront = "B-";
                    break;

                default:
                    break;
            }
            try{
                if(accountNumberFront.equals("")){
                    throw new IllegalArgumentException("");
                }
                accountNumberBack += countLine(absolutePathAccount, accountNumberFront) + 1;
                String fullAccountNumber = accountNumberFront+accountNumberBack;
                ServerExchangeRates change = new ServerExchangeRates();
                int finalAmount = DEFAULT_TRANSFER_LIMIT; // 3000 is based of DBS bank's limit
                int finalMaxAmount = MAX_TRANSFER_LIMIT; // 200000 is based of DBS bank's max limit
                if(!Objects.equals(currency, "SGD")){
                    String amount = change.getRates("SGD",DEFAULT_TRANSFER_LIMIT,currency)[3];
                    String amountMax = change.getRates("SGD",MAX_TRANSFER_LIMIT2,currency)[3];
                    double amount2 = Double.parseDouble(amount);
                    double amountMax2 = Double.parseDouble(amountMax);
                    System.out.println(amount2);
                    String amountStrFinal = "";
                    String amountStrFinal2 = "";

                    if(amount2 > DEFAULT_TRANSFER_LIMIT){
                        int temp = (int)amount2;
                        char[] ch = String.valueOf(temp).toCharArray();
                        amountStrFinal = amountStrFinal + ch[0] + ch[1];
                        for(int i = 2; i < String.valueOf(temp).length(); i++){
                            amountStrFinal += '0';
                        }
                        finalAmount = Integer.parseInt(amountStrFinal);
                    }

                    if(amountMax2 > MAX_TRANSFER_LIMIT){
                        int temp2 = (int)amountMax2;
                        char[] ch2 = String.valueOf(temp2).toCharArray();
                        amountStrFinal2 = amountStrFinal2 + ch2[0] + ch2[1];
                        for(int i = 2; i < String.valueOf(temp2).length(); i++){
                            amountStrFinal2 += '0';
                        }
                        finalMaxAmount = Integer.parseInt(amountStrFinal2);
                    }
                }
                BufferedWriter csvWriter = new BufferedWriter(new FileWriter(absolutePathAccount, true));
                csvWriter.write(fullAccountNumber + ","+accountData+","+finalMaxAmount+","+finalAmount+",0"+",0");
                csvWriter.newLine();
                csvWriter.flush();
                csvWriter.close();

                Date dateAndTime = new Date();
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/YYYY hh:mm:ss");
                String dateString =sdf.format(dateAndTime);
                String transData = String.join(",",fullAccountNumber,dateString,"Account Creation" ,"-","","","0");
                BufferedWriter csvWriter2 = new BufferedWriter(new FileWriter(TRANSCSV, true));
                csvWriter2.write(transData);
                csvWriter2.newLine();
                csvWriter2.flush();
                csvWriter2.close();

                out.writeUTF("Account Successfully Created!\nYour Account Number is: " + fullAccountNumber);
            }
            catch (IllegalArgumentException | ArrayIndexOutOfBoundsException e){
                System.out.println("Error: " + e.getMessage());
            }
        }
        catch (IOException e) { System.out.println(e); }
    }

    public static long countLine(String filePath, String accountType) throws IOException{
        long lines = 0;
        try (BufferedReader csvReader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = csvReader.readLine()) != null) {
                String[] temp = line.split(",", 2);
                if(temp[0].contains(accountType)){
                    lines++;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lines;
    }

    public boolean changeLimit(String inData){
        try{
            String row;
            String toReplace = "";
            String newLine = "";
            String []data = inData.split(",");
            String accountNum = data[0];
            int amount = Integer.parseInt(data[1]);
            BufferedReader csvReader = new BufferedReader(new FileReader(absolutePathAccount));
            Scanner sc = new Scanner(new File(absolutePathAccount));
            StringBuffer buffer = new StringBuffer();
            while ((row = csvReader.readLine()) != null) {
                buffer.append(sc.nextLine()+System.lineSeparator());
                String[] data2 = row.split(",");
                if(Objects.equals(data2[0], accountNum)){
                    toReplace = row;
                }
            }
            csvReader.close();
            String[] newLineArr = toReplace.split(",");
            newLineArr[newLineArr.length - 3] = String.valueOf(amount);
            newLine = String.join(",",newLineArr);
            String fileContents = buffer.toString();
            fileContents = fileContents.replaceAll(toReplace, newLine);
            FileWriter writer = new FileWriter(absolutePathAccount);
            writer.append(fileContents);
            writer.flush();
            writer.close();
            return true;
        }
        catch (IOException | ArrayIndexOutOfBoundsException e) { System.out.println(e.getMessage()); }
        return false;
    }

    public boolean changeEmail(String inData){
        try{
            String row;
            String toReplace = "";
            String newLine = "";
            String []data = inData.split(",");
            String accountNum = data[0];
            String newEmail = data[1];
            BufferedReader csvReader = new BufferedReader(new FileReader(absolutePathAccount));
            Scanner sc = new Scanner(new File(absolutePathAccount));
            StringBuffer buffer = new StringBuffer();
            while ((row = csvReader.readLine()) != null) {
                buffer.append(sc.nextLine()+System.lineSeparator());
                String[] data2 = row.split(",");
                if(Objects.equals(data2[0], accountNum)){
                    toReplace = row;
                }
            }
            csvReader.close();
            String[] newLineArr = toReplace.split(",");
            newLineArr[4] = newEmail;
            newLine = String.join(",",newLineArr);
            String fileContents = buffer.toString();
            fileContents = fileContents.replaceAll(toReplace, newLine);
            FileWriter writer = new FileWriter(absolutePathAccount);
            writer.append(fileContents);
            writer.flush();
            writer.close();
            return true;
        }
        catch (IOException | ArrayIndexOutOfBoundsException e) { System.out.println(e.getMessage()); }
        return false;
    }

    public boolean changePIN(String inData){
        try{
            String row;
            ArrayList<String> toReplaceArr = new ArrayList<>();
            String newLine = "";
            String []data = inData.split(",");
            String accountID = data[0];
            String pin = data[1];
            BufferedReader csvReader = new BufferedReader(new FileReader(absolutePathAccount));
            Scanner sc = new Scanner(new File(absolutePathAccount));
            StringBuffer buffer = new StringBuffer();
            while ((row = csvReader.readLine()) != null) {
                buffer.append(sc.nextLine()+System.lineSeparator());
                String[] data2 = row.split(",");
                if(Objects.equals(data2[1], accountID)){
                    toReplaceArr.add(row);
                }
            }
            csvReader.close();
            String fileContents = buffer.toString();
            for(String toReplace: toReplaceArr){
                String[] newLineArr = toReplace.split(",");
                newLineArr[2] = pin;
                newLine = String.join(",",newLineArr);
                fileContents = fileContents.replaceAll(toReplace, newLine);
            }
            FileWriter writer = new FileWriter(absolutePathAccount);
            writer.append(fileContents);
            writer.flush();
            writer.close();
            return true;
        }
        catch (IOException | ArrayIndexOutOfBoundsException e) { System.out.println(e.getMessage()); }
        return false;
    }

    public boolean changeID(String inData){
        try{
            String row;
            ArrayList<String> toReplaceArr = new ArrayList<>();
            String newLine = "";
            String []data = inData.split(",");
            String oldID = data[0];
            String newID = data[1];
            BufferedReader csvReader = new BufferedReader(new FileReader(absolutePathAccount));
            Scanner sc = new Scanner(new File(absolutePathAccount));
            StringBuffer buffer = new StringBuffer();
            while ((row = csvReader.readLine()) != null) {
                buffer.append(sc.nextLine()+System.lineSeparator());
                String[] data2 = row.split(",");
                if(Objects.equals(data2[1], oldID)){
                    toReplaceArr.add(row);
                }
            }
            csvReader.close();
            String fileContents = buffer.toString();
            for(String toReplace: toReplaceArr){
                String[] newLineArr = toReplace.split(",");
                newLineArr[1] = newID;
                newLine = String.join(",",newLineArr);
                fileContents = fileContents.replaceAll(toReplace, newLine);
            }
            FileWriter writer = new FileWriter(absolutePathAccount);
            writer.append(fileContents);
            writer.flush();
            writer.close();
            return true;
        }
        catch (IOException | ArrayIndexOutOfBoundsException e) { System.out.println(e.getMessage()); }
        return false;
    }

}
