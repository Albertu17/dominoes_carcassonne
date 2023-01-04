package JeuTuilesGenerique.Vue;

import javax.swing.JFrame;

import JeuCarcassonne.PartieCarcassonne;
import JeuCarcassonne.PiocheCarcassonne;
import JeuCarcassonne.VueCarcassonne;
import JeuDominos.PartieDominos;
import JeuDominos.PiocheDominos;
import JeuDominos.VueDominos;
import JeuTuilesGenerique.Modele.Joueurs;
import JeuTuilesGenerique.Modele.Pioche;
import JeuTuilesGenerique.Modele.Plateau;

import java.awt.* ;
import java.io.IOException; 

public class Launcher extends JFrame{

    public Launcher() {
        setVisible(true);
        setTitle("JEU");
        Dimension size = Toolkit.getDefaultToolkit().getScreenSize(); // Récupère taille de l'écran utilisateur.
        setSize((int) size.getWidth(), (int) size.getHeight()); // Met la fenêtre en plein écran.
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public static void launchCarcassonne(Joueurs joueurs, String nomPartie) throws IOException {
        Plateau plateau = new Plateau(5, 5);
        PiocheCarcassonne piocheC = new PiocheCarcassonne();
        PartieCarcassonne partieC = new PartieCarcassonne(joueurs, plateau, piocheC, nomPartie);
        VueCarcassonne vueC = new VueCarcassonne(partieC);
        vueC.setFenetre(new Launcher()) ;
    }

    public static void launchDominos(Joueurs joueurs, String nomPartie) throws IOException {
        Plateau plateau = new Plateau(5, 5);
        PiocheDominos piocheD = new PiocheDominos();
        PartieDominos partieD = new PartieDominos(joueurs, plateau, piocheD, nomPartie);
        VueDominos vueD = new VueDominos(partieD);
        vueD.setFenetre(new Launcher());
    }

    
    public static void launchMenu(){
        new Menu(new Launcher());
    }



    // ancienne fonction au cas ou :
                // public static void initialisation() {
                //     Pioche pioche = new Pioche(72);
                //     Joueurs joueurs = new Joueurs(2);
                //     Plateau plateau = new Plateau(5,5);
                //     Partie partie = new Partie(joueurs, plateau, pioche, "test");
                //     GameView gV = new GameView(new Fenetre());
                //     gV.setPartie(partie);
                //     gV.setGameView();
                // }

                // public static  void launchWithoutMenu(){
                //     GameView pane = new GameView(new Fenetre()) ;
                //     PartieCarcassonne partie = new PartieCarcassonne("TestCarcassonne") ;
                //     partie.getJoueurs().addPlayer("Pierre", false, true);
                //     partie.getJoueurs().addPlayer("Damiens", false, true);
                //     pane.setPartie(partie);
                //     pane.setGameView();
                // }

    public static void main(String[] args) {
        launchMenu();
    }
}
