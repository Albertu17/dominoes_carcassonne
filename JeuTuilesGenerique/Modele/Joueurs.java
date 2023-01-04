package JeuTuilesGenerique.Modele;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.*;

public class Joueurs implements Serializable{
    
    public List<Joueur> players ; 
    private static int nombreMaximalDeJoueur = 5 ;

    public Joueurs(){
        players = new ArrayList<Joueur>() ;
    }

    public Joueurs (int nbJoueurs) {
        this();
        nbJoueurs = Math.max(nbJoueurs, 2); // Nombre de joueurs min: 2.
        nbJoueurs = Math.min(nbJoueurs, 5); // Nombre de joueurs max: 5.
        for (int i = 0; i < nbJoueurs; i++) {
            players.add( new Joueur(Character.toString(65+i), false, true) );
        }
    }

    public int nbJoueurs() {
        return players.size();
    }

    public boolean addPlayer(String nom, boolean IA, boolean Pion){
        if (players.size() <= nombreMaximalDeJoueur ){
            players.add(new Joueur( nom,  IA,  Pion)) ;
            return true ;
        }
        return false ;
    }
    public boolean addPlayer(Joueur playeur){
        if (players.size() <= nombreMaximalDeJoueur ){
            players.add(playeur) ;
            return true ;
        }
        return false ;
    }

    public Joueur getPlayers (int index) {return players.get(index);}
    public List<Joueur> getList() {return players;}

    public Joueur getLast(){
        if (players.size() != 0 ){
            return players.get(players.size() - 1) ;
        }
        return null ;
    }

    public boolean nomLibre(String name){
        if (name.equals("")) return false ;
        
        for (Joueur jo : players) {
            if (name.equals(jo.getName())) return false ;
        }
        
        return true ;
    }

    public class Joueur implements Serializable{

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
    
        public int getScore() {return score;}
    
        public void addScore(int ajouter) {score += ajouter;}
    
        public String getName() {return nom;}
    
        public int getNbrPion() {
            return NbrPion;
        }
    
        public void setNbrPion(int nbrPion) {
            NbrPion = nbrPion;
        }
    
        public boolean isIA(){return IA ;}
        public void setIA(boolean IA){this.IA = IA ;}

        public class PanelJoueur extends JPanel {
    
            public PanelJoueur() {
                this.add(new JLabel(Joueur.this.getName() + " : " + String.valueOf(Joueur.this.getScore()) + "pts"));
                this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            }
        }
    }
}


