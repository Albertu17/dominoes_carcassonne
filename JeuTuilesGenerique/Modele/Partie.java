package JeuTuilesGenerique.Modele;

import java.io.Serializable;

public class Partie implements Serializable {

    public Joueurs joueurs;
    public Plateau plateau;
    public Pioche pioche;

    public Partie(Joueurs joueurs, Plateau plateau, Pioche pioche) {
        this.joueurs = joueurs;
        this.plateau = plateau;
        this.pioche = pioche;
    }
}