
package neljansuora.kayttoliittyma;

import java.awt.BorderLayout;
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
    
    
    public Kayttoliittyma(Neljansuora neljansuora){
        this.neljansuora = neljansuora;
//        this.neljansuora.kaynnista();
    }

    @Override
    public void run() {
        frame = new JFrame("Neljän Suora");
        frame.setPreferredSize(new Dimension(500, 500));
        
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        
        luoKomponentit(frame.getContentPane());
        
        frame.pack();
        frame.setVisible(true);
        
    }
    
    private void luoKomponentit(Container container){
        container.setLayout(new BorderLayout());
        
        container.add(new ValikkoPanel(1, 5, new String[5]), BorderLayout.NORTH);
        container.add(new PeliruudukkoPanel(this.neljansuora));
        
    }
    
    
    public JFrame getFrame(){
        return this.frame;
    }
    
}
