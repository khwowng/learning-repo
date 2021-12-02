import java.io.*;
import java.net.URL;


public class Stream2 {
    public static void main(String[] args) throws Exception
    {
        URL u = new URL(args[0]);

        try {
            InputStream is= u.openStream();

            // Länge der Datei
            int len=is.available();

            // ein Bytearray in der Länge der Datei
            byte[] myByteArray = new byte[len];

            int bytesRead=0;

            while (bytesRead < len) {
                // Lesen der Daten
                bytesRead+=is.read(myByteArray,bytesRead,len-bytesRead);
                // Ausgabe der Daten
                System.out.println(new String (myByteArray));
            }
        }

        catch (FileNotFoundException e) {
            System.out.println("File Disappeared");
        }
    }
}