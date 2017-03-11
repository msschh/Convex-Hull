package infasuratoare;

import static java.lang.Math.abs;
import static java.lang.Math.sqrt;
import java.util.*;

public class InfasuratoareConvexa {
    
    static public Punct px = new Punct(); 
    
    static double delta(Punct p, Punct q, Punct r) {
        /*
        |q.x - p.x   r.x - p.x| - matricea
        |q.y - p.y   r.y - p.y|
        */
        double x = (q.x - p.x) * (r.y - p.y) - (r.x - p.x) * (q.y - p.y);
        return x;
    }
	 
	
    static void solve() {

        int k = 0;  // presupunem ca primul se afla
        for (int i = 1; i < Infasuratoare.a.size(); ++i) {
            if (Infasuratoare.a.get(k).x > Infasuratoare.a.get(i).x) {
                k = i;
            } else if (Infasuratoare.a.get(k).x == Infasuratoare.a.get(i).x && Infasuratoare.a.get(k).y < Infasuratoare.a.get(i).y) {
                k = i;
            }
        }

        Punct paux = new Punct();
        paux.x = Infasuratoare.a.get(0).x;
        paux.y = Infasuratoare.a.get(0).y;
        Infasuratoare.a.get(0).x = Infasuratoare.a.get(k).x;
        Infasuratoare.a.get(0).y = Infasuratoare.a.get(k).y;
        Infasuratoare.a.get(k).x = paux.x;
        Infasuratoare.a.get(k).y = paux.y;

        px.x = Infasuratoare.a.get(0).x;
        px.y = Infasuratoare.a.get(0).y;

        Comparator compare = new PunctComparator();
        Collections.sort(Infasuratoare.a.subList(1, Infasuratoare.a.size()), compare);
        
        Infasuratoare.s.add(Infasuratoare.a.get(0));
        Infasuratoare.s.add(Infasuratoare.a.get(1));

        Panou.P.repaint();
    }
    
    static void creare(){
        if(Infasuratoare.pasInfasuratoare < Infasuratoare.a.size()){
            int sz = Infasuratoare.s.size();
            InfasuratoareConvexa.px.x = Infasuratoare.a.get(Infasuratoare.pasInfasuratoare).x;
            InfasuratoareConvexa.px.y = Infasuratoare.a.get(Infasuratoare.pasInfasuratoare).y;
            if (sz >= 2 && delta(Infasuratoare.s.get(sz - 2), Infasuratoare.s.get(sz - 1), InfasuratoareConvexa.px) >= 0) {    // viraj
                while (sz >= 2 && delta(Infasuratoare.s.get(sz - 2), Infasuratoare.s.get(sz - 1), InfasuratoareConvexa.px) >= 0) {
                    Infasuratoare.s.remove(Infasuratoare.s.size() - 1);
                    sz = Infasuratoare.s.size();
                    }
            }
            Infasuratoare.s.add(Infasuratoare.a.get(Infasuratoare.pasInfasuratoare));
            Panou.P.repaint();
        }
        else
            if(Infasuratoare.pasInfasuratoare == Infasuratoare.a.size()){
                Infasuratoare.s.add(Infasuratoare.s.get(0));
                Panou.P.repaint();
            }
            else
                if(Infasuratoare.infasurareCercuri == false){
                    Infasuratoare.infasurareCercuri = true;
                    Infasuratoare.GasireInfasuratoare = false;
                    Infasuratoare.PrimescPuncte = true;
                    
                    for(int i = 0; i < Infasuratoare.s.size() - 1; i++){
                        Punct A = Infasuratoare.s.get(i);
                        Punct B = Infasuratoare.s.get(i + 1);
                        
                        double dx, dy, dist;
                        int x1, y1, x2, y2, x3, y3, x4, y4;
                        dx = A.x - B.x;
                        dy = A.y - B.y;
                        dist = sqrt(dx * dx + dy * dy);
                        dx /= dist;
                        dy /= dist;
                        
                        x1 = (int)(A.x + 10 * dy);
                        y1 = (int)(A.y - 10 * dx);
                        x2 = (int)(A.x - 10 * dy);
                        y2 = (int)(A.y + 10 * dx);
                        x3 = (int)(B.x + 10 * dy);
                        y3 = (int)(B.y - 10 * dx);
                        x4 = (int)(B.x - 10 * dy);
                        y4 = (int)(B.y + 10 * dx);
                        
                        if(delta(B, A, new Punct(x1, y1)) < 0){
                            Infasuratoare.ss.add(new Punct(x1, y1));
                        }
                        else{
                            Infasuratoare.ss.add(new Punct(x2, y2));
                        }
                        
                        if(delta(A, B, new Punct(x3, y3)) > 0){
                            Infasuratoare.ss.add(new Punct(x3, y3));
                        }
                        else{
                            Infasuratoare.ss.add(new Punct(x4, y4));
                        }
                    }
                    
                    Panou.P.repaint();
                }
    }
    
