
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class SpreadServer {

    public static void main(String[] args) throws IOException {
        ServerSocket ss = new ServerSocket(1234);
        Socket s = ss.accept();
        DataOutputStream dos = new DataOutputStream(s.getOutputStream());
        DataInputStream dis = new DataInputStream(s.getInputStream());

        String code1 = "0 1 0 1";
        String code2 = "0 0 1 1";
        String code3 = "0 0 0 0";
        code1 += " " + code1;
        code2 += " " + code2;
        code3 += " " + code3;
        code1 = code1.replaceAll("\\s", "");
        code2 = code2.replaceAll("\\s", "");
        code3 = code3.replaceAll("\\s", "");
        Scanner sc1 = new Scanner(new File("D:\\f1.txt"));
        Scanner sc2 = new Scanner(new File("D:\\f2.txt"));
        Scanner sc3 = new Scanner(new File("D:\\f3.txt"));

        int codeInt1 = Integer.parseUnsignedInt(code1, 2);
        int codeInt2 = Integer.parseUnsignedInt(code2, 2);
        int codeInt3 = Integer.parseUnsignedInt(code3, 2);

        for (int counter = 0; counter < 4; counter++) {
            String s1 = sc1.nextLine();
            String s2 = sc2.nextLine();
            String s3 = sc3.nextLine();

            s1 = s1.replaceAll("\\s", "");
            s2 = s2.replaceAll("\\s", "");
            s3 = s3.replaceAll("\\s", "");

            if (s1.equals("00")) {
                s1 = "00000000";
            }
            if (s1.equals("01")) {
                s1 = "00001111";
            }
            if (s1.equals("10")) {
                s1 = "11110000";
            }
            if (s1.equals("11")) {
                s1 = "11111111";
            }

            if (s2.equals("00")) {
                s2 = "00000000";
            }
            if (s2.equals("01")) {
                s2 = "00001111";
            }
            if (s2.equals("10")) {
                s2 = "11110000";
            }
            if (s2.equals("11")) {
                s2 = "11111111";
            }

            if (s3.equals("00")) {
                s3 = "00000000";
            }
            if (s3.equals("01")) {
                s3 = "00001111";
            }
            if (s3.equals("10")) {
                s3 = "11110000";
            }
            if (s3.equals("11")) {
                s3 = "11111111";
            }

            int dataInt1 = Integer.parseUnsignedInt(s1, 2);
            int dataInt2 = Integer.parseUnsignedInt(s2, 2);
            int dataInt3 = Integer.parseUnsignedInt(s3, 2);

            String xor1 = String.format("%8s", Integer.toBinaryString(codeInt1 ^ dataInt1)).replace(' ', '0');
            String xor2 = String.format("%8s", Integer.toBinaryString(codeInt2 ^ dataInt2)).replace(' ', '0');
            String xor3 = String.format("%8s", Integer.toBinaryString(codeInt3 ^ dataInt3)).replace(' ', '0');

            int[] v1 = new int[8];
            int[] v2 = new int[8];
            int[] v3 = new int[8];

            for (int i = 0; i < 8; i++) {
                if (xor1.charAt(i) == '0') {
                    v1[i] = 1;
                }
                if (xor1.charAt(i) == '1') {
                    v1[i] = -1;
                }
                //System.out.println(v1[i]);
            }

            for (int i = 0; i < 8; i++) {
                if (xor2.charAt(i) == '0') {
                    v2[i] = 1;
                }
                if (xor2.charAt(i) == '1') {
                    v2[i] = -1;
                }
                // System.out.println(v2[i]);
            }

            for (int i = 0; i < 8; i++) {
                if (xor3.charAt(i) == '0') {
                    v3[i] = 1;
                }
                if (xor3.charAt(i) == '1') {
                    v3[i] = -1;
                }
                //System.out.println(v3[i]);
            }

            int[] sum = new int[8];
            String composite = "";
            for (int i = 0; i < 8; i++) {
                sum[i] = v1[i] + v2[i] + v3[i];
                composite += Integer.toString(sum[i]);
                if (i != 7) {
                    composite += " ";
                }
            }
            System.out.println(composite);
            dos.writeUTF(composite);
        }
    }
}
