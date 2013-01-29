
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
        
        add(new JButton("Uusi peli"));
        add(new JButton("Väriasetukset"));
        add(new JButton("Joku nappi"));
        
    }
    
}
