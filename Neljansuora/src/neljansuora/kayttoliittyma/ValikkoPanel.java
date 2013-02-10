package neljansuora.kayttoliittyma;

import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
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
     * Neljansuora kuvaa Neljansuora-pelin samannimistä luokkaa.
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
     * Kayttoliittyma kuvaa graafisen käyttöliittymän samannimistä luokkaa.
     *
     * @see Kayttoliittyma
     */
    private Kayttoliittyma kayttoliittyma;
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
     * @param kayttoliittyma Viite Kayttoliittyma- luokkaan
     */
    public ValikkoPanel(int korkeus, int leveys,
            Neljansuora neljansuora, TilannetietoPanel tilannetietoPanel, Kayttoliittyma kayttoliittyma, Piirtoalusta piirtoalusta) {
        super(new GridLayout(korkeus, leveys));

        this.neljansuora = neljansuora;
        this.tilannetietoPanel = tilannetietoPanel;
        this.kayttoliittyma = kayttoliittyma;
        this.piirtoalusta = piirtoalusta;
        luoKomponentit();
    }

    /**
     * Metodi, jossa luodaan ValikkoPanelin komponentit ja alustetaan niiden
     * toiminnot. Komponentit asetetaan GridLayouttiin.
     */
    private void luoKomponentit() {

        JButton uusiPeli = new JButton("Uusi peli");

        uusiPeli.addActionListener(new UusiPeliListener(this.neljansuora, this.kayttoliittyma,
                this.tilannetietoPanel, this.piirtoalusta));

        JButton variasetukset = new JButton("Väriasetukset");

        variasetukset.addActionListener(new VarinvaihtoListener(this.neljansuora, this.piirtoalusta));

        add(uusiPeli);
        add(variasetukset);


    }
}
