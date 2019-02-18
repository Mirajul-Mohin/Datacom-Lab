package datacom1;

import java.net.*;
import java.io.*;
import java.util.*;

public class Com1 {

public static void main(String[] args) throws IOException {
    ServerSocket ss;
    Socket s;
    DataInputStream din;
    DataOutputStream dout;
    System.out.println("Server START......");
    ss = new ServerSocket(5555);
    s = ss.accept();
  
    
    System.out.println("Client Connected.....");
    din = new DataInputStream(s.getInputStream());
    dout = new DataOutputStream(s.getOutputStream());

    File fll = new File("output.txt");
    BufferedWriter bww = new BufferedWriter(new FileWriter(fll));

    String str = "";
    while (true) {
        try {

            str = din.readUTF();

            int r = Integer.parseInt(str, 2);
            if(Integer.bitCount(r)%2==0)
            {
                System.err.println("Error in Parity");
            }
            else{
            r = r >> 1;

            char c = (char) r;

            bww.append(c);
            System.out.print(c);
            }
        } catch (Exception e) {
        }

        bww.flush();
        dout.flush();
    }

}

}
