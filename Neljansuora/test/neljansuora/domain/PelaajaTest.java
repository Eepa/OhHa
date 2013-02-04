
package neljansuora.domain;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Testiluokka PelaajaTest testaa Pelaaja-luokan toimivuutta yksikkötesteillä.
 * @author Eveliina Pakarinen
 */

public class PelaajaTest {
    
    Pelaaja pelaaja;
    
    @Before
    public void setUp() {
        this.pelaaja = new Pelaaja(2);
        
    }
     
    @Test
    public void luotuPelaajaOlemassa(){
        assertTrue(pelaaja != null);
    }
    
    @Test
    public void palauttaaVuoronumeronOikein(){
        assertEquals(2, pelaaja.getVuoronumero());
    }
    
    @Test
    public void lisaaUudenNappulanOikein(){
        this.pelaaja.lisaaUusiNappula(4, 3);
        
        assertEquals(1, pelaaja.getNappulat().size());
    }
    
    @Test
    public void nappulatPalautetaanOikein(){
        assertTrue(pelaaja.getNappulat() != null);
    }
}
