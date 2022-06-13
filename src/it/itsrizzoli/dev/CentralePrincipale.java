package it.itsrizzoli.dev;

import java.io.IOException;
import java.net.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

public class CentralePrincipale {

    public static void main(String[] args) throws IOException {

        // IOException include SocketException, UnknownHostException

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

        // Contenuto del campo DATA
        String dataContent = "CP [" + dtf.format(LocalDateTime.now()) + "]: POLL CL09";
        // Indirizzo remoto
        String remoteAddress = "127.0.0.1"; // localhost
        InetAddress srvIp = InetAddress.getByName(remoteAddress);
        // Porta
        int remotePort = 6789;

        DatagramPacket dp = new DatagramPacket(dataContent.getBytes(), dataContent.length(), srvIp, remotePort);

        DatagramSocket DataSender = new DatagramSocket();
        System.out.println("Client: pronto all'invio.");

        while (true) {

            try {
                dataContent = "CP [<" + dtf.format(LocalDateTime.now()) + ">]: POLL CL09";
                dp = new DatagramPacket(dataContent.getBytes(), dataContent.length(), srvIp, remotePort);

                DataSender.send(dp);
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
                DataSender.close();
                System.out.println("Client: chiuso.");
            }
        }
    }
}
