import java.io.*;
public class Stream1 {
    public static void main(String[] args) throws Exception
    {
        File f = new File(args[0]);
        FileInputStream is = new FileInputStream(f);
        int len = (int)f.length();
        byte[] data = new byte[len];
        int lenr = 0;
        while (lenr < len) {
            lenr += is.read(data,lenr,len-lenr);
        }
        String mess = new String (data);
        System.out.println(mess);
    }
}