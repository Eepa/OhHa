package neljansuora.peli;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import neljansuora.domain.Nappula;
import neljansuora.domain.Pelaaja;

public class NappulaKasittelija {

    private Map<Integer, String[]> lauta;
    private List<Pelaaja> pelaajat;

    public NappulaKasittelija(Map<Integer, String[]> lauta, List<Pelaaja> pelaajat) {
        this.lauta = lauta;
        this.pelaajat = pelaajat;
    }

    public boolean onkoMahdollinenSiirto(int vaakarivinNumero) {

        if (this.lauta.get(0)[vaakarivinNumero].equals(".")) {
            return true;
        }

        return false;
    }

    public void teeSiirto(int vaakarivinNumero, Pelaaja p) {

        int pystyrivinNumero = this.lauta.size() - 1;

        while (pystyrivinNumero >= 0) {

            String[] vaakarivi = this.lauta.get(pystyrivinNumero);

            if (vaakarivi[vaakarivinNumero].equals(".")) {
                p.lisaaUusiNappula(vaakarivinNumero, pystyrivinNumero);
                return;
            }
            pystyrivinNumero--;
        }

    }

    public boolean onkoRiittavanPitkiaSuoria(int merkkijononPituus) {

        String merkki = "";

        for (Pelaaja p : this.pelaajat) {

            if (p.getNappulat().size() < merkkijononPituus) {
                return false;
            }

            if (p.getVuoronumero() == 1) {
                merkki = "X";
            } else {
                merkki = "O";
            }

            if (this.onkoVaakasuorasti(merkki, merkkijononPituus)
                    || this.onkoPystysuorasti(merkki, merkkijononPituus)
                    || this.onkoVinostiKoillinenKaakko(merkki, merkkijononPituus)
                    || this.onkoVinostiLounasLuode(merkki, merkkijononPituus)) {
                return true;
            }
        }

        return false;
    }

    public boolean onkoVaakasuorasti(String merkki, int pituus) {

        for (int i = 0; i < this.lauta.size(); i++) {

            String[] rivi = this.lauta.get(i);
            String testattavaJono = "";

            for (int j = 0; j < rivi.length; j++) {
                testattavaJono = testattavaJono + rivi[j];
            }

            if (this.testaaMerkkijononPituus(testattavaJono, merkki, pituus) >= 4) {
                return true;
            }
        }
        return false;
    }

    public boolean onkoPystysuorasti(String merkki, int pituus) {

        for (int i = 0; i < this.lauta.size(); i++) {
            String testattavaJono = "";

            for (int j = 0; j < this.lauta.get(0).length; j++) {

                for (String[] rivi : this.lauta.values()) {
                    testattavaJono = testattavaJono + rivi[j];
                }
            }

            if (this.testaaMerkkijononPituus(testattavaJono, merkki, pituus) >= pituus) {
                return true;
            }
        }
        return false;
    }

    public boolean onkoVinostiKoillinenKaakko(String merkki, int pituus) {

        for (int i = 0; i < this.lauta.size(); i++) {
            String[] rivi = this.lauta.get(i);

            for (int j = 0; j < rivi.length; j++) {
                String merkkijono = "";
                String alkuPiste = rivi[j];

                merkkijono = merkkijono + alkuPiste;

                merkkijono = this.laskeSeuraavatPisteetKoillinenKaakko(i, j, merkkijono);


                if (this.testaaMerkkijononPituus(merkkijono, merkki, pituus) >= pituus) {
                    return true;
                }
            }
        }

        return false;
    }

    public boolean onkoVinostiLounasLuode(String merkki, int pituus) {

        for (int i = this.lauta.size()-1; i >= 0; i--) {
            String[] rivi = this.lauta.get(i);

            for (int j = 0; j < rivi.length; j++) {
                String merkkijono = "";
                String alkuPiste = rivi[j];

                merkkijono = merkkijono + alkuPiste;

                merkkijono = this.laskeSeuraavatPisteetLounasLuode(i, j, merkkijono);


                if (this.testaaMerkkijononPituus(merkkijono, merkki, pituus) >= pituus) {
                    return true;
                }
            }
        }

        return false;
    }
    
    public String laskeSeuraavatPisteetLounasLuode(int y, int x, String merkkijono){
        
        y--;
        x--;

        if (x < 0|| y < 0) {
            return merkkijono;
        } else {
            String seuraavaPiste = this.lauta.get(y)[x];

            merkkijono = merkkijono + seuraavaPiste;

            return this.laskeSeuraavatPisteetLounasLuode(y, x, merkkijono);
        }
    }

    public String laskeSeuraavatPisteetKoillinenKaakko(int y, int x, String merkkijono) {

        y++;
        x--;

        if (x < 0 || y > this.lauta.size() - 1) {
            return merkkijono;
        } else {
            String seuraavaPiste = this.lauta.get(y)[x];

            merkkijono = merkkijono + seuraavaPiste;

            return this.laskeSeuraavatPisteetKoillinenKaakko(y, x, merkkijono);
        }
    }

   
    public int testaaMerkkijononPituus(String testattavaJono, String merkki, int maksimipituus) {

        String tarkistusjono = teeJono(maksimipituus, merkki);

        int pisinMahdollinenSuora = this.selvitaLaudanLyhimmanSivunPituus();

        for (int i = maksimipituus; i <= pisinMahdollinenSuora; i++) {
            if (testattavaJono.contains(tarkistusjono)) {
                return i;
            }
            tarkistusjono = teeJono(i, merkki);
        }

        return 0;
    }

    public String teeJono(int pituus, String merkki) {

        String jono = "";

        for (int i = 0; i < pituus; i++) {
            jono = jono + merkki;
        }

        return jono;
    }

    public boolean onkoLautaTaynna() {

        int tarkistusluku = 0;

        //Tarkistetaan jokaiselta vaakariviltä sisältääkö se tyhjiä paikkoja

        for (int i = 0; i < this.lauta.size(); i++) {

            String[] vaakarivi = this.lauta.get(i);
            String rivinMerkit = "";

            for (int j = 0; j < vaakarivi.length; j++) {
                rivinMerkit = rivinMerkit + vaakarivi[j];
            }

            if (rivinMerkit.contains(".")) {
                return false;
            } else {
                tarkistusluku++;
            }

        }

        if (tarkistusluku == this.lauta.size()) {
            return true;
        }
        return false;
    }

    public int selvitaLaudanLyhimmanSivunPituus() {
        int korkeus = this.lauta.size();
        int leveys = this.lauta.get(0).length;

        if (korkeus < leveys) {
            return korkeus;
        } else {
            return leveys;
        }
    }
}
