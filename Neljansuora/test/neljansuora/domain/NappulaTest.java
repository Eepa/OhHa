
package neljansuora.domain;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 * Testiluokka NappulaTest tekee Nappula-luokalle yksikkötestit, jotka testaavat luokan toimivuutta.
 * @author Eveliina Pakarinen
 */

public class NappulaTest {
    
    Nappula nappula;
    
   
    @Before
    public void setUp() {
        this.nappula = new Nappula(5, 9);
    }
    
    @Test
    public void luotuNappulaOlemassa(){
        assertTrue(nappula != null);
    }
    
    @Test
    public void toimiikoPalautaX(){
        assertEquals(5, nappula.palautaX());
    }
    
    @Test
    public void toimiikoPalautaY(){
        assertEquals(9, nappula.palautaY());
    }
    
    
}
