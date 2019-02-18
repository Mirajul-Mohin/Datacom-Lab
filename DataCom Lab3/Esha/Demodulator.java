
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.*;
import java.util.Scanner;

public class Demodulator {

    public static void main(String[] args) throws IOException {
        Socket s = new Socket("localhost", 1234);
        DataOutputStream dos = new DataOutputStream(s.getOutputStream());
        DataInputStream dis = new DataInputStream(s.getInputStream());
//        Scanner sc = new Scanner(new File("D:\\input.txt"));

        // String composite = "-1 -3 +1 -1 +1 -1 -1 -3";
        FileWriter fw1 = new FileWriter("e:\\user1out.txt");
        FileWriter fw2 = new FileWriter("e:\\user2out.txt");
        FileWriter fw3 = new FileWriter("e:\\user3out.txt");

        for(int counter=0;counter<4;counter++){
        String composite = dis.readUTF();
        String code1 = "0 1 0 1";
        String code2 = "0 0 1 1";
        String code3 = "0 0 0 0";
        code1 += " " + code1;
        code2 += " " + code2;
        code3 += " " + code3;

        String[] c1 = code1.split(" ");
        String[] c2 = code2.split(" ");
        String[] c3 = code3.split(" ");

        int[] v1 = new int[8];
        int[] v2 = new int[8];
        int[] v3 = new int[8];

        for (int i = 0; i < 8; i++) {
            if (c1[i].equals("0")) {
                v1[i] = 1;
            }
            if (c1[i].equals("1")) {
                v1[i] = -1;
            }
        }

        for (int i = 0; i < 8; i++) {
            if (c2[i].equals("0")) {
                v2[i] = 1;
            }
            if (c2[i].equals("1")) {
                v2[i] = -1;
            }
        }

        for (int i = 0; i < 8; i++) {
            if (c3[i].equals("0")) {
                v3[i] = 1;
            }
            if (c3[i].equals("1")) {
                v3[i] = -1;
            }
        }

        int total1 = 0;
        int total2 = 0;
        int total3 = 0;

        String[] signals = composite.split(" ");
        String data1 = "";
        String data2 = "";
        String data3 = "";

        for (int i = 0; i < 8; i++) {
            int voltage = Integer.parseInt(signals[i]);
            voltage *= v1[i];
            total1 += (voltage);
            if (i == 3 || i == 7) {
                total1 /= 4;
                if (total1 > 0) {
                    data1 += "0 ";
                }
                if (total1 < 0) {
                    data1 += "1 ";
                }
                total1 = 0;
            }
        }

        for (int i = 0; i < 8; i++) {
            int voltage = Integer.parseInt(signals[i]);
            voltage *= v2[i];
            total2 += (voltage);
            if (i == 3 || i == 7) {
                total2 /= 4;
                if (total2 > 0) {
                    data2 += "0 ";
                }
                if (total2 < 0) {
                    data2 += "1 ";
                }
                total2 = 0;
            }
        }

        for (int i = 0; i < 8; i++) {
            int voltage = Integer.parseInt(signals[i]);
            voltage *= v3[i];
            total3 += (voltage);
            if (i == 3 || i == 7) {
                total3 /= 4;
                if (total3 > 0) {
                    data3 += "0 ";
                }
                if (total3 < 0) {
                    data3 += "1 ";
                }
                total3 = 0;
            }
        }

        fw1.write(data1);
        fw1.write("\r\n");
        fw2.write(data2);
        fw2.write("\r\n");
        fw3.write(data3);
        fw3.write("\r\n");

        }
        fw1.close();
        fw2.close();
        fw3.close();
    }
}
