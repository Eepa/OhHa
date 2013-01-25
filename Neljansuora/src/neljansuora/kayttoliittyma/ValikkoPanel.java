
package neljansuora.kayttoliittyma;

import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JPanel;


public class ValikkoPanel extends JPanel{
    
    public ValikkoPanel(int korkeus, int leveys, String[] komponenttienSisalto){
        super(new GridLayout(korkeus, leveys));
        luoKomponentit(komponenttienSisalto);
    }
    
    private void luoKomponentit(String[] sisalto){
        
        for(int i = 0; i < sisalto.length; i++){
          add(new JButton(sisalto[i]));  
        }
        
    }
    
}
