package exceptions;

public class ExpenseNotFoundException extends IllegalArgumentException{
    public ExpenseNotFoundException() {
        super("Exception not found");
    }
}
