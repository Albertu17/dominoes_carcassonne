package JeuDominos;

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

    // Pour DominoTerminal
    public PartieDominos(String nomPartie, boolean GUI){
        super(nomPartie) ;
        pioche = new PiocheDominos(GUI);
        nouvelleTuileAjouer();
        plateau.plateau[plateau.largeur /2][plateau.hauteur/2] = pioche.pickOne() ;
    }

    public int nbPoint(int x, int y){
        int pts = 0 ; 
        if (getBordAlEst(x, y) != null ) pts += ((BordDomino)aJouer.est).sommeBord() ;
        if (getBordAlOuest(x, y) != null ) pts += ((BordDomino)aJouer.ouest).sommeBord() ;
        if (getBordAuNord(x, y) != null ) pts += ((BordDomino)aJouer.nord).sommeBord() ;
        if (getBordAuSud(x, y) != null ) pts += ((BordDomino)aJouer.sud).sommeBord() ;

        return pts ;
    }
}
