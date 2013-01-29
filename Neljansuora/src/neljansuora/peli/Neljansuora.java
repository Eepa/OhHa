package neljansuora.peli;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;
import java.util.Scanner;
import java.util.Timer;
import neljansuora.kayttoliittyma.Paivitettava;
import neljansuora.kayttoliittyma.Piirtoalusta;

public class Neljansuora extends Timer implements ActionListener{

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
    }

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
        this.pelilauta.teeSiirrot();
        
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        this.pelilauta.teeSiirrot();
        
        this.paivitettava.paivita();
    }

    
}
