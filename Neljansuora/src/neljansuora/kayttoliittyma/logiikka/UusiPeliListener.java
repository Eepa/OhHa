
package neljansuora.kayttoliittyma.logiikka;

import neljansuora.kayttoliittyma.grafiikka.Piirtoalusta;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import neljansuora.kayttoliittyma.Kayttoliittyma;
import neljansuora.kayttoliittyma.Kayttoliittyma;
import neljansuora.peli.Neljansuora;

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
    
    /**
     * Kuvaa Neljansuora-pelin pelitilannetta kullakin hetkellä.
     * @see TilannetietoPanel
     */
    
    private TilannetietoPanel tilannetietopanel;
    
    /**
     * Kuvaa Neljansuora-pelin samannimistä luokkaa.
     * @see Neljansuora
     */
    
    private Neljansuora neljansuora;
    
    /**
     * Piirtoalusta, joka piirtää käyttöliittymän peliruudukon.
     * @see Piirtoalusta
     */
    
    private Piirtoalusta piirtoalusta;
    
    /**
     * Konstruktorissa asetetaan konstruktorin parametreinä saadut arvot 
     * luokan attribuutteihin.
     * @param neljansuora Kuvaa Neljansuora-peliä
     * @param kayttoliittyma Viite Kayttoliittyma- luokkaan
     * @param tilannetietopanel Viite TilannetietoPanel-luokkaan
     * @param piirtoalusta Kuvaa piirtoalustaa, joka piirtää peliruudukon.
     */
    
    public UusiPeliListener(Neljansuora neljansuora, Kayttoliittyma kayttoliittyma,
            TilannetietoPanel tilannetietopanel, Piirtoalusta piirtoalusta){
        this.kayttoliittyma = kayttoliittyma;
        this.neljansuora = neljansuora;
        this.tilannetietopanel = tilannetietopanel;
        this.piirtoalusta = piirtoalusta;
    }
    

    @Override
    public void actionPerformed(ActionEvent ae) {

        this.neljansuora.lopetaPeli();
        this.tilannetietopanel.aloitaUusiPeli();
        this.piirtoalusta.paivita();

    }
    
}
