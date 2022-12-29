package JeuCarcassonne;

import java.io.Serializable;

import JeuTuilesGenerique.Modele.*;

public class PartieCarcassonne extends Partie{

    public PartieCarcassonne(Joueurs joueurs, Plateau plateau, Pioche pioche) {
        super(joueurs, plateau, pioche);
    }
    
    public PartieCarcassonne(String nomPartie){
        super(nomPartie) ;

        pioche = new Pioche(72);
        

        nouvelleTuileAjouer();
    }

    public boolean check(Tuile t, int x, int y) {
        // TODO préciser fonction check
        return false;
    }  
}
