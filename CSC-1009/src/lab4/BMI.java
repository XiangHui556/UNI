package lab4;

import java.lang.Math;
import java.util.Scanner;

public class BMI {
    private int weight;
    private int height;
    private double BMIValue;

    public BMI(int weight, int height) {
        this.weight = weight;
        this.height = height;
        this.BMIValue = ((this.weight*0.45359237) / Math.pow((this.height*0.0254),2));
    }

    public int getWeight() {
        return weight;
    }

    public int getHeight() {
        return height;
    }

    public String getBMI() {
        if(this.BMIValue < 18.5){
            return "Underweight";
        }
        else if(18.5 <= this.BMIValue && this.BMIValue < 25.0){
            return "Normal";
        }
        else if(25.0 <= this.BMIValue && this.BMIValue < 30.0){
            return "Overweight";
        }
        else{
            return "Obese";
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print ("Enter weight in pounds: ");
        int pounds =  sc.nextInt();

        System.out.print ("Enter height in inches: ");
        int inches =  sc.nextInt();

        BMI test = new BMI(pounds, inches);

        System.out.println ("BMI is " + test.BMIValue);
        System.out.println (test.getBMI());

        sc.close();
    }
}
