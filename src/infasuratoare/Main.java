package infasuratoare;

import java.util.ArrayList;

public class Main{
    
    public static void main(String[] args) {
        Display D = new Display();
        D.setVisible(true);
    }
}

class Infasuratoare{
    public static ArrayList<Punct> a = new ArrayList<>();
    public static boolean PrimescPuncte = true;
    public static boolean GasireInfasuratoare = true;
    public static ArrayList<Punct> s = new ArrayList<>();
    public static int pasInfasuratoare = 0;
    public static Punct punctDeVerificat;
    public static int punctInCerc = -2;
    public static int punctInInfasuratoare = -2;
    public static boolean infasurareCercuri = false;
    public static ArrayList<Punct> ss = new ArrayList<>();
    public static int punctInDreptunghiuri = -2;
}