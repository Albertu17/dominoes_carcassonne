package Modele.Commun;
import java.util.ArrayList;
import java.util.Collections;
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

            // permet de mélanger la pioche pour ne pas avoir toujours la même partie
            Collections.shuffle(pioche) ;
        }else{
            int nombreTuiles = 72 ;
            
            while (nombreTuiles != 0){
                pioche.add(new Tuiles(false)) ;

                nombreTuiles-- ;
            }
        }

    
    }


     // creer (nombreTuiles) de façon aléatoire 
    public Pioche(int nombreTuiles){
        this() ;
        while (nombreTuiles != 0){
            pioche.add(new Tuiles(false)) ;

            nombreTuiles-- ;
        }
    }


    // renvoie un élément de la pioche, si la pioche est vide renvoie null
    public Tuiles pickOne(){
        if (! pioche.isEmpty()){
            return pioche.remove(0) ;
        }
        return null ;
    }
}
