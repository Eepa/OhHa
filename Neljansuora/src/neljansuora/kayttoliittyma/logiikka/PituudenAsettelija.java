package neljansuora.kayttoliittyma.logiikka;

import javax.swing.JOptionPane;

/**
 * Luokka PituudenAsettelija asettaa uudelle Neljansuora-pelille pelilaudan
 * leveyden ja pituuden ja voittosuoran vähimmäispituuden.
 *
 * @author evpa
 * @see Neljansuora
 * @see Kayttoliittyma
 */
public class PituudenAsettelija {

    /**
     * Asettaa Neljansuora-pelin Pelilaudan sivun pituuden käyttäjän syötteen
     * mukaan. Metodissa tarkistetaan annetun pituuden oikeellisuus käyttämällä
     * muita luokan omia metodeja uuden pituuden numeroarvon validointiin.
     * Metodi käyttää pituuden kyselemiseksi JOptionPane-luokan kyselylaatikkoa.
     *
     * @param pituudenNimi Nimi pituudelle, jonka arvoa ollaan asettamassa.
     * @return Palauttaa asetetun pituuden.
     */
    public int asetaLaudanSivunPituus(String pituudenNimi) {
        int palautettavaPituus = 0;

        String pituus = JOptionPane.showInputDialog(null, "Anna " + pituudenNimi + " väliltä 5-20. "
                + "Tyhjä asettaa oletusarvon. ", "Sivun pituuden asetus", 1);

        if (pituus == null) {
            System.exit(0);
        }
        if (pituus.isEmpty()) {
            return this.asetaOletusSivunpituus(pituudenNimi);
        }

        palautettavaPituus = this.testaaOlikoAnnettuSivunPituusLuku(pituus, pituudenNimi, palautettavaPituus);
        palautettavaPituus = this.testaaKuuluukoPituusAnnetulleValille(palautettavaPituus, 0, 0, 20, 5, pituudenNimi);

        return palautettavaPituus;
    }

    /**
     * Asettaa pelilaudan oletussivunpituuden annetun pituuden nimen mukaan.
     *
     * @param pituudenNimi Nimi pituudelle, jonka arvoa ollaan asettamassa.
     * @return Palauttaa asetetun pituuden.
     */
    public int asetaOletusSivunpituus(String pituudenNimi) {
        if (pituudenNimi.equals("leveys")) {
            return 7;
        }
        return 6;
    }

