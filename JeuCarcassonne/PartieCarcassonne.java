package JeuCarcassonne;

import java.util.List;

import JeuTuilesGenerique.Modele.*;
import JeuCarcassonne.TuileCarcassonne;

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

    public String cheminDossierSauvegardes() {
        return "Carcassonne/";
    }
    
    // Vérifie si une tuile est plaçable, la place le cas échéant et prépare le tour suivant.
    public void jouer(int x, int y) {
        if (check(x, y)) { 
            // l'appel de nbPoints à besoin d'être mis avant l'ajout de la tuile au plateau
            // car les coordonnées de la tuile peuvent changer si le plateau devient plus grand.
            joueurs.joueurAuTrait().addScore(nbPoints(x, y));
    
            // aggrandit le plateau si la tuile est placée en bordure de la grille du GUI.
            if (plateau.add(aJouer, x, y)) gui.repaintGrille();
            else gui.updateGrille(aJouer, x, y);
            // Spécificité Jeu Carcassonne
            if(!joueurs.joueurAuTrait().isIA() && joueurs.joueurAuTrait().getNbrPion() != 0){
                ((VueCarcassonne)gui).demanderSiPosePion();
                return ;
            }
            if (!joueurs.joueurAuTrait().isIA()) tourSuivant() ;
        }
    }

    public boolean recursiveIA (int x, int y, List<Tuile> tuilesTestees) {
        if (tuilesTestees.contains(plateau.plateau[x][y])) return false ;
        // Pour ne pas tester 2 fois la même tuile et donc tomber dans une boucle infinie.
        tuilesTestees.add(plateau.plateau[x][y]) ;
        // Teste si on peut passer la tuileAJouer aux coordonnées passées en argument dans les 4 sens possibles.
        for (int i = 0; i < 4; i++)  {
            if (check(x, y)) {
                // Spécificité Jeu Carcassonne
                ((TuileCarcassonne)aJouer).remettreImage();
                jouer(x, y);
                return true;
            }
            aJouer.rotate(true);
        }
        // recursif sur tuiles adjacentes :
        if (x != 1 && recursiveIA(x-1, y, tuilesTestees)) return true;
        if (x != plateau.hauteur-2 && recursiveIA(x+1, y, tuilesTestees)) return true;
        if (y != 1 && recursiveIA(x, y-1, tuilesTestees)) return true;
        if (y != plateau.largeur-2 && recursiveIA(x, y+1, tuilesTestees)) return true;
        return false;
    }
}
