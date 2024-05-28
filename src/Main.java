import models.Expense;
import models.Group;
import models.Transaction;
import models.User;
import models.strategies.EqualSplitStrategy;
import services.ExpenseService;
import services.GroupService;
import services.SettlementService;
import services.UserService;

import java.util.Arrays;
import java.util.List;

public class Main {
    private final static ExpenseService expenseService = new ExpenseService();
    private final static UserService userService = new UserService();
    private final static GroupService groupService = new GroupService();
    private final static SettlementService settlementService = new SettlementService();

    public static void main(String[] args) {
        User u1 = new User("Prakhar");
        User u2 = new User("Prachi");
        User u3 = new User("Shubhra");
        User u4 = new User("Mohit");
        userService.addUser(u1);
        userService.addUser(u2);
        userService.addUser(u3);
        userService.addUser(u4);
        Group g1 = new Group(Arrays.asList(u1, u2, u3, u4));
        groupService.addGroup(g1);
        Expense e1 = new Expense(u1, Arrays.asList(u1, u2, u3, u4), 100, null, g1, new EqualSplitStrategy());
        Expense e2 = new Expense(u2, Arrays.asList(u1, u2, u3, u4), 100, null, g1, new EqualSplitStrategy());
        expenseService.addExpense(e1);
        expenseService.addExpense(e2);
        List<Transaction> transactions = settlementService.settle(g1);
        System.out.println(transactions);
    }
}
