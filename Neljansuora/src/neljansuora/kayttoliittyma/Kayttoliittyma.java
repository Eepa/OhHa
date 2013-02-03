
package neljansuora.kayttoliittyma;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import neljansuora.peli.Neljansuora;
import neljansuora.peli.Pelilauta;


public class Kayttoliittyma implements Runnable {
    
    private JFrame frame;
    private Neljansuora neljansuora;
    private Piirtoalusta piirtoalusta;
    
    
    public Kayttoliittyma(Neljansuora neljansuora){
        this.neljansuora = neljansuora;
//        this.neljansuora.kaynnista();
    }

    @Override
    public void run() {
        frame = new JFrame("Neljän Suora");
        
        int leveys = (this.neljansuora.getPelilauta().getLauta().get(0).length) * 55;
        int korkeus = (this.neljansuora.getPelilauta().getLauta().size()+ 3) * 50;
        frame.setPreferredSize(new Dimension(leveys, korkeus));
        
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        
        luoKomponentit(frame.getContentPane());
        
        
        frame.pack();
        frame.setVisible(true);
        
    }
    
    private void luoKomponentit(Container container){
                
        container.setLayout(new BorderLayout());
        
        this.piirtoalusta = new Piirtoalusta(this.neljansuora);
        
        TilannetietoPanel tilannetietoPanel = new TilannetietoPanel(this.neljansuora);
        
        container.add(tilannetietoPanel, BorderLayout.SOUTH);
        
        container.add(new PiirtoPanel(this.neljansuora, this.piirtoalusta, tilannetietoPanel), 
                BorderLayout.CENTER);
        
        container.add(new ValikkoPanel(1, 3), BorderLayout.NORTH);
//        container.add(new RivinappulatPanel(this.neljansuora), BorderLayout.SOUTH);
        
        
        
        
    }
    
    public void kaynnistaPeli(){
        
//        neljansuora.kaynnista();
        
        Pelilauta lauta = this.neljansuora.getPelilauta();
        
        while(true){
            
            if(lauta.onkoLautaTaynna() || lauta.onkoNeljanSuoraa()){
                break;
            }
            
            neljansuora.pelaaKierros();
            
        }
        
        
        
        System.exit(0);
    
        
    }
    
    public Paivitettava getPaivitettava(){
        return this.piirtoalusta;
    }
    
    
    public JFrame getFrame(){
        return this.frame;
    }
    
}
