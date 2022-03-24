package Server.DataManipulation;

import java.io.File;


interface ServerCSV {
    String ACCOUNTCSV = "Server/resources/accounts.csv";
    String TRANSCSV = "Server/resources/transactions.csv";
    File fileAccount = new File(ACCOUNTCSV);
    String absolutePathAccount = fileAccount.getAbsolutePath();
    File fileTrans = new File(TRANSCSV);
    String absolutePathTrans = fileTrans.getAbsolutePath();
}
