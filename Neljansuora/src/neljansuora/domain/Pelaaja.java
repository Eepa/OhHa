
package neljansuora.domain;

import java.util.ArrayList;
import java.util.List;


public class Pelaaja {
    
    private int vuoronumero;
    private List<Nappula> nappulat;
//    private String nimi;
    
    public Pelaaja(int vuoronumero){
        this.vuoronumero = vuoronumero;
//        this.nimi = nimi;
        this.nappulat = new ArrayList<Nappula>();
    }
    
    public List getNappulat(){
        return this.nappulat;
    }
    
    public int getVuoronumero(){
        return this.vuoronumero;
    }
    
    public void lisaaUusiNappula(int x, int y){
        this.nappulat.add(new Nappula(x, y));
    }
    
    
}
