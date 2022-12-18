package Modele.Commun;

import Modele.Domino.FaceDomino;
import Modele.Carcassonne.FaceCarcassonne;

public class Face {
    public  int[] getNumero(){
        return new int[0] ;
    } 
    public boolean compatible(FaceDomino f){
        return false ;
    }
    public boolean compatible(FaceCarcassonne f){
        return false ;
    }
}
