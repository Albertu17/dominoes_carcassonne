package Modele.Commun;
import java.util.ArrayList;
import java.util.List;

public class Pioche {
    private List<Tuiles> pioche;



    Pioche(){
        pioche = new ArrayList<Tuiles>() ;
    }

    Pioche(boolean Carcassonne){
        this() ;

        if (Carcassonne){
            for(int i = 0 ; i < 72 ; i++){
                // ajout des caractéristique des Tuiles routes...
                // pioche.add() ;
            }
        }else{
            int nombreTuiles = 72 ;
            
            while (nombreTuiles != 0){
                int[][] tab = new int[4][] ;
                for (int i = 0; i < tab.length; i++){
                    tab[i] = tab_random(3, 3) ;
                }
                pioche.add(new Tuiles(tab)) ;

                nombreTuiles-- ;
            }
        }
    }


     // creer (nombreTuiles) de façon aléatoire 
    Pioche(int nombreTuiles){
        this() ;
        while (nombreTuiles != 0){
            int[][] tab = new int[4][] ;
            for (int i = 0; i < tab.length; i++){
                tab[i] = tab_random(3, 3) ;
            }
            pioche.add(new Tuiles(tab)) ;

            nombreTuiles-- ;
        }
    }

    // creer un tableau d'entier aléatoire entre 0 et (maxRandom),  de longeur (longeurTab)
    public int[] tab_random(int longeurTab, int maxRandomInclut){
        int[] tab = new int[longeurTab] ;
        for (int i = 0; i < tab.length; i++){
            tab[i] = (int) (Math.random() * (maxRandomInclut +1 ) ) ;
        }

        return tab ;
    }

    // renvoie un élément de la pioche, si la pioche est vide renvoie null
    public Tuiles pickOne(){
        if (! pioche.isEmpty()){
            return pioche.remove(0) ;
        }
        return null ;
    }
}
