package datacomlab2;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Datacomlab2 {

    static ServerSocket ss;
    static Socket s;
    static DataOutputStream dout;
    static DataInputStream din;
    
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        
        System.out.println("Server START......");
        try{
        ss=new ServerSocket(5000);
        s=ss.accept();
        System.out.println("Client Connected.....");
        din=new DataInputStream(s.getInputStream());
        dout=new DataOutputStream(s.getOutputStream());
        Scanner br=new Scanner(System.in);
        String str="";
        String s1="";
        //char c= new Char
        while(true){
            str= din.readUTF();
            System.out.println(str);
            String[] part = null;

        FileWriter fw=null;
        try
        {        
        for(int i=0;i<str.length();i++){            
            part = str.split("\\*");            
        }
            for(int p=1; p<part.length-1; p++)
            {
                //System.out.println(part[p]);
                String filename = "outputfile"+p;
                //part[p]= part[p].replaceAll(".","\n");
            
            fw = new FileWriter(filename+".txt",true);
                fw.write(part[p].replace('.', '\n'));
                fw.close();                
            }
            
        } catch (Exception e)
        {
            
        }
            //break;   
        }
        }
        catch(Exception e)
        {
            
        }
    }   
}