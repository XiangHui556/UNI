package Server.DataManipulation;

import java.io.*;
import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;

public class ServerTransactions implements ServerCSV{
    public boolean depositOrWithdrawal(String inData, boolean type){
        try{
            String row;
            String toReplace = "";
            String newLine = "";
            String []data = inData.split(",");
            String accountNum = data[0];
            double amount = Double.parseDouble(data[1]);
            String date = data[2];
            String curr = data[3];
            String memo = data[4];
            if(memo.isEmpty()){
                memo = "-";
            }
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
            double aBalance = Double.parseDouble(newLineArr[newLineArr.length - 1]);
            double cBalance = Double.parseDouble(newLineArr[newLineArr.length - 2]);
            String transData;
            if(type){
                aBalance += amount;
                cBalance += amount;
                transData = String.join(",",accountNum, date,"Deposit of " + amount+" "+curr.toUpperCase(),memo,"",String.valueOf(amount),String.valueOf(aBalance));
            }
            else{
                aBalance -= amount;
                cBalance -= amount;
                transData = String.join(",",accountNum,date,"Withdrawal of " + amount+" "+curr.toUpperCase(),memo,String.valueOf(amount),"",String.valueOf(aBalance));
            }
            newLineArr[newLineArr.length - 1] = String.valueOf(aBalance);
            newLineArr[newLineArr.length - 2] = String.valueOf(cBalance);
            newLine = String.join(",",newLineArr);
            String fileContents = buffer.toString();
            fileContents = fileContents.replaceAll(toReplace, newLine);
            FileWriter writer = new FileWriter(absolutePathAccount);
            writer.append(fileContents);
            writer.flush();
            writer.close();

            BufferedWriter csvWriter = new BufferedWriter(new FileWriter(TRANSCSV, true));
            csvWriter.write(transData);
            csvWriter.newLine();
            csvWriter.flush();
            csvWriter.close();
            return true;
        }
        catch (IOException | ArrayIndexOutOfBoundsException e) { System.out.println(e.getMessage()); }
        return false;
    }


    public boolean transfer(String inData){
        try{
            String row;
            String toReplace = "";
            String toReplace2 = "";
            String newLine = "";
            String newLine2 = "";
            String []data = inData.split(",");
            String accountNum = data[0];
            double amount = Double.parseDouble(data[1]);
            String date = data[2];
            String curr = data[3];
            String memo = data[4];
            String toAccount = data[5];
            String toAccountCurr = data[6];
            if(memo.isEmpty()){
                memo = "-";
            }
            BufferedReader csvReader = new BufferedReader(new FileReader(absolutePathAccount));
            Scanner sc = new Scanner(new File(absolutePathAccount));
            StringBuffer buffer = new StringBuffer();
            double convertAmount = amount;
            if(!curr.equalsIgnoreCase(toAccountCurr)){
                ServerExchangeRates ex = new ServerExchangeRates();
                convertAmount = Double.parseDouble(ex.getRates(curr, amount ,toAccountCurr)[3]);
            }
            while ((row = csvReader.readLine()) != null) {
                buffer.append(sc.nextLine()+System.lineSeparator());
                String[] data2 = row.split(",");
                String currAccountNum = data2[0];
                if(Objects.equals(currAccountNum, accountNum)){
                    toReplace = row;
                }
                else if(Objects.equals(currAccountNum, toAccount)){
                    toReplace2 = row;
                }

            }
            csvReader.close();
            String[] newLineArr = toReplace.split(",");
            String[] newLineArr2 = toReplace2.split(",");
            double aBalance = Double.parseDouble(newLineArr[newLineArr.length - 1]);
            double cBalance = Double.parseDouble(newLineArr[newLineArr.length - 2]);
            double aBalance2 = Double.parseDouble(newLineArr2[newLineArr2.length - 1]);
            double cBalance2 = Double.parseDouble(newLineArr2[newLineArr2.length - 2]);
            String fromName = newLineArr[3];
            String toName = newLineArr2[3];
            String transData;
            String transData2;

            aBalance -= amount;
            cBalance -= amount;
            transData2 = String.join(",",accountNum,date,"Transfer To " + toName,memo,String.valueOf(amount),"",String.valueOf(aBalance));

            aBalance2 += convertAmount;
            cBalance2 += convertAmount;
            transData = String.join(",",toAccount, date,"Transfer From " + fromName,memo,"",String.valueOf(convertAmount),String.valueOf(aBalance2));


            newLineArr[newLineArr.length - 1] = String.valueOf(aBalance);
            newLineArr[newLineArr.length - 2] = String.valueOf(cBalance);

            newLineArr2[newLineArr2.length - 1] = String.valueOf(aBalance2);
            newLineArr2[newLineArr2.length - 2] = String.valueOf(cBalance2);

            newLine = String.join(",",newLineArr);
            newLine2 = String.join(",",newLineArr2);

            String fileContents = buffer.toString();
            fileContents = fileContents.replaceAll(toReplace, newLine);
            fileContents = fileContents.replaceAll(toReplace2, newLine2);
            FileWriter writer = new FileWriter(absolutePathAccount);
            writer.append(fileContents);
            writer.flush();
            writer.close();

            BufferedWriter csvWriter = new BufferedWriter(new FileWriter(TRANSCSV, true));
            csvWriter.write(transData2);
            csvWriter.newLine();
            csvWriter.write(transData);
            csvWriter.newLine();
            csvWriter.flush();
            csvWriter.close();
            return true;
        }
        catch (IOException | ArrayIndexOutOfBoundsException e) { System.out.println(e.getMessage()); }
        return false;
    }

