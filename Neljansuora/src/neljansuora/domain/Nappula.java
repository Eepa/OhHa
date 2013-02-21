package neljansuora.domain;

/**
 * Kuvaa Nappula-oliota, joka kuvaa yksittäistä pelaajalle kuuluvaa nappulaa.
 *
 * @author Eveliina Pakarinen
 */
public class Nappula {

    /**
     * Nappulan sijainti pelilaudan x-akselilla.
     */
    private int x;
    /**
     * Nappulan sijainti pelilaudan y-akselilla.
     */
    private int y;

    /**
     * Konstruktori luo uuden nappulan ja asettaa muuttujien x ja y arvot arvot
     * niitä vastaaviin oliomuuttujiin.
     *
     * @param x Nappulan x-koordinaatin arvo
     * @param y Nappulan y-koordinaatin arvo
     */
    public Nappula(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int palautaX() {
        return this.x;
    }

    public int palautaY() {
        return this.y;
    }
}
