package JeuDominos;

import java.util.ArrayList;
import java.util.List;

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

    // Retourne le nombre de points que donnerait le placement de la tuileAJouer aux coordonnées x,y.
    public int nbPoints(int x, int y){
        int pts = 0; 
        if (getBordAlEst(x,y) != null) pts += ((BordDomino)aJouer.est).sommeBords();
        if (getBordAlOuest(x,y) != null) pts += ((BordDomino)aJouer.ouest).sommeBords();
        if (getBordAuNord(x,y) != null) pts += ((BordDomino)aJouer.nord).sommeBords();
        if (getBordAuSud(x,y) != null) pts += ((BordDomino)aJouer.sud).sommeBords();
        return pts ;
    }

    public String cheminDossierSauvegardes() {
        return "Domino/";
    }

    // Pour DominoTerminal
    public boolean recursiveIA (int x, int y, List<Tuile> tuilesTestees) {
        if (tuilesTestees.contains(plateau.plateau[x][y])) return false ;
        // Pour ne pas tester 2 fois la même tuile et donc tomber dans une boucle infinie.
        tuilesTestees.add(plateau.plateau[x][y]) ;
        // Teste si on peut passer la tuileAJouer aux coordonnées passées en argument dans les 4 sens possibles.
        for (int i = 0; i < 4; i++) {
            if (gui != null) {
                jouer(x, y);
                return true;
            }
            else if (jouerTerminal(x, y)) return true;
            aJouer.rotate(true);
        }
        // recursif sur tuiles adjacentes :
        if (x != 1 && recursiveIA(x-1, y, tuilesTestees)) return true;
        if (x != plateau.hauteur-2 && recursiveIA(x+1, y, tuilesTestees)) return true;
        if (y != 1 && recursiveIA(x, y-1, tuilesTestees)) return true;
        if (y != plateau.largeur-2 && recursiveIA(x, y+1, tuilesTestees)) return true;
        return false;
    }

    // Pour DominoTerminal
    public boolean jouerTerminal(int x, int y){
        if (check(x, y)) { 
            // l'appel de nbPoints à besoin d'etre mis avant l'ajout de la tuile au plateau.
            // car les coordonnées de la tuile peuvent changer si le plateau devient plus grand 
            joueurs.joueurAuTrait().addScore(nbPoints(x, y));
            plateau.add(aJouer, x, y);
            return true ; //besoin du boolean pour l'IA
        }
        return false ;
    }
    
    // Pour DominoTerminal
    public void gestionTourIA(){
        // empêche le joueur de jouer à la place de l'IA
        gui.getRotationDroite().setEnabled(false);
        gui.getRotationGauche().setEnabled(false);
        gui.getDefausser().setEnabled(false);

        // lancement d'un thread timer (en background) pour ne pas bloquer l'interface
        gui.getRotationDroite().setEnabled(true);
        gui.getRotationGauche().setEnabled(true);
        gui.getDefausser().setEnabled(true);
        tourIA();
    }

    public void tourIA() {
        recursiveIA(plateau.largeur/2, plateau.hauteur/2, new ArrayList<Tuile>()) ; 
        // Pour DominoTerminal
        if (gui != null) tourSuivant();
    }
}