    public boolean checkForUpdate(String data2){
        try{
            String[] data3 = data2.split(",");
            String accountNumber = data3[0];
            int clientAmount = Integer.parseInt(data3[1]);
            BufferedReader csvReader = new BufferedReader(new FileReader(absolutePathTrans));
            String row;
            int counter = 0;
            while ((row = csvReader.readLine()) != null) {
                String[] data = row.split(",");
                if(Objects.equals(accountNumber, data[0])){
                    counter += 1;
                }
            }
            csvReader.close();
            if(counter == clientAmount){
                return true;
            }
            else{
                return false;
            }

        }
        catch (IOException e) { System.out.println(e.getMessage()); }
        return false;
    }

    public void getTransactionDataToClient(String accountNumber, DataOutputStream out) throws IOException {
        try{
            BufferedReader csvReader = new BufferedReader(new FileReader(absolutePathTrans));
            String row;
            while ((row = csvReader.readLine()) != null) {
                String[] data = row.split(",");
                if(Objects.equals(accountNumber, data[0])){
                    out.writeUTF(row);
                }
            }
            out.writeUTF("EndOfTransmission");
            csvReader.close();

        }
        catch (IOException e) { System.out.println(e.getMessage()); }
    }


    public void getAllAccountsDataToClient(DataOutputStream out) throws IOException {
        try{
            BufferedReader csvReader = new BufferedReader(new FileReader(absolutePathAccount));
            String row;
            while ((row = csvReader.readLine()) != null) {
                out.writeUTF(row);
            }
            out.writeUTF("EndOfTransmission");
            csvReader.close();

        }
        catch (IOException e) { System.out.println(e.getMessage()); }
    }

    public void getAdminTransactionDataToClient(String accountNumbers, DataOutputStream out) throws IOException {
        try{
            BufferedReader csvReader = new BufferedReader(new FileReader(absolutePathTrans));
            String row;
            if(accountNumbers.toLowerCase().contains("all")){
                while ((row = csvReader.readLine()) != null) {
                    out.writeUTF(row);
                }
                out.writeUTF("EndOfTransmission");
                csvReader.close();
            }
            else{
                String[] accountNums = accountNumbers.split(",");
                while ((row = csvReader.readLine()) != null) {
                    String[] data = row.split(",");
                    if(Arrays.asList(accountNums).contains(String.valueOf(data[0]))){
                        out.writeUTF(row);
                    }
                }
                out.writeUTF("EndOfTransmission");
                csvReader.close();
            }

        }
        catch (IOException e) { System.out.println(e.getMessage()); }
    }

}
