package com.javastart;

import com.javastart.Entity.Account;
import com.javastart.Entity.Payment;
import com.javastart.Service.DataBaseService;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.net.ServerSocket;
import java.sql.SQLException;

public class Server {
    private ServerSocket serverSocket;
    private BufferedReader reader;
    private BufferedWriter writer;
    private int port;
    private ObjectMapper objectMapper;
    private DataBaseService dataBaseService = new DataBaseService();

    public Server(int port) {
        this.port = port;
    }

    public void start() throws IOException, SQLException {
        System.out.println("PROGRAM:\nStart Program\n");

    }
    private void createConnection(int port) {
        try {
            serverSocket = new ServerSocket(port);
            System.out.println("PROGRAM:\nServer Is Deployed\n");
        } catch (IOException e) {
            System.out.println("PROGRAM:\nNo Connection");
            e.printStackTrace();
        }
    }
    private void startListener() {
        new Thread(() -> {
            while (true) {
                try {
                    if (reader.ready()) {
                        readClientMessage();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    break;
                } catch (SQLException e) {
                    e.printStackTrace();
                    break;
                }
            }
        }).start();
    }
    public void readClientMessage() throws IOException, SQLException {
        String readLine = reader.readLine();

        if (readLine.equals("close")) {
            writer.close();
            reader.close();
        }
        if (readLine.contains("name")) {

        }
    }
    private void readAccount(String readLine) throws IOException, SQLException {
        objectMapper = new ObjectMapper();
        Account account = objectMapper.readValue(readLine, Account.class);

        System.out.println("---INFO---\nClient sent message:\n" +
                "New Account: id = " + account.getId() +
                ", name = " + account.getName() +
                ", bill = " + account.getBill().getAccountBill() + "\n");

        writer.write("Server got instance of Account: " +
                "New Account: id = " + account.getId() +
                ", name = " + account.getName() +
                ", bill = " + account.getBill().getAccountBill() + "\n");
        writer.flush();

        dataBaseService.addToTable(account);
        System.out.println("Server Wrote Instance In Database ("
                + account.getId() + ", " + account.getName() + ", " + account.getBill().getAccountBill() + ")\n");
    }
    private void readPayment(String readLine) throws IOException, SQLException {
        objectMapper = new ObjectMapper();
        Payment payment = objectMapper.readValue(readLine, Payment.class);

        System.out.println("PROGRAM:\nClient Sent Message:\n" +
                "New Payment: ClientId = " + payment.getId() +
                ", payment = " + payment.getAccountPayment() + "\n");
        writer.write("Server Got Instance Of Payment:\n" +
                "New Payment: ClientId = " + payment.getId() +
                ", payment = " + payment.getAccountPayment() + "\n");
        writer.flush();

        DataBaseService dataBaseService = new DataBaseService();
        String currentBill = dataBaseService.updateTable(payment);

        String toClient = ("Payment Committed Successfully\n" +
                "Current Status: " + "Client With ID = " + payment.getId() + " Has Bill = " + currentBill + "\n");
        System.out.println(toClient);
        writer.write(toClient);
        writer.flush();
    }
}
