package neljansuora.kayttoliittyma.logiikka;

import java.awt.GridLayout;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import neljansuora.domain.Pelaaja;
import neljansuora.kayttoliittyma.grafiikka.Paivitettava;
import neljansuora.peli.Pelilauta;


/**
 * TilannetietoPanel-luokka kertoo kulloisenkin pelitilanteen ja pitää kirjaa
 * siitä, kumman pelaajan vuoro on siirtää seuraava nappula kentälle.
 *
 * @author Eveliina Pakarinen
 */
public class TilannetietoPanel extends JPanel implements Paivitettava {

    /**
     * Kuvaa Neljansuora-pelin pelilautaa.
     *
     * @see Pelilauta
     */
    private Pelilauta pelilauta;
    /**
     * Kertoo, kumman pelaajan vuoro on siirtää pelissä seuraavaksi.
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
     * neljansuora attribuutin arvoksi parametreinaan saamansa arvon. Lisäksi
     * konstruktori alustaa vuoronumeroinnin alkamaan ykkösestä ja selvittää
     * Neljänsuora-pelin pelaajien määrän. Konstruktori kutsuu myös luokan omia
     * metodeja: setVuorossaoleva(), joka alustaa ja asettaa vuorossaolevalle
     * pelaajalle tekstimuotoisen attribuutin; luoTietopaneeli(), joka luo uuden
     * tietopaneelin.
     *
     * @param pelilauta Kuvaa Neljansuora-pelin pelilautaa
     */
    public TilannetietoPanel(Pelilauta pelilauta) {
        super(new GridLayout(1, 2));
        this.pelilauta = pelilauta;
        this.vuoronumero = 1;
        this.pelaajienMaara = this.pelilauta.getPelaajat().size();
        this.setVuorossaoleva();
        this.luoTietopaneeli();
    }

    public int getVuoronumero() {
        return this.vuoronumero;
    }
    
    /**
     * Alustaa vuoronumeron vastaamaan pelaajien määrää.
     */

    public void alustaVuoronumero() {
        this.vuoronumero = this.pelaajienMaara;
    }
    
    /**
     * Alustaa vuoronumeron kertomaan, mitä kierrosta pelataan.
     */

    public void alustaKierroksenVuoronumero() {
        if (this.vuoronumero < this.pelaajienMaara) {
            vuoronumero++;
        } else if (this.vuoronumero == this.pelaajienMaara) {
            vuoronumero = 1;
        }
    }

    public void setVuorossaoleva() {
        this.vuorossaoleva = this.haeOikeanPelaajanNimi();
    }
    
    /**
     * Palauttaa pelaajan nimen, jos oikean vuoronumeron omaava pelaaja löytyy 
     * pelaajalistasta. Muuten palauttaa tyhjän merkkijonon.
     * @return Palautettava merkkijono (pelaajan nimi tai tyhjä)
     */

    public String haeOikeanPelaajanNimi() {
        
        List<Pelaaja> pelaajat = this.pelilauta.getPelaajat();

        for (Pelaaja p : pelaajat) {
            if (p.getVuoronumero() == this.vuoronumero) {
                return p.getNimi();
            }
        }

        return "";
    }

    /**
     * Metodi asettaa aina seuraavan pelaajan vuorossaolevaksi pelaajaksi ja
     * muuttaa luokan attribuuttia vuorossaoleva vastaamaan uutta tilannetta.
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
        this.kukaVuorossa.setEditable(false);

        add(vuorossa);
        add(kukaVuorossa);
    }

    @Override
    public void paivita() {

        this.alustaKierroksenVuoronumero();
        this.setKukaVuorossa();

        if (this.pelilauta.onkoNeljanSuoraa()) {
            this.ilmoitaVoittaja();
        } else if (this.pelilauta.onkoLautaTaynna()) {
            this.ilmoitaLaudanTayttymisesta();
        }

    }

    /**
     * Ilmoittaa kuka voitti Neljansuora-pelin. Paneelista poistetaan ensin
     * kaikki sisältö ja sitten lisätään uusi JLabel kertomaan, kuka voitti.
     */
    public void ilmoitaVoittaja() {
        this.removeAll();

        this.alustaKierroksenVuoronumero();
        this.setKukaVuorossa();

        JLabel voittaja = new JLabel("Voittaja: " + this.vuorossaoleva
                + "        ONNEKSI OLKOON!     :)");

        add(voittaja);
    }

    /**
     * Ilmoittaa pelaajalle, jos pelilauta täyttyy. Paneelista poistetaan ensin
     * kaikki sisältö ja sitten lisätään uusi JLabel kertomaan laudan täyttymisestä.
     */
    public void ilmoitaLaudanTayttymisesta() {
        this.removeAll();

        this.alustaVuoronumero();
        this.setKukaVuorossa();

        JLabel lautaTaynna = new JLabel("Lauta täyttyi, aloita uusi peli!");

        add(lautaTaynna);
    }
    
    /**
     * Alustaa uuden pelin, jos jompikumpi pelaaja sai riittävän pitkän suoran. 
     * Paneelista poistetaan ensin kaikki sisältö ja sitten alustetaan paneeli samanlaiseksi 
     * kuin Neljänsuora-pelin ensimmäisellä käynnistyskerralla.
     */

    public void aloitaUusiPeli() {
        this.removeAll();
        this.alustaVuoronumero();
        this.setVuorossaoleva();

        this.luoTietopaneeli();
        this.paivita();

    }
}
