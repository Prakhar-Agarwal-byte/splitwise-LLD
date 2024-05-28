package models.strategies;

import exceptions.TotalPercentageNot100Exception;
import models.Balance;
import models.Expense;
import models.Group;
import models.User;

import java.util.ArrayList;
import java.util.List;

public class PercentageSplitStrategy implements ExpenseSplitStrategy{

    @Override
    public void splitExpense(Expense expense) {
        Group group = expense.getGroup();
        User from = expense.getFrom();
        List<User> to = expense.getTo();
        List<Balance> balances = new ArrayList<>();
        List<Integer> percentages = expense.getPartitions();
        int totalAmount = expense.getTotalAmount();
        int totalPercentage = 0;
        for (int percentage : percentages) {
            totalPercentage += percentage;
        }
        if (totalPercentage != 100) throw new TotalPercentageNot100Exception();
        for (int i = 0; i < to.size(); i++) {
            Balance balance = new Balance(from, to.get(i), (totalAmount*percentages.get(i))/100);
            balances.add(balance);
        }
        expense.getBalances().addAll(balances);
    }
}
