package neljansuora.peli;

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

    public Pelilauta getPelilauta() {
        return this.pelilauta;
    }

    public void tulostaPelilauta() {
        this.pelilauta.tulostaTilanne();
    }

    public void lisaaPelaajat() {
        this.pelilauta.luoPelaajat();

    }

    public void kaynnista() {

        int i = 0;

        while (i < 3) {

            this.pelilauta.tulostaTilanne();

            this.pelaaKierros();

            i++;
        }

        System.out.println("Loppu");
    }

    public void pelaaKierros() {
    }
}