    static void punctInCerc(){
        Infasuratoare.punctInCerc = -1;
        for(int i = 0; i < Infasuratoare.s.size() - 1; i++){
            if(sqrt((Infasuratoare.s.get(i).x - Infasuratoare.punctDeVerificat.x) * (Infasuratoare.s.get(i).x - Infasuratoare.punctDeVerificat.x) + (Infasuratoare.s.get(i).y - Infasuratoare.punctDeVerificat.y) * (Infasuratoare.s.get(i).y - Infasuratoare.punctDeVerificat.y)) <= 10){
                Infasuratoare.punctInCerc = i;
            }
        }
        Panou.M.labelCercuri(Infasuratoare.punctInCerc);
        Panou.P.repaint();
    }
    
    static void punctInInfasuratoare(){
        Infasuratoare.punctInInfasuratoare = 1;
        for(int i = 0; i < Infasuratoare.s.size() - 1; i++){
            if(delta(Infasuratoare.s.get(i), Infasuratoare.s.get(i + 1), Infasuratoare.punctDeVerificat) > 0){
                Infasuratoare.punctInInfasuratoare = -1;
            }
        }
        Panou.M.labelInfasuratoare(Infasuratoare.punctInInfasuratoare);
        Panou.P.repaint();
    }
    
    static void punctInDreptunghiuri(){
        int i = 0, j = 0;
        Punct A, B, C, D;
        Infasuratoare.punctInDreptunghiuri = -1;
        while(i < Infasuratoare.s.size() - 1){
            A = Infasuratoare.s.get(i);
            B = Infasuratoare.s.get(i + 1);
            C = Infasuratoare.ss.get(j);
            D = Infasuratoare.ss.get(j + 1);
            
            double S1 = abs(delta(A, B, C)) / 2;
            double S2 = abs(delta(A, C, D)) / 2;
            double S3 = abs(delta(A, B, D)) / 2;
            double S4 = abs(delta(B, C, D)) / 2;
            if (S1 == abs(delta(A, B, Infasuratoare.punctDeVerificat)) / 2 + abs(delta(A, C, Infasuratoare.punctDeVerificat)) / 2 + abs(delta(B, C, Infasuratoare.punctDeVerificat)) / 2) {
                Infasuratoare.punctInDreptunghiuri = i;
            }
            if (S2 == abs(delta(A, C, Infasuratoare.punctDeVerificat)) / 2 + abs(delta(A, D, Infasuratoare.punctDeVerificat)) / 2 + abs(delta(C, D, Infasuratoare.punctDeVerificat)) / 2) {
                Infasuratoare.punctInDreptunghiuri = i;
            }
            if (S3 == abs(delta(A, B, Infasuratoare.punctDeVerificat)) / 2 + abs(delta(A, D, Infasuratoare.punctDeVerificat)) / 2 + abs(delta(B, D, Infasuratoare.punctDeVerificat)) / 2) {
                Infasuratoare.punctInDreptunghiuri = i;
            }
            if (S4 == abs(delta(B, C, Infasuratoare.punctDeVerificat)) / 2 + abs(delta(B, D, Infasuratoare.punctDeVerificat)) / 2 + abs(delta(C, D, Infasuratoare.punctDeVerificat)) / 2) {
                Infasuratoare.punctInDreptunghiuri = i;
            }
            i++;
            j += 2;
        }
        Panou.M.labelDreptunghiuri(Infasuratoare.punctInDreptunghiuri);
        Panou.P.repaint();
    }
}

class PunctComparator implements Comparator<Punct>
{
    @Override
    public int compare(Punct a, Punct b) {
        return (int)InfasuratoareConvexa.delta(InfasuratoareConvexa.px, a, b);
    }
}
