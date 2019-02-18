package DemodulationServer;

import java.io.DataInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.net.ServerSocket;
import java.net.Socket;

public class Demodulation {

    public static void main(String[] args) throws IOException {

        System.out.println("Server started....");

        ServerSocket server = new ServerSocket(8888);
        Socket client = server.accept();

        System.out.println("Client connected....");

        DataInputStream din = new DataInputStream(client.getInputStream());

        String str, s1;

        String[] user = {"0101", "0011", "0000"};
        String[] bits = new String[3];
        char ch1, ch2, ch3;

        int sum, a, b, time = 0;
        
        FileWriter[] fou = new FileWriter[3];

        for (int i = 0; i < 3; i++) {
            String filename = "output" + (i + 1) + ".txt";
            fou[i] = new FileWriter(filename, true);
        }

        while (true) {

            if (time == 2) {
                break;
            }

            str = din.readUTF();
            int l = str.length();
            System.out.println(str);
            System.out.println("length=" + l);

            bits[0] = "";
            bits[1] = "";
            bits[2] = "";

            for (int in = 0; in < l / 2; in = in + 8) {

                for (int i = 0; i < 3; i++) {
                    sum = 0;
                    for (int j = in; j < in + 8; j++) {
                        ch1 = user[i].charAt(j % 4);
                        ch2 = str.charAt(j * 2);
                        ch3 = str.charAt(j * 2 + 1);

                        a = ch3 - 48;

                        if (ch2 == '-') {
                            a *= (-1);
                        }

                        if (ch1 == '0') {
                            b = 1;
                        } else {
                            b = -1;
                        }

                        sum += (a * b);

                        if (j % 4 == 3) {
                            int c = sum / 4;
                            if (c > 0) {
                                bits[i] += "0";
                            } else {
                                bits[i] += "1";
                            }
                            System.out.println("bits:" + i + "==" + bits[i]);

                            sum = 0;
                        }

                        System.out.println("sum=" + sum);
                        System.out.println("j==" + j);

                    }

                    System.out.println("bits:" + i + "==" + bits[i]);

                }

            }

            for (int i = 0; i < 3; i++) {
                String text = new String(new BigInteger(bits[i], 2).toByteArray());
                System.out.println("As text: " + text);

                fou[i].write(text+'\n');
                
            }

            time++;
        }
        
        for (int i = 0; i < 3; i++) {
            fou[i].close();
        }
    }

}
