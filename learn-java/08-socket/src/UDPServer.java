/*
Name: Thanh Ha Khuong
Studiengruppe: 20/042/61
E-Mail: thanhha.khuong@htw-dresden.de
*/

import java.net.*;
import java.io.*;

public class UDPServer {
    public static void main(String[] args) throws Exception{

        // Datenpaket
        DatagramPacket packr = null;

        // SendeSocket
        DatagramSocket ds = new DatagramSocket(Integer.parseInt(args[0]));

        while (true) {
            packr = new DatagramPacket(new byte[1024], 1024);
            ds.receive(packr);
            String mess = new String(packr.getData(), 0, packr.getLength());
            System.out.println(mess);

            StringBuilder str = new StringBuilder(mess);
            String ans = str.reverse().toString();
            packr.setData(ans.getBytes());
            packr.setLength(ans.length());
            ds.send(packr);
        }
    }
}

