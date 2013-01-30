
package neljansuora.kayttoliittyma;

import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JPanel;


public class ValikkoPanel extends JPanel{
    
    int leveys;
    
    public ValikkoPanel(int korkeus, int leveys){
        super(new GridLayout(korkeus, leveys));
        this.leveys = leveys;
        luoKomponentit();
    }
    
    private void luoKomponentit(){
        
        JButton uusiPeli = new JButton("Uusi peli");
        JButton variasetukset = new JButton("Väriasetukset");
        
        JButton jokuNappi = new JButton("Joku nappi");
        
        add(uusiPeli);
        add(variasetukset);
        add(jokuNappi);
        
    }
    
}
