
package neljansuora.kayttoliittyma;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Luokka kuvaa tapahtumankuuntelijaa, joka tarkkailee painetaanko ValikkoPanelin nappulaa "Uusi peli".
 * Jos nappulaa painetaan, tapahtumankuuntelija aloittaa uuden pelin.
 * @author Eveliina Pakarinen
 */

public class UusiPeliListener implements ActionListener{
    
    /**
     * Kuvaa Neljansuora-pelin graafisen käyttöliittymän toteuttavaa luokkaa.
     * @see Kayttoliittyma
     */
    
    private Kayttoliittyma kayttoliittyma;
    
    public UusiPeliListener(Kayttoliittyma kayttoliittyma){
        this.kayttoliittyma = kayttoliittyma;
    }
    

    @Override
    public void actionPerformed(ActionEvent ae) {
//        this.kayttoliittyma.kaynnistaPeli();
    }
    
}
