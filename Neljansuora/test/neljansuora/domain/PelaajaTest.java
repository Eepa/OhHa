
package neljansuora.domain;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;


public class PelaajaTest {
    
    Pelaaja pelaaja;
    
    @Before
    public void setUp() {
        this.pelaaja = new Pelaaja(1, "Pekka");
        
    }
    
    
    
    @Test
    public void luotuPelaajaOlemassa(){
        assertTrue(pelaaja != null);
    }
}
