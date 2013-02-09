package neljansuora;

import java.util.Scanner;
import javax.swing.SwingUtilities;
import neljansuora.kayttoliittyma.Kayttoliittyma;
import neljansuora.peli.Neljansuora;

/**
 * Main-luokka käynnistää uuden Neljansuora-pelin annettujen parametrien mukaan
 * ja luo ja käynnistää peliin graafisen käyttöliittymän.
 *
 * @author Eveliina Pakarinen
 */
public class Main {

    public static void main(String[] args) {
        // TODO code application logic here

        Scanner lukija = new Scanner(System.in);
        Neljansuora neljansuora = new Neljansuora(7, 6, 4, lukija);

        Kayttoliittyma kayttoliittyma = new Kayttoliittyma(neljansuora);
        SwingUtilities.invokeLater(kayttoliittyma);

        kayttoliittyma.kaynnistaPeli();

    }
}
