import java.net.*;
import java.io.*;
import java.awt.*;
import java.awt.event.*;

// URL: https://www.informatik.htw-dresden.de/~beck/PSPI/DownloadPraktikum/Textdatei/Selbstkritik.txt
// Host: www.informatik.htw-dresden.de
// Port: 80
// File: ~beck/PSPI/DownloadPraktikum/Textdatei/Selbstkritik.txt

public class ConnectionAWT extends Panel
{
    TextArea output = new TextArea(15,80);

    Panel top = new Panel(new FlowLayout());
    Choice ch = new Choice();
    Panel inputs = new Panel(new BorderLayout());
    CardLayout cl = new CardLayout();

    Panel URLPanel = new Panel(new FlowLayout());
    Panel URLConnectionPanel = new Panel(new FlowLayout());
    Panel socketPanel = new Panel(new FlowLayout());

    TextField URLTF = new TextField(50);
    TextField URLConnectionTF = new TextField(50);
    TextField socketHostTF = new TextField("host",30);
    TextField socketPortTF = new TextField("port",5);
    TextField socketFileTF = new TextField("file",40);

    byte[] getData(InputStream is) throws Exception {
        ByteArrayOutputStream bos = new ByteArrayOutputStream(1024);
        byte data[] = new byte[1024];
        int lenr;
        while((lenr = is.read(data)) != -1) {
            bos.write(data, 0 ,lenr);
        }
        data = bos.toByteArray();
        return data;
    }


    ActionListener aURL = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            try
            {
                URL url = new URL(URLTF.getText());
                InputStream is = url.openStream();
                String mess = new String(getData(is));
                output.setText(mess);
                is.close();
            }
            catch (Exception e1) {
                System.out.println(e1);
                System.exit(1);
            }
        }
    };

    ActionListener aURLC = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                URL url = new URL(URLConnectionTF.getText());
                URLConnection uc = url.openConnection();
                InputStream is = uc.getInputStream();
                String mess = new String(getData(is));
                output.setText(mess);
            }
            catch (Exception e2) {
                System.out.println(e2);
                System.exit(1);
            }
        }
    };

    ActionListener aSocket = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            try
            {
                Socket s;
                InetAddress ihost = InetAddress.getByName(socketHostTF.getText());
                s = new Socket(ihost,Integer.parseInt(socketPortTF.getText()));

                PrintStream	O=new PrintStream(s.getOutputStream());
                O.println("GET /"+ socketFileTF.getText() + "\r\n" );
                O.println("Host: "+ socketHostTF.getText() + ":" + Integer.parseInt(socketPortTF.getText())+"\r\n" );

                InputStream is = s.getInputStream();
                String mess = new String(getData(is));
                output.setText(mess);

                s.close();
            }
            catch (Exception e3) {
                System.out.println(e3);
                e3.printStackTrace();
            }

        }
    };

    ItemListener choiceListener = new ItemListener() {
        @Override
        public void itemStateChanged(ItemEvent i) {
            output.setText("");
            String connectChoice = i.getItem().toString();
            cl.show(inputs, connectChoice);
        }
    };

    public ConnectionAWT(){

        ch.add("URL");
        ch.add("URL-Connection");
        ch.add("Socket");
        ch.addItemListener(choiceListener);

        URLPanel.add(URLTF);
        URLTF.addActionListener(aURL);

        URLConnectionPanel.add(URLConnectionTF);
        URLConnectionTF.addActionListener(aURLC);

        socketPanel.add(socketHostTF);
        socketPanel.add(socketPortTF);
        socketPanel.add(socketFileTF);
        socketFileTF.addActionListener(aSocket);

        inputs.setLayout(cl);
        inputs.add(URLPanel, "URL");
        inputs.add(URLConnectionPanel, "URL-Connection");
        inputs.add(socketPanel, "Socket");

        top.add(ch);
        top.add(inputs);

        this.setLayout(new BorderLayout());
        this.add(top, BorderLayout.NORTH);
        this.add(output, BorderLayout.SOUTH);

    }

    public static void main(String args[])
    {
        Test p=new Test();
        Frame f=new Frame("FlowLayoutPanel");
        f.add(p);
        f.addWindowListener(new WindowAdapter(){public void windowClosing(WindowEvent e){System.exit(0);}});
        f.setVisible(true);
        f.pack();
    }

}