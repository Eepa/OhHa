
package neljansuora.peli;

import java.util.Scanner;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;


/**
 * Testiluokka NeljansuoraTest testaa luokan Neljansuora toimivuutta yksikkötesteillä.
 * @author Eveliina Pakarinen
 */

public class NeljansuoraTest {
    
    Scanner lukija;
    Neljansuora neljansuora;
    
    @Before
    public void setUp() {
        this.lukija = new Scanner(System.in);
        this.neljansuora = new Neljansuora(8, 9, 4, lukija);
        
    }
    
    @Test
    public void luotuNeljansuoraOlemassa(){
        assertTrue(neljansuora != null);
    }
    
    @Test
    public void pelilautaLuodaanAlussa(){
        assertTrue(this.neljansuora.getPelilauta() != null);
    }
    
    @Test
    public void kaynnistaMetodinSuoritusLoppuu(){
        // tehdaan sisalto kun osataan
    }
    
}
