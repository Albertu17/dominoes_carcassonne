package JeuCarcassonne;

import JeuTuilesGenerique.Modele.*;

public class PartieCarcassonne extends Partie{

    public PartieCarcassonne(Joueurs joueurs, Plateau plateau, Pioche pioche, String nomPartie) {
        super(joueurs, plateau, pioche, nomPartie);
    }
    
    public PartieCarcassonne(String nomPartie){
        super(nomPartie) ;
        pioche = new Pioche(72);
        nouvelleTuileAjouer();
    }

    public int nbPoints(Tuile t, int x, int y){


        return 0 ;
    }
}
