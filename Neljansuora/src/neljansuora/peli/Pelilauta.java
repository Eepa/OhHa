package neljansuora.peli;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import javax.swing.JOptionPane;
import neljansuora.domain.Nappula;
import neljansuora.domain.Pelaaja;
import neljansuora.kayttoliittyma.Paivitettava;

/**
 * Luokka Pelilauta kuvaa Neljansuora-pelin pelilautaa. Pelilaudalla on tietty
 * koko ja kaksi pelaajaa. Pelilauta tietää ja käyttää myös luokkia, jotka
 * auttavat muokkaamaan pelilaudan tilaa.
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
     *
     * @see Paivitettava
     */
    private Paivitettava paivitettava;

    /**
     * Konstruktorissa asetetaan Pelilaudan attribuuttien arvot ja alustetaan
     * lautaa kuvaava HashMap ja pelaajat sisältävä ArrayList. Lisäksi
     * konstruktorissa luodaan uudet LautaKasittelija- ja
     * NappulaKasittelija-luokat. Lisäksi konstruktorissa kutsutaan luokan omaa
     * metodia, jossa täytetään HashMap-lauta.
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

    /**
     * Metodi kutsuu LautaKasittelijan tulostaPelilauta() -metodia, jonka avulla
     * tekstikäyttöliittymä tulostaa Pelilaudan.
     *
     * @see LautaKasittelija
     */
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

    /**
     * Metodi luo tekstikäyttöliittymään pelaajat ja lisää ne Pelilaudan
     * pelaajat-listalle. Pelaajalle pitää itse antaa nimi. Jos nimeä ei anna,
     * metodi asettaa pelaajan nimeksi oletusarvoisen nimen
     * "Pelaaja[pelaajanIndeksi]"
     *
     * @see Pelaaja
     */
    public void luoPelaajat() {

        for (int i = 1; i <= 2; i++) {
            System.out.println("Anna pelaajan nimi:");
            String nimi = lukija.nextLine();

            if (nimi.isEmpty()) {
                nimi = "Pelaaja" + i;
            }

            Pelaaja pelaaja = new Pelaaja(i, nimi);
            this.pelaajat.add(pelaaja);
        }

    }

    /**
     * Metodi luo Pelaajat graafiseen käyttöliittymään kutsumalla luokan omaa
     * metodia kysyPelaajanNimi(i), jotta Pelaajalle voidaan asettaa nimi.
     *
     * @param pelaajaMaara Kertoo, kuinka monta pelaajaa peliin luodaan.
     * @see Pelaaja
     */
    public void luoPelaajatGraafiseenKayttoliittymaan(int pelaajaMaara) {

        for (int i = 1; i <= pelaajaMaara; i++) {

            Pelaaja pelaaja = new Pelaaja(i, this.kysyPelaajanNimi(i));
            this.pelaajat.add(pelaaja);
        }

    }

    /**
     * Kysyy graafista käyttöliittymää varten pelaajan nimen.
     *
     * @param indeksi Kertoo pelaajan vuoronumeron.
     * @return Palauttaa pelaajalle annetun nimen.
     */
    public String kysyPelaajanNimi(int indeksi) {

        String nimi = JOptionPane.showInputDialog(null, "Anna pelaajan nimi. Tyhjä asettaa oletusarvon. ", "Pelaajan nimi", 1);

        if (nimi == null) {
            System.exit(0);
        }

        if (nimi.isEmpty()) {
            return "Pelaaja" + indeksi;
        }

        if (nimi.length() > 10) {
            JOptionPane.showMessageDialog(null, "Nimi ei saa olla 10:tä merkkiä pidempi",
                    "Nimen antaminen epäonnistui", 0);
            return this.kysyPelaajanNimi(indeksi);
        }

        return nimi;
    }

    /**
     * Alustaa uuden pelilaudan String[] taulukot Neljansuora-peliä varten ja
     * täyttää taulukot tyhjillä arvoilla.
     */
    public void luoPelilauta() {

        for (int i = 0; i < this.korkeus; i++) {
            String[] uusiRivi = new String[this.leveys];
            this.lauta.put(i, uusiRivi);
        }

        this.taytaPelilauta();
    }

    /**
     * Täyttää laudan tyhjää paikkaa merkitsevillä merkeillä eli pisteillä ".".
     */
    public void taytaPelilauta() {
        this.lautaKasittelija.kirjoitaLaudalle(".");
    }

    public void setPaivitettava(Paivitettava paivitettava) {
        this.paivitettava = paivitettava;
    }

    public Paivitettava getPaivitettava() {
        return this.paivitettava;
    }

    /**
     * Tekee tekstikäyttöliittymää varten siirrot kaikille pelaajille. Metodi
     * kutsuu NappulaKasittelija-luokan metodeita, joiden avulla se tarkistaa
     * siirtojen oikeellisuuden. Lisäksi metodissa kutsutaan
     * LautaKasittelija-luokan metodeita, joiden avulla pelaajan siirto luetaan
     * ja lisätään nappulat kenttään.
     *
     * @see LautaKasittelija
     * @see NappulaKasittelija
     */
    public void teeTekstikayttoliittymanSiirrotKaikillePelaajille() {

        // muokkaa metodia paremmaksi

        for (Pelaaja p : this.pelaajat) {

            if (this.onkoNeljanSuoraa() || this.onkoLautaTaynna()) {
                return;
            }

            int pystyrivinNumero = this.lautaKasittelija.luePelaajanSiirto(p.getNimi());
            
            while (true) {

                if (this.nappulaKasittelija.onkoMahdollinenSiirto(pystyrivinNumero)) {
                    this.nappulaKasittelija.teeSiirto(pystyrivinNumero, p);
                    break;
                } else {
                    System.out.println("Ei mahdollinen siirto!");
                    pystyrivinNumero = this.lautaKasittelija.luePelaajanSiirto(p.getNimi());
                }
            }

            this.lautaKasittelija.lisaaNappulatKenttaan(this.pelaajat);
            this.tulostaPelilauta();

        }

    }

    /**
     * Tekee yhdelle pelaajalle siirron graafista käyttöliittymää varten. Siirto
     * tarkoittaa sitä, että pelaaja pudottaa yhden nappulan kentälle.
     *
     * @param pystyrivinNumero Kertoo rivin, jolle pelaajan nappula pudotetaan.
     * @param pelaajanVuoronumero Kertoo pelaajan vuoronumeron.
     */
    public void teeYhdenPelaajanSiirtoGraafistaKayttoliittymaaVarten(int pystyrivinNumero,
            int pelaajanVuoronumero) {

        for (Pelaaja p : this.pelaajat) {

            if (p.getVuoronumero() == pelaajanVuoronumero) {
                if (this.onkoNeljanSuoraa() || this.onkoLautaTaynna()) {
                    return;
                }

                this.nappulaKasittelija.teeSiirto(pystyrivinNumero, p);
            }
        }
        this.lautaKasittelija.lisaaNappulatKenttaan(this.pelaajat);
        this.paivitettava.paivita();

    }

    /**
     * Palauttaa totuusarvon siitä, onko laudalla riittävän pitkää suoraa.
     *
     * @return true - on suora, false - ei ole suoraa
     * @see NappulaKasittelija
     */
    public boolean onkoNeljanSuoraa() {
        return this.nappulaKasittelija.onkoRiittavanPitkiaSuoria();
    }

    /**
     * Palauttaa totuusarvon siitä, onko lauta täynnä nappuloita.
     *
     * @return true - täynnä, false - ei vielä täynnä
     * @see NappulaKasittelija
     */
    public boolean onkoLautaTaynna() {
        return this.nappulaKasittelija.onkoLautaTaynna();
    }

    /**
     * Metodi tyhjentää pelaajien nappulalistat ja täyttää pelilaudan tyhjillä
     * merkeillä. Lisäksi päivittää graafisen käyttöliittymän ruudun.
     *
     * @see Pelaaja
     */
    public void lopetaPeli() {
        for (Pelaaja p : this.pelaajat) {
            p.poistaNappulat();
        }

        this.taytaPelilauta();
        this.paivitettava.paivita();
    }
}
