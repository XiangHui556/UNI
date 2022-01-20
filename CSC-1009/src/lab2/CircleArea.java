package lab2;
import java.util.*;

public class CircleArea {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print ("Enter a number for radius: ");
        float radius =  sc.nextFloat();
        double area = radius * radius * Math.PI;
        System.out.println ("The area for the circle of radius "+ radius +" is " + area);
        sc.close();
    }
}
