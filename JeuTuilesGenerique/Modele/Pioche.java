package JeuTuilesGenerique.Modele;
import java.util.*;

public class Pioche {
    
    List<Tuile> pioche;

    public Pioche(int n) {
        pioche = new ArrayList<Tuile>() ;
    }

    // renvoie un élément de la pioche, ou null si cette dernière est vide.
    public Tuile pickOne(){
        if (! pioche.isEmpty()) return pioche.remove(0);
        return null ;
    }


    public void add(Tuile tuile){
        pioche.add(tuile) ;
        Collections.shuffle(pioche);
    }

    public boolean isEmpty() {
        return pioche.isEmpty();
    }

}
