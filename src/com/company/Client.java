package com.company;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    Socket socket;
    DataInputStream in;
    DataOutputStream out;
    String message = "";


    public Client() {
        Scanner scan = new Scanner(System.in);

        try {
            socket = new Socket("127.0.0.1", 9099);
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());

            // Запускаем вывод всех входящих сообщений в консоль
            Resender resend = new Resender();
            resend.start();

            while (!message.equals("q")) {
                message = scan.nextLine();
                out.writeUTF(message);
            }
            System.exit(0);
            in.close();
            resend.setStop();
        } catch (IOException e) {
            System.out.println("Sorry! Problems with connection, retry attempt.");
            e.printStackTrace();
        }
    }


    public static void main(String[] args) throws IOException {
        new Client();
    }


    // Этот класс будет получать сообщения от сервера и выводить их в консоль.
    private class Resender extends Thread {

        private boolean stoped;

        // Прекращает пересылку сообщений
        public void setStop() {
            stoped = true;
        }

        @Override
        public void run() {
            try {
                while (!stoped) {
                    String messageFromServer = in.readUTF();
                    System.out.println(messageFromServer);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}


