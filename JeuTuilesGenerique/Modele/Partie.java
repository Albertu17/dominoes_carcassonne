package JeuTuilesGenerique.Modele;

import java.io.Serializable;

public class Partie implements Serializable {

    Joueur[] joueurs;
    Plateau plateau;
    Pioche pioche;

    public Partie(Joueur[] joueurs, Plateau plateau, Pioche pioche) {
        this.joueurs = joueurs;
        this.plateau = plateau;
        this.pioche = pioche;
    }
}