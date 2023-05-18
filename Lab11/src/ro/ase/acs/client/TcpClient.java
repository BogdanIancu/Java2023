package ro.ase.acs.client;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class TcpClient {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try(Socket clientSocket = new Socket("localhost", 7777)){
            new Thread(()-> {
                try {
                    while(true) {
                        InputStream inputStream = clientSocket.getInputStream();
                        DataInputStream dataInputStream = new DataInputStream(inputStream);
                        String receivedMessage = dataInputStream.readUTF();
                        System.out.println(receivedMessage);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();

            while(true) {
                OutputStream outputStream = clientSocket.getOutputStream();
                DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
                String message = scanner.nextLine();
                dataOutputStream.writeUTF(message);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            scanner.close();
        }
    }
}
