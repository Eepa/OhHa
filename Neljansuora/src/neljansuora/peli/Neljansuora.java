package neljansuora.peli;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;
import java.util.Scanner;
import java.util.Timer;
import neljansuora.kayttoliittyma.grafiikka.Paivitettava;
import neljansuora.kayttoliittyma.grafiikka.Piirtoalusta;
import javax.swing.JOptionPane;

/**
 * Luokka Neljansuora kuvaa Neljansuora-peliä. Luokka luo pelille pelilaudan ja
 * käyttää pelilaudan toimintoja. Luokka käyttää myös Neljansuora-pelin
 * tekstikäyttöliittymää.
 *
 * @author Eveliina Pakarinen
 */
public class Neljansuora {

    /**
     * Kuvaa Neljansuora-pelin pelilautaa.
     *
     * @see Pelilauta
     */
    private Pelilauta pelilauta;
    /**
     * Scanner kuvaa lukijaa, jonka avulla käyttäjän syötteitä luetaan.
     */
    private Scanner lukija;
    /**
     * Paivitettavan avulla Neljansuora päivittää graafista käyttöliittymää.
     *
     * @see Paivitettava
     */
    private Paivitettava paivitettava;

    /**
     * Konstruktorissa luodaan uusi pelilauta ja ja pelaajat Neljansuora-pelille
     * sen mukaan käytetäänkö graafista vai tekstikäyttöliittymää. Lisäksi
     * luokan lukija-attribuuttiin asetetaan arvo.
     *
     * @param leveys Asetetaan pelilaudan leveydeksi.
     * @param korkeus Asetetaan pelilaudan korkeudeksi.
     * @param merkkijononPituus Kertoo, kuinka monta merkkiä lyhyimmässä
     * hyväksytyssä voittojonossa on.
     * @param lukija Lukija, joka lukee käyttäjän syötteet.
     * @param kayttoliittymanTyyppi Kertoo käytettävän käyttöliittymän tyypin.
     */
    public Neljansuora(int leveys, int korkeus, int merkkijononPituus, Scanner lukija, String kayttoliittymanTyyppi) {

        this.lukija = lukija;

        this.luoPelilauta(leveys, korkeus, merkkijononPituus);

        if (kayttoliittymanTyyppi.equals("graafinen")) {
            this.lisaaPelaajatGraafiseenKayttoliittymaan();
        } else if (kayttoliittymanTyyppi.equals("teksti")) {
            this.lisaaPelaajat();
        }

    }

    /**
     * Luo Neljansuora-pelille uuden annettujen parametrien kokoisen
     * Pelilaudan.
     *
     * @param leveys Uuden Pelilaudan leveys.
     * @param korkeus Uuden Pelilaudan korkeus.
     * @param merkkijononPituus Kertoo lyhimmän hyväksytyn suoran pituuden.
     * @see Pelilauta
     */
    public void luoPelilauta(int leveys, int korkeus, int merkkijononPituus) {
        this.pelilauta = new Pelilauta(leveys, korkeus, merkkijononPituus, this.lukija);

    }

    public Pelilauta getPelilauta() {
        return this.pelilauta;
    }

    /**
     * Lisää Pelilaudalle uudet Pelaajat tekstikäyttöliittymään.
     *
     * @see Pelaaja
     * @see Pelilauta
     */
    public void lisaaPelaajat() {
        this.pelilauta.luoPelaajat();
    }

    /**
     * Lisää Pelilaudalle uudet Pelaajat graafiseen käyttöliittymään.
     *
     * @see Pelaaja
     * @see Pelilauta
     */
    public void lisaaPelaajatGraafiseenKayttoliittymaan() {
        int pelaajaMaara = 2;

        this.pelilauta.luoPelaajatGraafiseenKayttoliittymaan(pelaajaMaara);
    }

    public void setPaivitettava(Paivitettava paivitettava) {
        this.paivitettava = paivitettava;
        this.pelilauta.setPaivitettava(paivitettava);
    }

    /**
     * Tekstikäyttöliittymää varten oleva käynnistä-metodi. Metodi käynnistää
     * Neljansuora-pelin tekstikäyttöliittymässä. Suorituksen aikana metodi
     * kutsuu Pelilaudan tulosta-metodeja ja tarkistaa, onko laudalla riittävän
     * pitkiä suoria. Jos suoria ei vielä ole, jatkaa metodi kierrosten
     * pelaamista, muuten metodi katkaisee pelin.
     *
     * @see Pelilauta
     */
    public void kaynnista() {

        this.pelilauta.tulostaPelilauta();

        while (true) {

            if (this.pelilauta.onkoNeljanSuoraa() || this.pelilauta.onkoLautaTaynna()) {
                System.out.println("Lopetuksen kautta tarkistus");
                break;
            }

            System.out.println("-----------------");

            this.pelaaKierros();

        }

        System.out.println("-----------------------");
        this.pelilauta.tulostaPelilauta();

        System.out.println("Peli loppui!");
    }

    /**
     * Kierroksen pelaamismetodi tekstikäyttöliittymää varten.
     *
     * @see Pelilauta
     */
    public void pelaaKierros() {
        this.pelilauta.teeTekstikayttoliittymanSiirrotKaikillePelaajille();
    }

    /**
     * Pelinlopetusmetodi graafista käyttöliittymää varten.
     *
     * @see Pelilauta
     */
    public void lopetaPeli() {
        this.pelilauta.lopetaPeli();

    }

}
