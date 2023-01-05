package JeuTuilesGenerique.Modele;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import JeuCarcassonne.PartieCarcassonne;
import JeuTuilesGenerique.Modele.Joueurs.Joueur;
import JeuTuilesGenerique.Vue.GameView;

public class Partie implements Serializable {

    public Joueurs joueurs ;
    public Plateau plateau;
    public Pioche pioche;
    public Tuile aJouer;
    public String nomPartie ;
    public GameView gui;

    public Partie(Joueurs joueurs, Plateau plateau, Pioche pioche, String nomPartie) {
        this.joueurs = joueurs;
        this.plateau = plateau;
        this.pioche = pioche;
        this.nomPartie = nomPartie;
        premiereTuile();
        nouvelleTuileAjouer();
        joueurs.nextJoueurAuTrait();
    }

    public Partie(String nomPartie){
        joueurs = new Joueurs() ;
        this.nomPartie = nomPartie ;
        plateau = new Plateau();
    }

    public void setGui(GameView gui) {
        this.gui = gui;
    }

    public void premiereTuile() {
        plateau.plateau[(int)plateau.hauteur/2][(int)plateau.largeur/2] = pioche.pickOne();
    }

    public void nouvelleTuileAjouer() {
        aJouer = pioche.pickOne();
        if (aJouer == null) aJouer = new Tuile();
    }

    // Vérifie si une tuile est plaçable.
    public boolean check(int x, int y) {
        if (plateau.plateau[x][y].getClass().equals(aJouer.getClass())) return false; // L'endroit où l'on
        // veut poser une tuile doit contenir une tuile vide (classe différente).
        Bord bordAuNord = getBordAuNord(x, y);
        Bord bordAlOuest = getBordAlOuest(x, y);
        Bord bordAuSud = getBordAuSud(x, y);
        Bord bordAlEst = getBordAlEst(x, y);
        // On ne peut pas poser une tuile si elle n'est adjacente à aucune autre tuile.
        if (bordAuNord == null && bordAlOuest == null && bordAuSud == null && bordAlEst == null)
            return false;
        if (!(aJouer.nord.estCompatibleAvec(bordAuNord))) return false;
        if (!(aJouer.ouest.estCompatibleAvec(bordAlOuest))) return false;
        if (!(aJouer.sud.estCompatibleAvec(bordAuSud))) return false;
        if (!(aJouer.est.estCompatibleAvec(bordAlEst))) return false;
        return true;
    }

    // Vérifie si une tuile est plaçable, la place le cas échéant et prépare le tour suivant.
    public boolean jouer(int x, int y) {
        if (check(x, y)) { 
            // l'appel de nbPoints à besoin d'etre mis avant l'ajout de la tuile au plateau.
            // car les coordonnées de la tuile peuvent changer si le plateau devient plus grand 
            joueurs.joueurAuTrait().addScore(nbPoint(x, y));
            

            // aggrandit le plateau si la tuile est placée en bordure de la grille du GUI.
            if (plateau.add(aJouer, x, y)) gui.repaintGrille();
            else gui.updateGrille(aJouer, x, y);
            joueurs.nextJoueurAuTrait();
            gui.repaintPanelJoueurs();
            nouvelleTuileAjouer();
            // gui.repaintTuileAJouer(); // TODO fonction dans GUI
            return true ; //besoin du boolean pour l'IA
        }
        return false ;
    }
    
    
    public boolean jouerTerminal(int x, int y){
        if (check(x, y)) { 
            // l'appel de nbPoints à besoin d'etre mis avant l'ajout de la tuile au plateau.
            // car les coordonnées de la tuile peuvent changer si le plateau devient plus grand 
            joueurs.joueurAuTrait().addScore(nbPoint(x, y));

            plateau.add(aJouer, x, y) ;
            joueurs.nextJoueurAuTrait();

            return true ; //besoin du boolean pour l'IA
        }
        return false ;

    }


    // prend en fonction de joueur au trait
    public void TourIA(Joueur j){
        RecursiveIA(plateau.largeur/2, plateau.hauteur/2, new ArrayList<Tuile>()) ;
    }

    public boolean RecursiveIA(int x, int y, List<Tuile> list){
        if (list.contains(plateau.plateau[x][y])) return false ;

        // pour ne pas tester 2 fois la meme tuile et donc faire une boucle infinie
        list.add(plateau.plateau[x][y]) ;

        // si on peut placer à cette place
        for (int i = 0 ; i < 4 ; i++){
            if (jouer(x, y)) return true ;
            else aJouer.Rotate(true);
        }

        // recursif sur tuille adjacentes :
        if (x!= 1 && y!=1 && x != plateau.plateau.length-2 && y != plateau.plateau[0].length-2 ){
            if (RecursiveIA(x-1, y, list) ) return true;
            
            if (RecursiveIA(x+1, y, list)) return true;
            
            if (RecursiveIA(x, y-1, list)) return true ;
            
            if (RecursiveIA(x, y+1, list)) return true;
            
        }
        return false  ;
        
        // TODO besoin de mettre ici next() joueurs au trait au cas où la tuille n'ets pas plaçable ;
        // plus mettre a jour le GUI
    }


    public boolean partieFinie() {
        return pioche.pioche.isEmpty();
    }

    // à redéfinir dans chaque variante du jeu
    public int nbPoint(int x, int y){
        return 0 ;
    }

    public Joueurs getJoueurs() {return joueurs;}
    public String getNomPartie() {return nomPartie;}

    public Bord getBordAuNord(int x, int y) {
        return plateau.plateau[x-1][y].sud;
    }

    public Bord getBordAlOuest(int x, int y) {
        return plateau.plateau[x][y-1].est;
    }

    public Bord getBordAuSud(int x, int y) {
        return plateau.plateau[x+1][y].nord;
    }

    public Bord getBordAlEst(int x, int y) {
        return plateau.plateau[x][y+1].ouest;
    }

    public Pioche getPioche(){return pioche ;}
    public Plateau getPlateau(){return plateau ;}

    public void save(){
        String path = "Sauvegarde/" + (this instanceof PartieCarcassonne ? "Carcassonne/" : "Domino/") ;
                    // enregistrer un objet
            try {  
                //Saving of object in a file
                FileOutputStream file = new FileOutputStream(path+ this.getNomPartie());
                ObjectOutputStream out = new ObjectOutputStream(file);
                
                // Method for serialization of object
                out.writeObject(this);
                out.close();
                file.close();
                
                System.out.println("Object has been serialized");
            }
            
            catch(IOException ex) {
                System.out.println(ex); 
            }
    }
}