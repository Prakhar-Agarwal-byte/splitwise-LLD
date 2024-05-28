package models.strategies;

import models.Expense;

public interface ExpenseSplitStrategy {
    void splitExpense(Expense expense);
}
