package neljansuora.peli;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import neljansuora.domain.Pelaaja;
import neljansuora.kayttoliittyma.grafiikka.Paivitettava;
import neljansuora.kayttoliittyma.grafiikka.Piirtoalusta;
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

    Pelilauta pelilauta;
    Scanner lukija;

    @Before
    public void setUp() {
        String teksti = "Pekka\n" + "Jukka\n";
        this.lukija = new Scanner(teksti);

        this.pelilauta = new Pelilauta(4, 4, 4, this.lukija);
    }

    @Test
    public void luotuPelilautaOlemassa() {
        assertTrue(pelilauta != null);
    }

    @Test
    public void luoKaksiPelaajaa() {
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
        int tarkistussumma = this.pelilauta.getLauta().size() + this.pelilauta.getLauta().get(0).length;

        assertEquals(8, tarkistussumma);

    }

    @Test
    public void rivinTayttoOnToiminutPelilautaaLuodessaOikeinEnsimmaisellaRivilla() {
        String[] taulukko = this.pelilauta.getLauta().get(0);
        String tulostus = palautaTaulukonMerkitMerkkijonossa(taulukko);

        String testiSana = palautaTestisana(taulukko);

        assertEquals(tulostus, testiSana);
    }

    private String palautaTaulukonMerkitMerkkijonossa(String[] taulukko) {
        String tulostus = "";
        for (int i = 0; i < taulukko.length; i++) {
            tulostus = tulostus + taulukko[i];
        }
        return tulostus;
    }

    private String palautaTestisana(String[] taulukko) {
        String testiSana = "";
        for (int j = 0; j < taulukko.length; j++) {
            testiSana = testiSana + ".";
        }
        return testiSana;
    }

    @Test
    public void tayttoJaLaudalleKirjoittaminenOvatToimineetPelilautaaLuodessaOikeinJokaisellaRivilla() {

        ArrayList<String> rivit = new ArrayList<String>();

        for (String[] rivi : this.pelilauta.getLauta().values()) {

            String uusiSana = teeRivinMerkeistaSana(rivi);

            rivit.add(uusiSana);

        }
        int tarkistusluku = palautaTarkistusluku(rivit);

        assertEquals(tarkistusluku, 4);

    }

    private int palautaTarkistusluku(ArrayList rivit) {
        String testisana = teeTestisana();
        int tarkistusluku = 0;

        for (Object rivinKirjainesitys : rivit) {

            if (rivinKirjainesitys.equals(testisana)) {
                tarkistusluku++;
            }

        }
        return tarkistusluku;
    }

    private String teeTestisana() {
        String testisana = "";
        for (int j = 0; j < this.pelilauta.getLauta().get(0).length; j++) {
            testisana += ".";
        }
        return testisana;
    }

    private String teeRivinMerkeistaSana(String[] rivi) {
        String uusiSana = "";

        for (int i = 0; i < rivi.length; i++) {
            uusiSana += rivi[i];
        }
        return uusiSana;
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
    public void luotuLautaKasittelijaOnOlemassaJaPalautetaanOikein() {
        assertTrue(this.pelilauta.getLautaKasittelija() != null);
    }

    @Test
    public void luotuPaivitettavaAsetetaanJaPalautetaanOikein() {
        Paivitettava paivitettava = new Piirtoalusta(new Neljansuora(4, 4, 4, this.lukija, "teksti"));
        this.pelilauta.setPaivitettava(paivitettava);

        assertTrue(this.pelilauta.getPaivitettava() != null);
    }

    @Test
    public void kakkosPelaajanSiirronTekeminenGraafistaKayttoliittymaaVartenToimii() {
        Neljansuora neljansuora = new Neljansuora(4, 4, 4, this.lukija, "teksti");
        Paivitettava paivitettava = new Piirtoalusta(neljansuora);
        neljansuora.getPelilauta().setPaivitettava(paivitettava);

        neljansuora.getPelilauta().teeYhdenPelaajanSiirtoGraafistaKayttoliittymaaVarten(0, 2);
        String merkkiLaudanOikeassaKohdassa = neljansuora.getPelilauta().getLauta().get(3)[0];

        assertEquals("O", merkkiLaudanOikeassaKohdassa);
    }

    @Test
    public void pelilautaaLuodessaLuodaanTarpeeksiRiveja() {
        assertEquals(this.pelilauta.getLauta().size(), 4);
    }

    @Test
    public void pelilautaaLuodessaLuodaanTarpeeksiPitkiaRiveja() {
        for (String[] rivi : this.pelilauta.getLauta().values()) {

            if (rivi.length != 4) {
                assertTrue(false);
            }
        }

        assertTrue(true);
    }

    @Test
    public void ykkosPelaajanSiirronTekeminenGraafistaKayttoliittymaaVartenToimii() {
        Neljansuora neljansuora = new Neljansuora(8, 9, 4, this.lukija, "teksti");
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

        pelilauta.getLautaKasittelija().kirjoitaLaudalle("X");
        this.pelilauta.luoPelaajat();
        lisaaPelaajalleNappuloita(this.pelilauta.getPelaajat().get(0), 4);
        lisaaPelaajalleNappuloita(this.pelilauta.getPelaajat().get(1), 4);
        boolean onkoNeljanSuoraaMetodinPalauttamaArvo = this.pelilauta.onkoNeljanSuoraa();

        assertTrue(onkoNeljanSuoraaMetodinPalauttamaArvo);
    }

    private void lisaaPelaajalleNappuloita(Pelaaja p, int maara) {
        for (int i = 0; i < maara; i++) {
            p.lisaaUusiNappula(i, 0);
        }
    }

    @Test
    public void pelinLopettaminenToimii() {
        Neljansuora neljansuora = new Neljansuora(4, 4, 4, this.lukija, "teksti");
        Paivitettava paivitettava = new Piirtoalusta(neljansuora);
        Pelilauta neljansuoranPelilauta = neljansuora.getPelilauta();

        neljansuoranPelilauta.setPaivitettava(paivitettava);

        this.lisaaPelaajalleNappuloita(neljansuoranPelilauta.getPelaajat().get(0), 3);

        neljansuoranPelilauta.getLautaKasittelija().lisaaNappulatKenttaan(neljansuoranPelilauta.getPelaajat());

        neljansuoranPelilauta.lopetaPeli();

        String nappuloidenMerkkijono = neljansuoranPelilauta.getLauta().get(0)[0] + neljansuoranPelilauta.getLauta().get(0)[1]
                + neljansuoranPelilauta.getLauta().get(0)[2];

        assertEquals("...", nappuloidenMerkkijono);
    }
}
