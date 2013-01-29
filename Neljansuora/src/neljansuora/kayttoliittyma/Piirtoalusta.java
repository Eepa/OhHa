package neljansuora.kayttoliittyma;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Map;
import javax.swing.JPanel;
import neljansuora.peli.Neljansuora;

public class Piirtoalusta extends JPanel implements Paivitettava {

    private Neljansuora neljansuora;

    public Piirtoalusta(Neljansuora neljansuora) {
        this.neljansuora = neljansuora;

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(Color.WHITE);

        Map<Integer, String[]> lauta = this.neljansuora.getPelilauta().getLauta();

        for (int i = 0; i < lauta.size(); i++) {

            String[] rivi = lauta.get(i);

            for (int j = 0; j < rivi.length; j++) {

                String merkki = rivi[j];

                if (merkki.equals(".")) {
                    
                    g.fillOval(i, j, lauta.get(0).length *20, lauta.size() *20);
                    
                }

            }

        }



    }

    public void lisaaEkanPelaajanRuutu() {
    }

    public void lisaaTokanPelaajanRuutu() {
    }

    public void lisaaTyhjaRuutu(Color vari) {
    }

    @Override
    public void paivita() {
        this.repaint();
    }
}
