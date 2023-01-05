package JeuTuilesGenerique.Modele;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import JeuCarcassonne.PartieCarcassonne;
import JeuCarcassonne.TuileCarcassonne;
import JeuDominos.TuileDomino;

public class Partie implements Serializable {

    public Joueurs joueurs ;
    public Plateau plateau;
    public Pioche pioche;
    public Tuile aJouer;
    public String nomPartie ;

    public Partie(Joueurs joueurs, Plateau plateau, Pioche pioche, String nomPartie) {
        this.joueurs = joueurs;
        this.plateau = plateau;
        this.pioche = pioche;
        this.nomPartie = nomPartie;
    }

    public Partie(String nomPartie){
        joueurs = new Joueurs() ;
        this.nomPartie = nomPartie ;
        plateau = new Plateau(5,5);
    }

    public void unePartie() {
        nouvelleTuileAjouer();
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

    // Vérifie si une tuile est plaçable et la place le cas échéant.
    public int jouer(int x, int y) {
        if (check(x, y)) { 
            int pts = this.nbPoint(x, y) ;
           plateau.add(aJouer, x, y) ;
           return pts;
        }
        return 0 ;
    }

    public boolean partieFinie() {
        return pioche.pioche.isEmpty();
    }

    //  a redéfinir dans chaque variante du jeu
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