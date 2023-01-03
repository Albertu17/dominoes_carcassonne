package JeuCarcassonne;

import JeuTuilesGenerique.Modele.Partie;
import JeuTuilesGenerique.Vue.GameView;

public class VueCarcassonne extends GameView{

    public VueCarcassonne(Partie partie) {
        this.partie = partie;

        // TODO Ajouter spécificités conteneurInfos
    }

    public void setGameView(){
        super.setGameView();

        super.titre.setText("Jeu de Carcassonne");
    }
}
