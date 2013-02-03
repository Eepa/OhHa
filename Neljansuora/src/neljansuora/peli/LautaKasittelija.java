package neljansuora.peli;

import java.util.List;
import java.util.Map;
import java.util.Scanner;
import neljansuora.domain.Nappula;
import neljansuora.domain.Pelaaja;

/**
 * LautaKasittelija-luokka kuvaa LautaKasittelijaa, joka käsittelee ja muokkaa Pelilaudan tilaa.
 * Kasittelijan avulla lisataan laudalle nappuloita, tulostetaan tekstikäyttöliittymän tuloste ja
 * luetaan Pelaajan siirtoja.
 * 
 * @author Eveliina Pakarinen
 */

public class LautaKasittelija {
    
    /**
     * Attribuutti lauta kuvaa Map-muotoista pelilautaa, jonka avaimet kuvaavat laudan y-akselin
     * rivejä ja String[]-taulukon indeksit x-akselin paikkoja.
     */

    private Map<Integer, String[]> lauta;
    
    /**
     * Lukija on Scanner-luokan ilmentymä, joka lukee käyttäjän syötteitä.
     */
    private Scanner lukija;
    
    /**
     * Lista pelaajat sisältää pelin pelaajat.
     */
    
    private List<Pelaaja> pelaajat;
    
    /**
     * Konstruktori asettaa LautaKasittelijan attribuuttien arvot parametreina annetuiksi arvoiksi.
     * @param lauta Kuvaa Neljansuora-pelin pelilautaa HashMapin avulla.
     * @param pelaajat Kuvaa Neljansuora-pelin pelaajia ArrayListin avulla.
     * @param lukija Scanner-luokan ilmentymä, joka lukee käyttäjän syötteitä.
     */

    public LautaKasittelija(Map<Integer, String[]> lauta, List<Pelaaja> pelaajat, Scanner lukija) {
        this.lauta = lauta;
        this.lukija = lukija;
        this.pelaajat = pelaajat;
    }

    public void tulostaPelilauta() {
        for (String[] rivi : this.lauta.values()) {

            for (int j = 0; j < rivi.length; j++) {
                System.out.print(rivi[j]);
            }
            System.out.println("");
        }
    }

    public void kirjoitaLaudalle(String syote) {

        for (String[] taulukko : this.lauta.values()) {

            for (int j = 0; j < taulukko.length; j++) {
                taulukko[j] = syote;
            }
        }
    }

    public void lisaaNappulatKenttaan(List<Pelaaja> pelaajat) {

        for (Pelaaja p : pelaajat) {

            if (p.getVuoronumero() == 1) {
                this.lisaaPelaajanNappulat("X", p.getNappulat());

            } else {
                this.lisaaPelaajanNappulat("O", p.getNappulat());
            }
        }

    }

    public void lisaaPelaajanNappulat(String merkki, List<Nappula> nappulat) {

        for (Nappula nappula : nappulat) {

            this.kirjoitaLaudalleNappula(merkki, nappula.palautaX(), nappula.palautaY());
        }

    }

    public void kirjoitaLaudalleNappula(String syote, int x, int y) {

        this.lauta.get(y)[x] = syote;

    }


    public int luePelaajanSiirto(String nimi) {

        System.out.println(nimi + " syötä numero 0:n ja " + (this.lauta.get(0).length - 1)
                + ":n väliltä");

        while (true) {
            try {

                int numero = Integer.parseInt(lukija.nextLine());

                if (numero < 0) {
                    throw new IllegalArgumentException();
                } else if (numero > this.lauta.get(0).length - 1) {
                    throw new IllegalArgumentException();
                }

                return numero;

            } catch (Exception e) {

                System.out.println("Ei kunnollinen numero!");

            }
        }
    }
}
