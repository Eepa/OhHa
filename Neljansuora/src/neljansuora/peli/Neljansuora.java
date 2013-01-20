package neljansuora.peli;

import java.util.Map;
import java.util.Scanner;

public class Neljansuora {

    private Pelilauta pelilauta;
    private Scanner lukija;

    public Neljansuora(int leveys, int korkeus, Scanner lukija) {

        this.lukija = lukija;

        this.luoPelilauta(leveys, korkeus);

        this.lisaaPelaajat();

    }

    public void luoPelilauta(int leveys, int korkeus) {
        this.pelilauta = new Pelilauta(leveys, korkeus, this.lukija);
    }
    
    public Pelilauta getPelilauta(){
        return this.pelilauta;
    }

    public void lisaaPelaajat() {
        this.pelilauta.luoPelaajat();
    }

    public void kaynnista() {

        int i = 0;
        
        this.pelilauta.tulostaPelilauta();
        
        while (true) {
            
            if (this.pelilauta.onkoNeljanSuoraa() || this.pelilauta.onkoLautaTaynna()) {
                //lisaa tarkistus kumpi pelaaja sai suoran sout esim pelaaja1 voitti
                System.out.println("Lopetuksen kautta tarkistus");
                break;
            }
            
            System.out.println("-----------------");

            this.pelaaKierros();


            i++;
        }

        System.out.println("-----------------------");
        this.pelilauta.tulostaPelilauta();

        System.out.println("Loppu");
    }

    public void pelaaKierros() {
        
        this.pelilauta.teeSiirrot();


    }
}
