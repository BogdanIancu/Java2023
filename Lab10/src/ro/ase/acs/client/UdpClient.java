package ro.ase.acs.client;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.Scanner;

public class UdpClient extends Thread {

    private DatagramSocket clientSocket;

    public UdpClient(DatagramSocket clientSocket) {
        this.clientSocket = clientSocket;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try (DatagramSocket clientSocket =
                     new DatagramSocket()) {

            UdpClient client=new UdpClient(clientSocket);
            client.start();
            while (true) {
                String message = scanner.nextLine();
                byte[] content = message.getBytes();
                InetAddress address =
                        InetAddress.getByName("localhost");
                DatagramPacket packetToBeSent =
                        new DatagramPacket(content,
                                content.length, address, 7777);
                clientSocket.send(packetToBeSent);

            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            scanner.close();
        }
    }

    @Override
    public void run() {
        try {
            while (true) {
                byte[] buffer = new byte[256];
                DatagramPacket packetToBeReceived =
                        new DatagramPacket(buffer, buffer.length);
                clientSocket.receive(packetToBeReceived);
                String receivedMessage = new String(
                        packetToBeReceived.getData());
                System.out.println(receivedMessage);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
