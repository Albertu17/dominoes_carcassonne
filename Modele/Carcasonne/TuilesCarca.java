package Modele.Carcasonne;

public class TuilesCarca extends Tuiles {
    private Face[] allface ;

    TuilesDomino(){
        allface = new Face[4] ;
        for (int i = 0; i < allface.length; i++){
            allface[i] = new Face(tab_random(3, 3)) ;
        }
        
    }

    public int[] tab_random(int longeurTab, int maxRandomInclut){
        int[] tab = new int[longeurTab] ;
        for (int i = 0; i < tab.length; i++){
            tab[i] = (int) (Math.random() * (maxRandomInclut +1 ) ) ;
        }

        return tab ;
    }
}
