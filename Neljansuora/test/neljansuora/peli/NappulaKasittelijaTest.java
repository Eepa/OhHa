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
        assertEquals(this.nappulakasittelija.selvitaLaudanPisimmanSivunPituus(), 4);
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

        this.lisaaPelaajanNappulat();
        
        Pelaaja p = this.neljansuora.getPelilauta().getPelaajat().get(0);
        
        this.lautakasittelija.lisaaPelaajanNappulat("X", p.getNappulat());

        assertFalse(this.nappulakasittelija.onkoMahdollinenSiirto(1));
    }
    
    public void lisaaPelaajanNappulat(){
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
    public void siirronTekeminenOnnistuu(){
        List<Pelaaja> pelaajat = this.neljansuora.getPelilauta().getPelaajat();
        
        this.nappulakasittelija.teeSiirto(0, pelaajat.get(0));
        
        this.lautakasittelija.lisaaNappulatKenttaan(pelaajat);
        
        String merkkiOikeassaKohdassa = this.neljansuora.getPelilauta().getLauta().get(3)[0];
        
        
        assertEquals("X", merkkiOikeassaKohdassa);
    }
    
    @Test
    public void pelaajanMerkkiPalautetaanOikein(){
        List<Pelaaja> pelaajat = this.neljansuora.getPelilauta().getPelaajat();
        
        String pelaajanMerkki = this.nappulakasittelija.annaPelaajanMerkki(pelaajat.get(1));
        assertEquals("O", pelaajanMerkki);
    }
    
    @Test
    public void onkoVaakasuorastiPalauttaaOikeinJosEiSuoria(){
        assertFalse(this.nappulakasittelija.onkoVaakasuorasti("X", 4));
    }
    
    @Test
    public void onkoVaakasuorastiPalauttaaOikeinJosOnSuoria(){
        this.lisaaPelaajanNappulat();
        
        Pelaaja p = this.neljansuora.getPelilauta().getPelaajat().get(0);
        
        this.lautakasittelija.lisaaPelaajanNappulat("X", p.getNappulat());
        
        assertTrue(this.nappulakasittelija.onkoVaakasuorasti("X", 4));
    }
    
    @Test
    public void onkoPystysuorastiPalauttaaOikeinJosEiSuoria(){
        assertFalse(this.nappulakasittelija.onkoPystysuorasti("X", 4));
    }
    
    @Test
    public void onkoPystysuorastiPalauttaaOikeinJosOnSuoria(){
        this.lisaaPelaajanNappulat();
        
        Pelaaja p = this.neljansuora.getPelilauta().getPelaajat().get(0);
        
        this.lautakasittelija.lisaaPelaajanNappulat("X", p.getNappulat());
        
        assertTrue(this.nappulakasittelija.onkoPystysuorasti("X", 4));
    }
    
    @Test
    public void testattavanJononLuominenToimii(){
        String jono = this.nappulakasittelija.luoTestattavaJono(this.neljansuora.getPelilauta().getLauta().get(0));
        assertEquals("....", jono);
    }
    
   @Test
    public void pystyrivinTestattavanJononLuominenToimii(){
        String jono = this.nappulakasittelija.luoPystyrivinTestattavaJono();
        assertEquals("................", jono);
    }
    
}
