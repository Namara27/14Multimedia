package Opdracht13_2;

import java.awt.*;
import java.applet.*;
import java.applet.AudioClip;
import java.util.*;
import java.awt.event.*;
import java.net.URL;

public class SpelKaarten extends Applet {
    String[] kleur = {"Schoppen", "Klaver", "Harten", "Ruiten"};
    String[] aantal = {"Aas", "Twee", "Drie", "Vier", "Vijf", "Zes", "Zeven", "Acht", "Negen", "Tien", "Boer", "Vrouw", "Heer"};
    Button knop;
    Random random1;
    String outputKleur, outputAantal;
    String[] deck = new String[52];
    String[] speler1 = new String[13];
    String[] speler2 = new String[13];
    String[] speler3 = new String[13];
    String[] speler4 = new String[13];
    int meh;
    AudioClip sound;

    public void init() {
        setSize(700, 550);
        random1 = new Random();

        URL pad = getClass().getResource("/resources/");
        sound = getAudioClip(pad, "Clowntoeter.wav");

        //knop
        knop = new Button("Deel kaart");
        KnopListener kl = new KnopListener();
        knop.addActionListener(kl);
        add(knop);

        for (int i = 0; i < 13; i++) {
            speler1[i] = "";
            speler2[i] = "";
            speler3[i] = "";
            speler4[i] = "";
        }

        outputAantal = "";
        outputKleur = "";
    }

    public void paint(Graphics g) {
        int y = 130;
        int y1 = 80;

        g.drawString("Speler 1", 40, 100);
        g.drawString("Speler 2", 200, 100);
        g.drawString("Speler 3", 360, 100);
        g.drawString("Speler 4", 520, 100);

        for (int i = 0; i < 13; i++) {
            g.drawString(speler1[i], 40, y);
            g.drawString(speler2[i], 200, y);
            g.drawString(speler3[i], 360, y);
            g.drawString(speler4[i], 520, y);
            y += 30;
        }

        for (int i = 0; i < 13; i++) {
            y1 += 30;
            g.drawLine(40, y1, 620, y1);
        }
    }

    class KnopListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            deck = new String[52];
            meh = 0;
            for (int i = 0; i < kleur.length; i++) {
                outputKleur = kleur[i];
                for (int j = 0; j < aantal.length; j++) {
                    outputAantal = aantal[j];
                    deck[meh] = outputKleur + " " + outputAantal;
                    meh++;
                }
            }
            for (int i = 0; i < 13; i++) {
                speler1[i] = deelKaart();
                speler2[i] = deelKaart();
                speler3[i] = deelKaart();
                speler4[i] = deelKaart();
            }
            sound.play();
            repaint();
        }
    }

    private String deelKaart() {
        int random = new Random().nextInt(deck.length);
        String kaart = deck[random];

        String[] hulpLijst = new String[deck.length - 1];
        int hulpindex = 0;
        for (int i = 0; i < deck.length; i++) {
            if (i != random) {
                hulpLijst[hulpindex] = deck[i];
                hulpindex++;
            }
        }
        deck = hulpLijst;
        return kaart;
    }
}