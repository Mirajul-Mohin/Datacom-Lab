
package datacomlab1;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Datacomlab1 {

    static ServerSocket ss;
    static Socket s;
    static DataOutputStream dout;
    static DataInputStream din;
    
    public static void main(String[] args) throws IOException{
        // TODO code application logic here
        FileWriter fw = new FileWriter("output.txt", true);
        String msing = "";
        try {
            ss = new ServerSocket(5000);
            s = ss.accept();

            din = new DataInputStream(s.getInputStream());

            dout = new DataOutputStream(s.getOutputStream());
            while (true) {
                msing = din.readUTF();
                //System.out.println(msing);

                int a = Integer.parseInt(msing);
                int numberOfOnes = Integer.bitCount(a);

                if (numberOfOnes % 2 == 0) {
                    System.err.println("Error in Parity !");
                } else {

                    a = a >> 1;
                    if(a!=10){
                    char c = (char) a;
                    System.out.println("Writing Character : " + c);
                    fw.write(c);
                    //fw.close();
                    }
                    else 
                    fw.write("\r\n");
                    //fw.close();
                }

            }

        } catch (Exception e) {

        }
        fw.close();
    }
    
}
