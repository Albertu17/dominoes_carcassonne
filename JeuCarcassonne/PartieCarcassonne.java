package JeuCarcassonne;

import JeuTuilesGenerique.Modele.*;

public class PartieCarcassonne extends Partie implements Jouable {

    public PartieCarcassonne(Joueurs joueurs, Plateau plateau, Pioche pioche) {
        super(joueurs, plateau, pioche);
    }

    @Override
    public boolean check(Tuile t, int x, int y) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean jouer(Tuile t, int x, int y) {
        // TODO Auto-generated method stub
        return false;
    }

    public boolean partieFinie() {
        return pioche.isEmpty();
    }
    
}
