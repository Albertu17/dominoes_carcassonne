package JeuDominos;

import JeuTuilesGenerique.Modele.Tuile;
import java.util.concurrent.ThreadLocalRandom;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.*;

public class TuileDomino extends Tuile  {

        
    public TuileDomino(boolean GUI) {
        // Création des bords dont les numéros sont pris au hasard.
        nord = new BordDomino(randNum(), randNum(), randNum());
        est = new BordDomino(randNum(), randNum(), randNum());
        sud = new BordDomino(randNum(), randNum(), randNum());
        ouest = new BordDomino(randNum(), randNum(), randNum());


        // Création du rendu visuel de la tuile domino
        if (GUI){

            setLayout(new GridLayout(5,5));
            int[] casesVides = {0, 4, 6, 7, 8, 11, 12, 13, 16, 17, 18, 20, 24};
            for (int i = 0; i < casesVides.length; i++) {
                JPanel panelVide = new JPanel();
                panelVide.setBackground(Color.BLACK);
            }
            add(caseNumero(nord.n1), 2);
            add(caseNumero(nord.n2), 3);
            add(caseNumero(nord.n3), 4);
            add(caseNumero(ouest.n1), 6);
            add(caseNumero(ouest.n2), 11);
            add(caseNumero(ouest.n3), 16);
            add(caseNumero(est.n1), 10);
            add(caseNumero(est.n2), 15);
            add(caseNumero(est.n3), 20);
            add(caseNumero(sud.n1), 22);
            add(caseNumero(sud.n2), 23);
            add(caseNumero(sud.n3), 24);
        }
    }

    // Renvoie un JPanel designé pour accueillir un numéro
    public JPanel caseNumero(int num) {
        JPanel caseNumero = new JPanel();
        caseNumero.setLayout(new BorderLayout());
        caseNumero.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        JLabel etiquette = new JLabel(String.valueOf(num));
        caseNumero.add(etiquette, BorderLayout.CENTER);
        etiquette.setHorizontalAlignment(0);
        return caseNumero;
    }

    // Retourne un numéro pris au hasard entre 1 et 4 exclu.
    public int randNum() {
        return ThreadLocalRandom.current().nextInt(1, 4);
    }
}

