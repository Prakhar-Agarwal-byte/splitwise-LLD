package exceptions;

public class UserNotFoundException extends IllegalArgumentException{
    public UserNotFoundException() {
        super("User not found");
    }
}
