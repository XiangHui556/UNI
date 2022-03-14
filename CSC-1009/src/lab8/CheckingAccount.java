package lab8;
import java.util.Scanner;

public class CheckingAccount {
    private double balance;
    private int accountNumber;

    public CheckingAccount(int accountNumber) {
        this.accountNumber = accountNumber;
    }

    public void deposit(double amount) {
        this.balance += amount;
    }

    public void withdraw(double amount) throws InsufficientFundsException {
        if((this.balance - amount) < 0){
            double moneyShort = amount - this.balance;
            throw new InsufficientFundsException(String. format("Sorry, but your account is short by: $ %.2f", moneyShort));
        }
        else{
            this.balance -= amount;
        }
    }

    public int getNumber(){return accountNumber;}

    public double getBalance() {
        return balance;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean loop = true;

        System.out.print("Please enter your account number: ");
        String input1 = sc.nextLine();
        int anumber = Integer.parseInt(input1);
        CheckingAccount account = new CheckingAccount(anumber);
        while(loop){
            try {
                System.out.print("\n1. Deposit\n2. Withdraw\n3. Get Account Number\n4. Get Balance\n5. Exit\nEnter choice: ");
                String input2 = sc.nextLine();
                int choice = Integer.parseInt(input2);
                float amount = 0.f;
                String input = "";
                switch (choice) {
                    case 1:
                        System.out.print("\nEnter amount to deposit: ");
                        input = sc.nextLine();
                        amount = Float.parseFloat(input);
                        if (amount < 0.f) {
                            throw new IllegalArgumentException("Non positive number!"); }
                        else{
                            account.deposit(amount);
                            System.out.println("\nyou have deposited $" + amount);
                        }
                        break;

                    case 2:
                        System.out.print("\nEnter amount to Withdraw: ");
                        input = sc.nextLine();
                        amount = Float.parseFloat(input);
                        if (amount < 0.f) {
                            throw new IllegalArgumentException("Non positive number!"); }
                        else{
                            account.withdraw(amount);
                            System.out.println("\nyou have withdraw $" + amount);
                            System.out.print("The balance after withdraw is: $" + account.getBalance());
                        }
                        break;

                    case 3:
                        System.out.println("\nYour account number is $" + account.getNumber());
                        break;

                    case 4:
                        System.out.println("\nYour account balance is " + account.getBalance());
                        break;

                    case 5:
                        System.out.println("\nExiting Programme");
                        loop = false;
                        break;

                    default:
                        System.out.println("\nInvalid Input");
                        break;
                }
            }
            catch (IllegalArgumentException | ArithmeticException | InsufficientFundsException e){
                System.out.println("Exception caught: " +  e.getMessage());
            }
        }
    }
}
