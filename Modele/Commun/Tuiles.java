package Modele.Commun;

import Modele.Domino.FaceDomino;

public class Tuiles {

    private Face haut ;
    private Face droite ;
    private Face bas ;
    private Face gauche ;

    private int id ;
    private static int compteur = 0 ;
    
    

    Tuiles(boolean Carcassonne) {
        id  = compteur++ ;


        if (Carcassonne){
            // recherche dans les fichiers en fonction de l'id 
        }else{
            haut = new FaceDomino() ;
            droite = new FaceDomino() ;
            bas = new FaceDomino() ;
            gauche = new FaceDomino() ;
        }

        
    }

   
    

    // // anciennne 
    // public void Rotate(int SensHoraire){
        
    //     Face hold  = allface[0] ;
    //     for (int i = 0  ; i < allface.length ; i++){
    //         allface[i] = allface[(i+SensHoraire)%allface.length] ;
    //     }
        
    //     allface[(SensHoraire)%allface.length]  = hold ;
        
    // }
    
    // positif --> tourner dans le sens Horaire
    public void Rotate(boolean SensHoraire){
        if (SensHoraire){
            Face hold = haut ; 
            haut = gauche ;
            gauche  = bas ;
            bas =  droite ;
            droite = hold  ;
        }else{
            Face hold = haut  ;
            haut = droite ;
            droite = bas ;
            bas = gauche ; 
            gauche = hold ;
        }
    }




    public Face getHaut() {
        return haut;
    }




    public Face getDroite() {
        return droite;
    }




    public Face getBas() {
        return bas;
    }




    public Face getGauche() {
        return gauche;
    }




    public int getId() {
        return id;
    }



}