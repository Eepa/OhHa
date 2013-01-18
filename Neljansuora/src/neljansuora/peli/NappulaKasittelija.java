
package neljansuora.peli;

import java.util.List;
import java.util.Map;
import neljansuora.domain.Nappula;
import neljansuora.domain.Pelaaja;


public class NappulaKasittelija {
    
    private Map<Integer, String[]> lauta;
    private List<Pelaaja> pelaajat;
    
    public NappulaKasittelija(Map<Integer, String[]> lauta, List<Pelaaja> pelaajat){
        this.lauta = lauta;
        this.pelaajat = pelaajat;
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
    
    public boolean onkoRiittavanPitkiaSuoria(){
        
        String merkki = "";
        
        for(Pelaaja p : this.pelaajat){
            
            if(p.getNappulat().size() < 4){
                return false;
            }
            
            if(p.getVuoronumero() == 1){
                merkki = "X";
            } else {
                merkki = "Y";
            }
            
            if(this.onkoVaakasuorasti(p.getNappulat(), merkki) || 
                    this.onkoPystysuorasti(p.getNappulat(), merkki) 
                    || this.onkoVinosti(p.getNappulat(), merkki)){
                return true;
            }
                        
        }
        
        return false;
    }
    
    public boolean onkoVaakasuorasti(List<Nappula> nappulat, String merkki){
        
        
        
        
        return true;
    }
    
    public boolean onkoPystysuorasti(List<Nappula> nappulat, String merkki){
        return true;
    }
    
    public boolean onkoVinosti(List<Nappula> nappulat, String merkki){
        return true;
    }
    
    
}
