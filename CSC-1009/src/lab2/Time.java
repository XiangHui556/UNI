package lab2;

import java.text.SimpleDateFormat;
import java.util.TimeZone;
import java.text.DateFormat;

public class Time {
    public static void main(String[] args) {
        java.util.Date gmt = new java.util.Date();
        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss");
        sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
        System.out.println("Current Time is " + sdf.format(gmt) + " GMT");
    }
}
