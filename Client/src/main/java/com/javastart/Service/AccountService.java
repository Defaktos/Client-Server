package com.javastart.Service;

import com.javastart.Entity.Account;
import com.javastart.Entity.Bill;

import java.util.Scanner;

public class AccountService {
    private Scanner input = new Scanner(System.in);

    public Account typeByKeyboard() {
        try {
            System.out.println("Input ID: ");
            int id = input.nextInt();
            System.out.println("Input Name: ");
            String name = input.next();
            System.out.println("Input Bill: ");
            int bill = input.nextInt();

            Account account = makeAccount(id, name, bill);
            return account;
        } catch (Exception e) {
            e.getMessage();
        }
        return null;
    }
    public Account makeAccount(int id, String name, int accountBill){
        Bill bill = new Bill();
        bill.setAccountBill(accountBill);
        Account account = new Account(id, name, bill);
        return account;
    }
}
