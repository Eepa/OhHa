package neljansuora.kayttoliittyma;

import javax.swing.JOptionPane;

public class PituudenAsettelija {

    public PituudenAsettelija() {
    }

    public int asetaLaudanSivunPituus(String pituudenNimi) {

        int palautettavaPituus = 0;

        String pituus = JOptionPane.showInputDialog(null, "Anna " + pituudenNimi + ". "
                + "Tyhjä asettaa oletusarvon. ", "Sivun pituuden asetus", 1);

        if (pituus == null) {
            System.exit(0);
        }

        //Asettaa oletuspituuden ja leveyden

        if (pituus.isEmpty()) {
            return this.asetaOletusSivunpituus(pituudenNimi);
        }

        palautettavaPituus = this.testaaOlikoAnnettuSivunPituusLuku(pituus, pituudenNimi, palautettavaPituus);
        
        palautettavaPituus = this.testaaKuuluukoPituusAnnetulleValille(palautettavaPituus, 0, 0, 25, 5, pituudenNimi);

        return palautettavaPituus;

    }

    public int asetaOletusSivunpituus(String pituudenNimi) {

        if (pituudenNimi.equals("leveys")) {
            return 7;
        }
        return 6;

    }

    public int testaaOlikoAnnettuSivunPituusLuku(String pituus, String pituudenNimi, int palautettavaPituus) {
        try {
            palautettavaPituus = Integer.parseInt(pituus);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Syötteen pitää olla joku luku!",
                    "Luvun antaminen epäonnistui", 0);
            this.asetaLaudanSivunPituus(pituudenNimi);
        }
        return palautettavaPituus;
    }

    public int asetaLeveys(String pituudenNimi) {
        return this.asetaLaudanSivunPituus(pituudenNimi);
    }

    public int asetaKorkeus(String pituudenNimi) {
        return this.asetaLaudanSivunPituus(pituudenNimi);
    }

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

    public int testaaOlikoAnnettuSuoranPituusLuku(String pituus, int leveys, int korkeus,
            String pituudenNimi, int palautettavaPituus) {

        try {
            palautettavaPituus = Integer.parseInt(pituus);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Syötteen pitää olla joku luku!",
                    "Luvun antaminen epäonnistui", 0);
            this.asetaEtsittavanSuoranPituus(leveys, korkeus, pituudenNimi);
        }
        return palautettavaPituus;
    }

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

    public int selvitaPisin(int leveys, int korkeus) {
        if (leveys > korkeus) {
            return leveys;
        }
        return korkeus;
    }
}
