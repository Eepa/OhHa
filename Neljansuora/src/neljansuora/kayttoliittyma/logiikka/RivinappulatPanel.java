
package neljansuora.kayttoliittyma.logiikka;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.swing.JButton;
import javax.swing.JPanel;
import neljansuora.peli.Neljansuora;

/**
 * RivinappulatPanel-luokka luo käyttöliittymään nappularivin, jonka nappuloiden avulla pelaaja 
 * voi omalla vuorollaan pudottaa pelilaudalle uuden nappulan. 
 * @author Eveliina Pakarinen
 */


public class RivinappulatPanel extends JPanel{
    
    /**
     * Kuvaa Neljansuora-pelin samannimistä luokkaa.
     * @see Neljansuora
     */
    
    private Neljansuora neljansuora;
    
    /**
     * Kuvaa Neljansuora-pelin pelitilannetta kullakin hetkellä.
     * @see TilannetietoPanel
     */
    
    private TilannetietoPanel tilannetietoPanel;
    
    /**
     * Konstruktori luo RivinappulatPanelille uuden layoutin ja asettaa luokan attribuuttien 
     * arvot konstruktorin parametrien arvoiksi. Konstruktori myös käyttää luokan omaa metodia 
     * luoNappulat(), joka luo paneelille omat nappulat.
     * @param neljansuora Kuvaa Neljansuora-peliä
     * @param tilannetietoPanel Kuvaa TilannetietoPanelia, joka kertoo tilannetiedot pelistä
     */
    
    public RivinappulatPanel(Neljansuora neljansuora, TilannetietoPanel tilannetietoPanel){
                       
        super(new GridLayout(1, neljansuora.getPelilauta().getLauta().get(0).length));
        this.neljansuora = neljansuora;
        this.tilannetietoPanel = tilannetietoPanel;        
        this.luoNappulat();
    }
    
    /**
     * Metodi luo ja asettaa layouttiin RivinappulatPanelille uudet nappulat, joiden avulla 
     * pelaajat voivat tehdä siirtoja.
     */
    
    private void luoNappulat(){
        int nappuloidenMaara = neljansuora.getPelilauta().getLauta().get(0).length;
        
        List<JButton> nappilista = new ArrayList<JButton>();
        
        for(int i = 0; i < nappuloidenMaara; i++){
            
            JButton uusiNappi = new JButton("" +i);   
            nappilista.add(uusiNappi);
        }
        
        this.lisaaActionListenerNappiinJaNappiPaneeliin(nappilista);
        
    }
    
    /**
     * Metodi lisää jokaiselle nappulalle tapahtumankuuntelijan NappulanPudotusListener, 
     * joka kertoo, kun nappulaa painetaan. Nappi lisätään samalla RivinappulatPaneeliin.
     * @param nappilista Lista napeista, joille lisätään ActionListener
     */
    
    private void lisaaActionListenerNappiinJaNappiPaneeliin(List<JButton> nappilista){
        for(JButton nappi : nappilista){
            nappi.addActionListener(new NappulanPudotusListener(this.neljansuora.getPelilauta(), 
                    nappi.getText(), this.tilannetietoPanel));
            
            add(nappi);
        }
    }
    
        
}
