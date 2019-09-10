package Opdracht13_1;

import java.awt.*;
import java.applet.*;
import java.awt.event.*;
import java.util.*;

public class EenKaart extends Applet {
    String[] kleur = {"Schoppen", "Klaver", "Harten", "Ruiten"};
    String[] kaart = {"Aas", "Twee", "Drie", "Vier", "Vijf", "Zes", "Zeven", "Acht", "Negen", "Tien", "Boer", "Vrouw", "Heer"};
    Button knop;
    Random random;
    String outputKleur;
    String outputKaart;

    public void init() {
        random = new Random();
        //knop
        knop = new Button("Deel kaart");
        KnopListener kl = new KnopListener();
        knop.addActionListener(kl);
        add(knop);

        outputKleur = "";
        outputKaart = "";
    }

    public void paint(Graphics g) {
        g.drawString(outputKleur + " " + outputKaart, 50, 60);
    }

    class KnopListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            outputKleur = kleur[random.nextInt(kleur.length)];
            outputKaart = kaart[random.nextInt(kaart.length)];
            repaint();
        }
    }
}
