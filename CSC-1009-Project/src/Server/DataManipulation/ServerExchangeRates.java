package Server.DataManipulation;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.net.URL;
import java.net.URLConnection;
import java.util.regex.Pattern;

public class ServerExchangeRates {
    static final private String URL = "https://www.x-rates.com/calculator/?amount=1&";
    static final private String URL2 = "https://www.x-rates.com/table/?from=ARS&amount=1";
    private static HashMap<String, String> currencies = new HashMap<String, String>();

    public ServerExchangeRates() {
    }

    public HashMap<String, String> getCurrencies() {
        return currencies;
    }

    public void initGetCurrencies() throws IOException {
        URL url = new URL(URL2);
        URLConnection con = url.openConnection();
        InputStream is = con.getInputStream();

        try(BufferedReader br = new BufferedReader(new InputStreamReader(is))) {
            String line = null;
            // read each line and save to hashmap
            while ((line = br.readLine()) != null) {
                if(line.contains("<option value='")){
                    line = line.replace("<option value='","");
                    line = line.replace("'>","");
                    line = line.replace("</option>","");
                    line = line.replace("\t","");
                    if(line.contains("selected")) {
                        //Do nothing
                    }
                    else if(Pattern.matches("[0-9]+", line)){

                    }
                    else{
                        line = line.substring(3);
                        //System.out.println(line);
                        String[] temp = line.split(" - ");
                        String key = temp[0];
                        String value = temp[1];
                        String testcase = currencies.get(key);
                        if (testcase != null) {
                            // Do nothing
                        } else {
                            ServerExchangeRates.currencies.put(key, value);
                        }

                    }
                }
            }
            //System.out.println(currencies);
        }
    }

    public String checkCurrencyAva(String choice){
        String testcase = ServerExchangeRates.currencies.get(choice.toUpperCase());
        if (testcase != null) {
            return choice.toUpperCase() + " - " + testcase;
        } else {
            return "";
        }
    }

    public String[] getRates(String currencyFrom, double amount, String currencyTo) throws IOException {
        String newUrl = URL + "from=" + currencyFrom + "&to=" + currencyTo;
        URL url = new URL(newUrl);

        float toRates = 0.0f;
        String fromCurr = null;
        String toCurr = null;

        URLConnection con = url.openConnection();
        InputStream is = con.getInputStream();

        try(BufferedReader br = new BufferedReader(new InputStreamReader(is))) {
            String line = null;

            // read each line and save to hashmap
            while ((line = br.readLine()) != null) {
                if(line.contains("<span class=\"ccOutputTxt\">")){
                    line = line.replace("<span class=\"ccOutputTxt\">","");
                    line = line.replace("</span>","");
                    line = line.replace("\t ","");
                    line = line.replace("\t","");
                    line = line.replace(" =","");
                    //System.out.println(line);
                    String[] temp = line.split(" ");
                    fromCurr = temp[1];
                }

                else if(line.contains("<span class=\"ccOutputRslt\">")){
                    line = line.replace("<span class=\"ccOutputRslt\">","");
                    line = line.replace("<span class=\"ccOutputTrail\">","");
                    line = line.replace("<span class=\"ccOutputCode\">","");
                    line = line.replace("</span>","");
                    line = line.replace("\t ","");
                    line = line.replace("\t","");
                    //System.out.println(line);
                    String[] temp = line.split(" ");
                    toRates = Float.parseFloat(temp[0]);
                    toCurr = temp[1];
                }
            }
        }
        double convrt = amount * toRates;
        String ratesCurr = fromCurr + ":" + toCurr;
        String ratesAmount = "1:" + toRates;
        String ratesConversion = String. format("%.2f " + fromCurr + " = %.2f " + toCurr, amount, convrt);
        String ratesConverted = String.valueOf(convrt);
        String[] output = new String [4];
        output[0] = ratesCurr;
        output[1] = ratesAmount;
        output[2] = ratesConversion;
        output[3] = ratesConverted;
        return output;
    }
}
