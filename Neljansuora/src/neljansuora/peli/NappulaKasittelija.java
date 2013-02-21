package neljansuora.peli;

import java.util.List;
import java.util.Map;
import neljansuora.domain.Pelaaja;

/**
 * NappulaKasittelija-luokka muokkaa Pelilaudan Pelaajien nappuloiden tilaa ja
 * tarkistaa, onko laudalle tullut riittävän pitkiä suoria. Kasittelija tekee
 * myös siirrot laudalle.
 *
 * @author Eveliina Pakarinen
 */
public class NappulaKasittelija {

    /**
     * Kuvaa Map-muotoista pelilautaa, jonka avaimet kuvaavat laudan y-akselin
     * rivejä ja String[]-taulukon indeksit x-akselin paikkoja.
     */
    private Map<Integer, String[]> lauta;
    /**
     * Lista pelaajat sisältää pelin pelaajat.
     */
    private List<Pelaaja> pelaajat;
    /**
     * Merkkijononpituus kertoo lyhimmän voittoon riittävän merkkijonon
     * pituuden.
     */
    private int merkkijononPituus;

    /**
     * Konstruktori asettaa LautaKasittelijan attribuuttien arvot parametreina
     * annetuiksi arvoiksi.
     *
     * @param lauta Kuvaa Neljansuora-pelin pelilautaa HashMapin avulla.
     * @param pelaajat Kuvaa Neljansuora-pelin pelaajia ArrayListin avulla.
     * @param merkkijononPituus Kertoo lyhimmän voittoon riittävän merkkijonon
     * pituuden.
     */
    public NappulaKasittelija(Map<Integer, String[]> lauta, List<Pelaaja> pelaajat, int merkkijononPituus) {
        this.lauta = lauta;
        this.pelaajat = pelaajat;
        this.merkkijononPituus = merkkijononPituus;
    }

    /**
     * Tarkistaa, mahtuuko annetulle vaakariville vielä nappuloita vai onko rivi
     * jo täynnä.
     *
     * @param vaakarivinNumero Pelilaudan x-akselin koordinaatti
     * @return Palauttaa true, jos on tilaa, muuten false.
     */
    public boolean onkoMahdollinenSiirto(int vaakarivinNumero) {

        if (this.lauta.get(0)[vaakarivinNumero].equals(".")) {
            return true;
        }

        return false;
    }

    /**
     * Lisää pelaajalle uuden siirron pelilaudalle.
     *
     * @param xKoordinaatti Pelilaudan x-akselin koordinaatti
     * @param p Pelaaja, jolle siirto lisätään
     */
    public void teeSiirto(int xKoordinaatti, Pelaaja p) {

        for (int vaakarivinNumero = this.lauta.size() - 1; vaakarivinNumero >= 0; vaakarivinNumero--) {
            String[] vaakarivi = this.lauta.get(vaakarivinNumero);

            if (vaakarivi[xKoordinaatti].equals(".")) {
                p.lisaaUusiNappula(xKoordinaatti, vaakarivinNumero);
                return;
            }
        }

    }

    /**
     * Tarkistaa pelilaudalta, onko sinne muodostunut jo riittävän pitkiä
     * suoria. Metodi kutsuu luokan muita tarkistusmetodeja tarkistuksen
     * suorittamiseksi.
     *
     * @return Palauttaa true, jos suora löytyy, muuten false.
     */
    public boolean onkoRiittavanPitkiaSuoria() {

        for (Pelaaja p : this.pelaajat) {

            if (p.getNappulat().size() < this.merkkijononPituus) {
                return false;
            }

            String merkki = this.annaPelaajanMerkki(p);

            if (this.onkoVaakasuorasti(merkki, this.merkkijononPituus)
                    || this.onkoPystysuorasti(merkki, this.merkkijononPituus)
                    || this.onkoVinostiKoillinenLounas(merkki, this.merkkijononPituus)
                    || this.onkoVinostiKaakkoLuode(merkki, this.merkkijononPituus)) {
                return true;
            }
        }

        return false;
    }

