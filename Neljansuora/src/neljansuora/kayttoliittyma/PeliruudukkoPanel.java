
package neljansuora.kayttoliittyma;

import java.awt.Canvas;
import java.awt.GridLayout;
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
        
    }
    
    private void luoRuudukko(){
        
        
        
        
        
    }
    
    
    
}
