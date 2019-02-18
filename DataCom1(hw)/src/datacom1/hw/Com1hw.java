package datacom1.hw;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
public class Com1hw {

public static ServerSocket server;
public static Socket client;
public static DataInputStream input;
public static DataOutputStream output;
public static FileWriter fw;

public static void main(String[] args) throws IOException {
    server = new ServerSocket(5555);
    client = server.accept();
    input = new DataInputStream(client.getInputStream());
    output = new DataOutputStream(client.getOutputStream());
    fw = new FileWriter("output.txt", true);
    while(true)
    {
        int sum=0;
        int mod;
        String str;
        try{
        str= input.readUTF();
        
        String[] part;
        part=str.split("#");
        for(int i = 0 ;i<part[0].length();i++)
        {
            sum+=part[0].charAt(i);
        }
        mod=sum%16;
        if(mod==Integer.parseInt(part[1]))
        {
            output.writeUTF("Received");
            System.out.println("Successed to Recieve "+str);
            fw.write(part[0]);
            fw.write(System.getProperty("line.separator"));
            fw.flush();
        }
        else
        {
            
            output.writeUTF("Error");
            System.out.println("Error Occured");
            //continue;
        }
        }
        catch(Exception e){}
        
        
        //fw.close();
    }
    
}

}
