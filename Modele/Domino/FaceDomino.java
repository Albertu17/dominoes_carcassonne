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


    // sujet à la manière dont les cotés des tuiles sont affiché
    public boolean compatible(FaceDomino face2){
        for (int i = 0 ; i < this.numero.length ; i++){
            if (this.numero != face2.numero) return false ;
        }

        return true ; 
    }

}
