
package neljansuora.kayttoliittyma;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.swing.JButton;
import javax.swing.JPanel;
import neljansuora.peli.Neljansuora;


public class RivinappulatPanel extends JPanel{
    
    private Neljansuora neljansuora;
    private TilannetietoPanel tilannetietoPanel;
    
    public RivinappulatPanel(Neljansuora neljansuora, TilannetietoPanel tilannetietoPanel){
                       
        super(new GridLayout(1, neljansuora.getPelilauta().getLauta().get(0).length));
        this.neljansuora = neljansuora;
        this.tilannetietoPanel = tilannetietoPanel;        
        this.luoNappulat();
    }
    
    private void luoNappulat(){
        int nappuloidenMaara = neljansuora.getPelilauta().getLauta().get(0).length;
        
        List<JButton> nappilista = new ArrayList<JButton>();
        
        for(int i = 0; i < nappuloidenMaara; i++){
            String nappulanIndeksi = "" + i;
            JButton uusiNappi = new JButton(nappulanIndeksi);   
            nappilista.add(uusiNappi);
        }
        
        for(JButton nappi : nappilista){
            nappi.addActionListener(new NappulanPudotusListener(this.neljansuora.getPelilauta(), 
                    nappi.getText(), this.tilannetietoPanel));
            
            add(nappi);
        }
        
        
    }
    
        
}
