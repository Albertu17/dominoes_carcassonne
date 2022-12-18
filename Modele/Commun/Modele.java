package Modele.Commun;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Modele implements Serializable{
    List<Joueur> Players ; 
    Plateau plateau ;
    Pioche pioche ;

    

    Modele(boolean Carcassonne){
        Players = new ArrayList<Joueur>() ;
        plateau = new Plateau() ; 

        pioche = new Pioche(Carcassonne) ;
        
    }

    public boolean addPlayer(Joueur playeur){
        if (Players.size() <= 6 ){
            Players.add(playeur) ;
            return true ;
        }
        return false ;
    }


    // test si la tuiles peut-être possé, la pose dans le cas échéans, et rajoute les points
    // public boolean addTuiles(Joueur player,  int NumeroDeTuiles, int x, int y ){
        
    // }








    
    
}
