package Modele.Domino;

import Modele.Commun.Face;

public class FaceDomino extends Face {

    private int[] numero;

    public FaceDomino() {
        int taille_face = 3 ;
        int MaxNombre = 4 ; 
        

        numero = new int[taille_face];

        for (int i = 0; i < numero.length; i++) {
            numero[i] = (int) (Math.random() * (MaxNombre +1 ) );
        }
    }

    public int[] getNumero() {
        return numero;
    }


    // sujet à la manière dont les cotés (chiffre) (si l'affichage du  tableau commence par le bas ou le haut) des tuiles sont affiché
    // mais devrait être correct 
    public boolean compatible(FaceDomino face2){
        for (int i = 0 ; i < this.numero.length ; i++){
            if (this.numero[i] != face2.numero[i]) return false ;
        }

        return true ; 
    }

}
