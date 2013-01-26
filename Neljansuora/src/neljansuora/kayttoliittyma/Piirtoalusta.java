
package neljansuora.kayttoliittyma;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;
import neljansuora.peli.Neljansuora;


public class Piirtoalusta extends JPanel implements Paivitettava{
    
    private Neljansuora neljansuora;
    
    public Piirtoalusta(Neljansuora neljansuora){
        this.neljansuora = neljansuora;
    }
    
    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        
        g.setColor(Color.BLUE);
    }

    @Override
    public void paivita() {
        
    }
    
    
    
}
