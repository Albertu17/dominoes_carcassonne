package JeuCarcassonne;

import JeuTuilesGenerique.Modele.Bord;

public class TuileCarcassonne {
    
    Bord centre;

    public TuileCarcassonne(Bord nord, Bord est, Bord sud, Bord ouest, Bord centre) {
        super(nord, est, sud, ouest);
        this.centre = centre;
    }
}
