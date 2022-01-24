package lab3;
import java.lang.Math;
import java.util.Date;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

public class Loan {

    private double annualInterestRate;
    private int numberofYears;
    private double loanAmount;
    private Date loanDate;

    public Loan() {
        this.annualInterestRate = 2.5;
        this.numberofYears = 1;
        this.loanAmount = 1000;
        this.loanDate = new Date();
    }

    public Loan(double annualInterestRate, int numberofYears, double loanAmount) {
        this.annualInterestRate = annualInterestRate;
        this.numberofYears = numberofYears;
        this.loanAmount = loanAmount;
        this.loanDate = new Date();
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

    public Date getLoanDate() {
        return loanDate;
    }

    public double getMonthlyPayment() {
        double monthly = (this.loanAmount * this.annualInterestRate)/(1-(1/Math.pow((1 + this.annualInterestRate) , (numberofYears * 12)));
        return monthly;
    }

    public double getTotalPayment() {
        double total = this.getMonthlyPayment() * numberofYears * 12;
        return total;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print ("Enter a number for radius: ");
        float radius =  sc.nextFloat();
        double area = radius * radius * Math.PI;
        System.out.println ("The area for the circle of radius "+ radius +" is " + area);
        sc.close();
    }
}