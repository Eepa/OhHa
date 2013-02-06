
package neljansuora.kayttoliittyma;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import neljansuora.peli.Neljansuora;


public class VarinvaihtoListener implements ActionListener{
    
    private Neljansuora neljansuora;
    private Piirtoalusta piirtoalusta;
    private int varipaletinNumero;
    
    public VarinvaihtoListener(Neljansuora neljansuora, Piirtoalusta piirtoalusta){
        
        this.neljansuora = neljansuora;
        this.piirtoalusta = piirtoalusta;
        this.varipaletinNumero = 1;
    }
    
    

    @Override
    public void actionPerformed(ActionEvent e) {
        
        // --> parempia ratkaisuita?? Ensin pitaa saada tama toimimaan
        
//        if(this.varipaletinNumero == 1){
//            
//        } else if(this.varipaletinNumero == 2){
//             
//        } else if(this.varipaletinNumero == 3){
//            
//        }
        
        
    }
    
    public void setVaripaletinNumero(){
        if(this.varipaletinNumero < 3){
            this.varipaletinNumero++;
        } else if(this.varipaletinNumero == 3){
            this.varipaletinNumero = 1;
        }
    }
    
    
    
}
