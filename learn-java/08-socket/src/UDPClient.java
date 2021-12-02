/*
Name: Thanh Ha Khuong
Studiengruppe: 20/042/61
E-Mail: thanhha.khuong@htw-dresden.de
*/

import java.awt.*;
import java.awt.event.*;
import java.net.*;

public class UDPClient extends Panel{

    String myHost;
    int myPort;
    byte[] mess;

    // Panel Components
    GridBagConstraints gbc = new GridBagConstraints();
    Label hostL = new Label("My Host ");
    Label portL = new Label("My Port ");
    Label messL = new Label("Message ");
    TextField hostTF = new TextField(50);
    TextField portTF = new TextField(50);
    TextField messTF = new TextField(50);

    public UDPClient() {

        setFont(new Font("System", Font.PLAIN, 24));
        setLayout(new GridBagLayout());
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weighty = 1.0; gbc.weightx = 1.0;

        gbc.gridy = 0;
        add(hostL,gbc);
        add(hostTF,gbc);

        gbc.gridy = 1;
        add(portL,gbc);
        add(portTF,gbc);

        gbc.gridy = 2;
        add(messL,gbc);
        add(messTF,gbc);

        // Datagramm versenden und empfangen
        messTF.addActionListener(start);
    }

    ActionListener start = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {

            // Info vom TextFields uebernehmen
            myHost = hostTF.getText();
            myPort = Integer.parseInt(portTF.getText());
            mess = messTF.getText().getBytes();

            try {

                // Liefert Internetadresse
                InetAddress addr = InetAddress.getByName(myHost);

                // Das Datenpaket
                DatagramPacket packs = new DatagramPacket(mess, mess.length, addr, myPort);

                // Der SendeSocket
                DatagramSocket ds = new DatagramSocket();
                ds.send(packs);

                // Antwort empfangen
                DatagramPacket packr = new DatagramPacket(new byte[1024], 1024);
                ds.receive(packr);

                // Warten auf Serverantwort
                String messr = new String(packr.getData(), 0, packr.getLength());
                System.out.println(messr);
                messTF.setText(messr);

                ds.close();
            }

            catch (Exception e1) {
                System.out.println(e1);
                System.exit(1);
            }
        }
    };


    public static void main(String[] args) throws Exception {
        TestClient client = new TestClient();
        Frame F = new Frame();
        F.add(client);
        F.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        F.setVisible(true);
        F.pack();

    }
}
