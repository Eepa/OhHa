
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
    
    
    public boolean onkoMahdollinenSiirto(int rivinNumero){
        
        if(this.lauta.get(0)[rivinNumero].equals(".")){
            return true;
        }
        
        return false;
    }
    
    public void teeSiirto(int rivinNumero){
        
        
        
    }
    
    public boolean onkoVapaaPaikka(int paikanXkoordinaatti){
        
        for(int i = 0; i < this.lauta.size(); i++){
            this.lauta.get(i)[paikanXkoordinaatti].equals(".");
        }
        
        
        
        return false;
    }
    
    
    
}
