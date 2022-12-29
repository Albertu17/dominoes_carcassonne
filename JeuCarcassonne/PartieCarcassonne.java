package JeuCarcassonne;

import JeuTuilesGenerique.Modele.*;

public class PartieCarcassonne extends Partie {

    public PartieCarcassonne(Joueurs joueurs, Plateau plateau, Pioche pioche) {
        super(joueurs, plateau, pioche);
    }
    
    public PartieCarcassonne(String nomPartie){
        this.nomPartie = nomPartie ;

        Pioche pioche = new Pioche(72);
        Joueurs joueurs = new Joueurs(2);
        Plateau plateau = new Plateau(5,5);


        nouvelleTuileAjouer();
    }

    public boolean check(Tuile t, int x, int y) {
        // TODO préciser fonction check
        return false;
    }  
}
