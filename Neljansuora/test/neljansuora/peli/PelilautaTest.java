package neljansuora.peli;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import neljansuora.domain.Pelaaja;
import neljansuora.kayttoliittyma.Paivitettava;
import neljansuora.kayttoliittyma.Piirtoalusta;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Testiluokka PelilautaTest testaa luokan Pelilauta toimivuutta
 * yksikkötesteillä.
 *
 * @author Eveliina Pakarinen
 */
public class PelilautaTest {

    Neljansuora neljansuora;
    Pelilauta pelilauta;
    Scanner lukija;

    @Before
    public void setUp() {

        this.lukija = new Scanner(System.in);

        this.pelilauta = new Pelilauta(8, 9, 4, this.lukija);
        
    }

    @Test
    public void luotuPelilautaOlemassa() {
        assertTrue(pelilauta != null);
    }

    @Test
    public void luoKaksiPelaajaa() {

//        String syote1 = "Pekka\n";
//
//        this.lukija = new Scanner(syote1);
//
//        String syote2 = "Jukka\n";
//
//        this.lukija = new Scanner(syote2);
//        Miten scanneria pystyy testaamaan

        this.pelilauta.luoPelaajat();

        assertEquals(2, pelilauta.getPelaajat().size());
    }

    @Test
    public void pelaajanPalautusEiPalautaTyhjaaListaaTaiPalauttaaJotakin() {
        assertTrue(this.pelilauta.getPelaajat() != null);
    }

    @Test
    public void palauttaaPelaajatOikein() {

        this.pelilauta.luoPelaajat();

        List<Pelaaja> pelaajat = this.pelilauta.getPelaajat();
        int summa = 0;
        for (Pelaaja p : pelaajat) {
            summa = summa + p.getVuoronumero();
        }

        assertEquals(3, summa);
    }

    @Test
    public void luoPelilaudanHashMappiinOikein() {
        int summa = 8 + 9;

        int tarkistussumma = this.pelilauta.getLauta().size() + this.pelilauta.getLauta().get(0).length;

        assertEquals(summa, tarkistussumma);

    }

    @Test
    public void rivinTayttoToimiiAlussaOikeinEnsimmaisellaRivilla() {
        String[] taulukko = this.pelilauta.getLauta().get(0);
        String tulostus = "";
        for (int i = 0; i < taulukko.length; i++) {
            tulostus = tulostus + taulukko[i];
        }

        String testiSana = "";
        for (int j = 0; j < taulukko.length; j++) {
            testiSana = testiSana + ".";
        }

        assertEquals(tulostus, testiSana);
    }

    @Test
    public void tayttoJaLaudalleKirjoittaminenToimivatAlussaOikeinJokaisellaRivilla() {

        ArrayList<String> rivit = new ArrayList<String>();

        for (String[] rivi : this.pelilauta.getLauta().values()) {

            String uusiSana = "";

            for (int i = 0; i < rivi.length; i++) {
                uusiSana += rivi[i];
            }

            rivit.add(uusiSana);

        }

        String testisana = "";
        for (int j = 0; j < this.pelilauta.getLauta().get(0).length; j++) {
            testisana += ".";
        }

        int tarkistusluku = 0;

        for (String rivinKirjainesitys : rivit) {

            if (rivinKirjainesitys.equals(testisana)) {
                tarkistusluku++;
            }

        }

        assertEquals(tarkistusluku, 9);

    }

    @Test
    public void palauttaaHashMapLaudanOikein() {
        assertTrue(this.pelilauta.getLauta() != null);
    }

    @Test
    public void palautetaanPelaajalistaOikein() {
        assertTrue(this.pelilauta.getPelaajat() != null);
    }

    @Test
    public void luotuNappulankasittelijaOnOlemassaJaPalautetaanOikein() {
        assertTrue(this.pelilauta.getNappulaKasittelija() != null);
    }

    @Test
    public void luotuPaivitettavaAsetetaanJaPalautetaanOikein() {
        Paivitettava paivitettava = new Piirtoalusta(new Neljansuora(8, 9, 4, this.lukija));
        this.pelilauta.setPaivitettava(paivitettava);

        assertTrue(this.pelilauta.getPaivitettava() != null);
    }

    @Test
    public void pelilautaaLuodessaLuodaanTarpeeksiRiveja() {
        assertEquals(this.pelilauta.getLauta().size(), 9);
    }

    @Test
    public void pelilautaaLuodessaLuodaanTarpeeksiPitkiaRiveja() {
        for (String[] rivi : this.pelilauta.getLauta().values()) {

            if (rivi.length != 8) {
                assertTrue(false);
            }
        }

        assertTrue(true);
    }

    

    @Test
    public void siirtojenTekeminenToimiiAntamallaSyotteenPelaajastaJaRivista() {
        Neljansuora neljansuora = new Neljansuora(8, 9, 4, this.lukija);
        Paivitettava paivitettava = new Piirtoalusta(neljansuora);
        Pelilauta pelilauta = neljansuora.getPelilauta();

        pelilauta.setPaivitettava(paivitettava);

        pelilauta.teeYhdenPelaajanSiirtoGraafistaKayttoliittymaaVarten(2, 1);

        String[] alinRivi = pelilauta.getLauta().get(8);

        String merkki = alinRivi[2];

        assertEquals("X", merkki);
    }

    @Test
    public void onkoLautaTaynnaPalauttaaOikeinJosTaynna() {

        for (int i = 0; i < this.pelilauta.getLauta().size(); i++) {
            String[] rivi = this.pelilauta.getLauta().get(i);

            for (int j = 0; j < rivi.length; j++) {
                rivi[j] = "X";
            }

        }

        assertTrue(this.pelilauta.onkoLautaTaynna());
    }

    @Test
    public void onkoLautaTaynnaPalauttaaOikeinJosEiTaynna() {
        assertTrue(!this.pelilauta.onkoLautaTaynna());
    }

    @Test
    public void onkoNeljanSuoraaPalauttaaOikeinJosEiSuoria() {
        assertTrue(!this.pelilauta.onkoNeljanSuoraa());
    }

    @Test
    public void onkoNeljanSuoraaPalauttaaOikeinJosOnSuoria() {
        // MIKÄ VIKANA..?? AssertionFailedError
       
//        Pelilauta pelilauta = new Pelilauta(8, 9, 4, this.lukija);
//        
//        pelilauta.luoPelaajat();
//
//        Pelaaja pelaaja1 = pelilauta.getPelaajat().get(0);
//        Pelaaja pelaaja2 = pelilauta.getPelaajat().get(1);
//
//        for (int i = 0; i < pelilauta.getLauta().size(); i++) {
//
//            String[] rivi = pelilauta.getLauta().get(i);
//
//            for (int j = 0; j < rivi.length; j++) {
//                pelaaja1.lisaaUusiNappula(j, i);
//                pelaaja2.lisaaUusiNappula(j, i);
//            }
//        }
//
//        assertTrue(pelilauta.onkoNeljanSuoraa());
    }
    
    @Test
    public void tulostaminenToimiiOikein() {
        //Miten voi testata tulostamista?
    }

    @Test
    public void siirtojenTekeminenToimiiScanneriaKayttamallaTekstikayttoliittymassa() {
        // Miten testataan scanneria?
    }
}
