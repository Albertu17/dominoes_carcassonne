package JeuTuilesGenerique.Modele;

public class Plateau {

    Tuile[][] plateau;
    
    public Plateau (int lignes, int colonnes) {
        plateau = new Tuile[lignes][colonnes];
    }

    public boolean add(Tuile tuile, int x, int y) {
        // creation d'un tableau plus grand si besoin 
        if (x < 0){
            Tuile[][] tab = new Tuile[plateau.length+1][plateau[0].length] ; 
            for (int i = 0; i < plateau.length; i++){
                for (int j = 0; j < plateau[0].length ;j++){
                    tab[i+1][j] = plateau[i][j] ; 
                }
            }
            plateau = tab.clone() ;
            x = 0 ;
        }
        else if (y <  0){
            Tuile[][] tab = new Tuile[plateau.length][plateau[0].length+1] ; 
            for (int i = 0; i < plateau.length; i++){
                for (int j = 0; j < plateau[0].length ;j++){
                    tab[i][j+1] = plateau[i][j] ; 
                }
            }
            plateau = tab.clone() ;
            y = 0;
        }
        else if (x >= plateau.length){
            Tuile[][] tab = new Tuile[plateau.length+1][plateau[0].length] ; 
            for (int i = 0; i < plateau.length; i++){
                for (int j = 0; j < plateau[0].length ;j++){
                    tab[i][j] = plateau[i][j] ; 
                }
            }
            plateau = tab.clone() ;
            
        }
        
        else if (y >= plateau[0].length){
            Tuile[][] tab = new Tuile[plateau.length][plateau[0].length+1] ; 
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
