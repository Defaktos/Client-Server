package com.javastart;

import java.io.IOException;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        Server server = new Server(8000);

        try {
            server.start();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
