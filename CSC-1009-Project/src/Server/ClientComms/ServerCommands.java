package Server.ClientComms;

import Server.DataManipulation.ServerAccountSettings;
import Server.DataManipulation.ServerExchangeRates;
import Server.DataManipulation.ServerTransactions;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.SocketException;

public class ServerCommands {
    ServerAccountSettings checks = new ServerAccountSettings();
    ServerTransactions trans = new ServerTransactions();
    ServerExchangeRates exchanges = new ServerExchangeRates();

    public void input(String inputData, DataOutputStream out) throws IOException {
        String[] commands = inputData.split(" - ", 2);
        String command = commands[0];
        String data = commands[1];
        //System.out.println(inputData);
        try{
            switch (command){
                case "CheckUniqueID":
                    if(checks.checkUnique(data)){
                        out.writeUTF("false");
                    }
                    else{
                        out.writeUTF("true");
                    }
                    break;

                case "LoginCheck":
                    if(checks.loginCheck(data)){
                        out.writeUTF("true");
                    }
                    else{
                        out.writeUTF("false");
                    }
                    break;

                case "LoginDetails":
                    checks.loginDetails(data, out);
                    break;

                case "CreateNewAccount":
                    checks.createNewAccountData(data, out);
                    break;

                case "ChangeLimit":
                    if(checks.changeLimit(data)){
                        out.writeUTF("true");
                    }
                    else{
                        out.writeUTF("false");
                    }
                    break;

                case "ChangePIN":
                    if(checks.changePIN(data)){
                        out.writeUTF("true");
                    }
                    else{
                        out.writeUTF("false");
                    }
                    break;

                case "ChangeEmail":
                    if(checks.changeEmail(data)){
                        out.writeUTF("true");
                    }
                    else{
                        out.writeUTF("false");
                    }
                    break;

                case "ChangeID":
                    if(checks.changeID(data)){
                        out.writeUTF("true");
                    }
                    else{
                        out.writeUTF("false");
                    }
                    break;

                case "CheckCurrency":
                    String outMessage = exchanges.checkCurrencyAva(data);
                    System.out.println(outMessage);
                    out.writeUTF(outMessage);
                    break;

                case "GetExchangeRates":
                    String[] dataArr2 = data.split(",");
                    String[] outMessageArr3 = exchanges.getRates(dataArr2[0] , 1, dataArr2[1]);
                    String outMessage3 = outMessageArr3[1];
                    System.out.println(outMessage3);
                    out.writeUTF(outMessage3);
                    break;

                case "Deposit":
                    if(trans.depositOrWithdrawal(data, true)){
                        out.writeUTF("true");
                    }
                    else{
                        out.writeUTF("false");
                    }
                    break;

                case "Withdraw":
                    if(trans.depositOrWithdrawal(data, false)){
                        out.writeUTF("true");
                    }
                    else{
                        out.writeUTF("false");
                    }
                    break;

                case "Transfer":
                    if(trans.transfer(data)){
                        out.writeUTF("true");
                    }
                    else{
                        out.writeUTF("false");
                    }
                    break;

                case "CheckValidAccountNumber":
                    String outMessage2 = checks.checkValidAccountNumber(data);
                    out.writeUTF(outMessage2);

                case "TransactionHistory":
                    trans.getTransactionDataToClient(data, out);
                    break;

                case "AdminTransactionHistory":
                    trans.getAdminTransactionDataToClient(data, out);
                    break;

                case "AllAccounts":
                    trans.getAllAccountsDataToClient(out);
                    break;

                case "CheckTransUpdate":
                    if(trans.checkForUpdate(data)){
                        out.writeUTF("true");
                    }
                    else{
                        out.writeUTF("false");
                    }
                    break;

                default:
                    out.writeUTF("");
                    break;
            }
        } catch (SocketException s) {
            System.out.println("A Socket Disconnected");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
