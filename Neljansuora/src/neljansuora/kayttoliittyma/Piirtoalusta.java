package neljansuora.kayttoliittyma;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Map;
import javax.swing.JPanel;
import neljansuora.peli.Neljansuora;

/**
 * 
 * @author Eveliina Pakarinen
 */

public class Piirtoalusta extends JPanel implements Paivitettava {

    private Neljansuora neljansuora;

    public Piirtoalusta(Neljansuora neljansuora) {
        super.setBackground(Color.blue);
        this.neljansuora = neljansuora;

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
      
        this.piirraKentta(g);


    }

    public void piirraKentta(Graphics g) {
        int leveys = this.neljansuora.getPelilauta().getLauta().get(0).length;
        int korkeus = this.neljansuora.getPelilauta().getLauta().size();
        for (int i = 0; i < korkeus; i++) {

            String[] rivi = this.neljansuora.getPelilauta().getLauta().get(i);

            for (int j = 0; j < leveys; j++) {

                String merkki = rivi[j];

                if (merkki.equals(".")) {
                    g.setColor(Color.white);
                    g.fillOval(j * 50, i * 50, 50, 50);
                    
                } else if (merkki.equals("X")) {
                    g.setColor(Color.red);
                    g.fillOval(j * 50, i * 50, 50, 50);
                } else if (merkki.equals("O")) {
                    g.setColor(Color.yellow);
                    g.fillOval(j * 50, i * 50, 50, 50);
                }
            }
        }

    }

    @Override
    public void paivita() {
       this.repaint();
    }
}
