
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


public class PelilautaTest {
    
    Pelilauta pelilauta;
    Scanner lukija;
    
    @Before
    public void setUp() {
        
//        String syote1 = "";
        
        this.lukija = new Scanner(System.in);
        this.pelilauta = new Pelilauta(8, 9, lukija);
    }
    
    @Test
    public void luotuPelilautaOlemassa(){
        assertTrue(pelilauta != null);
    }
    
    @Test
    public void luoKaksiPelaajaa(){
        
//        String syote1 = "Pekka\n";
//
//        this.lukija = new Scanner(syote1);
//
//        String syote2 = "Jukka\n";
//
//        this.lukija = new Scanner(syote2);
//          
        this.pelilauta.luoPelaajat();
          

        
        assertEquals(2, pelilauta.getPelaajat().size());
    }
    
    @Test
    public void palauttaaPelaajatOikein(){
        
        this.pelilauta.luoPelaajat();
        
        List<Pelaaja> pelaajat = this.pelilauta.getPelaajat();
        int summa = 0;
        for(Pelaaja p : pelaajat){
            summa = summa + p.getVuoronumero();
        }
        
        assertEquals(3, summa);
    }
    
}
