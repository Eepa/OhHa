package neljansuora;

import java.util.Scanner;
import javax.swing.SwingUtilities;
import neljansuora.kayttoliittyma.Kayttoliittyma;
import neljansuora.peli.Neljansuora;

/**
 * Main-luokka käynnistää uuden Neljansuora-pelin joko graafisessa käyttöliittymässä
 * tai tekstikäyttöliittymässä.
 *
 * @author Eveliina Pakarinen
 */
public class Main {

    public static void main(String[] args) {

        Scanner lukija = new Scanner(System.in);

        // Graafinen kayttoliittyma
//
        Kayttoliittyma kayttoliittyma = new Kayttoliittyma(lukija);
        SwingUtilities.invokeLater(kayttoliittyma);

        kayttoliittyma.kaynnistaPeli();

//        // Tekstikayttoliittyma
//        
//        Neljansuora neljansuora = new Neljansuora(7,6,4,lukija, "teksti");
//        neljansuora.kaynnista();

    }
}
