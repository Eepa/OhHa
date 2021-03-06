package neljansuora.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * Luokka Pelaaja kuvaa yhtä Neljansuora-pelin pelaajaa. Pelaajalla on oma nimi,
 * vuoronumero ja lista hänen omistamistaan Nappula-luokan nappuloista.
 *
 * @author Eveliina Pakarinen
 */
public class Pelaaja {

    /**
     * Kertoo, onko Pelaajan siirtovuoro pelissä ensimmäinen eli aloittaja vai
     * toinen.
     */
    private int vuoronumero;
    /**
     * Sisältää viitteet kaikkiin pelaajalle kuuluviin Nappula-olioihin.
     *
     * @see Nappula
     */
    private List<Nappula> nappulat;
    /**
     * Pelaajan nimi
     */
    private String nimi;

    /**
     * Luo uuden Pelaaja-olion ja asettaa pelaajan oliomuuttujien nimi ja
     * vuoronumero arvot. Lisäksi konstruktori alustaa Pelaajan Nappula-listan.
     *
     * @param vuoronumero Kertoo, mikä numero pelaajan vuoronumeroksi tulee.
     * @param nimi Kertoo Pelaajan nimen.
     */
    public Pelaaja(int vuoronumero, String nimi) {
        this.vuoronumero = vuoronumero;
        this.nimi = nimi;
        this.nappulat = new ArrayList<Nappula>();
    }

    public List getNappulat() {
        return this.nappulat;
    }

    public int getVuoronumero() {
        return this.vuoronumero;
    }

    /**
     * Lisaa uuden Nappulan Pelaajan nappulalistaan.
     *
     * @param x Asettaa Pelaajan uuden Nappulan x-koordinaatiksi parametrin
     * arvon.
     * @param y Asettaa Pelaajan uuden Nappulan y-kooridnaatiksi parametrin
     * arvon.
     *
     * @see Nappula
     */
    public void lisaaUusiNappula(int x, int y) {
        this.nappulat.add(new Nappula(x, y));
    }

    /**
     * Poistaa kaikki pelaajan nappulat pelaajan nappulalistalta.
     */
    public void poistaNappulat() {
        this.nappulat.removeAll(this.nappulat);
    }

    public String getNimi() {
        return this.nimi;
    }
}
