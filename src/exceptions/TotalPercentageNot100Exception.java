package exceptions;

public class TotalPercentageNot100Exception extends ArithmeticException{
    public TotalPercentageNot100Exception() {
        super("Total percentage not 100");
    }
}
