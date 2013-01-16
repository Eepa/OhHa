
package neljansuora;

import java.util.Scanner;
import neljansuora.peli.Neljansuora;



public class Main {
    

  
    public static void main(String[] args) {
        // TODO code application logic here
        
        Scanner lukija = new Scanner(System.in);
        
        Neljansuora neljansuora = new Neljansuora(6, 7, lukija);
        
        neljansuora.kaynnista();
        
   
    }
}
