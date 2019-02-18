package datacom2;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class DataCom2 {

public static void main(String[] args) throws IOException {

    String sum = null;

    Socket s;

    DataInputStream din;

    DataOutputStream dout;

    s = new Socket("localhost", 5000);

    dout = new DataOutputStream(s.getOutputStream());
    din = new DataInputStream(s.getInputStream());

    String str = "*";
    String fs="";
    
    char a;
    String[] st = new String[5];

    FileReader[] fr = new FileReader[5];
    Scanner[] fsc = new Scanner[5];

    for (int i = 0; i < 5; i++) {
        fr[i] = new FileReader("input" + i + ".txt");
        fsc[i] = new Scanner(fr[i]);
    }

    for (int i = 0; i < 5; i++) {
        st[i]=fsc[i].nextLine()+".";
        while (fsc[i].hasNext()) {
            st[i] += fsc[i].nextLine() + ".";
        }
    }
    
    
    for (int k = 0; k < 5; k++) {
        for (int m = 0; m < 5; m++) {
            
            String str2="";
            
            for (int x = 10 * k; x < st[m].length(); x++) {
                a = st[m].charAt(x);
                    
                    if (str2.length() > 9 ) 
                    {
                        break;
                    }
                    
                    str2 = str2 + a;
            }
            str=str+str2+"*";
        }
        System.out.println("%"+str+"%");
        dout.writeUTF(str);
        str="*";
        dout.flush();
    }
    
}
}
