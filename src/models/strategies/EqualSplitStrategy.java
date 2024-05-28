package models.strategies;

import models.Balance;
import models.Expense;
import models.Group;
import models.User;

import java.util.ArrayList;
import java.util.List;

public class EqualSplitStrategy implements ExpenseSplitStrategy{

    @Override
    public void splitExpense(Expense expense) {
        Group group = expense.getGroup();
        User from = expense.getFrom();
        List<User> to = expense.getTo();
        List<Balance> balances = new ArrayList<>();
        int totalAmount = expense.getTotalAmount();
        int eachUserAmount = totalAmount/to.size();
        for (User user : to) {
            Balance balance = new Balance(from, user, eachUserAmount);
            balances.add(balance);
        }
        expense.getBalances().addAll(balances);
    }
}
