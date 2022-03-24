package Client.Interface;

public class InsufficientFundsException extends Exception{
    public InsufficientFundsException(double amount, double balance, String curr) throws InsufficientFundsException {
        String exceed = String.valueOf(amount - balance);
        String message = "Sorry, your account is short of " + ConsoleColors.RED + exceed + ConsoleColors.RESET + curr + " to make the withdrawal";
        throw new InsufficientFundsException(message);
    }

    public InsufficientFundsException(String message) {
        super(message);
    }
}
