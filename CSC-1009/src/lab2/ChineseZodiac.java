package lab2;
import java.util.*;

public class ChineseZodiac {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print ("Enter a year: ");
        int year =  sc.nextInt();
        String[] zodiac = {"Rat", "Ox", "Tiger", "Rabbit", "Dragon", "Snake", "Horse", "Goat", "Monkey", "Rooster", "Dog", "Pig"};
        int base = 2020;
        int cal = year - base;
        cal = cal % 12;
        if (cal < 0){
            cal = 12 + cal;
            System.out.println (zodiac[cal]);
        }
        else{
            System.out.println (zodiac[cal]);
        }
        sc.close();
    }
}
