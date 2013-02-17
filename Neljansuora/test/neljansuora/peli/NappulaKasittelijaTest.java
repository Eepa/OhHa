package neljansuora.peli;

import java.util.Scanner;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Testiluokka NappulaKasittelijaTest testaa NappulaKasittelija-luokan
 * toimivuutta yksikkötesteillä.
 *
 * @author Eveliina Pakarinen
 */
public class NappulaKasittelijaTest {

    private Scanner lukija;
    private Neljansuora neljansuora;
    private NappulaKasittelija nappulakasittelija;

    @Before
    public void setUp() {
        String teksti ="Pekka\n" + "Jukka\n";
        this.lukija = new Scanner(teksti);

        this.neljansuora = new Neljansuora(4, 4, 4, this.lukija, "teksti");

        this.nappulakasittelija = new NappulaKasittelija(this.neljansuora.getPelilauta().getLauta(),
                this.neljansuora.getPelilauta().getPelaajat(), 4);

    }

    @Test
    public void luotuNappulaKasittelijaOlemassa() {
        assertTrue(this.nappulakasittelija != null);
    }

    @Test
    public void pisimmanSivunEtsiminenToimii() {
        assertEquals(this.nappulakasittelija.selvitaLaudanPisimmanSivunPituus(), 4);
    }

    @Test
    public void onkoLautaTaynnaPalauttaaOikeinJosEiTaynna() {
        assertTrue(!this.nappulakasittelija.onkoLautaTaynna());
    }

    @Test
    public void onkoLautaTaynnaPalauttaaOikeinJosTaynna() {
        // TODO
    }

    @Test
    public void teeJonoPalauttaaOikeanMittaisenJononOikeallaMerkilla() {
        assertEquals(this.nappulakasittelija.teeJono(4, "X"), "XXXX");
    }

    @Test
    public void testaaMerkkijononPituusPalauttaaOikeinJosTyhjaJono() {
        assertEquals(this.nappulakasittelija.testaaMerkkijononPituus("", "X", 4), 0);
    }

    @Test
    public void testaaMerkkijononPituusPalauttaaOikeinJosLiianPitkaJono() {
        assertEquals(this.nappulakasittelija.testaaMerkkijononPituus("XXXXXXXXXXX", "X", 4), 4);
    }

    @Test
    public void testaaMerkkijononPituusPalauttaaOikeinJosAlleMaksimipituus() {
        assertEquals(this.nappulakasittelija.testaaMerkkijononPituus("XXX", "X", 4), 0);
    }

    @Test
    public void testaaMerkkijononPituusPalauttaaOikeinJosTasanMaksimipituus() {
        assertEquals(this.nappulakasittelija.testaaMerkkijononPituus("XXXXX", "X", 4), 4);
    }

    @Test
    public void onkoRiittavanPitkiaSuoriaPalauttaaOikeinJosEiSuoria() {
        assertTrue(!this.nappulakasittelija.onkoRiittavanPitkiaSuoria());
    }

    @Test
    public void onkoRiittavanPitkiaSuoriaPalauttaaOikeinJosSuora() {
        //TODO
    }

    @Test
    public void onkoMahdollinenSiirtoPalauttaaOikeinJosOnMahdollinen() {

        int totuuksienMaara = 4;

        for (int i = 0; i < 4; i++) {
            if (this.nappulakasittelija.onkoMahdollinenSiirto(i)) {
                totuuksienMaara--;
            }
        }
        assertEquals(totuuksienMaara, 0);
    }

    @Test
    public void onkoMahdollinenSiirtoPalauttaaOikeinJosEiOleMahdollinen() {

//        for (int j = 0; j < 9; j++) {
//           String[] rivi = this.neljansuora.getPelilauta().getLauta().get(j);
//        
//            for (int i = 0; i < rivi.length; i++) {
//                this.neljansuora.getPelilauta().teeYhdenPelaajanSiirto(j, 1);
//            }
//        }
//        
//        int epatotuuksienMaara = 8;
//
//        for (int i = 0; i < 8; i++) {
//            if (!this.nappulakasittelija.onkoMahdollinenSiirto(i)) {
//                epatotuuksienMaara--;
//            }
//        }
//        assertEquals(epatotuuksienMaara, 0);
        
        //MITEN SAA LAUDAN TÄYTEEN ILMAN ETTÄ TULEE NELJAN SUORIA??
    }
}
