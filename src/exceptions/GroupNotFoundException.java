package exceptions;

public class GroupNotFoundException extends IllegalArgumentException{
    public GroupNotFoundException() {
        super("Group not found");
    }
}
