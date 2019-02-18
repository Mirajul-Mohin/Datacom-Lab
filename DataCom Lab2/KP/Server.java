
package Server;

import java.io.DataInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static void main(String[] args) throws IOException {
        
        System.out.println("Server started....");
        
        ServerSocket server = new ServerSocket(8888);
        Socket client = server.accept();
        
        System.out.println("Client connected....");
        
        DataInputStream din = new DataInputStream(client.getInputStream());
        
        String str;
        String s1;
        int time=0;
        
        while(true){
            
            if(time==5)break;
            
            str = din.readUTF();
            System.out.println(str);
            String ara[] = new String[6];
            
            ara= str.split("@@");
            
            for(int i=0;i<5;i++){
                
                String filename = "output"+i+".txt";
                FileWriter fou = new FileWriter(filename,true);
                fou.write(ara[i+1].replace('#', '\n'));
                fou.close();
            }
            
            time++;
        }
        
    }
    
}
