package Modele.Commun;
public class Joueur {
    private int score ;
    private final String nom ;
    private int NbrPion ;
    private boolean IA ;  


    Joueur(String nom, boolean IA, boolean Pion){
        this.nom = nom ;
        score = 0 ;
        NbrPion = Pion ? 5 :0  ; // à voir 
        this.IA = IA ;
    }

    public int getScore(){return score ;}

    public void addScore(int ajouter){ score += ajouter ;}

    public String getName(){ return nom ;}



    public int getNbrPion() {
        return NbrPion;
    }

    public void setNbrPion(int nbrPion) {
        NbrPion = nbrPion;
    }
    
    

}
