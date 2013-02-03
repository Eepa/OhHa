
package neljansuora.domain;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;


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
