
package neljansuora.kayttoliittyma;

import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import neljansuora.peli.Neljansuora;


public class ValikkoPanel extends JPanel{
    
    private int leveys;
    private Neljansuora neljansuora;
    private TilannetietoPanel tilannetietoPanel;
    private Kayttoliittyma kayttoliittyma;
    
    public ValikkoPanel(int korkeus, int leveys, 
            Neljansuora neljansuora, TilannetietoPanel tilannetietoPanel, Kayttoliittyma kayttoliittyma){
        super(new GridLayout(korkeus, leveys));
        this.leveys = leveys;
        this.neljansuora = neljansuora;
        this.tilannetietoPanel = tilannetietoPanel;
        luoKomponentit();
    }
    
    private void luoKomponentit(){
        
        JButton uusiPeli = new JButton("Uusi peli");
        
        uusiPeli.addActionListener(new UusiPeliListener(this.kayttoliittyma));
        
        JButton variasetukset = new JButton("Väriasetukset");
        
        JButton jokuNappi = new JButton("Joku nappi");
        
        add(uusiPeli);
        add(variasetukset);
        add(jokuNappi);
        
    }
    
}
