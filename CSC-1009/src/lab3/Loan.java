package lab3;

import java.lang.Math;
import java.math.RoundingMode;
import java.util.Scanner;
import java.text.DecimalFormat;

public class Loan {

    private double annualInterestRate;
    private int numberofYears;
    private double loanAmount;
    final private java.util.Date loanDate;

    private static final DecimalFormat df = new DecimalFormat("0.00");
    final private long millis = System.currentTimeMillis();

    public Loan() {
        this.annualInterestRate = 2.5;
        this.numberofYears = 1;
        this.loanAmount = 1000;
        this.loanDate = new java.util.Date(this.millis);
    }

    public Loan(double annualInterestRate, int numberofYears, double loanAmount) {
        this.annualInterestRate = annualInterestRate;
        this.numberofYears = numberofYears;
        this.loanAmount = loanAmount;
        this.loanDate = new java.util.Date(this.millis);
    }

    public double getAnnualInterestRate() {

        return annualInterestRate;
    }

    public void setAnnualInterestRate(double annualInterestRate) {

        this.annualInterestRate = annualInterestRate;
    }

    public int getNumberofYears() {

        return numberofYears;
    }

    public void setNumberofYears(int numberofYears) {

        this.numberofYears = numberofYears;
    }

    public double getLoanAmount() {

        return loanAmount;
    }

    public void setLoanAmount(double loanAmount) {

        this.loanAmount = loanAmount;
    }

    public java.util.Date getLoanDate() {

        return loanDate;
    }

    public double getMonthlyPayment() {
        return ((this.loanAmount * ((this.annualInterestRate/100)/12))/(1-(1/Math.pow((1+(this.annualInterestRate/100)/12),(this.numberofYears*12)))));
    }

    public double getTotalPayment() {
        return (this.getMonthlyPayment() * numberofYears * 12);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print ("Enter annual interest rate, for example, 8.25: ");
        double interest =  sc.nextDouble();

        System.out.print ("Enter number of years as an integer: ");
        int years =  sc.nextInt();

        System.out.print ("Enter loan amount, for example, 120000.95: ");
        double amount =  sc.nextDouble();

        Loan test = new Loan(interest, years, amount);

        df.setRoundingMode(RoundingMode.UP);

        System.out.println ("The loan was Created on " + test.loanDate);
        System.out.println ("The monthly payment is $" + df.format(test.getMonthlyPayment()));
        System.out.println ("The total payment is $" + df.format(test.getTotalPayment()));

        sc.close();
    }
}