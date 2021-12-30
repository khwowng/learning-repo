import java.io.*;

class Stream4 {
    public static void main(String[] args) throws Exception
    {
        File f = new File(args[0]);
        try {
            // FileReader und CharArrayWriter statt FileInputStream und ByteArrayOutputStream
            FileReader fr = new FileReader(f);
            CharArrayWriter cw = new CharArrayWriter(128);

            // Char Array in 128 Chars Blöcke
            char data[]=new char[128];

            // Lesen von Daten
            int lenr = 0;
            while ((lenr=fr.read(data)) != -1) {
                cw.write(data,0,lenr);
            }

            // Ausgabe von Daten
            data= cw.toCharArray();
            System.out.println(new String(data));

        }
        catch (FileNotFoundException e) {
            System.out.println("File Disappeared");
        }
    }
}