package ro.ase.acs.server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.Vector;

public class TcpServer {
    private static List<Socket> clients = new Vector<>();

    private static String receiveMessage(Socket clientSocket) throws IOException {
        InputStream inputStream = clientSocket.getInputStream();
        DataInputStream dataInputStream = new DataInputStream(inputStream);
        String receivedMessage = dataInputStream.readUTF();
        return receivedMessage;
    }

    private static void sendMessage(Socket clientSocket, String message) throws IOException {
        OutputStream outputStream = clientSocket.getOutputStream();
        DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
        dataOutputStream.writeUTF(message);
    }

    public static void main(String[] args) {
        int port = 7777;
        try(ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Server started on port " + port);
            while(true) {
                Socket clientSocket = serverSocket.accept();
                clients.add(clientSocket);
                new Thread(()-> {
                    try {
                        while(true) {
                            String message = receiveMessage(clientSocket);
                            for(Socket client : clients) {
                                if(client.isConnected()) {
                                    sendMessage(client, message);
                                }
                            }
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
