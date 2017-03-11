/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package infasuratoare;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;

/**
 *
 * @author Alex
 */
public class Plan extends JPanel{
    
    public static final int WE = 700;
    public static final int HE = 700;
        
    Plan() {
            this.setVisible(true);
            this.setSize(WE, HE);
            this.setLocation(0, 0);
            this.setEnabled(true);
            this.setBackground(Color.WHITE);
            addMouseListener(new PointLocater());
    }
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(Color.black);
        g.drawLine(350, 0, 350, 700);
        g.drawLine(0, 350, 700, 350);

        g.setColor(Color.black);
        for (int i = 0; i < Infasuratoare.a.size(); ++i) {
            if(Infasuratoare.punctInCerc >= 0 && Infasuratoare.s.get(Infasuratoare.punctInCerc) == Infasuratoare.a.get(i)){
                g.setColor(Color.BLUE);
                g.fillOval(Infasuratoare.a.get(i).x, Infasuratoare.a.get(i).y, 20, 20);
                //g.drawLine(Infasuratoare.a.get(i).x + 10, Infasuratoare.a.get(i).y + 10, Infasuratoare.a.get(i).x + 10, Infasuratoare.a.get(i).y + 10);
                g.setColor(Color.black);
            }
            else{
                g.drawOval(Infasuratoare.a.get(i).x, Infasuratoare.a.get(i).y, 20, 20);
                g.drawLine(Infasuratoare.a.get(i).x + 10, Infasuratoare.a.get(i).y + 10, Infasuratoare.a.get(i).x + 10, Infasuratoare.a.get(i).y + 10);
            }
        }

        if(Infasuratoare.infasurareCercuri == false){
            if(Infasuratoare.PrimescPuncte == false || (Infasuratoare.PrimescPuncte == true && Infasuratoare.GasireInfasuratoare == false)){
                for (int i = Infasuratoare.s.size() - 1; i > 0 ; --i) {
                    g.drawLine(Infasuratoare.s.get(i).x + 10, Infasuratoare.s.get(i).y + 10, Infasuratoare.s.get(i - 1).x + 10, Infasuratoare.s.get(i - 1).y + 10);
                }
            }   
        }
        else{
            for (int i = Infasuratoare.ss.size() - 1; i > 0 ; --i) {
                g.drawLine(Infasuratoare.ss.get(i).x + 10, Infasuratoare.ss.get(i).y + 10, Infasuratoare.ss.get(i - 1).x + 10, Infasuratoare.ss.get(i - 1).y + 10);
                --i;
            }
        }

        g.setColor(Color.RED);
        if(Infasuratoare.PrimescPuncte == false && Infasuratoare.GasireInfasuratoare == false){
            g.drawOval(Infasuratoare.punctDeVerificat.x + 8, Infasuratoare.punctDeVerificat.y + 8, 4, 4);
        }
        g.setColor(Color.BLUE);
        if(Infasuratoare.punctInDreptunghiuri >=0){
            g.drawLine(Infasuratoare.s.get(Infasuratoare.punctInDreptunghiuri).x + 10, Infasuratoare.s.get(Infasuratoare.punctInDreptunghiuri).y + 10, Infasuratoare.s.get(Infasuratoare.punctInDreptunghiuri + 1).x + 10, Infasuratoare.s.get(Infasuratoare.punctInDreptunghiuri + 1).y + 10);
            g.drawLine(Infasuratoare.s.get(Infasuratoare.punctInDreptunghiuri).x + 10, Infasuratoare.s.get(Infasuratoare.punctInDreptunghiuri).y + 10, Infasuratoare.ss.get(2 * Infasuratoare.punctInDreptunghiuri).x + 10, Infasuratoare.ss.get(2 * Infasuratoare.punctInDreptunghiuri).y + 10);
            g.drawLine(Infasuratoare.ss.get(2 * Infasuratoare.punctInDreptunghiuri + 1).x + 10, Infasuratoare.ss.get(2 * Infasuratoare.punctInDreptunghiuri + 1).y + 10, Infasuratoare.s.get(Infasuratoare.punctInDreptunghiuri + 1).x + 10, Infasuratoare.s.get(Infasuratoare.punctInDreptunghiuri + 1).y + 10);
            g.drawLine(Infasuratoare.ss.get(2 * Infasuratoare.punctInDreptunghiuri).x + 10, Infasuratoare.ss.get(2 * Infasuratoare.punctInDreptunghiuri).y + 10, Infasuratoare.ss.get(2 * Infasuratoare.punctInDreptunghiuri + 1).x + 10, Infasuratoare.ss.get(2 * Infasuratoare.punctInDreptunghiuri + 1).y + 10);
        }
    }
}

class PointLocater extends MouseAdapter
{
    @Override
    public void mousePressed(MouseEvent e)
    {
        if(Infasuratoare.PrimescPuncte == true){
            if(Infasuratoare.GasireInfasuratoare == true){
                Point p = e.getPoint();
                Infasuratoare.a.add(new Punct((int)p.getX() - 10, (int)p.getY() - 10));
                Panou.P.repaint();
            }
            else{
                Infasuratoare.PrimescPuncte = false;
                Point p = e.getPoint();
                Infasuratoare.punctDeVerificat = new Punct((int)p.getX() - 10, (int)p.getY() - 10);
                Panou.P.repaint();
            }
        }
    }
}