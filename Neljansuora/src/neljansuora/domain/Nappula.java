
package neljansuora.domain;
/**
 * Luokka kuvaa Nappula-oliota, joka on kuvaa yksittäistä pelaajalle kuuluvaa nappulaa.
 * @author Eveliina Pakarinen
 */

public class Nappula {
    
    /**
     * Koordinaatti x kuvaa nappulan sijaintia pelilaudan vaaka-akselilla.
     */
    
    private int x;
    
    /**
     * Koordinaatti y kuvaa nappulan sijaintia pelilaudan pystyakselilla.
     */
    private int y;
    
    /**
     * Konstruktori luo uuden nappulan ja asettaa muuttujien x ja y arvot arvot niitä vastaaviin oliomuuttujiin.
     * @param x Nappulan x-koordinaatin arvo
     * @param y Nappulan y-koordinaatin arvo
     */
    
    public Nappula(int x, int y){
        this.x = x;
        this.y = y;
    }
    
    public int palautaX(){
        return this.x;
    }
    
    public int palautaY(){
        return this.y;
    }
    
}
