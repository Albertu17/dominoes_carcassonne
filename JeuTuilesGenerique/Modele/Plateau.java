package JeuTuilesGenerique.Modele;

import java.io.Serializable;


public class Plateau implements Serializable{

    public Tuile[][] plateau;
    public int hauteur;
    public int largeur;
    
    public Plateau () {
        hauteur =  7;
        largeur =  7;
        // On remplit le plateau de tuiles vides à l'initialisation.
        plateau = newplateauFullTuileVide(hauteur, largeur) ;
        
    }

    private Tuile[][] newplateauFullTuileVide(int hauteur, int largeur){
        this.hauteur = hauteur ;
        this.largeur = largeur ;
        plateau = new Tuile[hauteur][largeur];
        // On remplit le plateau de tuiles vides à l'initialisation.
        for (int i = 0; i < (hauteur); i++) {
            for (int j = 0; j < largeur; j++) {
                plateau[i][j] = new Tuile() ;
            }
        }
        return plateau ;
    }
    
    public boolean add(Tuile tuile, int x, int y) {
        tuile.setCoordonnées(x, y);
        boolean aggrandissement = false;
        if (x == 1){
            hauteur++;
            aggrandissement = true;
            Tuile[][] tab = plateau.clone() ;
            plateau = newplateauFullTuileVide(plateau.length+1, plateau[0].length) ;
            
            for (int i = 0; i < tab.length; i++){
                for (int j = 0; j < tab[0].length ;j++){
                    plateau[i+1][j] = tab[i][j] ; 
                }
            }
            x = x+1;
        }
        else if (x == plateau.length - 2){
            hauteur++;
            aggrandissement = true;
            Tuile[][] tab = plateau.clone() ;
            plateau = newplateauFullTuileVide(plateau.length+1, plateau[0].length) ;
            
            for (int i = 0; i < tab.length; i++){
                for (int j = 0; j < tab[0].length ;j++){
                    plateau[i][j] = tab[i][j] ; 
                }
            }
        }
        if ( y==1  ){
            largeur++;
            aggrandissement = true;
            Tuile[][] tab = plateau.clone() ;
            plateau = newplateauFullTuileVide(plateau.length, plateau[0].length+1) ;
            
            for (int i = 0; i < tab.length; i++){
                for (int j = 0; j < tab[0].length ;j++){
                    plateau[i][j+1] = tab[i][j] ; 
                }
            }
            y = y +1 ;
        }
        else if ( y == plateau[0].length-2 ){
            hauteur++;
            aggrandissement = true;
            Tuile[][] tab = plateau.clone() ;
            plateau = newplateauFullTuileVide(plateau.length, plateau[0].length+1) ;
            
            for (int i = 0; i < tab.length; i++){
                for (int j = 0; j < tab[0].length ;j++){
                    plateau[i][j] = tab[i][j] ; 
                }
            }
        }
        
        // ajout sur le plateau
        plateau[x][y] = tuile ;
        return aggrandissement;
    }
}