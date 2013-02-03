
package neljansuora.kayttoliittyma;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class UusiPeliListener implements ActionListener{
    
    private Kayttoliittyma kayttoliittyma;
    
    public UusiPeliListener(Kayttoliittyma kayttoliittyma){
        this.kayttoliittyma = kayttoliittyma;
    }
    

    @Override
    public void actionPerformed(ActionEvent ae) {
//        this.kayttoliittyma.kaynnistaPeli();
    }
    
}
