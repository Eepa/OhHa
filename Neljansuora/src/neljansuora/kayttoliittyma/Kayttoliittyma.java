package neljansuora.kayttoliittyma;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.util.Scanner;
import javax.swing.JFrame;
import javax.swing.WindowConstants;
import neljansuora.kayttoliittyma.grafiikka.Paivitettava;
import neljansuora.kayttoliittyma.grafiikka.PiirtoPanel;
import neljansuora.kayttoliittyma.grafiikka.Piirtoalusta;
import neljansuora.kayttoliittyma.logiikka.PituudenAsettelija;
import neljansuora.kayttoliittyma.logiikka.TilannetietoPanel;
import neljansuora.kayttoliittyma.logiikka.ValikkoPanel;
import neljansuora.peli.Neljansuora;
import neljansuora.peli.Pelilauta;



/**
 * Kayttoliittyma-luokka kuvaa Neljansuora-pelin graafista käyttöliittymää.
 * Käyttöliittymässä käynnistetään pelin graafinen käyttöliittymä ja luodaan
 * käyttöliittymän komponentit ja asetetaan ne käyttöliittymään.
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
     * Kuvaa PituudenAsettelija-luokkaa, joka asettelee uuden Neljansuora-pelin 
     * Pelilaudan koon ja voittosuoran vähimmäispituuden.
     * @see PituudenAsettelija
     * @see Neljansuora
     */
    private PituudenAsettelija pituudenasettelija;

    /**
     * Konstruktorissa luodaan uusi Neljansuora-peli, johon asetetaan PituudenAsettelijan 
     * avulla laudan koko ja voittosuoran pituus. Konstruktori asettaa myös parametrina saamansa 
     * lukijan arvon oliomuuttujaan.
     *
     * @param lukija Scanner-luokan ilmentymä, joka lukee pelaajan syötteitä.
     * @see PituudenAsettelija
     */
    public Kayttoliittyma(Scanner lukija) {
        this.pituudenasettelija = new PituudenAsettelija();
        
        int leveys = this.pituudenasettelija.setLeveys("leveys");
        int korkeus = this.pituudenasettelija.setKorkeus("korkeus");
        int suoranPituus = this.pituudenasettelija.asetaEtsittavanSuoranPituus(leveys, korkeus, "suoran pituus");
        
        this.neljansuora = new Neljansuora(leveys, korkeus, suoranPituus, lukija, "graafinen");
    }

   /**
    * Luo uuden ruudun Neljansuora-pelille. Ruudun koko määräytyy Neljansuora-pelin 
    * pelilaudan koon mukaan.
    */

    @Override
    public void run() {
        frame = new JFrame("Neljän Suora");

        int leveys = (this.neljansuora.getPelilauta().getLauta().get(0).length) * 51;
        int korkeus = (this.neljansuora.getPelilauta().getLauta().size() + 2) * 51;
        frame.setPreferredSize(new Dimension(leveys, korkeus));

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setResizable(false);
        luoKomponentit(frame.getContentPane());
        this.setNeljansuoranPiirtoalusta();

        frame.pack();
        frame.setVisible(true);

    }

    /**
     * Luodaan käyttöliittymän eri komponentit ja asetetaan
     * käyttöliittymän layout. Layouttiin asetetaan käyttöliittymän eri
     * komponenttien osat oikeille paikoilleen. Metodi luo myös uuden
     * Piirtoalustan, joka piirtää käyttöliittymään peliruudukon.
     *
     * @param container Container-luokan ilmentymä
     */
    private void luoKomponentit(Container container) {

        container.setLayout(new BorderLayout());

        this.piirtoalusta = new Piirtoalusta(this.neljansuora);

        TilannetietoPanel tilannetietoPanel = new TilannetietoPanel(this.neljansuora.getPelilauta());

        container.add(tilannetietoPanel, BorderLayout.SOUTH);

        container.add(new PiirtoPanel(this.neljansuora, this.piirtoalusta, tilannetietoPanel),
                BorderLayout.CENTER);

        container.add(new ValikkoPanel(1, 3, this.neljansuora, tilannetietoPanel, 
                this.piirtoalusta), BorderLayout.NORTH);

    }

    /**
     * Käynnistää uuden Neljansuora-pelin ja pelin loputtua sulkee pelin.
     */
    public void kaynnistaPeli() {

        Pelilauta lauta = this.neljansuora.getPelilauta();

        while (true) {

            if (lauta.onkoLautaTaynna() || lauta.onkoNeljanSuoraa()) {
                break;
            }
        }

    }
    
    /**
     * Asettaa Neljansuoralle uuden Paivitettava-rajapinnan toteuttavan luokan.
     * @see Neljansuora
     * @see Paivitettava
     */

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