    /**
     * Palauttaa pelaajan vuoronumeron perusteella oikean pelaajaa vastaavan
     * merkin.
     *
     * @param p Viite tiettyyn Pelaajaan
     * @return Palauttaa pelaajaa vastaavan nappulamerkin
     * @see Pelaaja
     */
    public String annaPelaajanMerkki(Pelaaja p) {
        if (p.getVuoronumero() == 1) {
            return "X";
        }
        return "O";
    }

    /**
     * Tarkistaa, onko laudalla vaakasuorassa olevia riittävän pitkiä suoria.
     *
     * @param merkki Merkki, jonka suoria tarkistetaan.
     * @param pituus Suoran vähimmäispituus
     * @return Palauttaa true, jos suora löytyy, muuten false.
     */
    public boolean onkoVaakasuorasti(String merkki, int pituus) {

        for (int i = 0; i < this.lauta.size(); i++) {

            String[] rivi = this.lauta.get(i);

            String testattavaJono = this.luoTestattavaJono(rivi);

            if (this.testaaMerkkijononPituus(testattavaJono, merkki, pituus) == pituus) {
                return true;
            }
        }
        return false;
    }

    /**
     * Luo parametrina annetun String[]-taulukon merkeistä merkkijonon, josta
     * pystyy tarkistamaan, mitä merkkejä jono sisältää.
     *
     * @param rivi Kuvaa Pelilaudan yhtä riviä.
     * @return Palauttaa rivin merkeistä luodun merkkijonon.
     * @see Pelilauta
     */
    public String luoTestattavaJono(String[] rivi) {
        String testattavaJono = "";

        for (int j = 0; j < rivi.length; j++) {
            testattavaJono = testattavaJono + rivi[j];
        }

        return testattavaJono;
    }

    /**
     * Luo Pelilaudan pystyrivin merkeistä merkkijonon, joka palautetaan
     * merkkijonon sisältötarkistusta varten.
     *
     * @return Palauttaa pystyrivin merkeistä luodun merkkijonon.
     */
    public String luoPystyrivinTestattavaJono() {
        String testattavaJono = "";

        for (int j = 0; j < this.lauta.get(0).length; j++) {

            for (String[] rivi : this.lauta.values()) {
                testattavaJono += rivi[j];
            }
        }
        return testattavaJono;
    }

    /**
     * Testaa, onko laudalla pystysuoria suoria.
     *
     * @param merkki Merkki, jonka suoria tarkistetaan.
     * @param pituus Suoran vähimmäispituus
     * @return Palauttaa true, jos suora löytyy, muuten false.
     */
    public boolean onkoPystysuorasti(String merkki, int pituus) {

        String testattavaJono = this.luoPystyrivinTestattavaJono();

        if (this.testaaMerkkijononPituus(testattavaJono, merkki, pituus) == pituus) {
            return true;

        }
        return false;
    }

    /**
     * Testaa, onko Pelilaudalla vinossa olevia suoria, joiden suunta alkaa
     * vasemmasta alakulmasta ja päättyy oikealle yläkulmaan.
     *
     * @param merkki Merkki, jonka suoria tarkistetaan.
     * @param pituus Suoran vähimmäispituus
     * @return Palauttaa true, jos suora löytyy, muuten false.
     */
    public boolean onkoVinostiKoillinenLounas(String merkki, int pituus) {

        for (int i = 0; i < this.lauta.size(); i++) {
            String[] rivi = this.lauta.get(i);

            if (this.muodostaMerkkijonoVinolleSuoralle(rivi, merkki, pituus, i, "koillinenlounas")) {
                return true;
            }
        }
        return false;
    }

    /**
     * Testaa, onko Pelilaudalla vinossa olevia suoria, joiden suunta alkaa
     * oikeasta alakulmasta ja päättyy vasemmalle yläkulmaan.
     *
     * @param merkki Merkki, jonka suoria tarkistetaan.
     * @param pituus Suoran vähimmäispituus
     * @return Palauttaa true, jos suora löytyy, muuten false.
     */
    public boolean onkoVinostiKaakkoLuode(String merkki, int pituus) {

        for (int i = this.lauta.size() - 1; i >= 0; i--) {
            String[] rivi = this.lauta.get(i);

            if (this.muodostaMerkkijonoVinolleSuoralle(rivi, merkki, pituus, i, "kaakkoluode")) {
                return true;
            }
        }
        return false;
    }

