package datacom2.hw;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Com2hw {

static FileWriter[] fw;
static DataInputStream is;
static DataOutputStream os;

public static void main(String[] args) throws IOException {

    ServerSocket server = new ServerSocket(5555);
    Socket client = server.accept();
    System.out.println("client connected");
    os = new DataOutputStream(client.getOutputStream());
    is = new DataInputStream(client.getInputStream());
    fw = new FileWriter[5];

    for (int i = 0; i < 5; i++) {
        fw[i] = new FileWriter("output" + i + ".txt");
    }

    while (true) {

        String msg = is.readUTF();
        if (msg.equals("stop")) {
            break;
        }
        System.out.println(msg);
        filewriting(msg);
    }
    for (int i = 0; i < 5; i++) {
        fw[i].close();
    }

}

public static void filewriting(String msg) throws IOException {
    String[] part = msg.split("#");
    for (int i = 1; i < part.length - 1;) {
        int source = Integer.parseInt(part[i]);
        i++;
        int dest = Integer.parseInt(part[i]);
        i++;
        String data = part[i];

        String tmp[] = part[i].split("\\*");
        if (tmp.length > 1) 
        {
            for (int j = 0; j < tmp.length - 1; j++) 
            {
               
                fw[dest].write(tmp[j]);
                fw[dest].write(System.getProperty("line.separator"));
                
                
            }
            fw[dest].write(tmp[tmp.length - 1]);
        } else {
            fw[dest].write(data);
        }
        

        i++;
    }

}
}
