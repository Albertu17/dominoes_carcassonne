import java.util.ArrayList;
import java.util.List;
import java.util.function.LongUnaryOperator;

public class Pioche {
    private List<Tuiles> pioche;


    // creer (nombreTuiles) de façon aléatoire 
    Pioche(int nombreTuiles){
        pioche = new ArrayList<Tuiles>() ;
        while (nombreTuiles != 0){
            int[][] tab = new int[4][] ;
            for (int i = 0; i < tab.length; i){
                tab[i] = tab_random(4, 5) ;
            }
            pioche.add(new Tuiles(tab)) ;

            nombreTuiles-- ;
        }
    }

    // creer un tableau d'entier aléatoire entre 0 et (maxRandom),  de longeur (longeurTab)
    public int[] tab_random(int longeurTab, int maxRandom){
        int[] tab = new int[longeurTab] ;
        for (int i = 0; i < tab.length; i++){
            tab[i] = (int) Math.random() * (maxRandom) ;
        }

        return tab ;
    }
}
