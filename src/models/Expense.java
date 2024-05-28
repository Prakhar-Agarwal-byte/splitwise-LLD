package models;

import models.strategies.ExpenseSplitStrategy;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Expense{
    private final UUID id;
    private User from;
    private List<User> to;
    private Integer totalAmount;
    private List<Integer> partitions;
    private final Group group;
    private ExpenseSplitStrategy strategy;
    private List<Balance> balances;

    public Expense(User from, List<User> to, Integer totalAmount, List<Integer> partitions, Group group, ExpenseSplitStrategy strategy) {
        this.id = UUID.randomUUID();
        this.from = from;
        this.to = to;
        this.totalAmount = totalAmount;
        this.partitions = partitions;
        this.group = group;
        this.strategy = strategy;
        this.balances = new ArrayList<>();
    }

    public UUID getId() {
        return id;
    }

    public User getFrom() {
        return from;
    }

    public void setFrom(User from) {
        this.from = from;
    }

    public List<User> getTo() {
        return to;
    }

    public void setTo(List<User> to) {
        this.to = to;
    }

    public Integer getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Integer totalAmount) {
        this.totalAmount = totalAmount;
    }

    public List<Integer> getPartitions() {
        return partitions;
    }

    public void setPartitions(List<Integer> partitions) {
        this.partitions = partitions;
    }

    public Group getGroup() {
        return group;
    }

    public ExpenseSplitStrategy getStrategy() {
        return strategy;
    }

    public void setStrategy(ExpenseSplitStrategy strategy) {
        this.strategy = strategy;
    }

    public List<Balance> getBalances() {
        return balances;
    }

    public void setBalances(List<Balance> balances) {
        this.balances = balances;
    }

    public void split() {
        this.balances = new ArrayList<>();
        this.strategy.splitExpense(this);
    }
}
