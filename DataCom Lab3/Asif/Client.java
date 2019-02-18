package datacomlab03;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class Client {

    public static void main(String[] args) throws IOException {
        Socket s;
        DataInputStream din;
        DataOutputStream dout;
        s = new Socket("192.168.1.20", 2200);
        din = new DataInputStream(s.getInputStream());
        dout = new DataOutputStream(s.getOutputStream());
        String ss;

        ss = din.readUTF();
        System.out.print(ss + " ");
        dout.flush();

        String[] sc = {"01010101", "00110011", "00000000"};

        int ptr = 0;
        int[] num = new int[32];
        for (int pos = 0; pos < ss.length(); ++pos) {
            if (ss.charAt(pos) != '-') {
                num[ptr++] = Character.getNumericValue(ss.charAt(pos));
            }
        }

        ptr = 0;
        for (int pos = 0; pos < ss.length(); ++pos) {
            if (ss.charAt(pos) != '-') {
                ptr++;
            } else {
                num[ptr] = -num[ptr];
            }
        }

        String[] dec = new String[3];
        for (int j = 0; j < 3; j++) {
            dec[j] = "";
            for (int i = 0; i < 4; i++) {
                dec[j] += sc[j];
            }
        }
        
        int[][] x = new int[3][32];
        int[] sum = new int[32];

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 32; j++) {
                if (dec[i].charAt(j) == '0') {
                    x[i][j] = num[j];
                } else {
                    x[i][j] = -num[j];
                }
            }
        }

        for (int i = 0; i < 3; i++) {
            String bit = "";
            for (int j = 0; j < 8; j++) {
                if (((x[i][j * 4] + x[i][j * 4 + 1] + x[i][j * 4 + 2] + x[i][j * 4 + 3]) / 4) == 1) {
                    bit += "0";
                } else {
                    bit += "1";
                }
            }
            int parseInt = Integer.parseInt(bit, 2);
            char c = (char) parseInt;

            try {
                PrintWriter writer = new PrintWriter("D:\\fileout"+i+".txt", "UTF-8");
                writer.println(c);
                writer.close();
            } catch (IOException e) {
            }
        }
    }
}
