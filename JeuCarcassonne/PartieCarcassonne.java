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
}
