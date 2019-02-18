package datacom1.hw;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.net.Socket;
import java.util.Random;
import java.util.Scanner;

public class DataCom1Hw {

/**
 * @param args the command line arguments
 */
public static void main(String[] args) throws IOException {
    // TODO code application logic here

    Socket echoSocket = new Socket("localhost", 5555);
    System.out.println("serverconnected");
    DataInputStream din = new DataInputStream(echoSocket.getInputStream());
    DataOutputStream dout = new DataOutputStream(echoSocket.getOutputStream());

    FileReader fr = new FileReader("input.txt");

    Scanner sc = new Scanner(fr);
    int sum = 0, mod;
    String st;
    Random rd = new Random();

    while (sc.hasNext()) {
        st = sc.nextLine();
        for (int i = 0; i < st.length(); i++) {
            sum += st.charAt(i);
        }
        mod = sum % 16;
        
        if (rd.nextInt(100) < 30) {
            dout.writeUTF(st + "#" + mod + 1);
            System.out.println("SENT(Error) "+st + "#" + mod+1);
            dout.flush();
        } else {
            dout.writeUTF(st + "#" + mod);
            System.out.println("SENT "+st + "#" + mod);
            dout.flush();
        }
        String stt = din.readUTF();
        sum = 0;
        if (stt.equals("Error")) {
            for (int i = 0; i < st.length(); i++) {
                sum += st.charAt(i);
            }
            mod = sum % 16;
            System.out.println("AGAIN SENT "+st + "#" + mod);
            dout.writeUTF(st + "#" + mod);
            dout.flush();
            sum = 0;
            
        }


    }
    dout.close();

}

}
