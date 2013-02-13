package neljansuora.kayttoliittyma.logiikka;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import neljansuora.peli.Pelilauta;

/**
 * Luokka kuvaa tapahtumankuuntelijaa, joka tarkkailee, painetaanko RivinappulatPanelin nappuloita.
 * Jos nappuloita painetaan, suorittaa luokan ilmentymä nappulan pudottamisen pelilaudalle.
 * @author Eveliina Pakarinen
 */

public class NappulanPudotusListener implements ActionListener {

    /**
     * Pelilauta kuvaa Neljansuora-pelin pelilautaa.
     * @see Pelilauta
     */
    
    private Pelilauta lauta;
    
    /**
     * NappulanNumero kuvaa jokaisen nappulan paikkanumeroa eli indeksiä pelilaudalla.
     */
    
    private int nappulanNumero;
    
    /**
     * TilannetietoPanel kuvaa Neljansuora-pelin pelitilannetta kullakin hetkellä.
     * @see TilannetietoPanel
     */
    
    private TilannetietoPanel tilannetietoPanel;
    
    /**
     * Konstruktori luo uuden NappulanPudotusListenerin ja asettaa luokan attribuuttien arvoiksi 
     * konstruktorin parametreina annetut arvot.
     * @param lauta Kuvaa Pelilauta-luokan ilmentymää, joka sisältää pelilaudan.
     * @param nappulanNumero Kuvaa jokaisen nappulan indeksiä pelilaudalla
     * @param tilannetietoPanel Kuvaa TilanneTietoPanelin ilmentymää, joka kertoo pelitilanteen.
     */

    public NappulanPudotusListener(Pelilauta lauta, String nappulanNumero,
            TilannetietoPanel tilannetietoPanel) {
        this.lauta = lauta;
        this.tilannetietoPanel = tilannetietoPanel;

        try {
            this.nappulanNumero = Integer.parseInt(nappulanNumero);
        } catch (Exception e) {
            System.out.println("Ei oikea numero!");
        }

    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if(!this.lauta.getNappulaKasittelija().onkoMahdollinenSiirto(nappulanNumero)){
            return;
        }
        
        this.lauta.teeYhdenPelaajanSiirtoGraafistaKayttoliittymaaVarten(nappulanNumero, this.tilannetietoPanel.getVuoronumero());
        this.tilannetietoPanel.paivita();
    }
}
