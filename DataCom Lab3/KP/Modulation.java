package ModulationServer;

import static java.awt.SystemColor.text;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;
import java.net.Socket;
import java.util.Scanner;

public class Modulation {

    public static void main(String[] args) throws IOException {

        System.out.println("Client started.....");

        Socket client = new Socket("localhost", 8888);

        DataInputStream din = new DataInputStream(client.getInputStream());
        DataOutputStream dout = new DataOutputStream(client.getOutputStream());

        System.out.println("Server Connected.........");

        FileReader file1 = new FileReader("input1.txt");
        FileReader file2 = new FileReader("input2.txt");
        FileReader file3 = new FileReader("input3.txt");

        Scanner[] sc = new Scanner[3];

        sc[0] = new Scanner(file1);
        sc[1] = new Scanner(file2);
        sc[2] = new Scanner(file3);

        try {
            for (int i = 0; i < 2; i++) {

                String[] user = {"0101", "1010", "0011", "1100", "0000", "1111"};

                String[] line = new String[3];
                String[] binary = new String[3];

                for (int j = 0; j < 3; j++) {
                    line[j] = sc[j].nextLine();
                    binary[j] = new BigInteger(line[j].getBytes()).toString(2);
                    binary[j] = "0" + binary[j];
                    System.out.println("binary:" + j + ":" + binary[j]);
                }

                String str = "";
                char ch;
                int l = binary[0].length();

                String voltage = "";

                for (int j = 0; j < l; j = j + 2) {

                    String xor[] = new String[3];

                    for (int k = 0; k < 3; k++) {

                        xor[k] = "";

                        for (int m = j; m < j + 2; m++) {
                            ch = binary[k].charAt(m);
                            if (ch == '0') {
                                xor[k] += user[k * 2];
                            } else {
                                xor[k] += user[k * 2 + 1];
                            }

                        }

                        System.out.println("XOR" + k + " " + xor[k]);
                    }

                    for (int k = 0; k < 8; k++) {
                        int a = 0;
                        for (int m = 0; m < 3; m++) {
                            ch = xor[m].charAt(k);
                            if (ch == '0') {
                                a++;
                            } else {
                                a--;
                            }
                        }

                        if (a > 0) {
                            voltage += "+" + a;
                        } else {
                            voltage += a;
                        }
                    }

                    System.out.println("Voltage" + voltage);
                }

                System.out.println("Voltage" + voltage);
                dout.writeUTF(voltage);
                dout.flush();
            }

        } catch (Exception e) {
        }

    }
}
