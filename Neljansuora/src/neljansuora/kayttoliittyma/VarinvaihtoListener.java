package neljansuora.kayttoliittyma;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import neljansuora.peli.Neljansuora;

public class VarinvaihtoListener implements ActionListener {

    private Neljansuora neljansuora;
    private Piirtoalusta piirtoalusta;
    private int varipaletinNumero;

    public VarinvaihtoListener(Neljansuora neljansuora, Piirtoalusta piirtoalusta) {

        this.neljansuora = neljansuora;
        this.piirtoalusta = piirtoalusta;
        this.varipaletinNumero = 1;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.setVaripaletinNumero();

        // --> parempia ratkaisuita?? Ensin pitaa saada tama toimimaan

        if (this.varipaletinNumero == 1) {

            this.piirtoalusta.vaihdaTaustavaria(Color.blue);
            this.piirtoalusta.vaihdaPelaajanVaria(Color.red, 1);
            this.piirtoalusta.vaihdaPelaajanVaria(Color.yellow, 2);

        } else if (this.varipaletinNumero == 2) {

            this.piirtoalusta.vaihdaTaustavaria(Color.black);
            this.piirtoalusta.vaihdaPelaajanVaria(Color.orange, 1);
            this.piirtoalusta.vaihdaPelaajanVaria(Color.magenta, 2);


        } else if (this.varipaletinNumero == 3) {

            this.piirtoalusta.vaihdaTaustavaria(Color.DARK_GRAY);
            this.piirtoalusta.vaihdaPelaajanVaria(Color.CYAN, 1);
            this.piirtoalusta.vaihdaPelaajanVaria(Color.PINK, 2);

        }

        this.piirtoalusta.paivita();
    }

    public void setVaripaletinNumero() {
        if (this.varipaletinNumero < 3) {
            this.varipaletinNumero++;
        } else if (this.varipaletinNumero == 3) {
            this.varipaletinNumero = 1;
        }
    }
}
