package models;

import models.observers.Observer;

import java.util.UUID;

public class User implements Observer {
    private final UUID id;
    private String name;

    public User(String name) {
        this.id = UUID.randomUUID();
        this.name = name;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public void update(Object o) {
        System.out.println("Notification for " + this.name + ": " + o);
    }
}
