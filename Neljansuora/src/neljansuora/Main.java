
package neljansuora;

import java.util.Scanner;
import javax.swing.SwingUtilities;
import neljansuora.kayttoliittyma.Kayttoliittyma;
import neljansuora.peli.Neljansuora;



public class Main {
    

  
    public static void main(String[] args) {
        // TODO code application logic here
        Scanner lukija = new Scanner(System.in);
        
        Kayttoliittyma kayttoliittyma = new Kayttoliittyma(new Neljansuora(7, 6, 4, lukija));
        SwingUtilities.invokeLater(kayttoliittyma);
        
   
    }
}
