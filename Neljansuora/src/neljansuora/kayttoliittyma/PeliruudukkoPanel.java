
package neljansuora.kayttoliittyma;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.GridLayout;
import java.util.Map;
import javax.swing.JPanel;
import neljansuora.peli.Neljansuora;


public class PeliruudukkoPanel extends JPanel{
    
    private Neljansuora neljansuora;
    private Paivitettava piirtoalusta;
    private int leveys;
    private int korkeus;
    
    public PeliruudukkoPanel(Neljansuora neljansuora, Paivitettava piirtoalusta){
        
               
        super(new GridLayout(neljansuora.getPelilauta().getLauta().size(), neljansuora.getPelilauta().getLauta().get(0).length));
        this.neljansuora = neljansuora;
        this.piirtoalusta = piirtoalusta;
        
        this.setBackground(Color.blue);
        
    }
    
    private void piirraNappulat(){
        
        Map<Integer, String[]> lauta = this.neljansuora.getPelilauta().getLauta();
        
        for(int i = 0; i < lauta.size(); i++){
            
            String[] rivi = lauta.get(i);
            
            for(int j = 0; j < rivi.length; j++){
                
                String merkki = rivi[j];
                
                if(merkki.equals(".")){
                    
                }else if(merkki.equals("X")){
                    
                }else{
                    
                }
                
            }
            
        }
        
        
    }
    
        
}
