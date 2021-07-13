package com.javastart.Entity;

public class Account {
    private int id;
    private String name;
    private Bill bill;

    public Account(int id, String name, Bill bill) {
        this.id = id;
        this.name = name;
        this.bill = bill;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Bill getBill() {
        return bill;
    }

    public void setBill(Bill bill) {
        this.bill = bill;
    }
}
