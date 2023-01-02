package JeuTuilesGenerique.Vue;

import javax.swing.JFrame;

import JeuCarcassonne.PartieCarcassonne;
import JeuCarcassonne.PiocheCarcassonne;
import JeuCarcassonne.VueCarcassonne;
import JeuDominos.PartieDominos;
import JeuDominos.PiocheDominos;
import JeuDominos.VueDominos;
import JeuTuilesGenerique.Modele.Joueurs;
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
        // TODO lancer menu sur cette fenêtre
    }

    public static void launchCarcassonne(Joueurs joueurs, String nomPartie) throws IOException {
        Plateau plateau = new Plateau(5, 5);
        PiocheCarcassonne piocheC = new PiocheCarcassonne();
        PartieCarcassonne partieC = new PartieCarcassonne(joueurs, plateau, piocheC, nomPartie);
        VueCarcassonne vueC = new VueCarcassonne(partieC);
    }

    public static void launchDominos(Joueurs joueurs, String nomPartie) throws IOException {
        Plateau plateau = new Plateau(5, 5);
        PiocheDominos piocheD = new PiocheDominos();
        PartieDominos partieD = new PartieDominos(joueurs, plateau, piocheD, nomPartie);
        VueDominos vueD = new VueDominos(partieD);
    }

    // public static void main(String[] args) {
    //     new Launcher();
    // }
}