    /**
     * Muodostaa merkkijonon vinolle suoralle rekursiivisesti lähtien annetun
     * String[]- taulukon ensimmäisestä paikasta.
     *
     * @param rivi String[]-taulukon rivi, jonka ensimmäisestä merkistä suoran
     * muodostaminen aloitetaan.
     * @param merkki Merkki, jonka suoria tarkistetaan.
     * @param pituus Suoran vähimmäispituus
     * @param i Y-akselin kooridnaatti, jolla rivi on.
     * @param suoranSuunta Kertoo, minkä suuntaista suoraa etsitään.
     * @return Palauttaa true, jos suora löytyy, muuten false.
     */
    public boolean muodostaMerkkijonoVinolleSuoralle(String[] rivi, String merkki,
            int pituus, int i, String suoranSuunta) {

        for (int j = 0; j < rivi.length; j++) {
            String merkkijono = "" + rivi[j];

            merkkijono = this.muodostaVinoMerkkijono(i, j, merkkijono, suoranSuunta);

            if (this.testaaMerkkijononPituus(merkkijono, merkki, pituus) == pituus) {
                return true;
            }
        }
        return false;
    }

    /**
     * Muodostaa vinon merkkijonon rekursiivisesti sen perusteella, mihin
     * suuntaan vinon suoran pitää kulkea.
     *
     * @param i Y-akselin koordinaatti
     * @param j X-akselin koordinaatti
     * @param merkkijono Jo muodostettu merkkijono
     * @param suoranSuunta Kertoo, minkä suuntaista suoraa etsitään.
     * @return Palauttaa muodostetun merkkijonon.
     */
    public String muodostaVinoMerkkijono(int i, int j, String merkkijono, String suoranSuunta) {
        if (suoranSuunta.equals("koillinenlounas")) {
            merkkijono = this.laskeSeuraavatPisteetKoillinenLounas(i, j, merkkijono, suoranSuunta);
        } else {
            merkkijono = this.laskeSeuraavatPisteetKaakkoLuode(i, j, merkkijono, suoranSuunta);
        }
        return merkkijono;
    }

    /**
     * Laskee rekursiivisesti seuraavan pisteen merkkijonoon, jonka metodi saa
     * parametrina. Kutsuu luokan omaa laskeSeuraavaPiste()-metodia seuraavan
     * pisteen laskemiseksi.
     *
     * @param y Y-akselin koordinaatti
     * @param x X-akselin koordinaatti
     * @param merkkijono Merkkijono, johon uudet merkit lisätään.
     * @param suoranSuunta Kertoo, minkä suuntaista suoraa etsitään.
     * @return Palauttaa muodostetun merkkijonon.
     */
    public String laskeSeuraavatPisteetKaakkoLuode(int y, int x, String merkkijono, String suoranSuunta) {
        return laskeSeuraavaPiste(y, x, -1, -1, merkkijono, suoranSuunta);
    }

    /**
     * Laskee rekursiivisesti seuraavan pisteen merkkijonoon, jonka metodi saa
     * parametrina. Kutsuu luokan omaa laskeSeuraavaPiste()-metodia seuraavan
     * pisteen laskemiseksi.
     *
     * @param y Y-akselin koordinaatti
     * @param x X-akselin koordinaatti
     * @param merkkijono Merkkijono, johon uudet merkit lisätään.
     * @param suoranSuunta Kertoo, minkä suuntaista suoraa etsitään.
     * @return Palauttaa muodostetun merkkijonon.
     */
    public String laskeSeuraavatPisteetKoillinenLounas(int y, int x, String merkkijono, String suoranSuunta) {
        return laskeSeuraavaPiste(y, x, 1, -1, merkkijono, suoranSuunta);
    }

    /**
     * Tarkistaa y-akselin pisteeseen liittyvän ehdon sen mukaan, minkä
     * suuntaista vinoa suoraa etsitään.
     *
     * @param y Y-akselin tarkasteltava piste
     * @param suoranSuunta Kertoo, minkä suuntaista suoraa etsitään.
     * @return Palauttaa true, jos ehto täyttyy, muuten false.
     */
    public boolean tarkistaYEhtoSeuraavaaPistettaLaskettaessa(int y, String suoranSuunta) {
        if (suoranSuunta.equals("koillinenlounas")) {
            return y > this.lauta.size() - 1;
        }
        return y < 0;
    }

