package JeuDominos;

import JeuTuilesGenerique.Modele.Partie;
import JeuTuilesGenerique.Vue.GameView;

public class VueDominos extends GameView {

    public VueDominos(Partie partie) {
        this.partie = partie ;

        // TODO ajouter spécificités VueDomino
    }

    public void setGameView(){
        super.setGameView();

        super.titre.setText("Jeu de Dominos");
    }
    
}

