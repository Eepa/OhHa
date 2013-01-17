
package neljansuora.peli;

import java.util.List;
import java.util.Map;
import neljansuora.domain.Pelaaja;


public class PelaajaSuorittaja {
    
    private Map<Integer, String[]> lauta;
    private List<Pelaaja> pelaajat;
    
    public PelaajaSuorittaja(Map<Integer, String[]> lauta, List<Pelaaja> pelaajat){
        this.lauta = lauta;
        this.pelaajat = pelaajat;
    }
    
    
    public boolean onkoMahdollinenSiirto(int pystyRivinNumero){
        
        if(this.lauta.get(0)[pystyRivinNumero].equals(".")){
            return true;
        }
        
        return false;
    }
    
    public void teeSiirto(int pystyRivinNumero, Pelaaja p){
        
        int vaakaRivinNumero = 0;
        
        for(int i = 0; i < this.lauta.size(); i++){
            while(!this.lauta.get(i)[pystyRivinNumero].equals(".")){
                vaakaRivinNumero = i;
                p.lisaaUusiNappula(pystyRivinNumero, vaakaRivinNumero);
                return;
            }
        }
        
    }
    
    public int etsiVapaaPaikka(int pystyRivinNumero){
        
        
        
        return 0;
    }
    
    
    
}
