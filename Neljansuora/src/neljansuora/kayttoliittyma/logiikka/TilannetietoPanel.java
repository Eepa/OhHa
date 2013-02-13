package neljansuora.kayttoliittyma.logiikka;

import neljansuora.kayttoliittyma.grafiikka.Paivitettava;
import java.awt.GridLayout;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import neljansuora.domain.Pelaaja;
import neljansuora.peli.Neljansuora;

/**
 * TilannetietoPanel-luokka kertoo kulloisenkin pelitilanteen ja pitää kirjaa
 * siitä, kumman pelaajan vuoro on siirtää seuraava nappula kentälle.
 *
 * @author Eveliina Pakarinen
 */
public class TilannetietoPanel extends JPanel implements Paivitettava {

    /**
     * Neljansuora kuvaa Neljansuora-pelin samannimistä luokkaa.
     *
     * @see Neljansuora
     */
    private Neljansuora neljansuora;
    /**
     * Kertoo, kumman pelaajan vuoro on siirtää pelissä.
     */
    private int vuoronumero;
    /**
     * Antaa vuorossaolevan pelaajan nimen tekstimuodossa.
     */
    private String vuorossaoleva;
    /**
     * Sisältää tiedon vuorossaolevasta pelaajasta.
     */
    private JTextArea kukaVuorossa;
    
    /**
     * Kertoo Neljansuora-pelin Pelaajien määrän.
     */
    
    private int pelaajienMaara;

    /**
     * Konstruktori asettaa Tilannetietopaneelille uuden layoutin ja asettaa
     * neljansuora attribuutin arvoksi parametreinaan saamansa arvon. Lisäksi konstruktori
     * alustaa vuoronumeroinnin alkamaan ykkösestä ja selvittää Neljänsuora-pelin 
     * pelaajien määrän. Konstruktori kutsuu myös
     * luokan omia metodeja: setVuorossaoleva(), joka alustaa ja asettaa
     * vuorossaolevalle pelaajalle tekstimuotoisen attribuutin;
     * luoTietopaneeli(), joka luo uuden tietopaneelin.
     *
     * @param neljansuora Kuvaa Neljansuora-peliä
     */
    public TilannetietoPanel(Neljansuora neljansuora) {
        super(new GridLayout(1, 2));
        this.neljansuora = neljansuora;
        this.vuoronumero = 1;
        this.pelaajienMaara = this.neljansuora.getPelilauta().getPelaajat().size();
        this.setVuorossaoleva();
        this.luoTietopaneeli();
    }

    public int getVuoronumero() {
        return this.vuoronumero;
    }

    public void alustaVuoronumero() {
        this.vuoronumero = this.pelaajienMaara;
    }

    public void setVuoronumero() {
        if (this.vuoronumero < this.pelaajienMaara) {
            vuoronumero++;
        } else if (this.vuoronumero == this.pelaajienMaara) {
            vuoronumero = 1;
        }
    }

    public void setVuorossaoleva() {
        
        this.vuorossaoleva = this.haeOikeanPelaajanNimi();
    }
    
    public String haeOikeanPelaajanNimi(){
        String nimi = "";
        
        List<Pelaaja> pelaajat = this.neljansuora.getPelilauta().getPelaajat();
        
        for(Pelaaja p : pelaajat){
            if(p.getVuoronumero() == this.vuoronumero){
                return p.getNimi();
            } else {
                continue;
            }
        }
        
        return nimi;
    }

    /**
     * Metodi asettaa aina seuraavan pelaajan vuorossaolevaksi pelaajaksi ja
     * muuttaa attribuuttia vuorossaoleva.
     */
    private void setKukaVuorossa() {
        this.setVuorossaoleva();
        this.kukaVuorossa.setText(vuorossaoleva);
    }

    /**
     * Metodi luo uudet komponentit TilannetietoPaneliin ja asettaa ne
     * layouttiin.
     */
    private void luoTietopaneeli() {
        JLabel vuorossa = new JLabel("Vuorossa:");
        this.kukaVuorossa = new JTextArea(this.vuorossaoleva);

        add(vuorossa);
        add(kukaVuorossa);
    }

    @Override
    public void paivita() {
        
        this.setVuoronumero();
        this.setKukaVuorossa();

        if (this.neljansuora.getPelilauta().onkoNeljanSuoraa()) {
            this.ilmoitaVoittaja();
        } else if (this.neljansuora.getPelilauta().onkoLautaTaynna()) {
            this.ilmoitaLaudanTayttymisesta();
        }

    }

    public void ilmoitaVoittaja() {
        this.removeAll();

        this.setVuoronumero();
        this.setKukaVuorossa();

        JLabel voittaja = new JLabel("Voittaja: " + this.vuorossaoleva
                + "        ONNEKSI OLKOON!     :)");
        add(voittaja);
    }

    public void ilmoitaLaudanTayttymisesta() {
        this.removeAll();

        this.alustaVuoronumero();
        this.setKukaVuorossa();

        JLabel lautaTaynna = new JLabel("Lauta täyttyi, aloita uusi peli!");

        add(lautaTaynna);
    }

    public void aloitaUusiPeli() {
        this.removeAll();
        this.alustaVuoronumero();
        this.setVuorossaoleva();
        
        this.luoTietopaneeli();
        this.paivita();
        
    }
}
