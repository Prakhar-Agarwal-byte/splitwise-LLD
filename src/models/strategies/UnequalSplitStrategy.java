package models.strategies;

import exceptions.TotalNotEqualException;
import models.Balance;
import models.Expense;
import models.Group;
import models.User;

import java.util.ArrayList;
import java.util.List;

public class UnequalSplitStrategy implements ExpenseSplitStrategy{

    @Override
    public void splitExpense(Expense expense) {
        Group group = expense.getGroup();
        User from = expense.getFrom();
        List<User> to = expense.getTo();
        List<Balance> balances = new ArrayList<>();
        List<Integer> partitions = expense.getPartitions();
        int totalAmount = expense.getTotalAmount();
        int total = 0;
        for (int part: partitions) {
            total += part;
        }
        if (total != totalAmount) { throw new TotalNotEqualException();}
        for (int i = 0; i < to.size(); i++) {
            User user = to.get(i);
            Integer amount = partitions.get(i);
            Balance balance = new Balance(from, user, amount);
            balances.add(balance);
        }
        expense.getBalances().addAll(balances);
    }
}
