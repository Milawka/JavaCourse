package com.company;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.*;

public class Server {

    ServerSocket serverSocket;
    Socket socket;
    List<Connection> chatRoom1 = Collections.synchronizedList(new ArrayList<>());  // Это для обеспечения доступа из разных потоков
    List<Connection> chatRoom2 = Collections.synchronizedList(new ArrayList<>());
    List<Connection> chatRoom3 = Collections.synchronizedList(new ArrayList<>());


    public Server() {
        try {
            serverSocket = new ServerSocket(9099);


            while (true) {
                socket = serverSocket.accept();
                Connection connection = new Connection(socket);

                connection.out.writeUTF("Hello! What chat you want to enter? (Send any number from 1 to 3)");
                connection.out.writeUTF("If you want to exit send 'q'.");
                String chatRoom_number = connection.in.readUTF();

                if (chatRoom_number == null) {
                    return;
                }
                if (chatRoom_number.equals("1")) {
                    chatRoom1.add(connection);
                    connection.setCurrentChatRoom(chatRoom1);
                } else if (chatRoom_number.equals("2")) {
                    chatRoom2.add(connection);
                    connection.setCurrentChatRoom(chatRoom2);
                } else if (chatRoom_number.equals("3")) {
                    chatRoom3.add(connection);
                    connection.setCurrentChatRoom(chatRoom3);
                }
                connection.start();
            }
        } catch (IOException e) {
            System.out.println("Sorry! Problem with socket creation, check your Internet connection.");
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        new Server();
    }

    // Закрываем все потоки всех соединений и серверный сокет
    private void closeAll(List<Connection> chatRoom) {
        try {
            serverSocket.close();

            synchronized (chatRoom) {
                Iterator<Connection> iterator = chatRoom.iterator();
                while (iterator.hasNext()) {
                    ((Connection) iterator.next()).close(chatRoom);
                }
            }
        } catch (Exception e) {
            System.out.println("Threads were not closed.");
        }
    }


    // Работаем с конкретным пользователем
    private class Connection extends Thread {

        private DataInputStream in;
        private DataOutputStream out;
        private Socket socket;
        private List<Connection> currentChatRoom;


        public void setCurrentChatRoom(List<Connection> currentChatRoom) {
            this.currentChatRoom = currentChatRoom;
        }


        public Connection(Socket socket) {
            this.socket = socket;

            try {
                in = new DataInputStream(socket.getInputStream());
                out = new DataOutputStream(socket.getOutputStream());
            } catch (IOException e) {
                System.out.println("Sorry! Problems with connection, please retry attempt.");
                e.printStackTrace();
                close(currentChatRoom);
            }
        }

        @Override
        public void run() {

            String message = "";
            SocketAddress userIP = socket.getRemoteSocketAddress();

            try {
                while (true) {
                    message = in.readUTF();

                    if (message == null) {
                        continue;
                    }
                    if (message.equals("q")) {
                        in.close();
                        currentChatRoom.remove(this);

                        synchronized (currentChatRoom) {
                            Iterator<Connection> iterator = currentChatRoom.iterator();
                            while (iterator.hasNext()) {
                                ((Connection) iterator.next()).out.writeUTF("Пользователь " + userIP + " отключился");
                            }
                        }
                        return;
                    }
                    synchronized (currentChatRoom) {
                        Iterator<Connection> iterator = currentChatRoom.iterator();
                        while (iterator.hasNext()) {
                            ((Connection) iterator.next()).out.writeUTF(message);
                        }
                    }
                }
            } catch (EOFException e) {
                try {
                    in.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                return;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // Закрываем входной, выходной потоки и сокет
        public void close(List<Connection> chatRoom) {
            try {
                in.close();
                out.close();
                socket.close();

                chatRoom.remove(this);
                if (chatRoom.size() == 0) {
                    Server.this.closeAll(chatRoom);
                    System.exit(0);
                }
            } catch (Exception e) {
                System.err.println("Threads were not closed.");
            }
        }
    }


}

