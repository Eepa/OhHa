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
        this.lukija = new Scanner(System.in);

        this.neljansuora = new Neljansuora(8, 9, 4, this.lukija, "teksti");

        this.nappulakasittelija = new NappulaKasittelija(this.neljansuora.getPelilauta().getLauta(),
                this.neljansuora.getPelilauta().getPelaajat(), 4);

    }

    @Test
    public void luotuNappulaKasittelijaOlemassa() {
        assertTrue(this.nappulakasittelija != null);
    }

    @Test
    public void pisimmanSivunEtsiminenToimii() {
        assertEquals(this.nappulakasittelija.selvitaLaudanPisimmanSivunPituus(), 9);
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
        assertEquals(this.nappulakasittelija.teeJono(5, "X"), "XXXXX");
    }

    @Test
    public void testaaMerkkijononPituusPalauttaaOikeinJosTyhjaJono() {
        assertEquals(this.nappulakasittelija.testaaMerkkijononPituus("", "X", 5), 0);
    }

    @Test
    public void testaaMerkkijononPituusPalauttaaOikeinJosLiianPitkaJono() {
        assertEquals(this.nappulakasittelija.testaaMerkkijononPituus("XXXXXXXXXXX", "X", 5), 5);
    }

    @Test
    public void testaaMerkkijononPituusPalauttaaOikeinJosAlleMaksimipituus() {
        assertEquals(this.nappulakasittelija.testaaMerkkijononPituus("XXXX", "X", 5), 0);
    }

    @Test
    public void testaaMerkkijononPituusPalauttaaOikeinJosTasanMaksimipituus() {
        assertEquals(this.nappulakasittelija.testaaMerkkijononPituus("XXXXX", "X", 5), 5);
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

        int totuuksienMaara = 8;

        for (int i = 0; i < 8; i++) {
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
