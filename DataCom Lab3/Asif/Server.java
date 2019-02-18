package datacomlab03;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server {

    public static void main(String[] args) throws IOException {

        ServerSocket ss;
        Socket s;
        DataInputStream din;
        DataOutputStream dout;
        System.out.println("Server START......");
        ss = new ServerSocket(2200);
        s = ss.accept();
        System.out.println("Client Connected.....");
        din = new DataInputStream(s.getInputStream());
        dout = new DataOutputStream(s.getOutputStream());
        String[] sc = {"01010101", "00110011", "00000000"};
        String[] code = new String[3];

        for (int j = 0; j < 3; j++) {

            String FILENAME = "D:\\filename" + j + ".txt";

            try (BufferedReader br = new BufferedReader(new FileReader(FILENAME))) {

                String in;

                while ((in = br.readLine()) != null) {
                    //System.out.println(in);
                    List<Integer> ascii = new ArrayList<>();

                    code[j] = "";
                    for (char c : in.toCharArray()) {
                        ascii.add((int) c);
                        String intString = String.format("%08d", Integer.parseInt(Integer.toBinaryString((int) c)));
                    //System.out.println(c+" "+intString);

                        for (int i = 0; i < 4; i++) {

                            String s1 = Character.toString(intString.charAt(i * 2));
                            String s2 = Character.toString(intString.charAt(i * 2 + 1));
                            s1 = s1 + s1 + s1 + s1;
                            s2 = s2 + s2 + s2 + s2;
                            
                            String bitf = s1 + s2;
                            for (int k = 0; k < 8; k++) {
                                int q1 = Character.getNumericValue(bitf.charAt(k));
                                int q2 = Character.getNumericValue(sc[j].charAt(k));
                                int q3 = q1 ^ q2;
                                code[j] += q3;
                            }
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        
        String signal = "";
        for (int j = 0; j < code[0].length(); j++) {
            int[] w = new int[3];
            for (int i = 0; i < 3; i++) {
                if (Character.getNumericValue(code[i].charAt(j)) == 0) {
                    w[i] = 1;
                } else {
                    w[i] = -1;
                }
            }
            int total =w[0] + w[1] + w[2];
            signal += Integer.toString(total);
        }
        System.out.println(signal);
        dout.writeUTF(signal);
        dout.flush();
    }
}
