package neljansuora.peli;

import java.util.List;
import java.util.Map;
import java.util.Scanner;
import neljansuora.domain.Nappula;
import neljansuora.domain.Pelaaja;

public class PeliSuorittaja {

    private Map<Integer, String[]> lauta;
    private Scanner lukija;

    public PeliSuorittaja(Map<Integer, String[]> lauta, Scanner lukija) {
        this.lauta = lauta;
        this.lukija = lukija;
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

        String[] taulukko = this.lauta.get(y);

        taulukko[x] = syote;

    }

    public int luePelaajanSiirto(String nimi) {
        
        System.out.println(nimi + " syötä numero 1:n ja " + this.lauta.get(0).length 
                + ":n väliltä");

        while (true) {
            try {
                
                int numero = Integer.parseInt(lukija.nextLine());
                return numero-1;
                
            } catch (Exception e) {
                
                System.out.println("Ei kunnollinen numero!");
                
            }
        }

    }
}
