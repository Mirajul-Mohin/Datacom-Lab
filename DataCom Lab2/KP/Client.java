
package Client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Client {

  
    public static void main(String[] args) throws IOException {
        
        System.out.println("Client started.....");
        
        Socket client = new Socket("localhost",8888);
        
        DataInputStream din = new DataInputStream(client.getInputStream());
        DataOutputStream dout = new DataOutputStream(client.getOutputStream());
        
        System.out.println("Server Connected.........");
        
        try {
            for(int i=0;i<5;i++){
                
                String str="@";
                
                FileReader[] file = new FileReader[5];
                
                for(int j=0;j<5;j++){
                    
                    String str1="@",line="";
                    char ch;
                    String filename = "input"+j+".txt";
                    file[j] = new FileReader(filename);
                    Scanner sc = new Scanner(file[j]);
                    
                    while(sc.hasNext()){
                        line += sc.nextLine()+"#";
                    }
                        int l = line.length();
                        
                        for(int k=10*i;k<l;k++){
                            ch = line.charAt(k);
                            if(str1.length()>10)
                                break;
                            
                            str1+=ch;
                        }
                    
                    System.out.println("*"+str1+"*");
                    
                    str+=str1+"@";
                    
                    System.out.println("*"+str+"*");
                }
                
                System.out.println("*"+str+"*");
                    
                dout.writeUTF(str+"@");
                dout.flush();
                    
            }
        } catch (Exception e) {
        }
    }
    
}
