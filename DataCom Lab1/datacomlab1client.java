
package datacomlab1;
import java.net.*;
import java.io.*;
import java.util.*;
import java.io.FileReader;

public class datacomlab1client {
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        char[] array = new char[15];
        Socket s;

        DataInputStream din;

        DataOutputStream dout;

        s = new Socket("localhost", 5000);

        System.out.println(s);

        dout = new DataOutputStream(s.getOutputStream());

        try {
            File f = new File("input.txt");

            BufferedReader b = new BufferedReader(new FileReader(f));

            String readLine = "";

            while ((readLine = b.readLine()) != null) {
                array = readLine.toCharArray();
                for (int i = 0; i < array.length; i++) {

                    // System.out.println("sending character: " + array);
                    int a = (int) array[i];
                    String msg = Integer.toBinaryString(a);
                    System.out.println(msg);

                    a = a << 1;
                    if (Integer.bitCount(a) % 2 == 0) {
                        a = a | 1;
                    }
                    int r = (int) (Math.random() * 100);
                    if (r < 50) {
                        a = a | 1;
                    }
                    dout.writeUTF("" + a);
                }
                int a = (int) '\n';
                a = a << 1;
                a = a | 1;
                dout.writeUTF("" + a);
            }
                //dout.writeUTF(msg);
               // dout.writeUTF("" + a);
                
                
                dout.flush();
                dout.close();
            
            
        }
    catch (IOException e) {
            e.printStackTrace();}
    }
    
}
