public class Joueur {
    private int score ;
    private final String nom ;


    Joueur(String nom){
        this.nom = nom ;
    }

    public int getScore(){return score ;}

    public void addScore(int ajouter){ score += ajouter ;}

    public String getName(){ return nom ;}

}
