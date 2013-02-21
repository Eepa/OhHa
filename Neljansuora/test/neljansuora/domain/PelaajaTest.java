package neljansuora.domain;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 * Testiluokka PelaajaTest testaa Pelaaja-luokan toimivuutta yksikkötesteillä.
 *
 * @author Eveliina Pakarinen
 */
public class PelaajaTest {

    Pelaaja pelaaja;

    @Before
    public void setUp() {
        this.pelaaja = new Pelaaja(2, "Pekka");
    }

    @Test
    public void luotuPelaajaOlemassa() {
        assertTrue(pelaaja != null);
    }

    @Test
    public void palauttaaVuoronumeronOikein() {
        assertEquals(2, pelaaja.getVuoronumero());
    }

    @Test
    public void lisaaUudenNappulanOikein() {
        this.pelaaja.lisaaUusiNappula(4, 3);
        assertEquals(1, pelaaja.getNappulat().size());
    }

    @Test
    public void nappulatPalautetaanOikein() {
        assertTrue(pelaaja.getNappulat() != null);
    }

    @Test
    public void palauttaaPelaajanNimenOikein() {
        assertEquals("Pekka", pelaaja.getNimi());
    }

    @Test
    public void tyhjentaaNappulalistanOikein() {
        this.pelaaja.lisaaUusiNappula(4, 3);
        this.pelaaja.poistaNappulat();
        assertEquals(0, pelaaja.getNappulat().size());
    }
}
