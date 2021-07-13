package com.javastart.Entity;

public class Payment {
    private int id;
    private int accountPayment;

    public Payment(int id, int accountPayment) {
        this.id = id;
        this.accountPayment = accountPayment;
    }

    public int getId() {
        return id;
    }

    public int getAccountPayment() {
        return accountPayment;
    }
}
