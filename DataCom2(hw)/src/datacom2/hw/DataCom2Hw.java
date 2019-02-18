package datacom2.hw;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.net.Socket;
import java.util.Random;
import java.util.Scanner;

public class DataCom2Hw {

public static void main(String[] args) throws IOException {

        Socket echoSocket = new Socket("localhost", 5555);
        System.out.println("serverconnected");
        DataInputStream din = new DataInputStream(echoSocket.getInputStream());
        DataOutputStream dout = new DataOutputStream(echoSocket.getOutputStream());
        
        FileReader[] fr= new FileReader[5];
        Scanner[] fsc= new Scanner[5];
        String[] s= new String[5];
        
        for(int i = 0 ; i< 5 ; i++)
        {
             fr[i]=new FileReader("input"+i+".txt");
             fsc[i]=new Scanner(fr[i]);
        }
        for(int i=0 ; i<5 ; i++)
        {
             s[i]=fsc[i].nextLine();
             while(fsc[i].hasNext())
             {
                 s[i]+="*"+fsc[i].nextLine();
             }
        }
        int[] l = new int[5];
        
        for(int i =0 ; i< 5; i++)
        {
            l[i]=0;
        }
        
        String temp;
        
        while(l[0]<s[0].length()||l[1]<s[1].length()||l[2]<s[2].length()||l[3]<s[3].length()||l[4]<s[4].length())
        {
            Random r = new Random();
            
            int rd= r.nextInt(5);
            temp="start";
            
            for(int i = 0 ; i< 5 ; i++)
            {
                if(rd==i)
                    continue;
                temp+="#"+i+"#"+i+"#";
                
                if(s[i].length()>l[i]+10)
                {
                    temp+=s[i].substring(l[i],l[i]+10);
                }
                else if(s[i].length()>l[i])
                {
                    temp+=s[i].substring(l[i],s[i].length());
                }
                l[i]+=10;
            }
            temp+="#end";
            System.out.println(temp);
            dout.writeUTF(temp);
            dout.flush();
        }
        dout.writeUTF("stop");
        dout.close();
        for(int i  = 0 ; i<5 ; i++)
        {
            fr[i].close();
        }
        echoSocket.close();
        
        
}

}
