package services;

import daos.GroupDao;
import daos.GroupDaoImpl;
import daos.UserDao;
import daos.UserDaoImpl;
import exceptions.ExpenseNotFoundException;
import models.Expense;
import models.Group;
import models.Transaction;
import models.User;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ExpenseService {
    private final GroupDao groupDao = new GroupDaoImpl();
    private final UserDao userDao = new UserDaoImpl();
    private final SettlementService settlementService = new SettlementService();

    public void addExpense(Expense expense) {
        Group group = expense.getGroup();
        expense.split();
        group.getExpenses().add(expense);
        group.notifyObservers("added expense" + expense);
    }

    public void updateExpense(Expense expense) {
        Group group = expense.getGroup();
        Expense oldExpense = group.getExpenses().stream().filter(e -> e.getId().equals(expense.getId())).findFirst().orElse(null);
        if (oldExpense == null) {throw new ExpenseNotFoundException();}
        group.getExpenses().remove(oldExpense);
        group.getExpenses().add(expense);
        expense.split();
        group.notifyObservers("updated expense" + expense);
    }

    public void deleteExpense(Expense expense) {
        Group group = expense.getGroup();
        group.getExpenses().remove(expense);
        group.notifyObservers("deleted expense" + expense);
    }

    public List<Expense> getAllExpensesForFriend(User sourceUser, User destinationUser) {
        List<Group> groups = groupDao.getAllGroups();
        List<Expense> allExpenses = new ArrayList<>();
        for (Group group : groups) {
            List<Expense> expenses = group.getExpenses().stream().filter(e ->
                e.getFrom().getId().equals(sourceUser.getId()) && e.getTo().contains(destinationUser)
            ).collect(Collectors.toList());
            allExpenses.addAll(expenses);

        }
        return allExpenses;
    }

    public List<Transaction> settleExpensesForGroup(Group group) {
        return settlementService.settle(group);
    }
}
