
package neljansuora;

import java.util.Scanner;
import javax.swing.SwingUtilities;
import neljansuora.kayttoliittyma.Kayttoliittyma;
import neljansuora.peli.Neljansuora;



public class Main {
    

    public static void main(String[] args) {
        // TODO code application logic here
        
        Scanner lukija = new Scanner(System.in);
        Neljansuora neljansuora = new Neljansuora(7, 6, 4, lukija);
        
        Kayttoliittyma kayttoliittyma = new Kayttoliittyma(neljansuora);
        SwingUtilities.invokeLater(kayttoliittyma);
        
        while(kayttoliittyma.getPaivitettava() == null){
            try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                System.out.println("Piirtoalustaa ei ole vielä luotu.");
            }
        }
        
        neljansuora.setPaivitettava(kayttoliittyma.getPaivitettava());
        kayttoliittyma.kaynnistaPeli();
   
    }
}
