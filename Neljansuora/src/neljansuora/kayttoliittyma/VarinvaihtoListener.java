package neljansuora.kayttoliittyma;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import neljansuora.peli.Neljansuora;

public class VarinvaihtoListener implements ActionListener {

    private Neljansuora neljansuora;
    private Piirtoalusta piirtoalusta;
    private int varipaletinNumero;
    private Map<Integer, List<Color>> varientalletuslista;

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

    public void setVaripaletinNumero() {
        if (this.varipaletinNumero < 2) {
            this.varipaletinNumero++;
        } else if (this.varipaletinNumero == 2) {
            this.varipaletinNumero = 0;
        }
    }

    public void alustaOletusVarilista() {
        this.lisaaUusiVarilista(Color.blue, Color.red, Color.yellow);
        this.lisaaUusiVarilista(Color.black, Color.orange, Color.magenta);
        this.lisaaUusiVarilista(Color.DARK_GRAY, Color.CYAN, Color.PINK);
    }

    public void lisaaUusiVarilista(Color taustavari, Color pelaajan1Vari, Color pelaajan2Vari) {
        this.varientalletuslista.put(this.varientalletuslista.size(), new ArrayList<Color>());

        this.varientalletuslista.get(this.varientalletuslista.size() - 1).add(taustavari);
        this.varientalletuslista.get(this.varientalletuslista.size() - 1).add(pelaajan1Vari);
        this.varientalletuslista.get(this.varientalletuslista.size() - 1).add(pelaajan2Vari);

    }

    public void vaihdaVarit(List<Color> varit) {
        this.piirtoalusta.vaihdaTaustavaria(varit.get(0));
        this.piirtoalusta.vaihdaPelaajanVaria(varit.get(1), 1);
        this.piirtoalusta.vaihdaPelaajanVaria(varit.get(2), 2);
    }
}
