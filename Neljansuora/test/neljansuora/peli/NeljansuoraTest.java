package neljansuora.peli;

import java.util.Scanner;
import neljansuora.kayttoliittyma.Paivitettava;
import neljansuora.kayttoliittyma.Piirtoalusta;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Testiluokka NeljansuoraTest testaa luokan Neljansuora toimivuutta
 * yksikkötesteillä.
 *
 * @author Eveliina Pakarinen
 */
public class NeljansuoraTest {

    Scanner lukija;
    Neljansuora neljansuora;

    @Before
    public void setUp() {
        String teksti ="Pekka\n" + "Jukka\n";
        this.lukija = new Scanner(teksti);
        this.neljansuora = new Neljansuora(8, 9, 4, lukija, "teksti");

    }

    @Test
    public void luotuNeljansuoraOlemassa() {
        assertTrue(neljansuora != null);
    }

    @Test
    public void pelilautaLuodaanAlussaJaPalautetaanOikein() {
        assertTrue(this.neljansuora.getPelilauta() != null);
    }

    @Test
    public void pelaajatLuodaanOikein() {
        assertTrue(this.neljansuora.getPelilauta().getPelaajat() != null);
    }

    @Test
    public void paivitettavaLisataanOikein() {
        Paivitettava paivitettava = new Piirtoalusta(this.neljansuora);
        this.neljansuora.setPaivitettava(paivitettava);

        assertTrue(this.neljansuora.getPelilauta().getPaivitettava() != null);
    }

    @Test
    public void kaynnistaMetodinSuoritusLoppuu() {
        // tehdaan sisalto kun osataan
        // ei osata vielakaan.. kysytaan pajassa
        // ei osaa scanner-testeja tehda, eika testata Piirtoalustaa
    }

    @Test
    public void kierroksenPelaaminenToimii() {
        // Ei osata tehda testeja Piirtoalustalle ja scanner-luokkaa kayttavalle jutulle
    }
}
