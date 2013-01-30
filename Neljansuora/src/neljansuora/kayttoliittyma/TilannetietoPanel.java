
package neljansuora.kayttoliittyma;

import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import neljansuora.peli.Neljansuora;


public class TilannetietoPanel extends JPanel{
    
    private Neljansuora neljansuora;
    
    public TilannetietoPanel(Neljansuora neljansuora){
        super(new GridLayout(2,1));
        this.neljansuora = neljansuora;
        this.luoTietopaneeli();
    }
    
    private void luoTietopaneeli(){
        JLabel vuorossa = new JLabel("Vuorossa:");
        JLabel kukaVuorossa = new JLabel("Pelaaja1");
        
        add(vuorossa);
        add(kukaVuorossa);
    }
    
}
