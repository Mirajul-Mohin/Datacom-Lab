package datacomlab2client;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Datacomlab2client {

    public static void main(String[] args) throws IOException{
        // TODO code application logic here
        char[] array = new char[15];

        String sum = null;

        Socket s;

        DataInputStream din;

        DataOutputStream dout;

        char a;
        String msgser=null;
        s = new Socket("localhost", 5000);

        dout = new DataOutputStream(s.getOutputStream());
        din = new DataInputStream(s.getInputStream());

        String str = "*";
        
        int k=0;
        
        FileReader[] fr = new FileReader[5];        
               
        try{
            for(k=0; k<5; k++){
            for(int l=0; l<5; l++)
            {
                String filename = "inputfile"+l;

            fr[l] = new FileReader(filename+".txt");
            }
            
            for(int l =0; l<5; l++){
                Scanner sc = new Scanner(fr[l]);
            
            String str2 = "";
            String lin="";
            
            while(sc.hasNext()){
                lin = lin+sc.nextLine()+".";
            }
                for (int i = 10*k; i < lin.length(); i++) {
                    a = lin.charAt(i);                
                    if(str2.length()>9) break;
                    str2 = str2+a;
                }                              
                       
            str = str+str2+"*";           
            }
            System.out.println("%"+str+"%");
            dout.writeUTF("%"+str+"%");
            dout.flush();
            str="*";            
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        }
    }
