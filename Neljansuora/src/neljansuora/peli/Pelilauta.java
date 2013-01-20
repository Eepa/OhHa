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
    private LautaKasittelija lautaKasittelija;
    private NappulaKasittelija nappulaKasittelija;

    public Pelilauta(int leveys, int korkeus, Scanner lukija) {
        this.leveys = leveys;
        this.korkeus = korkeus;
        this.lukija = lukija;

        this.lauta = new HashMap<Integer, String[]>();
        this.pelaajat = new ArrayList<Pelaaja>();
        this.lautaKasittelija = new LautaKasittelija(this.lauta, this.pelaajat, this.lukija);
        this.nappulaKasittelija = new NappulaKasittelija(this.lauta, this.pelaajat);

        this.luoPelilauta();

    }

    public void tulostaPelilauta() {

        this.lautaKasittelija.tulostaPelilauta();

    }

    public Map<Integer, String[]> getLauta() {
        return this.lauta;
    }

    public List<Pelaaja> getPelaajat() {
        return this.pelaajat;
    }

    public void luoPelaajat() {

        for (int i = 1; i <= 2; i++) {
//            System.out.println("Anna pelaajan nimi:");
//            String nimi = lukija.nextLine();

            Pelaaja pelaaja = new Pelaaja(i);
            this.pelaajat.add(pelaaja);
        }

    }

    public void luoPelilauta() {

        for (int i = 0; i < this.korkeus; i++) {
            String[] uusiRivi = new String[this.leveys];
            this.lauta.put(i, uusiRivi);
        }

        this.taytaPelilauta();
    }

    public void taytaPelilauta() {

        this.lautaKasittelija.kirjoitaLaudalle(".");


    }

    public void teeSiirrot() {
        
        // muokkaa metodia paremmaksi

        for (Pelaaja p : this.pelaajat) {

            if (this.onkoNeljanSuoraa() || this.onkoLautaTaynna()) {
                return;
            }

            String nimi = "Pelaaja" + p.getVuoronumero();
            boolean jatketaanko = true;

            int vaakarivinNumero = this.lautaKasittelija.luePelaajanSiirto(nimi);
            while (jatketaanko) {

                if (this.nappulaKasittelija.onkoMahdollinenSiirto(vaakarivinNumero)) {
                    this.nappulaKasittelija.teeSiirto(vaakarivinNumero, p);
                    jatketaanko = false;
                } else {
                    System.out.println("Ei mahdollinen siirto!");
                    vaakarivinNumero = this.lautaKasittelija.luePelaajanSiirto(nimi);
                    jatketaanko = true;
                }
            }

            this.lautaKasittelija.lisaaNappulatKenttaan(this.pelaajat);
            this.tulostaPelilauta();
        }

    }

    public boolean onkoNeljanSuoraa() {

        return this.nappulaKasittelija.onkoRiittavanPitkiaSuoria();
    }
    
     public boolean onkoLautaTaynna() {

        return this.nappulaKasittelija.onkoLautaTaynna();
    }
}
