import JeuTuilesGenerique.Modele.*;

public class PartieDominos extends Partie {

    public PartieDominos(Joueurs joueurs, Plateau plateau, Pioche pioche) {
        super(joueurs, plateau, pioche);
    }

    public boolean check(Tuile t, int x, int y) {
        // TODO préciser fonction check
        return false;
    }
}
