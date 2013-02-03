
package neljansuora.kayttoliittyma;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import neljansuora.peli.Pelilauta;


public class NappulanPudotusListener implements ActionListener{
    
    private Pelilauta lauta;
    private int nappulanNumero;
    private TilannetietoPanel tilannetietoPanel;
    
    public NappulanPudotusListener(Pelilauta lauta, String nappulanNumero, 
            TilannetietoPanel tilannetietoPanel){
        this.lauta = lauta;
        this.tilannetietoPanel = tilannetietoPanel;
        
        try{
            this.nappulanNumero = Integer.parseInt(nappulanNumero);
        } catch(Exception e){
            System.out.println("Ei oikea numero!");
        }
        
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        this.lauta.teeSiirto(nappulanNumero, this.tilannetietoPanel.getVuoronumero());
        this.tilannetietoPanel.paivita();
    }
    
}
