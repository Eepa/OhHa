package neljansuora.kayttoliittyma;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.Scanner;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import neljansuora.peli.Neljansuora;
import neljansuora.peli.Pelilauta;

/**
 * Kayttoliittyma-luokka kuvaa Neljansuora-pelin graafista käyttöliittymää.
 * Kayttoliittymassa käynnistetään pelin graafinen käyttöliittymä ja luodaan
 * käyttöliittymän komponentit ja asetetaan ne käyttöliittymään.
 *
 *
 * @author Eveliina Pakarinen
 */
public class Kayttoliittyma implements Runnable {

    /**
     * JFrame-luokan ilmentymä frame
     */
    private JFrame frame;
    /**
     * Kuvaa Neljansuora-luokkaa ja samannimistä peliä.
     *
     * @see Neljansuora
     */
    private Neljansuora neljansuora;
    /**
     * Piirtoalusta, joka piirtää käyttöliittymän peliruudukon.
     *
     * @see Piirtoalusta
     */
    private Piirtoalusta piirtoalusta;

    /**
     * Konstruktorissa asetetaan attribuutteihin konstruktorin parametrien
     * arvot.
     *
     * @param neljansuora Kuvaa Neljansuora-peliä
     */
    public Kayttoliittyma(Scanner lukija) {
        int leveys = this.asetaLeveys("leveys");
        int korkeus = this.asetaKorkeus("korkeus");
        int suoranPituus = this.asetaSuoranPituus(leveys, korkeus, "suoran pituus");
        this.neljansuora = new Neljansuora(leveys, korkeus, suoranPituus, lukija);

    }

    public int asetaPituus(String pituudenNimi) {

        String pituus = pituudenNimi;

        int palautettavaPituus = 0;

        pituus = JOptionPane.showInputDialog(null, "Tyjä asettaa oletusarvon. "
                + "Anna " + pituudenNimi + ": ", pituudenNimi, 1);

        if (pituus == null) {
            System.exit(0);
        }

        //Asettaa oletuspituuden ja leveyden

        if (pituus.isEmpty()) {

            if (pituudenNimi.equals("leveys")) {
                return 7;
            }

            return 6;
        }

        try {
            palautettavaPituus = Integer.parseInt(pituus);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Syötteen pitää olla joku luku!",
                    "Luvun antaminen epäonnistui", 0);
            this.asetaPituus(pituudenNimi);
        }

        return palautettavaPituus;

    }

    public int asetaLeveys(String pituudenNimi) {
        return this.asetaPituus(pituudenNimi);
    }

    public int asetaKorkeus(String pituudenNimi) {
        return this.asetaPituus(pituudenNimi);
    }

    public int asetaSuoranPituus(int leveys, int korkeus, String pituudenNimi) {
        String pituus = pituudenNimi;

        int palautettavaPituus = 0;
        
        int pisin = this.selvitaPisin(leveys, korkeus);

        pituus = JOptionPane.showInputDialog(null, "Tyhjä asettaa oletusarvon. "
                + "Anna " + pituudenNimi + " väliltä 0-" + pisin + ": ", pituudenNimi, 1);

        if (pituus == null) {
            System.exit(0);
        }

        if (pituus.isEmpty()) {
            return 4;
        }

        try {
            palautettavaPituus = Integer.parseInt(pituus);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Syötteen pitää olla joku luku!",
                    "Luvun antaminen epäonnistui", 0);
            this.asetaSuoranPituus(leveys, korkeus, pituudenNimi);
        }

        try {
            if (palautettavaPituus > pisin) {
                throw new IllegalArgumentException();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Syötteen luku oli liian suuri!",
                    "Luvun antaminen epäonnistui", 0);
            this.asetaSuoranPituus(leveys, korkeus, pituudenNimi);
        }

        return palautettavaPituus;

    }
    
    public int selvitaPisin(int leveys, int korkeus){
        if(leveys > korkeus){
            return leveys;
        } return korkeus;
    }

    @Override
    public void run() {
        frame = new JFrame("Neljän Suora");

        int leveys = (this.neljansuora.getPelilauta().getLauta().get(0).length) * 50;
        int korkeus = (this.neljansuora.getPelilauta().getLauta().size() + 2) * 48;
        frame.setPreferredSize(new Dimension(leveys, korkeus));

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setResizable(false);
        luoKomponentit(frame.getContentPane());
        this.setNeljansuoranPiirtoalusta();

        frame.pack();
        frame.setVisible(true);

    }

    /**
     * Metodissa luodaan käyttöliittymän eri komponentit ja asetetaan
     * käyttöliittymän layout. Layouttiin asetetaan käyttöliittymän eri
     * komponenttien osat oikeille paikoilleen. Metodi luo myös uuden
     * Piirtoalustan, joka piirtää käyttöliittymään peliruudukon.
     *
     * @param container Container-luokan ilmentymä
     */
    private void luoKomponentit(Container container) {

        container.setLayout(new BorderLayout());

        this.piirtoalusta = new Piirtoalusta(this.neljansuora);

        TilannetietoPanel tilannetietoPanel = new TilannetietoPanel(this.neljansuora);

        container.add(tilannetietoPanel, BorderLayout.SOUTH);

        container.add(new PiirtoPanel(this.neljansuora, this.piirtoalusta, tilannetietoPanel),
                BorderLayout.CENTER);

        container.add(new ValikkoPanel(1, 3, this.neljansuora, tilannetietoPanel, this, this.piirtoalusta), BorderLayout.NORTH);

    }

    /**
     * Metodi käynnistää uuden Neljansuora-pelin ja pelin loputtua sulkee pelin.
     */
    public void kaynnistaPeli() {

        Pelilauta lauta = this.neljansuora.getPelilauta();

        while (true) {

            if (lauta.onkoLautaTaynna() || lauta.onkoNeljanSuoraa()) {
                break;
            }
        }

    }

    public void setNeljansuoranPiirtoalusta() {
        while (getPaivitettava() == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                System.out.println("Piirtoalustaa ei ole vielä luotu.");
            }
        }

        neljansuora.setPaivitettava(this.getPaivitettava());
    }

    public Paivitettava getPaivitettava() {
        return this.piirtoalusta;
    }

    public JFrame getFrame() {
        return this.frame;
    }
}
