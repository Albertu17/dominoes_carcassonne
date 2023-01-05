package JeuTuilesGenerique.Modele;

import java.io.Serializable;


public class Plateau implements Serializable{

    public Tuile[][] plateau;
    public int hauteur;
    public int largeur;
    
    public Plateau (int lignes, int colonnes) {
        this.hauteur =  lignes;
        this.largeur =  colonnes;
        // On remplit le plateau de tuiles vides à l'initialisation.
        plateau = newplateauFullTuileVide(lignes, colonnes) ;
        
    }

    private Tuile[][] newplateauFullTuileVide(int hauteur, int largeur){
        plateau = new Tuile[hauteur][largeur];
        // On remplit le plateau de tuiles vides à l'initialisation.
        for (int i = 0; i < (hauteur); i++) {
            for (int j = 0; j < largeur; j++) {
                plateau[i][j] = new Tuile() ;
            }
        }
        return plateau ;
    }
    
    public void add(Tuile tuile, int x, int y) {
        
        if (x== 0 || y==0 || x == plateau.length || y == plateau[0].length ){
            Tuile[][] tab = plateau.clone() ;
            plateau = newplateauFullTuileVide(plateau.length+2, plateau.length+2) ;
            
            for (int i = 0; i < tab.length; i++){
                for (int j = 0; j < tab[0].length ;j++){
                    plateau[i+1][j+1] = tab[i][j] ; 
                }
            }
            
        }
        // ajout sur le plateau
        plateau[x][y] = tuile ;

        // creation d'un tableau plus grand si besoin 
        // if (x == 0){
        //     Tuile[][] tab = newplateauFullTuileVide(plateau.length+2, plateau.length+2) ;
        //     for (int i = 0; i < plateau.length; i++){
        //         for (int j = 0; j < plateau[0].length ;j++){
        //             tab[i+1][j] = plateau[i][j] ; 
        //         }
        //     }
        //     plateau = tab.clone() ;
        //     x = 2 ;
        // }
        // else if (y <  0){
        //     Tuile[][] tab = new Tuile[plateau.length][plateau[0].length+1] ; 
        //     for (int i = 0; i < plateau.length; i++){
        //         for (int j = 0; j < plateau[0].length ;j++){
        //             tab[i][j+1] = plateau[i][j] ; 
        //         }
        //     }
        //     plateau = tab.clone() ;
        //     y = 0;
        // }
        // else if (x >= plateau.length){
        //     Tuile[][] tab = new Tuile[plateau.length+1][plateau[0].length] ; 
        //     for (int i = 0; i < plateau.length; i++){
        //         for (int j = 0; j < plateau[0].length ;j++){
        //             tab[i][j] = plateau[i][j] ; 
        //         }
        //     }
        //     plateau = tab.clone() ;
            
        // }
        
        // else if (y >= plateau[0].length){
        //     Tuile[][] tab = new Tuile[plateau.length][plateau[0].length+1] ; 
        //     for (int i = 0; i < plateau.length; i++){
        //         for (int j = 0; j < plateau[0].length ;j++){
        //             tab[i][j] = plateau[i][j] ; 
        //         }
        //     }
        //     plateau = tab.clone() ;
            
        // }


    }

}
