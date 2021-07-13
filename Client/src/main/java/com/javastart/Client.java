package com.javastart;

import com.javastart.Entity.Account;
import com.javastart.Entity.Payment;
import com.javastart.Service.AccountService;
import com.javastart.Service.PaymentService;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {
    private BufferedWriter writer;
    private BufferedReader reader;
    private ObjectMapper objectMapper;
    private Scanner input = new Scanner(System.in);
    private AccountService accountService = new AccountService();
    private PaymentService paymentService = new PaymentService();

    public Client(int port) {
        System.out.println("PROGRAM:\nStart Program\n");
        try {
            Socket socket = new Socket("localhost", port);
            writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            while (socket.isConnected()) {
                System.out.println("PROGRAM:\nClient Is Connected To Server\n");
                System.out.println("Input 1 - To Create Account\n2 - To Make Payment\n3 - To Close Program");

                int inputNumber = input.nextInt();
                if (inputNumber == 1) {
                    Account account = accountService.typeByKeyboard();
                    sendAccount(account);
                } if (inputNumber == 2) {
                    Payment payment = paymentService.makePayment();
                    sendPayment(payment);
                } if (inputNumber == 3) {
                    writer.write("Close");
                    writer.close();
                    reader.close();
                    socket.close();
                }
            }
        } catch (IOException e) {
            e.getMessage();
        }
    }
    public void sendAccount(Account account) throws IOException {
        objectMapper = new ObjectMapper();
        String objectToServer = objectMapper.writeValueAsString(account);
        writer.write(objectToServer + "\n");
        writer.flush();
        System.out.println("PROGRAM:\nAccount Sent To Server\n");
        String readAccount = reader.readLine();
        System.out.println("PROGRAM:\nServer Sent Message\n" + readAccount + "\n");
    }
    public void sendPayment(Payment payment) throws IOException {
        objectMapper = new ObjectMapper();
        String paymentToServer = objectMapper.writeValueAsString(payment);
        writer.write(paymentToServer + "\n");
        writer.flush();
        System.out.println("PROGRAM:\nPayment Sent To Server\n");
        String readPayment = reader.readLine();
        System.out.println("PROGRAM:\nServer Sent Message\n" + readPayment + "\n");
    }
}
