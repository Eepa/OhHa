package neljansuora.peli;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import neljansuora.domain.Nappula;
import neljansuora.domain.Pelaaja;

public class Pelilauta {

    private int leveys;
    private int korkeus;
    private Scanner lukija;
    private Map<Integer, String[]> lauta;
    private List<Pelaaja> pelaajat;

    public Pelilauta(int leveys, int korkeus, Scanner lukija) {
        this.leveys = leveys;
        this.korkeus = korkeus;
        this.lukija = lukija;

        this.lauta = new HashMap<Integer, String[]>();
        this.pelaajat = new ArrayList<Pelaaja>();

        this.luoPelilauta();

    }

    public void tulostaPelilauta() {
        for (String[] rivi : this.lauta.values()) {

            for (int j = 0; j < rivi.length; j++) {
                System.out.print(rivi[j]);
            }
            System.out.println("");
        }
    }

    public void luoPelaajat() {

        for (int i = 1; i <= 2; i++) {
//            System.out.println("Anna pelaajan nimi:");
//            String nimi = lukija.nextLine();

            Pelaaja pelaaja = new Pelaaja(i);
            this.pelaajat.add(pelaaja);
        }

    }

    public List<Pelaaja> getPelaajat() {
        return this.pelaajat;
    }

    public void luoPelilauta() {

        for (int i = 0; i < this.korkeus; i++) {
            String[] uusiRivi = new String[this.leveys];
            this.lauta.put(i, uusiRivi);
        }

        this.taytaPelilauta();
        this.lisaaNappulatKenttaan();
    }

    public void taytaPelilauta() {

        this.kirjoitaLaudalle(".");


    }

    public void lisaaNappulatKenttaan() {

        for (Pelaaja p : this.pelaajat) {

            if (p.getVuoronumero() == 1) {
                this.lisaaPelaajanNappulat("X", p.getNappulat());

            } else {
                this.lisaaPelaajanNappulat("O", p.getNappulat());
            }
        }

    }

    public void kirjoitaLaudalle(String syote) {
        for (String[] taulukko : this.lauta.values()) {

            for (int j = 0; j < taulukko.length; j++) {
                taulukko[j] = syote;
            }
        }
    }

    public void lisaaPelaajanNappulat(String merkki, List<Nappula> nappulat) {

        for (Nappula nappula : nappulat) {
            this.kirjoitaLaudalle(merkki, nappula.palautaX(), nappula.palautaY());
        }

    }

    public void kirjoitaLaudalle(String syote, int x, int y) {

        for (int i = 0; i < this.lauta.size(); i++) {
            if (i == y) {
                String[] taulukko = this.lauta.get(i);

                for (int j = 0; j < taulukko.length; j++) {

                    if (j == x) {
                        taulukko[j] = syote;
                    }
                }

            }
        }
    }
}
