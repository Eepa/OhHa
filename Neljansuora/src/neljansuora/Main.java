
package neljansuora;

import java.util.Scanner;
import javax.swing.SwingUtilities;
import neljansuora.kayttoliittyma.Kayttoliittyma;
import neljansuora.peli.Neljansuora;



public class Main {
    

  
    public static void main(String[] args) {
        // TODO code application logic here
        Kayttoliittyma kayttoliittyma = new Kayttoliittyma();
        SwingUtilities.invokeLater(kayttoliittyma);
        
        Scanner lukija = new Scanner(System.in);
        
        Neljansuora neljansuora = new Neljansuora(7, 6, 4, lukija);
        
        neljansuora.kaynnista();
        
        
   
    }
}
