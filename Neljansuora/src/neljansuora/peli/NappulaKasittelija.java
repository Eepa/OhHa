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

    public void teeSiirto(int pystyRivinNumero, Pelaaja p) {

        int vaakarivinNumero = this.lauta.size() - 1;

        while (vaakarivinNumero >= 0) {

            String[] rivi = this.lauta.get(vaakarivinNumero);

            if (rivi[pystyRivinNumero].equals(".")) {
                p.lisaaUusiNappula(pystyRivinNumero, vaakarivinNumero);
                return;
            }
            vaakarivinNumero--;
        }

    }

    public boolean onkoRiittavanPitkiaSuoria() {

        String merkki = "";

        for (Pelaaja p : this.pelaajat) {

            if (p.getNappulat().size() < 4) {
                return false;
            }

            if (p.getVuoronumero() == 1) {
                merkki = "X";
            } else {
                merkki = "Y";
            }

            if (this.onkoVaakasuorasti(p.getNappulat(), merkki) >= 4
                    || this.onkoPystysuorasti(p.getNappulat(), merkki) >= 4
                    || this.onkoVinosti(p.getNappulat(), merkki) >= 4) {
                return true;
            }

        }

        return false;
    }

    public int onkoVaakasuorasti(List<Nappula> nappulat, String merkki) {
        int perakkaistenMaara = 0;

        for (int i = 0; i < this.lauta.size(); i++) {

            String[] rivi = this.lauta.get(i);
            String jono = "";

            for(int j = 0; j < rivi.length; j++){
                jono = jono + rivi[j];
            }

        }


        return perakkaistenMaara;
    }

    

    public int onkoPystysuorasti(List<Nappula> nappulat, String merkki) {
        return 0;
    }

    public int onkoVinosti(List<Nappula> nappulat, String merkki) {
        return 0;
    }

}
