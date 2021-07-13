package com.javastart.Service;

import com.javastart.Entity.Payment;

import java.util.Scanner;

public class PaymentService {
    private Scanner input = new Scanner(System.in);

    public Payment makePayment() {
        try {
            System.out.println("Input ID: ");
            int id = input.nextInt();
            System.out.println("Input Payment: ");
            int amountOfPayment = input.nextInt();

            Payment payment = new Payment(id, amountOfPayment);
            return payment;
        } catch (Exception e) {
            e.getMessage();
        }
        return null;
    }
}
