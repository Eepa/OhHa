package neljansuora.peli;

import java.util.List;
import java.util.Scanner;
import neljansuora.domain.Pelaaja;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

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
    private LautaKasittelija lautakasittelija;

    @Before
    public void setUp() {
        String teksti = "Pekka\n" + "Jukka\n";
        this.lukija = new Scanner(teksti);

        this.neljansuora = new Neljansuora(4, 4, 4, this.lukija, "teksti");

        this.lautakasittelija = new LautaKasittelija(this.neljansuora.getPelilauta().getLauta(),
                this.neljansuora.getPelilauta().getPelaajat(), this.lukija);

        this.nappulakasittelija = new NappulaKasittelija(this.neljansuora.getPelilauta().getLauta(),
                this.neljansuora.getPelilauta().getPelaajat(), 4);
    }

    @Test
    public void luotuNappulaKasittelijaOlemassa() {
        assertTrue(this.nappulakasittelija != null);
    }

    @Test
    public void pisimmanSivunEtsiminenToimii() {
        assertEquals(4, this.nappulakasittelija.selvitaLaudanPisimmanSivunPituus());
    }

    @Test
    public void pisimmanSivunEtsiminenToimiiEriLauta() {
        String teksti = "Pekka\n" + "Jukka\n";
        Scanner lukijaUusi = new Scanner(teksti);

        Neljansuora neljansuoraUusi = new Neljansuora(5, 9, 4, lukijaUusi, "teksti");

        LautaKasittelija lautakasittelijaUusi = new LautaKasittelija(neljansuoraUusi.getPelilauta().getLauta(),
                neljansuoraUusi.getPelilauta().getPelaajat(), lukijaUusi);

        NappulaKasittelija nappulakasittelijaUusi = new NappulaKasittelija(neljansuoraUusi.getPelilauta().getLauta(),
                neljansuoraUusi.getPelilauta().getPelaajat(), 4);

        assertEquals(9, nappulakasittelijaUusi.selvitaLaudanPisimmanSivunPituus());
    }

    @Test
    public void onkoLautaTaynnaPalauttaaOikeinJosEiTaynna() {
        assertTrue(!this.nappulakasittelija.onkoLautaTaynna());
    }

    @Test
    public void onkoLautaTaynnaPalauttaaOikeinJosTaynna() {
        this.lisaaPelaajanNappulat();

        Pelaaja p = this.neljansuora.getPelilauta().getPelaajat().get(0);
        this.lautakasittelija.lisaaPelaajanNappulat("X", p.getNappulat());

        assertTrue(this.nappulakasittelija.onkoLautaTaynna());
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
        this.lisaaPelaajanNappulat();

        Pelaaja p = this.neljansuora.getPelilauta().getPelaajat().get(0);
        this.lautakasittelija.lisaaPelaajanNappulat("X", p.getNappulat());

        assertTrue(this.nappulakasittelija.onkoRiittavanPitkiaSuoria());
    }

    @Test
    public void onkoMahdollinenSiirtoPalauttaaOikeinJosOnMahdollinen() {
        int oikeidenMaara = 4;

        for (int i = 0; i < 4; i++) {
            if (this.nappulakasittelija.onkoMahdollinenSiirto(i)) {
                oikeidenMaara--;
            }
        }
        assertEquals(oikeidenMaara, 0);
    }

    @Test
    public void onkoMahdollinenSiirtoPalauttaaOikeinJosEiOleMahdollinen() {
        this.lisaaPelaajanNappulat();
        
        Pelaaja p = this.neljansuora.getPelilauta().getPelaajat().get(0);
        this.lautakasittelija.lisaaPelaajanNappulat("X", p.getNappulat());
        
        assertFalse(this.nappulakasittelija.onkoMahdollinenSiirto(1));
    }

    public void lisaaPelaajanNappulat() {
        Pelaaja p = this.neljansuora.getPelilauta().getPelaajat().get(0);

        for (int i = 0; i < this.neljansuora.getPelilauta().getLauta().size(); i++) {
            String[] rivi = this.neljansuora.getPelilauta().getLauta().get(i);
            for (int j = 0; j < rivi.length; j++) {
                p.lisaaUusiNappula(j, i);
            }
        }
        this.lautakasittelija.lisaaPelaajanNappulat("X", p.getNappulat());
    }

    @Test
    public void siirronTekeminenOnnistuu() {
        List<Pelaaja> pelaajat = this.neljansuora.getPelilauta().getPelaajat();
        this.nappulakasittelija.teeSiirto(0, pelaajat.get(0));
        this.lautakasittelija.lisaaNappulatKenttaan(pelaajat);
        
        String merkkiOikeassaKohdassa = this.neljansuora.getPelilauta().getLauta().get(3)[0];
        assertEquals("X", merkkiOikeassaKohdassa);
    }

    @Test
    public void pelaajanMerkkiPalautetaanOikein() {
        List<Pelaaja> pelaajat = this.neljansuora.getPelilauta().getPelaajat();

        String pelaajanMerkki = this.nappulakasittelija.annaPelaajanMerkki(pelaajat.get(1));
        assertEquals("O", pelaajanMerkki);
    }

    @Test
    public void onkoVaakasuorastiPalauttaaOikeinJosEiSuoria() {
        assertFalse(this.nappulakasittelija.onkoVaakasuorasti("X", 4));
    }

    @Test
    public void onkoVaakasuorastiPalauttaaOikeinJosOnSuoria() {
        this.lisaaPelaajanNappulat();
        
        Pelaaja p = this.neljansuora.getPelilauta().getPelaajat().get(0);
        this.lautakasittelija.lisaaPelaajanNappulat("X", p.getNappulat());

        assertTrue(this.nappulakasittelija.onkoVaakasuorasti("X", 4));
    }

    @Test
    public void onkoPystysuorastiPalauttaaOikeinJosEiSuoria() {
        assertFalse(this.nappulakasittelija.onkoPystysuorasti("X", 4));
    }

    @Test
    public void onkoPystysuorastiPalauttaaOikeinJosOnSuoria() {
        this.lisaaPelaajanNappulat();
        
        Pelaaja p = this.neljansuora.getPelilauta().getPelaajat().get(0);
        this.lautakasittelija.lisaaPelaajanNappulat("X", p.getNappulat());
        
        assertTrue(this.nappulakasittelija.onkoPystysuorasti("X", 4));
    }

    @Test
    public void testattavanJononLuominenToimii() {
        String jono = this.nappulakasittelija.luoTestattavaJono(this.neljansuora.getPelilauta().getLauta().get(0));
        assertEquals("....", jono);
    }

    @Test
    public void pystyrivinTestattavanJononLuominenToimii() {
        String jono = this.nappulakasittelija.luoPystyrivinTestattavaJono();
        assertEquals("................", jono);
    }

    @Test
    public void onkoVinostiKoillinenLounasPalauttaaOikeinJosEiSuoria() {
        assertFalse(this.nappulakasittelija.onkoVinostiKoillinenLounas("X", 4));
    }

    @Test
    public void onkoVinostiKoillinenLounasPalauttaaOikeinJosOnSuoria() {
        this.lisaaPelaajanNappulat();

        Pelaaja p = this.neljansuora.getPelilauta().getPelaajat().get(0);
        this.lautakasittelija.lisaaPelaajanNappulat("X", p.getNappulat());

        assertTrue(this.nappulakasittelija.onkoVinostiKoillinenLounas("X", 4));
    }

    @Test
    public void onkoVinostiKaakkoLuodePalauttaaOikeinJosEiSuoria() {
        assertFalse(this.nappulakasittelija.onkoVinostiKaakkoLuode("X", 4));
    }

    @Test
    public void onkoVinostiKaakkoLuodePalauttaaOikeinJosOnSuoria() {
        this.lisaaPelaajanNappulat();

        Pelaaja p = this.neljansuora.getPelilauta().getPelaajat().get(0);
        this.lautakasittelija.lisaaPelaajanNappulat("X", p.getNappulat());

        assertTrue(this.nappulakasittelija.onkoVinostiKaakkoLuode("X", 4));
    }

    @Test
    public void muodostaMerkkijonoVinolleSuorallePalauttaaOikeinJosEiSuoriaKaakkoLuode() {
        String[] rivi = this.neljansuora.getPelilauta().getLauta().get(0);

        assertFalse(this.nappulakasittelija.muodostaMerkkijonoVinolleSuoralle(rivi, "X", 4, 0, "kaakkoluode"));
    }

    @Test
    public void muodostaMerkkijonoVinolleSuorallePalauttaaOikeinJosEiSuoriaKoillinenLounas() {
        String[] rivi = this.neljansuora.getPelilauta().getLauta().get(0);

        assertFalse(this.nappulakasittelija.muodostaMerkkijonoVinolleSuoralle(rivi, "X", 4, 0, "koillinenlounas"));
    }

    @Test
    public void muodostaVinoMerkkijonoPalauttaaOikeanJononKoillinenLounas() {
        String[] rivi = this.neljansuora.getPelilauta().getLauta().get(3);
        String merkkijono = "";
        
        for (int j = 0; j < rivi.length; j++) {
            merkkijono += rivi[j];

            merkkijono = this.nappulakasittelija.muodostaVinoMerkkijono(3, j, merkkijono, "koillinenlounas");
        }
        assertEquals("....", merkkijono);
    }

    @Test
    public void muodostaVinoMerkkijonoPalauttaaOikeanJononKaakkoLuode() {
        String[] rivi = this.neljansuora.getPelilauta().getLauta().get(0);
        String merkkijono = "";
        
        for (int j = 0; j < rivi.length; j++) {
            merkkijono += rivi[j];
            
            merkkijono = this.nappulakasittelija.muodostaVinoMerkkijono(0, j, merkkijono, "kaakkoluode");
        }
        assertEquals("....", merkkijono);
    }

    @Test
    public void laskeSeuraavatPisteetKaakkoLuodePalauttaaOikeanMerkkijonon() {
        String[] rivi = this.neljansuora.getPelilauta().getLauta().get(0);
        String merkkijono = "";
        
        for (int j = 0; j < rivi.length; j++) {
            merkkijono += rivi[j];

            merkkijono = this.nappulakasittelija.laskeSeuraavatPisteetKaakkoLuode(0, j, merkkijono, "kaakkoluode");
        }
        assertEquals("....", merkkijono);
    }

    @Test
    public void laskeSeuraavatPisteetKoillinenLounasPalauttaaOikeanMerkkijonon() {
        String[] rivi = this.neljansuora.getPelilauta().getLauta().get(3);
        String merkkijono = "";
        
        for (int j = 0; j < rivi.length; j++) {
            merkkijono += rivi[j];

            merkkijono = this.nappulakasittelija.laskeSeuraavatPisteetKoillinenLounas(3, j, merkkijono, "koillinenlounas");
        }
        assertEquals("....", merkkijono);
    }

    @Test
    public void yEhdonLaskeminenToimiiKoillinenLounasLiianIsoLuku() {
        assertTrue(this.nappulakasittelija.tarkistaYEhtoSeuraavaaPistettaLaskettaessa(4, "koillinenlounas"));
    }

    @Test
    public void yEhdonLaskeminenToimiiKoillinenLounasSopivaLuku() {
        assertFalse(this.nappulakasittelija.tarkistaYEhtoSeuraavaaPistettaLaskettaessa(3, "koillinenlounas"));
    }

    @Test
    public void yEhdonLaskeminenToimiiKaakkoLuodeLiianIsoLuku() {
        assertTrue(this.nappulakasittelija.tarkistaYEhtoSeuraavaaPistettaLaskettaessa(-1, "kaakkoluode"));
    }

    @Test
    public void yEhdonLaskeminenToimiiKaakkoLuodeSopivaLuku() {
        assertFalse(this.nappulakasittelija.tarkistaYEhtoSeuraavaaPistettaLaskettaessa(0, "kaakkoluode"));
    }

    @Test
    public void laskeSeuraavaPistePalauttaaOikeanMerkkijononKoillinenLounas() {
        String[] rivi = this.neljansuora.getPelilauta().getLauta().get(3);
        String merkkijono = "";
        
        for (int j = 0; j < rivi.length; j++) {
            merkkijono += rivi[j];

            merkkijono = this.nappulakasittelija.laskeSeuraavaPiste(3, j, 1, -1, merkkijono, "koillinenlounas");
        }
        assertEquals("....", merkkijono);
    }

    @Test
    public void laskeSeuraavaPistePalauttaaOikeanMerkkijononKaakkoLuode() {
        String[] rivi = this.neljansuora.getPelilauta().getLauta().get(0);
        String merkkijono = "";
        
        for (int j = 0; j < rivi.length; j++) {
            merkkijono += rivi[j];

            merkkijono = this.nappulakasittelija.laskeSeuraavaPiste(0, j, -1, -1, merkkijono, "kaakkoluode");
        }
        assertEquals("....", merkkijono);
    }
}
