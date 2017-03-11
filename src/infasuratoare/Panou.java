package infasuratoare;

import javax.swing.JPanel;

public class Panou extends JPanel{
    public static final int WEI = 1000;
    public static final int HEI = 700;
    
    public static Plan P;
    public static Meniu M;

    Panou() {
        this.setVisible(true);
        this.setSize(WEI, HEI);
        this.setLocation(0, 0);
        this.setEnabled(true);
        //this.setLayout(new BorderLayout());
        this.setLayout(null);

        P = new Plan();
        M = new Meniu();

        this.add(M);
        this.add(P);
    }

}
