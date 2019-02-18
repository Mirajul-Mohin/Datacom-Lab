
import static java.awt.SystemColor.text;
import java.math.BigInteger;

public class Test {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        String str = "12";
       // int a = Integer.parseInt(str);
        String binary = new BigInteger(str.getBytes()).toString(2);
        System.out.println("As binary: "+Integer.toBinaryString(65));
        System.out.println("ahfka    "+binary);
        
        byte[] bytes = str.getBytes();
        
        System.out.println(bytes);
        
        for(int i=0;i<bytes.length;i++){
            System.out.println(bytes[i]);
        }
        int a=Integer.parseInt("-3");
        System.out.println(a);
        
    }
    
}
