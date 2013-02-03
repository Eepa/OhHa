package neljansuora.kayttoliittyma;

import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import neljansuora.peli.Neljansuora;

public class TilannetietoPanel extends JPanel implements Paivitettava {

    private Neljansuora neljansuora;
    private int vuoronumero;
    private String vuorossaoleva;
    private JTextArea kukaVuorossa;

    public TilannetietoPanel(Neljansuora neljansuora) {
        super(new GridLayout(1, 2));
        this.neljansuora = neljansuora;
        this.vuoronumero = 1;
        this.setVuorossaoleva();
        this.luoTietopaneeli();
    }

    public int getVuoronumero() {
        return this.vuoronumero;
    }

    public void setVuoronumero() {
        if (this.vuoronumero == 1) {
            vuoronumero++;
        } else if (this.vuoronumero == 2) {
            vuoronumero--;
        }
    }

    public void setVuorossaoleva() {
        this.vuorossaoleva = "Pelaaja" + this.vuoronumero;
    }

    private void setKukaVuorossa() {
        this.setVuorossaoleva();
        this.kukaVuorossa.setText(vuorossaoleva);

    }

    private void luoTietopaneeli() {
        JLabel vuorossa = new JLabel("Vuorossa:");
        this.kukaVuorossa = new JTextArea(this.vuorossaoleva);

        add(vuorossa);
        add(kukaVuorossa);
    }

    @Override
    public void paivita() {
             
        this.setVuoronumero();
        this.setKukaVuorossa();
        
        if(this.neljansuora.getPelilauta().onkoLautaTaynna() 
               || this.neljansuora.getPelilauta().onkoNeljanSuoraa()){
            this.removeAll();
            
            this.setVuoronumero();
            this.setKukaVuorossa();
            
            JLabel voittaja = new JLabel("Voittaja: " + this.vuorossaoleva 
                    + "        ONNEKSI OLKOON!     :)");
            add(voittaja);            
        }
        
    }
}
