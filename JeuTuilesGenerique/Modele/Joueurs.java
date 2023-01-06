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
            players.add( new Joueur(Character.toString(65+i), false) );
        }
    }

    public int nbJoueurs() {
        return players.size();
    }

    public boolean addPlayer(String nom, boolean IA, boolean Pion){
        if (players.size() <= nombreMaximalDeJoueur ){
            players.add(new Joueur( nom,  IA)) ;
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

    public Joueur joueurAuTrait() {
        for (Joueur j: players) {
            if (j.auTrait) return j;
        }
        return null;
    }

    public Joueur getSuivant(Joueur joueur) {
        for(int i = 0; i < players.size(); i++) {
            if (players.get(i).equals(joueur)) {
                if (i == players.size()-1) return players.get(0);
                return players.get(i+1);
            }
        }
        return null;
    }

    public void nextJoueurAuTrait() {
        Joueur joueurAuTrait = joueurAuTrait();
        if (joueurAuTrait == null) players.get(0).setAuTrait(true);
        else {
            joueurAuTrait.setAuTrait(false);
            getSuivant(joueurAuTrait).setAuTrait(true);
        }
    }

    public class Joueur implements Serializable{

        public int score ;
        public final String nom ;
        private int NbrPion ;
        private boolean IA ;  
        boolean auTrait;
    
        public Joueur(String nom, boolean IA){
            this.nom = nom ;
            score = 0 ;
            NbrPion = 0 ; // Augmenté si
            this.IA = IA ;
        }

        public void setAuTrait(boolean auTrait) {
            this.auTrait = auTrait;
        }
    
        public int getScore() {return score;}
    
        public void addScore(int ajout) {score += ajout;}
    
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
            
            JLabel nomEtPoints;

            public PanelJoueur() {
                setBorder(BorderFactory.createLineBorder(Color.BLACK));
                nomEtPoints = new JLabel();
                add(nomEtPoints);
                if (Joueur.this.auTrait) nomEtPoints.setText("--> " + Joueur.this.getName() + " : " + String.valueOf(Joueur.this.getScore()) + "pts");
                else nomEtPoints.setText(Joueur.this.getName() + " : " + String.valueOf(Joueur.this.getScore()) + "pts");
            }

            public void updatePanel() {
                if (Joueur.this.auTrait) nomEtPoints.setText("--> " + Joueur.this.getName() + " : " + String.valueOf(Joueur.this.getScore()) + "pts");
                else nomEtPoints.setText(Joueur.this.getName() + " : " + String.valueOf(Joueur.this.getScore()) + "pts");
                revalidate();
                repaint();
            }
        }
    }
}


