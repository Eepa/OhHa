
package neljansuora.kayttoliittyma.logiikka;

import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JPanel;
import neljansuora.peli.Pelilauta;



/**
 * RivinappulatPanel-luokka luo käyttöliittymään nappularivin, jonka nappuloiden avulla pelaaja 
 * voi omalla vuorollaan pudottaa pelilaudalle uuden nappulan. 
 * @author Eveliina Pakarinen
 */


public class RivinappulatPanel extends JPanel{
    
    /**
     * Kuvaa Neljansuora-pelin pelilautaa.
     * @see Pelilauta
     */
    
    private Pelilauta pelilauta;
    
    /**
     * Kuvaa Neljansuora-pelin pelitilannetta kullakin hetkellä.
     * @see TilannetietoPanel
     */
    
    private TilannetietoPanel tilannetietoPanel;
    
    /**
     * Konstruktori luo RivinappulatPanelille uuden layoutin ja asettaa luokan attribuuttien 
     * arvot konstruktorin parametrien arvoiksi. Konstruktori myös käyttää luokan omaa metodia 
     * luoNappulat(), joka luo paneelille omat nappulat.
     * @param pelilauta Kuvaa Neljansuora-pelin pelilautaa
     * @param tilannetietoPanel Kuvaa TilannetietoPanelia, joka kertoo tilannetiedot pelistä
     */
    
    public RivinappulatPanel(Pelilauta pelilauta, TilannetietoPanel tilannetietoPanel){
                       
        super(new GridLayout(1, pelilauta.getLauta().get(0).length));
        this.pelilauta = pelilauta;
        this.tilannetietoPanel = tilannetietoPanel;        
        this.luoNappulat();
    }
    
    /**
     * Metodi luo ja asettaa layouttiin RivinappulatPanelille uudet nappulat, joiden avulla 
     * pelaajat voivat tehdä siirtoja.
     */
    
    private void luoNappulat(){
        int nappuloidenMaara = this.pelilauta.getLauta().get(0).length;
        
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
            nappi.addActionListener(new NappulanPudotusListener(this.pelilauta, 
                    nappi.getText(), this.tilannetietoPanel));
            
            add(nappi);
        }
    }
    
        
}
