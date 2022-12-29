import JeuTuilesGenerique.Modele.Tuile;
import java.util.concurrent.ThreadLocalRandom;

public class TuileDomino extends Tuile  {
    
    // Création d'une tuile dont les numéros des 4 bords sont pris au hasard.
    public TuileDomino() {
        nord = new BordDomino(randNum(), randNum(), randNum());
        est = new BordDomino(randNum(), randNum(), randNum());
        sud = new BordDomino(randNum(), randNum(), randNum());
        ouest = new BordDomino(randNum(), randNum(), randNum());
    }

    // Retourne un numéro au hasard entre 0 et 3 exclu.
    public int randNum() {
        return ThreadLocalRandom.current().nextInt(0, 3);
    }
}
