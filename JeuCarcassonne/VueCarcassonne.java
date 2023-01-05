package JeuCarcassonne;

import JeuTuilesGenerique.Modele.Partie;
import JeuTuilesGenerique.Vue.GameView;

public class VueCarcassonne extends GameView{

    public VueCarcassonne(Partie partie) {
        super(partie);
        super.titre.setText("Jeu de Carcassonne");
        // TODO Ajouter spécificités conteneurInfos
    }
}
