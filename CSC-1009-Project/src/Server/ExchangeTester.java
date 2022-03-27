package Server;

import Server.DataManipulation.ServerExchangeRates;

import java.io.IOException;
import java.util.Arrays;

public class ExchangeTester {
    public static void main(String[] args) throws IOException {
        //ServerCSVAccess test = new ServerCSVAccess();
        //test.getData();

        ServerExchangeRates test2 = new ServerExchangeRates();
        String[] testtext = test2.getRates("USD",100,"INR");
        System.out.println(Arrays.toString(testtext));
    }
}
