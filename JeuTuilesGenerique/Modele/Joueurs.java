package JeuTuilesGenerique.Modele;

import java.util.ArrayList;
import java.util.List;

public class Joueurs {
    
    public List<Joueur> players ; 
    private static int nombreMaximalDeJoueur = 5 ;

    public Joueurs (int nbJoueurs) {
        nbJoueurs = Math.max(nbJoueurs, 2); // Nombre de joueurs min: 2.
        nbJoueurs = Math.min(nbJoueurs, 5); // Nombre de joueurs max: 5.
        
        for (int i = 0; i < nbJoueurs; i++) {
            players.add( new Joueur(Character.toString(65+i), false, true) );
        }
    }

    public Joueurs(){
        players = new ArrayList<Joueur>() ;
    }

    public int nbJoueurs() {
        return players.size();
    }

    public class Joueur {
        public int score ;
        public final String nom ;
        private int NbrPion ;
        private boolean IA ;  
    
    
        public Joueur(String nom, boolean IA, boolean Pion){
            this.nom = nom ;
            score = 0 ;
            NbrPion = Pion ? 5 :0  ; // à voir le nombre de Pion
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
    
        public boolean isIA(){return IA ;}
        public void setIA(boolean IA){this.IA = IA ;}
        
    }

    public boolean addPlayer(Joueur playeur){
        if (players.size() <= nombreMaximalDeJoueur ){
            players.add(playeur) ;
            return true ;
        }
        return false ;
    }
    public Joueur getPlayers(int index){return players.get(index) ;}
    public List<Joueur> getPlayers(){return players ;}
}


