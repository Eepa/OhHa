package neljansuora.peli;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;
import java.util.Scanner;
import java.util.Timer;
import neljansuora.kayttoliittyma.Paivitettava;
import neljansuora.kayttoliittyma.Piirtoalusta;

public class Neljansuora {

    private Pelilauta pelilauta;
    private Scanner lukija;
    private Paivitettava paivitettava;

    public Neljansuora(int leveys, int korkeus, int merkkijononPituus, Scanner lukija) {
        super();
               
        this.lukija = lukija;

        this.luoPelilauta(leveys, korkeus, merkkijononPituus);
        
        this.lisaaPelaajat();

    }

    public void luoPelilauta(int leveys, int korkeus, int merkkijononPituus) {
        this.pelilauta = new Pelilauta(leveys, korkeus, merkkijononPituus, this.lukija);
        
    }
    
    public Pelilauta getPelilauta(){
        return this.pelilauta;
    }

    public void lisaaPelaajat() {
        this.pelilauta.luoPelaajat();
    }
    
    public void setPaivitettava(Paivitettava paivitettava){
        this.paivitettava = paivitettava;
        this.pelilauta.setPaivitettava(paivitettava);
    }

    public void kaynnista() {

        this.pelilauta.tulostaPelilauta();
        this.paivitettava.paivita();
        
        while (true) {
            
            if (this.pelilauta.onkoNeljanSuoraa() || this.pelilauta.onkoLautaTaynna()) {
                //lisaa tarkistus kumpi pelaaja sai suoran sout esim pelaaja1 voitti
                System.out.println("Lopetuksen kautta tarkistus");
                break;
            }
            
            System.out.println("-----------------");

            this.pelaaKierros();
            this.paivitettava.paivita();
        }

        System.out.println("-----------------------");
        this.pelilauta.tulostaPelilauta();

        System.out.println("Loppu");
        System.exit(0);
    }

    public void pelaaKierros() {
        this.pelilauta.teeSiirrot();
        this.paivitettava.paivita();
    }

    

    public void taulukonLapiKaynti(){
        int[][] taulu = new int[5][5];
        
        for (int i = 0; i < taulu.length; i++) {
            for (int j = 0; j < taulu[i].length; j++) {
                System.out.println("koordinaatit on " + i + ", " + j);
                taulu[i][j] = 5;
                
            }
            
        }
    }
}
