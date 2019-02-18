package datacom1;

import java.net.*;
import java.io.*;
import java.util.*;

public class DataCom1 {

public static void main(String args[]) throws IOException {
    Socket clientSocket;
    DataInputStream dinC;
    DataOutputStream doutC;
    clientSocket = new Socket("localhost", 5555);
    System.out.println(clientSocket);

    dinC = new DataInputStream(clientSocket.getInputStream());
    doutC = new DataOutputStream(clientSocket.getOutputStream());

    String s1, st;
    char ch;

    File fl = new File("input.txt");
    BufferedReader rd = new BufferedReader(new FileReader(fl));

    while ((s1 = rd.readLine()) != null) {

        Random r = new Random();

        for (int i = 0; i < s1.length(); i++) {
            ch = s1.charAt(i);
            int t = ch;
            int x = t << 1;

            int cnt = Integer.bitCount(x);

            if (cnt % 2 == 0) {
                if (r.nextInt(40) == 1) {
                    doutC.writeUTF(Integer.toBinaryString(x));
                    doutC.flush();
                } 
                else {
                    x = x | 1;
                    doutC.writeUTF(Integer.toBinaryString(x));
                    doutC.flush();
                }

            } else {
                String s = Integer.toBinaryString(x);
                doutC.writeUTF(s);
                doutC.flush();

            }

        }
        doutC.writeUTF("10101");
        doutC.flush();
    }

}

}
