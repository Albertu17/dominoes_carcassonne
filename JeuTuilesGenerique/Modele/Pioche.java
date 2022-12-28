package JeuTuilesGenerique.Modele;
import java.util.*;


public class Pioche {
    private List<Tuile> pioche;


    Pioche(){
        pioche = new ArrayList<Tuile>() ;
    }

    Pioche(boolean Carcassonne){
        this() ;

        if (Carcassonne){
            for(int i = 0 ; i < 72 ; i++){
                // ajout des caractéristique des Tuile, routes...
                // pioche.add() ;
            }

            // permet de mélanger la pioche pour ne pas avoir toujours la même partie
            Collections.shuffle(pioche) ;
            
        }else{
            int nombreTuiles = 72 ;
            
            while (nombreTuiles != 0){
                pioche.add(new Tuile(false)) ;

                nombreTuiles-- ;
            }
        }

    
    }


     // creer (nombreTuiles) de façon aléatoire 
    public Pioche(int nombreTuiles){
        this() ;
        while (nombreTuiles != 0){
            pioche.add(new Tuile(false)) ;

            nombreTuiles-- ;
        }
    }


    // renvoie un élément de la pioche, si la pioche est vide renvoie null
    public Tuile pickOne(){
        if (! pioche.isEmpty()){
            return pioche.remove(0) ;
        }
        return null ;
    }

    public void add(Tuile tuile){
        pioche.add(tuile) ;
        Collections.shuffle(pioche);
    }
}
