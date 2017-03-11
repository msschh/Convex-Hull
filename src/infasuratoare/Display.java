package infasuratoare;

import javax.swing.*;

public class Display extends JFrame{
    
    public static final int W = 1000;
    public static final int H = 700;

    Panou Pa;
    
    Display(){
        this.setTitle("Infasuratoare");
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(W, H);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        
        Pa = new Panou();
        add(Pa);
    }
}
