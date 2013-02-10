package neljansuora.peli;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;
import java.util.Scanner;
import java.util.Timer;
import neljansuora.kayttoliittyma.Paivitettava;
import neljansuora.kayttoliittyma.Piirtoalusta;

/**
 * Luokka Neljansuora kuvaa Neljansuora-peliä. Luokka luo pelille pelilaudan ja
 * käyttää pelilaudan toimintoja. Luokka käyttää myös Neljansuoran
 * tekstikäyttöliittymää.
 *
 * @author Eveliina Pakarinen
 */
public class Neljansuora {

    /**
     * Pelilauta kuvaa Neljansuora-pelin pelilautaa.
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
     */
    private Paivitettava paivitettava;

    /**
     * Konstruktorissa luodaan uusi pelilauta ja ja pelaajat
     * Neljansuora-pelille. Lisäksi luokan lukija-attribuuttiin asetetaan arvo.
     *
     * @param leveys Asetetaan pelilaudan leveydeksi.
     * @param korkeus Asetetaan pelilaudan korkeudeksi.
     * @param merkkijononPituus Kertoo, kuinka monta merkkiä lyhyimmässä
     * hyväksytyssä voittojonossa on.
     * @param lukija Lukija, joka lukee käyttäjän syötteet.
     */
    public Neljansuora(int leveys, int korkeus, int merkkijononPituus, Scanner lukija) {


        this.lukija = lukija;

        this.luoPelilauta(leveys, korkeus, merkkijononPituus);

        this.lisaaPelaajat();

    }

    public void luoPelilauta(int leveys, int korkeus, int merkkijononPituus) {
        this.pelilauta = new Pelilauta(leveys, korkeus, merkkijononPituus, this.lukija);

    }

    public Pelilauta getPelilauta() {
        return this.pelilauta;
    }

    public void lisaaPelaajat() {
        int pelaajaMaara = 0;
        
         // JATKA T'st'
        
        this.pelilauta.luoPelaajatGraafiseenKayttoliittymaan(0);
    }

    public void setPaivitettava(Paivitettava paivitettava) {
        this.paivitettava = paivitettava;
        this.pelilauta.setPaivitettava(paivitettava);
    }

    // TEKSTIKAYTTOLIITTYMAA VARTEN KAYNNISTAMETODI
    public void kaynnista() {

        this.pelilauta.tulostaPelilauta();

        while (true) {

            if (this.pelilauta.onkoNeljanSuoraa() || this.pelilauta.onkoLautaTaynna()) {
                //lisaa tarkistus kumpi pelaaja sai suoran sout esim pelaaja1 voitti
                System.out.println("Lopetuksen kautta tarkistus");
                break;
            }

            System.out.println("-----------------");

            this.pelaaKierros();

        }

        System.out.println("-----------------------");
        this.pelilauta.tulostaPelilauta();

        System.out.println("Loppu");
    }

    public void pelaaKierros() {
        this.pelilauta.teeTekstikayttoliittymanSiirrotKaikillePelaajille();
    }

    public void lopetaPeli() {
        this.pelilauta.lopetaPeli();

    }
// Opetetaan, kuinka voi käyttää kaksiulotteista taulukkoa. Pitäisikö neljansuoran logiikka muuttaa siihen.
//    public void taulukonLapiKaynti(){
//        int[][] taulu = new int[5][5];
//        
//        for (int i = 0; i < taulu.length; i++) {
//            for (int j = 0; j < taulu[i].length; j++) {
//                System.out.println("koordinaatit on " + i + ", " + j);
//                taulu[i][j] = 5;
//                
//            }
//            
//        }
//    }
}
