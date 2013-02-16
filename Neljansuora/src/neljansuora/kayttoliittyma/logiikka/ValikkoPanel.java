package neljansuora.kayttoliittyma.logiikka;

import neljansuora.kayttoliittyma.grafiikka.Piirtoalusta;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import neljansuora.kayttoliittyma.Kayttoliittyma;
import neljansuora.peli.Neljansuora;

/**
 * ValikkoPanel-luokka kuvaa graafiseen käyttöliittymään liittyvää napeista
 * koostuvaa valikkoa, jossa pystyy aloittamaan uuden pelin ja vaihtamaan
 * pelilaudan väriä.
 *
 * @author Eveliina Pakarinen
 */
public class ValikkoPanel extends JPanel {

    /**
     * Kuvaa Neljansuora-peliä.
     *
     * @see Neljansuora
     */
    private Neljansuora neljansuora;
    /**
     * TilannetietoPanel kuvaa graafisen käyttöliittymän tilannetietopaneelia.
     *
     * @see TilannetietoPanel
     */
    private TilannetietoPanel tilannetietoPanel;
   
    /**
     * Piirtoalusta, joka piirtää käyttöliittymän peliruudukon.
     * @see Piirtoalusta
     */
    
    private Piirtoalusta piirtoalusta;

    /**
     * Konstruktori asettaa ValikkoPaneelille layoutin ja sijoittaa olion
     * attribuutteihin konstruktorin parametreina annetut tiedot.
     * Konstruktorissa kutsutaan myös ValikkoPanelin omaa metodia, jossa luodaan
     * paneelin komponentit.
     *
     * @param korkeus ValikkoPanelin GridLayoutin korkeus
     * @param leveys ValikkoPanelin GridLayoutin leveys
     * @param neljansuora Viite Neljansuora-luokkaan
     * @param tilannetietoPanel Viite TilannetietoPanel-luokkaan
     * @param kayttoliittyma Viite Kayttoliittyma-luokkaan
     * @param piirtoalusta Kuvaa piirtoalustaa, joka piirtää peliruudukon.
     */
    public ValikkoPanel(int korkeus, int leveys,
            Neljansuora neljansuora, TilannetietoPanel tilannetietoPanel, Piirtoalusta piirtoalusta) {
        super(new GridLayout(korkeus, leveys));

        this.neljansuora = neljansuora;
        this.tilannetietoPanel = tilannetietoPanel;
        this.piirtoalusta = piirtoalusta;
        luoKomponentit();
    }

    /**
     * Luodaan ValikkoPanelin komponentit ja alustetaan niiden
     * toiminnot. Komponentit asetetaan GridLayouttiin.
     */
    private void luoKomponentit() {

        JButton uusiPeli = new JButton("Uusi peli");

        uusiPeli.addActionListener(new UusiPeliListener(this.neljansuora,
                this.tilannetietoPanel, this.piirtoalusta));

        JButton variasetukset = new JButton("Väriasetukset");

        variasetukset.addActionListener(new VarinvaihtoListener(this.piirtoalusta));

        add(uusiPeli);
        add(variasetukset);


    }
}