    /**
     * Testaa, onko metodille annettu sivun pituus luku. Jos pituus ei ole luku,
     * metodi heittää poikkeuksen ja käsittelee sen.
     *
     * @param pituus Tarkasteltava ja tarkistettava pituuden arvo
     * @param pituudenNimi Nimi pituudelle, jonka arvoa ollaan asettamassa
     * @param palautettavaPituus Pituus, joka palautetaan metodin lopuksi.
     * @return Palauttaa pituuden numeroarvon.
     */
    public int testaaOlikoAnnettuSivunPituusLuku(String pituus, String pituudenNimi,
            int palautettavaPituus) {
        
        try {
            palautettavaPituus = Integer.parseInt(pituus);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Syötteen pitää olla joku luku!",
                    "Luvun antaminen epäonnistui", 0);
            palautettavaPituus = this.asetaLaudanSivunPituus(pituudenNimi);
        }
        return palautettavaPituus;
    }

    public int setLeveys(String pituudenNimi) {
        return this.asetaLaudanSivunPituus(pituudenNimi);
    }

    public int setKorkeus(String pituudenNimi) {
        return this.asetaLaudanSivunPituus(pituudenNimi);
    }

    /**
     * Asettaa Neljansuora-pelissä etsittävän voittosuoran vähimmäispituuden
     * käyttäjän syötteen mukaan. Metodissa tarkistetaan annetun pituuden
     * oikeellisuus käyttämällä muita luokan omia metodeja uuden pituuden
     * numeroarvon validointiin. Metodi käyttää pituuden kyselemiseksi
     * JOptionPane-luokan kyselylaatikkoa.
     *
     * @param leveys Pelilaudan leveys
     * @param korkeus Pelilaudan korkeus
     * @param pituudenNimi Nimi pituudelle, jonka arvoa ollaan asettamassa.
     * @return Palauttaa asetetun pituuden
     * @see Pelilauta
     */
    public int asetaEtsittavanSuoranPituus(int leveys, int korkeus, String pituudenNimi) {
        int palautettavaPituus = 0;

        int pisin = this.selvitaPisin(leveys, korkeus);

        String pituus = JOptionPane.showInputDialog(null, "Anna " + pituudenNimi + " väliltä 4-" + pisin
                + ". Tyhjä asettaa oletusarvon. ", "Suoran pituuden asetus", 1);

        if (pituus == null) {
            System.exit(0);
        }
        if (pituus.isEmpty()) {
            return 4;
        }

        palautettavaPituus = this.testaaOlikoAnnettuSuoranPituusLuku(pituus, leveys, korkeus, pituudenNimi, palautettavaPituus);
        palautettavaPituus = this.testaaKuuluukoPituusAnnetulleValille(palautettavaPituus, leveys, korkeus, pisin, 4, pituudenNimi);

        return palautettavaPituus;
    }

    /**
     * Testaa, onko metodille annettu suoran pituus luku. Jos pituus ei ole
     * luku, metodi heittää poikkeuksen ja käsittelee sen.
     *
     * @param pituus Tarkasteltava ja tarkistettava pituuden arvo
     * @param leveys Pelilaudan leveys
     * @param korkeus Pelilaudan korkeus
     * @param pituudenNimi Nimi pituudelle, jonka arvoa ollaan asettamassa
     * @param palautettavaPituus Pituus, joka palautetaan metodin lopuksi.
     * @return Palauttaa oikeanmuotoisen pituuden numeroarvon.
     */
    public int testaaOlikoAnnettuSuoranPituusLuku(String pituus, int leveys, int korkeus,
            String pituudenNimi, int palautettavaPituus) {

        try {
            palautettavaPituus = Integer.parseInt(pituus);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Syötteen pitää olla joku luku!",
                    "Luvun antaminen epäonnistui", 0);
            palautettavaPituus = this.asetaEtsittavanSuoranPituus(leveys, korkeus, pituudenNimi);
        }
        return palautettavaPituus;
    }

    /**
     * Selvittää, kuuluuko parametrina annettu palautettavaPituus jollekin
     * tietylle välille. Jos tarkastus epäonnistuu metodi heittää poikkeuksen ja
     * käsittelee sen.
     *
     * @param palautettavaPituus Pituus, joka palautetaan metodin lopuksi
     * @param leveys Pelilaudan leveys
     * @param korkeus Pelilaudan korkeus
     * @param ylaraja Pituuden yläraja
     * @param alaraja Pituuden alaraja
     * @param pituudenNimi Nimi pituudelle, jonka arvoa ollaan asettamassa.
     * @return Palauttaa oikeanmuotoisen pituuden numeroarvon.
     */
    public int testaaKuuluukoPituusAnnetulleValille(int palautettavaPituus, int leveys, int korkeus,
            int ylaraja, int alaraja, String pituudenNimi) {

        try {
            if (palautettavaPituus < alaraja || palautettavaPituus > ylaraja) {
                throw new IllegalArgumentException();
            }
            return palautettavaPituus;

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "Luku oli liian suuri tai liian pieni!",
                    "Luvun antaminen epäonnistui", 0);

            if (pituudenNimi.equals("suoran pituus")) {
                return this.asetaEtsittavanSuoranPituus(leveys, korkeus, pituudenNimi);
            } else {
                return this.asetaLaudanSivunPituus(pituudenNimi);
            }
        }
    }

    /**
     * Selvittää parametreina annetuista luvuista suuremman ja palauttaa sen.
     *
     * @param leveys Pelilaudan leveys
     * @param korkeus Pelilaudan korkeus
     * @return Palauttaa suuremman lukuarvon (leveyden tai korkeuden)
     */
    public int selvitaPisin(int leveys, int korkeus) {
        if (leveys > korkeus) {
            return leveys;
        }
        return korkeus;
    }
}
