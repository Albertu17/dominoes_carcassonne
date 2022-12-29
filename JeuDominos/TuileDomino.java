import JeuTuilesGenerique.Modele.Tuile;
import java.util.concurrent.ThreadLocalRandom;

public class TuileDomino extends Tuile {
    
    public TuileDomino() {
        nord = new BordDomino(ThreadLocalRandom.current().nextInt(0, 3), ThreadLocalRandom.current().nextInt(0,3), ThreadLocalRandom.current().nextInt(0,3));
        est = new BordDomino(ThreadLocalRandom.current().nextInt(0, 3), ThreadLocalRandom.current().nextInt(0,3), ThreadLocalRandom.current().nextInt(0,3));
        sud = new BordDomino(ThreadLocalRandom.current().nextInt(0, 3), ThreadLocalRandom.current().nextInt(0,3), ThreadLocalRandom.current().nextInt(0,3));
        ouest = new BordDomino(ThreadLocalRandom.current().nextInt(0, 3), ThreadLocalRandom.current().nextInt(0,3), ThreadLocalRandom.current().nextInt(0,3));
    }
}
