package Modele.Commun;
public class Plateau {
    private Tuiles[][] plateau;


    public Tuiles[][] getPlateau() {
        return plateau;
    }


    public Plateau(){

        // plateau  = new Tuiles[nombreTuiles+2][nombreTuiles+2] ;
        // ou 
        plateau = new Tuiles[1][1] ;
        
    }

    
    public boolean add(Tuiles tuile, int x, int y) {
        // creation d'un tableau plus grand si besoin 
        if (x < 0){
            Tuiles[][] tab = new Tuiles[plateau.length+1][plateau[0].length] ; 
            for (int i = 0; i < plateau.length; i++){
                for (int j = 0; j < plateau[0].length ;j++){
                    tab[i+1][j] = plateau[i][j] ; 
                }
            }
            plateau = tab.clone() ;
            x = 0 ;
        }
        else if (y <  0){
            Tuiles[][] tab = new Tuiles[plateau.length][plateau[0].length+1] ; 
            for (int i = 0; i < plateau.length; i++){
                for (int j = 0; j < plateau[0].length ;j++){
                    tab[i][j+1] = plateau[i][j] ; 
                }
            }
            plateau = tab.clone() ;
            y = 0;
        }
        else if (x >= plateau.length){
            Tuiles[][] tab = new Tuiles[plateau.length+1][plateau[0].length] ; 
            for (int i = 0; i < plateau.length; i++){
                for (int j = 0; j < plateau[0].length ;j++){
                    tab[i][j] = plateau[i][j] ; 
                }
            }
            plateau = tab.clone() ;
            
        }
        
        else if (y >= plateau[0].length){
            Tuiles[][] tab = new Tuiles[plateau.length][plateau[0].length+1] ; 
            for (int i = 0; i < plateau.length; i++){
                for (int j = 0; j < plateau[0].length ;j++){
                    tab[i][j] = plateau[i][j] ; 
                }
            }
            plateau = tab.clone() ;
            
        }

        // ajout
        if  (plateau[x][y] == null){
            plateau[x][y] = tuile ;
            return true ;
        }else{
            return false ;
        }
    }
}
