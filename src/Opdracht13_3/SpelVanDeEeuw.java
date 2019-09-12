package Opdracht13_3;

import java.awt.*;
import java.applet.*;
import java.awt.event.*;
import java.net.URL;

public class SpelVanDeEeuw extends Applet {
    TextField tekstvak;
    Button knop;
    Label label;
    Image afbeelding, winpic, losepic;
    URL pad;
    int cookiemonsters = 23;
    int invoer, speler, pc;
    String errorTekst, computerTekst, win, lose, teller;

    public void init() {
        setSize(600, 500);
        pad = getClass().getResource("/resources/");
        afbeelding = getImage(pad, "Cookiemonster1.png");
        winpic = getImage(pad, "win.png");
        losepic = getImage(pad, "lose.png");

        //tekstvak
        label = new Label("How many Cookie Monsters do you want to eat this turn? (1, 2 or 3)");
        tekstvak = new TextField("", 10);
        errorTekst = "";
        computerTekst = "";
        win = "";
        lose = "";
        teller = "";

        //knop
        knop = new Button("Nom Nom");
        KnopListener kl = new KnopListener();
        knop.addActionListener(kl);

        add(label);
        add(tekstvak);
        add(knop);
    }

    public void paint(Graphics g) {
        int x = 30;
        int y = 100;

        for (int i = 0; i < cookiemonsters; i++) {
            g.drawImage(afbeelding, x, y, 50, 50, this);
            x += 60;
            if (i % 4 == 3) {
                x = 30;
                y += 60;
            }
        }
        if (cookiemonsters <= 0) {
            g.drawImage(winpic, 30, 100, 200, 200, this);
        }
        if (cookiemonsters == 1) {
            g.drawImage(losepic, 30, 100, 200, 200, this);
        }
        g.drawString(teller, 25, 60);
        g.drawString(computerTekst, 25, 40);
        g.setColor(Color.green);
        g.drawString(win, 25, 60);
        g.setColor(Color.red);
        g.drawString(lose, 25, 60);
        g.drawString(errorTekst, 25, 40);
    }

    class KnopListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            invoer = Integer.parseInt(tekstvak.getText());
            errorTekst = "";
            if (invoer < 4 && invoer > 0) {
                speler = invoer;
                cookiemonsters -= speler;
            } else {
                errorTekst = "Ongeldig getal";
            }

            pc = computer();
            cookiemonsters -= pc;
            computerTekst = "The computer ate " + pc + " Cookie Monsters";
            teller = "There's " + cookiemonsters + " left to be eaten";

            if (cookiemonsters <= 0) {
                win = "Congrats, you ate all the Cookie Monsters, you brutal murderer";
                lose = "";
                teller = "";
            }
            if (cookiemonsters == 1) {
                lose = "Rip, the Cookie Monsters took their revenge on you and used you as side dish for their dinner";
                win = "";
                teller = "";
            }
            repaint();
        }
    }

    int computer() {
        int aantal = 0;
        int computer;
        computer = cookiemonsters % 4;
        int randomGetal;
        double random;
        random = Math.random();
        randomGetal = (int) (random * 3 + 1);

        if (computer == 0) {
            aantal = 3;
        }
        if (computer == 1) {
            aantal = randomGetal;
        }
        if (computer == 2) {
            aantal = 1;
        }
        if (computer == 3) {
            aantal = 2;
        }
        return aantal;
    }
}