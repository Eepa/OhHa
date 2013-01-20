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
                merkki = "O";
            }

            if (this.onkoVaakasuorasti(merkki)
                    || this.onkoPystysuorasti(merkki)
                    || this.onkoVinostiVasemmaltaOikealleYlapuoli(merkki)
                    || this.onkoVinostiOikealtaVasemmalleYlapuoli(merkki)
                    || this.onkoVinostiOikealtaVasemmalleAlapuoli(merkki)
                    || this.onkoVinostiVasemmaltaOikealleAlapuoli(merkki)) {
                return true;
            }

        }

        return false;
    }

    public boolean onkoVaakasuorasti(String merkki) {


        for (int i = 0; i < this.lauta.size(); i++) {

            String[] rivi = this.lauta.get(i);
            String jono = "";

            for (int j = 0; j < rivi.length; j++) {
                jono = jono + rivi[j];

            }

            if (this.testaaMerkkijononPituus(jono, merkki) >= 4) {
                return true;
            }
        }
        return false;
    }

    public boolean onkoPystysuorasti(String merkki) {

        for (int i = 0; i < this.lauta.size(); i++) {
            String jono = "";

            for (int j = 0; j < this.lauta.get(0).length; j++) {
                for (String[] rivi : this.lauta.values()) {
                    jono = jono + rivi[j];
                }
            }

            if (this.testaaMerkkijononPituus(jono, merkki) >= 4) {
                return true;
            }
        }
        return false;
    }

    public boolean onkoVinostiVasemmaltaOikealleYlapuoli(String merkki) {
        
        //wanha

        for (int i = 0; i < this.lauta.size(); i++) {
            int x = 0;
            int y = i;

            String jono = "";

            if (this.selvitaLyhinPituusLaudassa() < i) {
                for (int j = 0; j < this.selvitaLyhinPituusLaudassa(); j++) {

                    jono = jono + this.lauta.get(y)[x];

                    y--;
                    x++;
                }

            } else {
                for (int j = 0; j < i; j++) {

                    jono = jono + this.lauta.get(y)[x];

                    y--;
                    x++;
                }
            }


            if (this.testaaMerkkijononPituus(jono, merkki) >= 4) {
                return true;
            }


        }


        return false;
    }

    public boolean onkoVinostiVasemmaltaOikealleAlapuoli(String merkki) {

        //uusi
        
        for (int i = 0; i < this.lauta.size(); i++) {
            int x = this.lauta.get(0).length - 1;
            int y = this.selvitaLyhinPituusLaudassa()-i;

            String jono = "";

            if (this.selvitaLyhinPituusLaudassa() < i) {
                for (int j = 0; j < this.selvitaLyhinPituusLaudassa(); j++) {

                    jono = jono + this.lauta.get(y)[x];

                    y++;
                    x--;
                }

            } else {
                for (int j = 0; j < i; j++) {

                    jono = jono + this.lauta.get(y)[x];

                    y++;
                    x--;
                }
            }


            if (this.testaaMerkkijononPituus(jono, merkki) >= 4) {
                return true;
            }


        }



        return false;
    }

    public boolean onkoVinostiOikealtaVasemmalleYlapuoli(String merkki) {
        
        //wanha

        for (int i = 0; i < this.lauta.size(); i++) {
            int x = this.lauta.size();
            int y = i;

            String jono = "";

            if (this.selvitaLyhinPituusLaudassa() < i) {
                for (int j = 0; j < this.selvitaLyhinPituusLaudassa(); j++) {

                    jono = jono + this.lauta.get(y)[x];

                    y--;
                    x--;
                }
            } else {
                for (int j = 0; j < i; j++) {

                    jono = jono + this.lauta.get(y)[x];

                    y--;
                    x--;
                }
            }


            if (this.testaaMerkkijononPituus(jono, merkki) >= 4) {
                return true;
            }


        }

        return false;
    }

    public boolean onkoVinostiOikealtaVasemmalleAlapuoli(String merkki) {
        
        //uusi

        for (int i = 0; i < this.lauta.size(); i++) {
            int x = 0;
            int y = this.selvitaLyhinPituusLaudassa()- i;

            String jono = "";

            if (this.selvitaLyhinPituusLaudassa() < i) {
                for (int j = 0; j < this.selvitaLyhinPituusLaudassa(); j++) {

                    jono = jono + this.lauta.get(y)[x];

                    y++;
                    x++;
                }
            } else {
                for (int j = 0; j < i; j++) {

                    jono = jono + this.lauta.get(y)[x];

                    y++;
                    x++;
                }
            }


            if (this.testaaMerkkijononPituus(jono, merkki) >= 4) {
                return true;
            }


        }


        return false;
    }

    public int testaaMerkkijononPituus(String jono, String merkki) {

        String xjono = teeJono(4, merkki);

        int lyhin = this.selvitaLyhinPituusLaudassa();


        for (int i = 4; i <= lyhin; i++) {
            if (jono.contains(xjono)) {
                return i;
            }
            xjono = teeJono(i, merkki);
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

        for (int i = 0; i < this.lauta.size(); i++) {

            String[] rivi = this.lauta.get(i);
            String jono = "";

            for (int j = 0; j < rivi.length; j++) {
                jono = jono + rivi[j];
            }

            if (jono.contains(".")) {
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

    public int selvitaLyhinPituusLaudassa() {
        int hashMapkoko = this.lauta.size();
        int rivinKoko = this.lauta.get(0).length;

        if (hashMapkoko < rivinKoko) {
            return hashMapkoko;
        } else {
            return rivinKoko;
        }
    }
}
