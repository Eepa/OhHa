
package neljansuora.kayttoliittyma;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import neljansuora.peli.Neljansuora;


public class PiirtoPanel extends JPanel{
    
    private Neljansuora neljansuora;
    private Piirtoalusta piirtoalusta;
    private TilannetietoPanel tilannetietoPanel;
    
    
    public PiirtoPanel(Neljansuora neljansuora, Piirtoalusta piirtoalusta, 
            TilannetietoPanel tilannetietoPanel){
        super(new BorderLayout());
        
        this.neljansuora = neljansuora;
        this.piirtoalusta = piirtoalusta;
        this.tilannetietoPanel = tilannetietoPanel;
        luoOsat();
    }
    
    private void luoOsat(){
        add(this.piirtoalusta, BorderLayout.CENTER);
        add(new RivinappulatPanel(this.neljansuora, this.tilannetietoPanel), BorderLayout.SOUTH);
    }
    
}
