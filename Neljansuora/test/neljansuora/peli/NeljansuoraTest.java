package neljansuora.peli;

import java.util.Scanner;
import neljansuora.kayttoliittyma.grafiikka.Paivitettava;
import neljansuora.kayttoliittyma.grafiikka.Piirtoalusta;
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
        this.neljansuora = new Neljansuora(4, 4, 4, lukija, "teksti");

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

   }
