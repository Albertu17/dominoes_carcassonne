package JeuDominos;

import java.nio.file.FileStore;

import JeuTuilesGenerique.Modele.*;

public class PartieDominos extends Partie {

    public PartieDominos(Joueurs joueurs, Plateau plateau, Pioche pioche, String nomPartie) {
        super(joueurs, plateau, pioche, nomPartie);
    }
    
    public PartieDominos(String nomPartie){
        super(nomPartie) ;
        pioche = new PiocheDominos(false);
        nouvelleTuileAjouer();
    }
    public PartieDominos(String nomPartie, boolean GUI){
        super(nomPartie) ;
        pioche = new PiocheDominos(GUI);
        nouvelleTuileAjouer();
        plateau.plateau[plateau.largeur /2][plateau.hauteur/2] = pioche.pickOne() ;
    }
}
