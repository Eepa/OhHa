package neljansuora.kayttoliittyma.logiikka;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import neljansuora.kayttoliittyma.grafiikka.Piirtoalusta;
import neljansuora.peli.Neljansuora;

/**
 * Luokka VarinvaihtoListener säätelee Piirtoalustan värejä ja asettaa 
 * uusia värejä Piirtoalustan värivalikoimaan.
 * @author evpa
 * @see Piirtoalusta
 */

public class VarinvaihtoListener implements ActionListener {
    
    /**
     * Neljansuora kuvaa Neljansuora-pelin samannimistä luokkaa.
     *
     * @see Neljansuora
     */
    
    private Neljansuora neljansuora;
    
    /**
     * Piirtoalusta, joka piirtää käyttöliittymän peliruudukon.
     * @see Piirtoalusta
     */
    private Piirtoalusta piirtoalusta;
    
    /**
     * Kertoo, mikä väripaletti kulloinkin on käytössä. Numero viittaa 
     * varientalletuslistan avainarvoihin.
     */
    private int varipaletinNumero;
    
    /**
     * Pitää tallessa erilaisia värilistoja Piirtoalustaa varten. Listat on talletettu 
     * numeroavaimen (varipaletinNumero) taakse.
     */
    private Map<Integer, List<Color>> varientalletuslista;
    
    /**
     * Konstruktorissa alustetaan varipaletinNumeron numerointi ja varientalletuslista. 
     * Sen jälkeen alustetaan oletusvärilista luokan omalla metodilla. Lisäksi luokan attribuutteihin 
     * asetetaan konstruktorin parametrien arvot.
     * @param neljansuora Kuvaa Neljansuora-peliä
     * @param piirtoalusta Kuvaa piirtoalustaa, joka piirtää peliruudukon.
     */

    public VarinvaihtoListener(Neljansuora neljansuora, Piirtoalusta piirtoalusta) {

        this.neljansuora = neljansuora;
        this.piirtoalusta = piirtoalusta;
        this.varipaletinNumero = 0;
        this.varientalletuslista = new HashMap<Integer, List<Color>>();
        this.alustaOletusVarilista();

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.setVaripaletinNumero();

        this.vaihdaVarit(this.varientalletuslista.get(this.varipaletinNumero));

        this.piirtoalusta.paivita();
    }
    
    /**
     * Metodi kasvattaa väripaletin numeroa, jos se on pienempi kuin varientalletuslistan 
     * varipalettien määrä, tai asettaa sen nollaksi, jos kierros on käyty loppuun.
     */

    public void setVaripaletinNumero() {
        if (this.varipaletinNumero < this.varientalletuslista.size()-1) {
            this.varipaletinNumero++;
        } else if (this.varipaletinNumero == this.varientalletuslista.size()-1) {
            this.varipaletinNumero = 0;
        }
    }
    
    /**
     * Asetetaan varientalletuslistalle Neljansuora-pelin oletusvärit käyttämällä 
     * luokan omaa metodia.
     */

    public void alustaOletusVarilista() {
        this.lisaaUusiVarilista(Color.blue, Color.red, Color.yellow);
        this.lisaaUusiVarilista(Color.black, Color.orange, Color.magenta);
        this.lisaaUusiVarilista(Color.DARK_GRAY, Color.CYAN, Color.PINK);
    }
    
    /**
     * Lisää varientalletuslistalle uuden värilistan ja asettaa värilistalle 
     * metodin parametreinä saadut värit.
     * @param taustavari Piirtoalustan taustaväri
     * @param pelaajan1Vari Pelaajan1 väri (pelaaja käyttää merkkiä "X")
     * @param pelaajan2Vari Pelaajan2 väri (pelaaja käyttää merkkiä "O")
     */

    public void lisaaUusiVarilista(Color taustavari, Color pelaajan1Vari, Color pelaajan2Vari) {
        this.varientalletuslista.put(this.varientalletuslista.size(), new ArrayList<Color>());

        this.varientalletuslista.get(this.varientalletuslista.size() - 1).add(taustavari);
        this.varientalletuslista.get(this.varientalletuslista.size() - 1).add(pelaajan1Vari);
        this.varientalletuslista.get(this.varientalletuslista.size() - 1).add(pelaajan2Vari);
    }
    
    /**
     * Vaihtaa piirtoalustan värit parametrina saamansa listan väreihin.
     * @param varit Värilista, jonka väreihin piirtoalusta muunnetaan.
     */

    public void vaihdaVarit(List<Color> varit) {
        this.piirtoalusta.vaihdaTaustavaria(varit.get(0));
        this.piirtoalusta.vaihdaPelaajanVaria(varit.get(1), 1);
        this.piirtoalusta.vaihdaPelaajanVaria(varit.get(2), 2);
    }
}
