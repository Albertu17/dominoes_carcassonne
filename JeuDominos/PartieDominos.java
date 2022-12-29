package JeuDominos;

import JeuTuilesGenerique.Modele.*;

public class PartieDominos extends Partie {

    public PartieDominos(Joueurs joueurs, Plateau plateau, Pioche pioche) {
        super(joueurs, plateau, pioche);
    }
    public PartieDominos(String nomPartie){
        super(nomPartie) ;

        pioche = new Pioche(72);
        

        nouvelleTuileAjouer();
    }

    public boolean check(Tuile t, int x, int y) {
        // TODO préciser fonction check
        return false;
    }
}