    /**
     * Lisää rekursiivisesti annettuun merkkijonoon aina seuraavan pisteen ja
     * palauttaa valmiin merkkijonon.
     *
     * @param y Y-akselin koordinaatti
     * @param x X-akselin koordinaatti
     * @param yLisays Lisäys, joka lisätään y-akselin koordinaattiin.
     * @param xLisays Lisäys, joka lisätään x-akselin koordinaattiin.
     * @param merkkijono Merkkijono, johon merkkejä lisätään.
     * @param suoranSuunta Kertoo, minkä suuntaista suoraa etsitään.
     * @return Palauttaa valmiin merkkijonon.
     */
    public String laskeSeuraavaPiste(int y, int x, int yLisays, int xLisays, String merkkijono, String suoranSuunta) {

        y = y + yLisays;
        x = x + xLisays;

        if (x < 0 || this.tarkistaYEhtoSeuraavaaPistettaLaskettaessa(y, suoranSuunta)) {
            return merkkijono;
        } else {
            String seuraavaPiste = this.lauta.get(y)[x];

            merkkijono = merkkijono + seuraavaPiste;

            return this.laskeSeuraavaPiste(y, x, yLisays, xLisays, merkkijono, suoranSuunta);
        }
    }

    /**
     * Testaa, onko testattavassa merkkijonossa tarpeeksi pitkiä suoria.
     *
     * @param testattavaJono Jono, josta etsitään tietyn merkin tietynpituisia
     * suoria.
     * @param merkki Merkki, josta muodostettua uutta suoraa etsitään
     * testattavastaJonosta.
     * @param vahimmaispituus Lyhimmän etsityn suoran pituus.
     * @return Palauttaa testatun merkkijonon sisältämän vähimmäispituuden, jos
     * vähimmäispituuden mittainen suora löytyy merkkijonosta. Muuten palauttaa
     * 0.
     */
    public int testaaMerkkijononPituus(String testattavaJono, String merkki, int vahimmaispituus) {

        String tarkistusjono = teeJono(vahimmaispituus, merkki);
        if (testattavaJono.contains(tarkistusjono)) {
            return vahimmaispituus;
        }
        return 0;
    }

    /**
     * Tekee annetun pituisen merkkijonon annetuista merkeistä.
     *
     * @param pituus Haluttu merkkijonon pituus
     * @param merkki Merkki, josta merkkijono muodostetaan
     * @return Palauttaa metodin luoman merkkijonon.
     */
    public String teeJono(int pituus, String merkki) {
        String jono = "";
        for (int i = 0; i < pituus; i++) {
            jono += merkki;
        }
        return jono;
    }

    /**
     * Metodi tarkistaa jokaiselta vaakariviltä, sisältääkö kyseinen rivi tyhjiä
     * paikkoja. Jos sisältää, metodi palauttaa heti false. Jos ei sisällä,
     * tarkistus jatkuu.
     *
     * @return Palauttaa true, jos tarkistusluku on yhtäsuuri kuin laudan
     * korkeus, muuten false.
     */
    public boolean onkoLautaTaynna() {
        int tarkistusluku = 0;

        for (int i = 0; i < this.lauta.size(); i++) {

            String rivinMerkit = luoTestattavaJono(this.lauta.get(i));

            if (rivinMerkit.contains(".")) {
                return false;
            } else {
                tarkistusluku++;
            }
        }
        return tarkistusluku == this.lauta.size();
    }

    /**
     * Selvittää pelilaudan pisimmän sivun pituuden (pidempi pituus laudan x-
     * tai y-akselin pituuksista).
     *
     * @return Palauttaa pisimmän sivun pituuden.
     */
    public int selvitaLaudanPisimmanSivunPituus() {
        int korkeus = this.lauta.size();
        int leveys = this.lauta.get(0).length;

        if (korkeus > leveys) {
            return korkeus;
        }
        return leveys;
    }
}
