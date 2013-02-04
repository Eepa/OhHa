package neljansuora.peli;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import neljansuora.domain.Nappula;
import neljansuora.domain.Pelaaja;
import neljansuora.kayttoliittyma.Paivitettava;

/**
 * Luokka Pelilauta kuvaa Neljansuora-pelin pelilautaa. Pelilaudalla on tietty
 * koko ja kaksi pelaajaa. Pelilauta tietää myös luokkia, jotka auttavat
 * muokkaamaan pelilaudan tilaa.
 *
 * @author Eveliina Pakarinen
 */
public class Pelilauta {

    /**
     * Leveys kertoo Pelilaudan leveyden.
     */
    private int leveys;
    /**
     * Korkeus kertoo Pelilaudan korkeuden.
     */
    private int korkeus;
    /**
     * MerkkijononPituus kertoo lyhyimmän hyväksytyn voittojonon pituuden.
     */
    private int merkkijononPituus;
    /**
     * Lukija on Scanner-luokan ilmentymä, joka lukee käyttäjän syötteitä.
     */
    private Scanner lukija;
    /**
     * Map lauta sisältää pelilaudan. Y-akselia kuvaavat HashMapin avaimet ja
     * x-akselia String[] merkkijonotaulukon indeksien arvot.
     */
    private Map<Integer, String[]> lauta;
    /**
     * List pelaajat sisältää viitteet Neljansuoran Pelaaja-olioihin.
     *
     * @see Pelaaja
     */
    private List<Pelaaja> pelaajat;
    /**
     * LautaKasittelija viittaa LautaKasittelija-luokkaan, jonka avulla
     * Pelilaudan tilaa muokataan.
     *
     * @see LautaKasittelija
     */
    private LautaKasittelija lautaKasittelija;
    /**
     * NappulaKasittelija viitta NappulaKasittelija-luokkaan, jonka avulla
     * Pelilaudan nappuloiden tilaa muokataan.
     *
     * @see NappulaKasittelija
     */
    private NappulaKasittelija nappulaKasittelija;
    /**
     * Paivitettavan avulla Pelilauta päivittää graafista käyttöliittymää.
     */
    private Paivitettava paivitettava;

    /**
     * Konstruktorissa asetetaan Pelilaudan attribuuttien arvot ja alustetaan
     * lautaa kuvaava HashMap ja pelaajat sisältävä ArrayList. Lisäksi
     * konstruktorissa luodaan uudet LautaKasittelija- ja
     * NappulaKasittelija-luokat. Lisäksi konstruktorissa kutsutaan luokan omaa
     * metodia, jossa taytetaan HashMap lauta.
     *
     * @param leveys Pelilaudan leveys
     * @param korkeus Pelilaudan korkeus
     * @param merkkijononPituus Lyhimmän hyväksytyn voittojonon pituus
     * @param lukija Scanner-luokan ilmentymä, joka lukee käyttäjän syötteitä
     */
    public Pelilauta(int leveys, int korkeus, int merkkijononPituus, Scanner lukija) {
        this.leveys = leveys;
        this.korkeus = korkeus;
        this.merkkijononPituus = merkkijononPituus;
        this.lukija = lukija;

        this.lauta = new HashMap<Integer, String[]>();
        this.pelaajat = new ArrayList<Pelaaja>();
        this.lautaKasittelija = new LautaKasittelija(this.lauta, this.pelaajat, this.lukija);
        this.nappulaKasittelija = new NappulaKasittelija(this.lauta, this.pelaajat, this.merkkijononPituus);

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

    public NappulaKasittelija getNappulaKasittelija() {
        return this.nappulaKasittelija;
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

    public void setPaivitettava(Paivitettava paivitettava) {
        this.paivitettava = paivitettava;
    }

    public Paivitettava getPaivitettava() {
        return this.paivitettava;
    }

    public void teeTekstikayttoliittymanSiirrotKaikillePelaajille() {

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
            this.paivitettava.paivita();
        }

    }

    public void teeYhdenPelaajanSiirto(int rivinNumero, int pelaajanVuoronumero) {

        for (Pelaaja p : this.pelaajat) {

            if (p.getVuoronumero() != pelaajanVuoronumero) {
                continue;
            }

            if (this.onkoNeljanSuoraa() || this.onkoLautaTaynna()) {
                return;
            }

            this.nappulaKasittelija.teeSiirto(rivinNumero, p);
        }

        this.lautaKasittelija.lisaaNappulatKenttaan(this.pelaajat);
        this.tulostaPelilauta();
        this.paivitettava.paivita();

    }

    public boolean onkoNeljanSuoraa() {
        return this.nappulaKasittelija.onkoRiittavanPitkiaSuoria();
    }

    public boolean onkoLautaTaynna() {
        return this.nappulaKasittelija.onkoLautaTaynna();
    }
}
