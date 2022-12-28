package Modele.Commun;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Modele implements Serializable{
    List<Joueur> players ; 
    Plateau plateau ;
    Pioche pioche ;

    

    public Modele(boolean Carcassonne){
        players = new ArrayList<Joueur>() ;
        plateau = new Plateau() ; 

        pioche = new Pioche(Carcassonne) ;
        plateau.add(pioche.pickOne(), 0, 0) ;
        
    }

    public boolean addPlayer(Joueur playeur){
        if (players.size() <= 6 ){
            players.add(playeur) ;
            return true ;
        }
        return false ;
    }


    // test si la tuiles peut-être possé, la pose dans le cas échéans, et rajoute les points
    public boolean addTuiles(Joueur player,  int IdDeTuiles, int x, int y ){
        
        return false ;
    }

    public Joueur getPlayers(int index){return players.get(index) ;}
    public List<Joueur> getPlayers(){return players ;}








    
    
}
