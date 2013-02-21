package neljansuora.peli;

import java.util.Scanner;
import neljansuora.domain.Pelaaja;
import neljansuora.kayttoliittyma.grafiikka.Paivitettava;
import neljansuora.kayttoliittyma.grafiikka.Piirtoalusta;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

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
    
    @Test
    public void pelinLopettaminenOnnistuu(){
        Paivitettava paivitettava = new Piirtoalusta(neljansuora);
        Pelilauta neljansuoranPelilauta = neljansuora.getPelilauta();

        neljansuora.setPaivitettava(paivitettava);

        lisaaPelaajalleNappuloita(neljansuoranPelilauta.getPelaajat().get(0), 3);

        neljansuoranPelilauta.getLautaKasittelija().lisaaNappulatKenttaan(neljansuoranPelilauta.getPelaajat());

        neljansuora.lopetaPeli();

        String nappuloidenMerkkijono = neljansuoranPelilauta.getLauta().get(0)[0] + neljansuoranPelilauta.getLauta().get(0)[1]
                + neljansuoranPelilauta.getLauta().get(0)[2];

        assertEquals("...", nappuloidenMerkkijono);
    }
    
    private void lisaaPelaajalleNappuloita(Pelaaja p, int maara) {
        for (int i = 0; i < maara; i++) {
            p.lisaaUusiNappula(i, 0);
        }
    }

   }
