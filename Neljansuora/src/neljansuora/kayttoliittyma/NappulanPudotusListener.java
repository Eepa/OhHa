
package neljansuora.kayttoliittyma;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import neljansuora.peli.Pelilauta;


public class NappulanPudotusListener implements ActionListener{
    
    private Pelilauta lauta;
    private int nappulanNumero;
    
    public NappulanPudotusListener(Pelilauta lauta, String nappulanNumero){
        this.lauta = lauta;
        
        try{
            this.nappulanNumero = Integer.parseInt(nappulanNumero);
        } catch(Exception e){
            System.out.println("Ei oikea numero!");
        }
        
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        this.lauta.teeSiirto(nappulanNumero);
    }
    
}
