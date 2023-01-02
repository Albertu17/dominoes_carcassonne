package JeuCarcassonne;

import javax.swing.JLabel;

import JeuTuilesGenerique.Modele.*;
import JeuTuilesGenerique.Vue.GameView;

public class VueCarcassonne extends GameView{

    public VueCarcassonne(Partie partie) {
        this.partie = partie;
        super.titre.setText("Jeu de Carcassonne");

        // TODO Ajouter spécificités conteneurInfos
    }
}
