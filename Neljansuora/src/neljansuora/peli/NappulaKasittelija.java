package neljansuora.peli;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import neljansuora.domain.Nappula;
import neljansuora.domain.Pelaaja;

/**
 * NappulaKasittelija-luokka muokkaa Pelilaudan Pelaajien nappuloiden tilaa ja
 * tarkistaa, onko laudalle tullut riittävän pitkiä suoria. Kasittelija tekee
 * myös laudalle siirrot.
 *
 * @author Eveliina Pakarinen
 */
public class NappulaKasittelija {

    /**
     * Attribuutti lauta kuvaa Map-muotoista pelilautaa, jonka avaimet kuvaavat
     * laudan y-akselin rivejä ja String[]-taulukon indeksit x-akselin paikkoja.
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

    public boolean onkoMahdollinenSiirto(int vaakarivinNumero) {

        if (this.lauta.get(0)[vaakarivinNumero].equals(".")) {
            return true;
        }

        return false;
    }

    public void teeSiirto(int xKoordinaatti, Pelaaja p) {

        for (int vaakarivinNumero = this.lauta.size() - 1; vaakarivinNumero >= 0; vaakarivinNumero--) {
            String[] vaakarivi = this.lauta.get(vaakarivinNumero);

            if (vaakarivi[xKoordinaatti].equals(".")) {
                p.lisaaUusiNappula(xKoordinaatti, vaakarivinNumero);
                return;
            }
        }

    }

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

    public String annaPelaajanMerkki(Pelaaja p) {
        if (p.getVuoronumero() == 1) {
            return "X";
        }
        return "O";
    }

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

    public String luoTestattavaJono(String[] rivi) {
        String testattavaJono = "";

        for (int j = 0; j < rivi.length; j++) {
            testattavaJono = testattavaJono + rivi[j];
        }

        return testattavaJono;
    }

    public String luoTestattavaJono() {
        String testattavaJono = "";

        for (int j = 0; j < this.lauta.get(0).length; j++) {

            for (String[] rivi : this.lauta.values()) {
                testattavaJono = testattavaJono + rivi[j];
            }
        }
        return testattavaJono;
    }

    public boolean onkoPystysuorasti(String merkki, int pituus) {

        for (int i = 0; i < this.lauta.size(); i++) {

            String testattavaJono = this.luoTestattavaJono();

            if (this.testaaMerkkijononPituus(testattavaJono, merkki, pituus) == pituus) {
                return true;
            }
        }
        return false;
    }

    public boolean onkoVinostiKoillinenLounas(String merkki, int pituus) {

        for (int i = 0; i < this.lauta.size(); i++) {
            String[] rivi = this.lauta.get(i);

//            if(this.muodostaMerkkijonoVinolleSuoralle(rivi, merkki, pituus, i, "koillinenlounas")) {
//                return true;
//            }

            for (int j = 0; j < rivi.length; j++) {
                String merkkijono = "";
                String alkuPiste = rivi[j];

                merkkijono = merkkijono + alkuPiste;

                merkkijono = this.laskeSeuraavatPisteetKoillinenLounas(i, j, merkkijono, "koillinenlounas");

//
                if (this.testaaMerkkijononPituus(merkkijono, merkki, pituus) == pituus) {
                    return true;
                }
            }
        }

        return false;
    }

//    public boolean muodostaMerkkijonoVinolleSuoralle(String[] rivi, String merkki, int pituus, int i, String suoranSuunta) {
//        String merkkijono = "";
//        for (int j = 0; j < rivi.length; j++) {
//
//            String alkuPiste = rivi[j];
//
//            merkkijono = merkkijono + alkuPiste;
//
//            if (suoranSuunta.equals("koillinenlounas")) {
//                merkkijono = this.laskeSeuraavatPisteetKoillinenLounas(i, j, merkkijono, suoranSuunta);
//            } else {
//                merkkijono = this.laskeSeuraavatPisteetKaakkoLuode(i, j, merkkijono, suoranSuunta);
//            }
//            
//            if (this.testaaMerkkijononPituus(merkkijono, merkki, pituus) == pituus) {
//                    return true;
//                }
//                     
//        }
//
//        return false;
//    }

    public boolean onkoVinostiKaakkoLuode(String merkki, int pituus) {

        for (int i = this.lauta.size() - 1; i >= 0; i--) {
            String[] rivi = this.lauta.get(i);
            
//            String merkkijono = this.muodostaMerkkijonoVinolleSuoralle(rivi, merkki, pituus, i, "kaakkoluode");

            for (int j = 0; j < rivi.length; j++) {
                String merkkijono = "";
                String alkuPiste = rivi[j];

                merkkijono = merkkijono + alkuPiste;

                merkkijono = this.laskeSeuraavatPisteetKaakkoLuode(i, j, merkkijono, "kaakkoluode");


                if (this.testaaMerkkijononPituus(merkkijono, merkki, pituus) == pituus) {
                    return true;
                }
            }
        }

        return false;
    }

    public String laskeSeuraavatPisteetKaakkoLuode(int y, int x, String merkkijono, String suoranSuunta) {

        return laskeSeuraavaPiste(y, x, -1, -1, merkkijono, suoranSuunta);
//        y--;
//        x--;
//
//        if (x < 0 || y < 0) {
//            return merkkijono;
//        } else {
//            String seuraavaPiste = this.lauta.get(y)[x];
//
//            merkkijono = merkkijono + seuraavaPiste;
//
//            return this.laskeSeuraavatPisteetKaakkoLuode(y, x, merkkijono);
//        }
    }

    public boolean tarkistaYEhtoGeneerisyydessa(int y, String suoranSuunta) {
        if (suoranSuunta.equals("koillinenlounas")) {
            return y > this.lauta.size() - 1;
        }

        return y < 0;
    }

    public String laskeSeuraavatPisteetKoillinenLounas(int y, int x, String merkkijono, String suoranSuunta) {

        return laskeSeuraavaPiste(y, x, 1, -1, merkkijono, suoranSuunta);
//        y++;
//        x--;
//
//        if (x < 0 || y > this.lauta.size() - 1) {
//            return merkkijono;
//        } else {
//            String seuraavaPiste = this.lauta.get(y)[x];
//
//            merkkijono = merkkijono + seuraavaPiste;
//
//            return this.laskeSeuraavatPisteetKoillinenLounas(y, x, merkkijono);
//        }
    }

    public String laskeSeuraavaPiste(int y, int x, int yLisays, int xLisays, String merkkijono, String suoranSuunta) {

        y = y + yLisays;
        x = x + xLisays;

        if (x < 0 || this.tarkistaYEhtoGeneerisyydessa(y, suoranSuunta)) {
            return merkkijono;
        } else {
            String seuraavaPiste = this.lauta.get(y)[x];

            merkkijono = merkkijono + seuraavaPiste;

            return this.laskeSeuraavaPiste(y, x, yLisays, xLisays, merkkijono, suoranSuunta);
        }
    }

    public int testaaMerkkijononPituus(String testattavaJono, String merkki, int maksimipituus) {

        String tarkistusjono = teeJono(maksimipituus, merkki);
        if (testattavaJono.contains(tarkistusjono)) {
            return maksimipituus;
        }

        return 0;
    }

    public String teeJono(int pituus, String merkki) {

        String jono = "";

        for (int i = 0; i < pituus; i++) {
            jono += merkki;
        }

        return jono;
    }

    public boolean onkoLautaTaynna() {

        int tarkistusluku = 0;

        //Tarkistetaan jokaiselta vaakariviltä sisältääkö se tyhjiä paikkoja

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

    public int selvitaLaudanPisimmanSivunPituus() {
        int korkeus = this.lauta.size();
        int leveys = this.lauta.get(0).length;

        if (korkeus > leveys) {
            return korkeus;
        }
        return leveys;
    }
}
