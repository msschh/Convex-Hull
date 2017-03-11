
package infasuratoare;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class Meniu extends JPanel{
    public static final int WEM = 294;
    public static final int HEM = 671; 
    JLabel label1, label2, label3;

    Meniu() {
        this.setVisible(true);
        this.setSize(WEM, HEM);
        this.setLocation(700, 0);
        this.setEnabled(true);
        this.setBackground(Color.LIGHT_GRAY);
        this.setBorder(BorderFactory.createLineBorder(Color.black));
        this.setLayout(null);

        JTextField T = new JTextField();
        T.setVisible(true);
        T.setEditable(true);
        T.setBounds(50, 20, 200, 24);
        T.setPreferredSize( new Dimension(200, 24));
        T.setBackground(Color.YELLOW);
        add(T);

        JTextField Te = new JTextField();
        Te.setVisible(true);
        Te.setEditable(true);
        Te.setBounds(50, 50, 200, 24);
        Te.setPreferredSize( new Dimension(200, 24));
        Te.setBackground(Color.YELLOW);
        add(Te);

        JButton Add = new JButton("Add Point");
        Add.setEnabled(true);
        Add.setVisible(true);
        Add.setBounds(100, 100, 100, 30);
        Add.setPreferredSize( new Dimension(100, 30));
        Add.setBackground(Color.RED);
        Add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(T.getText().equals("") || Te.getText().equals("")){}
                else{
                    if(Infasuratoare.PrimescPuncte == true){
                        String sx = T.getText();
                        String sy = Te.getText();
                        int cx = Integer.parseInt(sx) + 340;
                        int cy = 700 - (Integer.parseInt(sy) + 360);
                        if(Infasuratoare.GasireInfasuratoare == true){
                            Infasuratoare.a.add(new Punct(cx, cy));
                        }
                        else{
                            Infasuratoare.PrimescPuncte = false;
                            Infasuratoare.punctDeVerificat = new Punct(cx, cy);
                        }
                        T.setText("");
                        Te.setText("");

                        Panou.P.repaint();
                    }
                }
            }
        });
        add(Add);

        JButton Start = new JButton("Next Step");
        Start.setPreferredSize( new Dimension(100, 30));
        Start.setEnabled(true);
        Start.setVisible(true);
        Start.setBounds(100, 150, 100, 30);
        Start.setBackground(Color.GREEN);
        Start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(Infasuratoare.GasireInfasuratoare == true){
                    if(Infasuratoare.pasInfasuratoare == 0){
                        Infasuratoare.PrimescPuncte = false;
                        //Panou.P.repaint();
                        InfasuratoareConvexa.solve();
                        Infasuratoare.pasInfasuratoare = 2;
                    }
                    else{
                        InfasuratoareConvexa.creare();
                        Infasuratoare.pasInfasuratoare++;
                    }  
                }
            }
        });
        add(Start);

        JButton Verificare = new JButton("Check");
        Verificare.setPreferredSize( new Dimension(100, 30));
        Verificare.setBounds(100, 200, 100, 30);
        Verificare.setEnabled(true);
        Verificare.setVisible(true);
        Verificare.setBackground(Color.ORANGE);
        Verificare.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(Infasuratoare.punctInCerc == -2){
                    InfasuratoareConvexa.punctInCerc();
                }
                else
                if(Infasuratoare.punctInCerc == -1){
                    if(Infasuratoare.punctInInfasuratoare == -2){
                        InfasuratoareConvexa.punctInInfasuratoare();
                    }
                    else
                    if(Infasuratoare.punctInInfasuratoare == -1){
                        if(Infasuratoare.punctInDreptunghiuri == -2){
                            InfasuratoareConvexa.punctInDreptunghiuri();
                        }
                    }
                }
                
                
            }
        });
        add(Verificare);
        
        JButton Clear = new JButton("Clear");
        Clear.setPreferredSize( new Dimension(100, 30));
        Clear.setEnabled(true);
        Clear.setVisible(true);
        Clear.setBounds(100, 250, 100, 30);
        Clear.setBackground(Color.BLUE);
        Clear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Infasuratoare.a.clear();
                Infasuratoare.PrimescPuncte = true;
                Infasuratoare.GasireInfasuratoare = true;
                Infasuratoare.s.clear();
                Infasuratoare.pasInfasuratoare = 0;
                Infasuratoare.punctInCerc = -2;
                Infasuratoare.punctInInfasuratoare = -2;
                Infasuratoare.infasurareCercuri = false;
                Infasuratoare.punctInDreptunghiuri = -2;
                Infasuratoare.ss.clear();
                labelCercuri(Infasuratoare.punctInCerc);
                labelInfasuratoare(Infasuratoare.punctInInfasuratoare);
                labelDreptunghiuri(Infasuratoare.punctInDreptunghiuri);
                Panou.P.repaint();
            }
        });
        add(Clear);
        
        JButton Exit = new JButton("Exit");
        Exit.setPreferredSize( new Dimension(100, 30));
        Exit.setEnabled(true);
        Exit.setVisible(true);
        Exit.setBounds(100, 600, 100, 50);
        Exit.setBackground(Color.YELLOW);
        Exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    System.exit(0);
            }
        });
        add(Exit);
        
        label1 = new JLabel("Pas1: Verificam discurile.", SwingConstants.CENTER);
        label1.setVisible(true);
        label1.setHorizontalTextPosition(JLabel.CENTER);
        label1.setVerticalTextPosition(JLabel.BOTTOM);
        label1.setBounds(25, 350, 250, 30);
        label1.setForeground(Color.black);
        label1.setBackground(Color.GRAY);
        label1.setOpaque(true);
        add(label1);
        
        label2 = new JLabel("Pas2: Verificam interiorul infasuratorii.", SwingConstants.CENTER);
        label2.setVisible(true);
        label2.setHorizontalTextPosition(JLabel.CENTER);
        label2.setVerticalTextPosition(JLabel.BOTTOM);
        label2.setBounds(25, 400, 250, 30);
        label2.setForeground(Color.black);
        label2.setBackground(Color.GRAY);
        label2.setOpaque(true);
        add(label2);
        
        label3 = new JLabel("Pas3: Verificam dreptunghiurile.", SwingConstants.CENTER);
        label3.setVisible(true);
        label3.setHorizontalTextPosition(JLabel.CENTER);
        label3.setVerticalTextPosition(JLabel.BOTTOM);
        label3.setBounds(25, 450, 250, 30);
        label3.setForeground(Color.black);
        label3.setBackground(Color.GRAY);
        label3.setOpaque(true);
        add(label3);
    }
    
    void labelCercuri(int i){
        if(i == -2){
            label1.setText("Pas1: Verificam discurile.");
        }
        else{
            if(i == -1){
                label1.setText("Punctul nu apartine nici unui disc!");
            }
            else{
                label1.setText("Punctul apartine unui disc!");
            } 
        }
    }
    
    void labelInfasuratoare(int i){
        if(i == -2){
            label2.setText("Pas2: Verificam interiorul infasuratorii.");
        }
        else{
            if(i == -1){
                label2.setText("Punctul nu este in interiorul infasuratorii!");
            }
            else{
                label2.setText("Punctul este in interiorul infasuratorii!");
            } 
        }
    }
    
    void labelDreptunghiuri(int i){
        if(i == -2){
            label3.setText("Pas3: Verificam dreptunghiurile.");
        }
        else{
            if(i == -1){
                label3.setText("Punctul nu apartine nici unui dreptunghi!");
            }
            else{
                label3.setText("Punctul apartine unui dreptunghi!");
            } 
        }
    }
}
