
package neljansuora.domain;

import java.util.ArrayList;
import java.util.List;


public class Pelaaja {
    
    private int vuoronumero;
    private List<Nappula> nappulat;
    private String nimi;
    
    public Pelaaja(int vuoronumero, String nimi){
        this.vuoronumero = vuoronumero;
        this.nappulat = new ArrayList<Nappula>();
    }
    
    
}
