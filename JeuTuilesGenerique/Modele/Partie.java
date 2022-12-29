package JeuTuilesGenerique.Modele;

import java.io.Serializable;

public class Partie implements Serializable {

    public Joueurs joueurs;
    public Plateau plateau;
    public Pioche pioche;
    public Tuile aJouer;

    public Partie(Joueurs joueurs, Plateau plateau, Pioche pioche) {
        this.joueurs = joueurs;
        this.plateau = plateau;
        this.pioche = pioche;
        nouvelleTuileAjouer();
    }

    public void nouvelleTuileAjouer() {
        aJouer = pioche.pickOne();
        if (aJouer == null) aJouer = new Tuile();
    }

    // Fonction adaptée à un jeu dans les extensions de cette classe.
    public boolean check(Tuile t, int x, int y) {
        return true;
    }

    public void jouer(Tuile t, int x, int y) {
        if (check(t, x, y)) plateau.plateau[x][y] = t;
    }

    public boolean partieFinie() {
        return pioche.pioche.isEmpty();
    }
    
}