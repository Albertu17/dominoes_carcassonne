package JeuDominos;

import JeuTuilesGenerique.Modele.Pioche;

public class PiocheDominos extends Pioche {
    
    public PiocheDominos() {
        super(); // Création d'une arraylist de tuiles.
        for (int i = 0; i < 72; i++) {
            pioche.add(new TuileDomino()); // Ajoute domino construit au hasard.
        }
    }
}
