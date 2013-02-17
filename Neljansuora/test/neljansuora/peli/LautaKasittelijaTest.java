package neljansuora.peli;

import java.util.ArrayList;
import java.util.Scanner;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Testiluokka LautaKasittelijaTest testaa luokan LautaKasittelija toimivuutta
 * yksikkötesteillä.
 *
 * @author Eveliina Pakarinen
 */
public class LautaKasittelijaTest {

    private LautaKasittelija lautakasittelija;
    private Neljansuora neljansuora;
    private Scanner lukija;

    @Before
    public void setUp() {
        String teksti ="Pekka\n" + "Jukka\n";
        this.lukija = new Scanner(teksti);

        this.neljansuora = new Neljansuora(4, 4, 4, this.lukija, "teksti");

        this.lautakasittelija = new LautaKasittelija(this.neljansuora.getPelilauta().getLauta(),
                this.neljansuora.getPelilauta().getPelaajat(), this.lukija);

    }

    @Test
    public void luotuLautaKasittelijaOlemassa() {
        assertTrue(this.lautakasittelija != null);
    }

    @Test
    public void laudalleKirjoittaminenToimiiOikeinJosKirjoitetaanTyhjanPaikanMerkkiaAlussa() {
        ArrayList<String> rivit = new ArrayList<String>();

        for (String[] rivi : this.neljansuora.getPelilauta().getLauta().values()) {

            String uusiSana = "";

            for (int i = 0; i < rivi.length; i++) {
                uusiSana += rivi[i];
            }

            rivit.add(uusiSana);

        }

        String testisana = "";
        for (int j = 0; j < this.neljansuora.getPelilauta().getLauta().get(0).length; j++) {
            testisana += ".";
        }

        int tarkistusluku = 0;

        for (String rivinKirjainesitys : rivit) {

            if (rivinKirjainesitys.equals(testisana)) {
                tarkistusluku++;
            }

        }

        assertEquals(tarkistusluku, 4);
    }

    @Test
    public void pelaajienNappuloidenLisaaminenKenttaanToimiiOikein() {
        //TODO --> auttaa ehka ratkaisemaan aiemman ongelman uuden nappulan lisaamisessa..
    }

    @Test
    public void yhdenPelaajanNappuloidenLisaaminenToimiiOikein() {
        //TODO
    }

    @Test
    public void yhdenSatunnaisenNappulanKirjoittaminenLaudalleToimiiOikein() {
        this.lautakasittelija.kirjoitaLaudalleNappula("X", 0, 3);

        String merkki = this.neljansuora.getPelilauta().getLauta().get(3)[0];

        assertEquals("X", merkki);
    }
  
}
