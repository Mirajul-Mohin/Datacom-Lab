package datacom2;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Com2 {

static ServerSocket ss;
static Socket s;
static DataOutputStream dout;
static DataInputStream din;

public static void main(String[] args) throws IOException {
    // TODO code application logic here

    System.out.println("Server START......");
    try {
        ss = new ServerSocket(5000);
        s = ss.accept();
        System.out.println("Client Connected.....");
        din = new DataInputStream(s.getInputStream());
        dout = new DataOutputStream(s.getOutputStream());
        Scanner br = new Scanner(System.in);
        String str = "";
        String s1 = "";
        //char c= new Char
        int i = 0;
        while (true) {
            str = din.readUTF();

            String[] part = null;

            FileWriter fw = null;
            try {

                for (int f = 0; f < str.length(); f++) {
                    part = str.split("\\*");
                }

                for (int p = 1; p < part.length; p++) {

                    String filename = "output" + p;

                    fw = new FileWriter(filename + ".txt", true);

                    fw.write(part[p].replace('.', '\n'));
                    fw.close();
                }

            } catch (Exception e) {

            }
            //break;   
        }
    } catch (Exception e) {

    }
}
}
