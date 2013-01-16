
package neljansuora.peli;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import neljansuora.domain.Pelaaja;


public class Pelilauta {
    
    private int leveys;
    private int korkeus;
    
    private Scanner lukija;
    
    private Map<Integer, String[]> lauta;
    private List<Pelaaja> pelaajat;
    
    
    
    public Pelilauta(int leveys, int korkeus, Scanner lukija){
        this.leveys = leveys;
        this.korkeus = korkeus;
        this.lukija = lukija;
        
        this.lauta = new HashMap<Integer, String[]>();
        this.pelaajat = new ArrayList<Pelaaja>();
        
        this.luoPelilauta();
        
    }
    
    public void tulostaTilanne(){
        
    }
    
    public void luoPelaajat(){
        
        for(int i = 1; i <= 2; i++){
//            System.out.println("Anna pelaajan nimi:");
//            String nimi = lukija.nextLine();
            
            Pelaaja pelaaja = new Pelaaja(i);
            this.pelaajat.add(pelaaja);
        }
        
    }
    
    public List<Pelaaja> palautaPelaajat(){
        return this.pelaajat;
    }
    
    public void luoPelilauta(){
        
        for(int i = 0; i < this.korkeus; i++){
            String[] uusiRivi = new String[this.leveys];
            this.lauta.put(i, uusiRivi);
        }
        
        this.taytaPelilauta();
        this.lisaaNappulatKenttaan();
    }
    
    public void taytaPelilauta(){
        
        for(String[] taulukko : this.lauta.values()){
            
            for(int j = 0; j < taulukko.length; j++){
                taulukko[j] = ".";
            }
            
        }
        
        
    }
    
    public void lisaaNappulatKenttaan(){
        
    }
    
    
}
