package models;

import models.observers.Observable;
import models.observers.Observer;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Group implements Observable {
    private final UUID id;
    private List<User> members;
    private List<Expense> expenses;

    public Group(List<User> members) {
        this.id = UUID.randomUUID();
        this.members = members;
        this.expenses = new ArrayList<>();
    }

    public UUID getId() {
        return id;
    }

    public List<User> getMembers() {
        return members;
    }

    public void setMembers(List<User> members) {
        this.members = members;
    }

    public List<Expense> getExpenses() {
        return expenses;
    }

    public void setExpenses(List<Expense> expenses) {
        this.expenses = expenses;
    }

    @Override
    public void addObserver(Observer observer) {

    }

    @Override
    public void removeObserver(Observer observer) {

    }

    @Override
    public void notifyObservers(Object expense) {
        for (User user: members) {
            user.update(expense);
        }
    }
}
