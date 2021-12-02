import java.io.*;
public class Stream3 {
    public static void main(String[] args) throws Exception
    {
        File f = new File(args[0]);
        try {
            FileInputStream fis = new FileInputStream(f);
            // ByteArrayOutputStream und Bytearray zu 128 Byte
            ByteArrayOutputStream bos=new ByteArrayOutputStream(128);
            byte data[]=new byte[128];
            // Daten lesen
            int lenr = 0;
            while ((lenr=fis.read(data))!=-1) {
                bos.write(data,0,lenr);
            }
            // Daten ausgeben
            data=bos.toByteArray();
            System.out.println(new String(data));
        }
        catch (FileNotFoundException e) {
            System.out.println("File Disappeared");
        }
    }
}