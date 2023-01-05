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
            int[] contenuCases = new int[] {-1, nord.n1, nord.n2, nord.n3, -1,
                                            ouest.n1, -1, -1, -1, est.n1, 
                                            ouest.n2, -1, -1, -1, est.n2, 
                                            ouest.n3, -1, -1, -1, est.n3, 
                                            -1, sud.n1, sud.n2, sud.n3, -1,
                                            };
            for (int i = 0; i < 25; i++) {
                if (contenuCases[i] == -1) {
                    JPanel panelVide = new JPanel();
                    panelVide.setBackground(Color.BLACK);
                    add(panelVide);
                } else add(caseNumero(contenuCases[i]));
            }
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

