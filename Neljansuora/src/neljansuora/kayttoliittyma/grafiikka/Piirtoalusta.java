package neljansuora.kayttoliittyma.grafiikka;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Map;
import javax.swing.JPanel;
import neljansuora.peli.Neljansuora;

/**
 * Piirtoalusta-luokka piirtää graafiseen käyttöliittymään peliruudukon, jossa
 * näkyvät pelaajien nappulat ja tyhjät ruudut. Piirtoalustalle voi asettaa eri
 * värejä taustaksi ja pelaajien nappuloiden väreiksi.
 *
 * @author Eveliina Pakarinen
 */
public class Piirtoalusta extends JPanel implements Paivitettava {

    /**
     * Kuvaa Neljansuora-pelin samannimistä luokkaa.
     *
     * @see Neljansuora
     */
    private Neljansuora neljansuora;
    /**
     * Kertoo Piirtoalustan taustavärin.
     */
    private Color taustavari;
    /**
     * Kertoo nappulanvärin pelaajalle, joka käyttää pelimerkkinään "X"-merkkiä.
     */
    private Color pelaajanXVari;
    /**
     * Kertoo nappulanvärin pelaajalle, joka käyttää pelimerkkinään "O"-merkkiä.
     */
    private Color pelaajanOVari;

    /**
     * Konstruktori asettaa piirtoalustan oletustaustavärin ja asettaa
     * attribuutteihin konstruktorin parametrien arvot.
     *
     * @param neljansuora Kuvaa Neljansuora-peliä
     */
    public Piirtoalusta(Neljansuora neljansuora) {
        super.setBackground(Color.blue);
        this.neljansuora = neljansuora;
        this.taustavari = Color.blue;
        this.pelaajanXVari = Color.red;
        this.pelaajanOVari = Color.yellow;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        this.piirraKentta(g);

    }

    /**
     * Metodi piirtää Piirtoalustalle Neljansuora-pelin pelikentän, jossa
     * näkyvät tyhjät ruudut ja pelaajien nappulat. Piirtämisen apuna metodi
     * käyttää Neljansuora-pelin pelilautaa.
     *
     * @param g Graphics-luokan ilmentymä
     */
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
                    g.setColor(this.pelaajanXVari);
                    g.fillOval(j * 50, i * 50, 50, 50);
                } else if (merkki.equals("O")) {
                    g.setColor(this.pelaajanOVari);
                    g.fillOval(j * 50, i * 50, 50, 50);
                }
            }
        }

    }
    
    /**
     * Vaihtaa Piirtoalustan taustaväriä.
     * @param vari Kertoo uuden värin.
     */

    public void vaihdaTaustavaria(Color vari) {
        this.taustavari = vari;
        this.setBackground(vari);
    }

    /**
     * Vaihtaa pelaajan nappulan väriä.
     * @param vari Kertoo uuden värin.
     * @param pelaajanNumero Kertoo mille pelaajalle väri asetetaan.
     */
    
    public void vaihdaPelaajanVaria(Color vari, int pelaajanNumero) {
        if (pelaajanNumero == 1) {
            this.pelaajanXVari = vari;
        } else if (pelaajanNumero == 2) {
            this.pelaajanOVari = vari;
        }
    }

    @Override
    public void paivita() {
        this.repaint();
    }
}
