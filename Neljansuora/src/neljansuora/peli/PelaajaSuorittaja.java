
package neljansuora.peli;

import java.util.List;
import java.util.Map;
import neljansuora.domain.Pelaaja;


public class PelaajaSuorittaja {
    
    private Map<Integer, String[]> lauta;
    
    
    public PelaajaSuorittaja(Map<Integer, String[]> lauta){
        this.lauta = lauta;
        
    }
    
    
    public boolean onkoMahdollinenSiirto(int vaakarivinNumero){
        
        if(this.lauta.get(0)[vaakarivinNumero].equals(".")){
            return true;
        }
        
        return false;
    }
    
    public void teeSiirto(int pystyRivinNumero, Pelaaja p){
        
        int vaakarivinNumero = this.lauta.size()-1;
        
        while(vaakarivinNumero >= 0){
            
            String[] rivi = this.lauta.get(vaakarivinNumero);
            
            if(rivi[pystyRivinNumero].equals(".")){
                p.lisaaUusiNappula(pystyRivinNumero, vaakarivinNumero);
                return;
            }
            vaakarivinNumero--;
        }
        
    }
    
    public int etsiVapaaPaikka(int vaakaRivinNumero){
        
        
        
        return 0;
    }
    
    
    
}
