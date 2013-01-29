
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
        
        int leveys = (this.neljansuora.getPelilauta().getLauta().get(0).length +1) * 50;
        int korkeus = (this.neljansuora.getPelilauta().getLauta().size()+1) * 50;
        frame.setPreferredSize(new Dimension(leveys, korkeus));
        
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        
        luoKomponentit(frame.getContentPane());
        
        frame.pack();
        frame.setVisible(true);
        
    }
    
    private void luoKomponentit(Container container){
        
        this.piirtoalusta = new Piirtoalusta(this.neljansuora);
        container.add(this.piirtoalusta);
        
        container.setLayout(new BorderLayout());
        
        container.add(new ValikkoPanel(1, 3), BorderLayout.NORTH);
        container.add(new PeliruudukkoPanel(this.neljansuora, this.piirtoalusta));
        
    }
    
    public Paivitettava getPaivitettava(){
        return this.piirtoalusta;
    }
    
    
    public JFrame getFrame(){
        return this.frame;
    }
    
}
