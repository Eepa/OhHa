package neljansuora.peli;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import neljansuora.domain.Pelaaja;
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
        String teksti = "Pekka\n" + "Jukka\n";
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

            String uusiSana = luoRivinMerkeistaMerkkijono(rivi);

            rivit.add(uusiSana);

        }

        String testisana = annaTestisana();

        int tarkistusluku = 0;

        for (String rivinKirjainesitys : rivit) {

            if (rivinKirjainesitys.equals(testisana)) {
                tarkistusluku++;
            }

        }

        assertEquals(tarkistusluku, 4);
    }

    public String luoRivinMerkeistaMerkkijono(String[] rivi) {
        String uusiSana = "";

        for (int i = 0; i < rivi.length; i++) {
            uusiSana += rivi[i];
        }
        return uusiSana;
    }

    public String annaTestisana() {
        String testisana = "";
        for (int j = 0; j < this.neljansuora.getPelilauta().getLauta().get(0).length; j++) {
            testisana += ".";
        }
        return testisana;
    }

    @Test
    public void pelaajienNappuloidenLisaaminenKenttaanToimiiOikein() {

        List<Pelaaja> pelaajat = this.neljansuora.getPelilauta().getPelaajat();
        int i = 0;

        for (Pelaaja p : pelaajat) {
            p.lisaaUusiNappula(i, 0);
            i++;
        }

        this.lautakasittelija.lisaaNappulatKenttaan(pelaajat);

        String rivinMerkit = luoRivinMerkeistaMerkkijono(this.neljansuora.getPelilauta().getLauta().get(0));

        assertEquals("XO..", rivinMerkit);
    }

    @Test
    public void yhdenPelaajanNappuloidenLisaaminenToimiiOikein() {

        List<Pelaaja> pelaajat = this.neljansuora.getPelilauta().getPelaajat();
        int i = 0;

        Pelaaja p = pelaajat.get(0);
        p.lisaaUusiNappula(0, 0);
        p.lisaaUusiNappula(1, 0);
        p.lisaaUusiNappula(2, 0);
        
        this.lautakasittelija.lisaaPelaajanNappulat("X", p.getNappulat());
        String rivinMerkit = luoRivinMerkeistaMerkkijono(this.neljansuora.getPelilauta().getLauta().get(0));
        assertEquals("XXX.", rivinMerkit);
    }

    @Test
    public void yhdenSatunnaisenNappulanKirjoittaminenLaudalleToimiiOikein() {
        this.lautakasittelija.kirjoitaLaudalleNappula("X", 3, 3);

        String merkki = this.neljansuora.getPelilauta().getLauta().get(3)[3];

        assertEquals("X", merkki);
    }
}
