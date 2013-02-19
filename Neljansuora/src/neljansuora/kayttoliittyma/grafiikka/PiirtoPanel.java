
package neljansuora.kayttoliittyma.grafiikka;

import java.awt.BorderLayout;
import javax.swing.JPanel;
import neljansuora.kayttoliittyma.logiikka.RivinappulatPanel;
import neljansuora.kayttoliittyma.logiikka.TilannetietoPanel;
import neljansuora.peli.Neljansuora;



/**
 * Luokka PiirtoPanel lisää Neljansuora-pelin graafiseen käyttöliittymään piirtopaneelin ja 
 * nappulat, joiden avulla pelaajat tekevät siirtojaan.
 * @author Eveliina Pakarinen
 */

public class PiirtoPanel extends JPanel{
    
    /**
     * Neljansuora kuvaa Neljansuora-pelin samannimistä luokkaa.
     * @see Neljansuora
     */
    
    private Neljansuora neljansuora;
    
    /**
     * Piirtoalusta, joka piirtää käyttöliittymän peliruudukon.
     * @see Piirtoalusta
     */
    private Piirtoalusta piirtoalusta;
    
    /**
     * TilannetietoPanel kuvaa Neljansuora-pelin kulloistakin pelitilannetta.
     * @see TilannetietoPanel
     */
    
    private TilannetietoPanel tilannetietoPanel;
    
    /**
     * Konstruktori luo PiirtoPaneelille uuden layoutin ja asettaa attribuuttien arvot
     * konstruktorin parametreinä annetuiksi arvoiksi. Konstruktorissa käytetään myös 
     * luokan omaa metodia luoOsat, jossa luodaan PiirtoPanelin erilliset osat.
     * @param neljansuora Kuvaa Neljansuora-peliä
     * @param piirtoalusta Kuvaa piirtoalustaa, joka piirtää peliruudukon.
     * @param tilannetietoPanel Kuvaa TilannetietoPanel-luokan ilmentymää, joka kertoo pelitilanteen.
     */
    
    public PiirtoPanel(Neljansuora neljansuora, Piirtoalusta piirtoalusta, 
            TilannetietoPanel tilannetietoPanel){
        super(new BorderLayout());
        
        this.neljansuora = neljansuora;
        this.piirtoalusta = piirtoalusta;
        this.tilannetietoPanel = tilannetietoPanel;
        luoOsat();
    }
    
    /**
     * Metodi luo ja asettaa layouttiin PiirtoPanelin osat, joita ovat Piirtoalusta ja 
     * nappularivi, jonka avulla pelaajat tekevät siirtojaan.
     */
    
    private void luoOsat(){
        add(this.piirtoalusta, BorderLayout.CENTER);
        add(new RivinappulatPanel(this.neljansuora.getPelilauta(), this.tilannetietoPanel), BorderLayout.SOUTH);
    }
    
}
