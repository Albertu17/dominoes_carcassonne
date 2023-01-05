package JeuTuilesGenerique.Vue;

import javax.swing.JFrame;

import JeuCarcassonne.PartieCarcassonne;
import JeuCarcassonne.PiocheCarcassonne;
import JeuCarcassonne.VueCarcassonne;
import JeuDominos.PartieDominos;
import JeuDominos.PiocheDominos;
import JeuDominos.VueDominos;
import JeuTuilesGenerique.Modele.Joueurs;
import JeuTuilesGenerique.Modele.Partie;
import JeuTuilesGenerique.Modele.Plateau;

import java.awt.* ;
import java.io.IOException; 

public class Launcher extends JFrame{

    public void createWinwow() {
        setVisible(true);
        setTitle("JEU");
        
        // setExtendedState(JFrame.MAXIMIZED_BOTH); 
        // setUndecorated(true);

        Dimension size = Toolkit.getDefaultToolkit().getScreenSize(); // Récupère taille de l'écran utilisateur.
        setSize((int) size.getWidth(), (int) size.getHeight()); // Met la fenêtre en plein écran.
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public void launch() {
        createWinwow();
        new Menu(this); // On passe au menu un objet de cette classe pour qu'il puisse appeler
        // launchCarcassonne ou launchDomino.
    }

    public void launchRunningGame(Partie partie) {
        if (partie instanceof PartieCarcassonne) {
            VueCarcassonne vueC = new VueCarcassonne(partie);
            getContentPane().add(vueC.conteneurGlobal);
        } else {
            VueDominos vueD = new VueDominos(partie);
            getContentPane().add(vueD.conteneurGlobal);
        }
    }

    public void launchCarcassonne(Joueurs joueurs, String nomPartie) throws IOException {
        Plateau plateau = new Plateau();
        PiocheCarcassonne piocheC = new PiocheCarcassonne();
        PartieCarcassonne partieC = new PartieCarcassonne(joueurs, plateau, piocheC, nomPartie);
        VueCarcassonne vueC = new VueCarcassonne(partieC);
        getContentPane().add(vueC.conteneurGlobal);
    }

    public void launchDominos(Joueurs joueurs, String nomPartie) {
        Plateau plateau = new Plateau();
        PiocheDominos piocheD = new PiocheDominos(true);
        PartieDominos partieD = new PartieDominos(joueurs, plateau, piocheD, nomPartie);
        VueDominos vueD = new VueDominos(partieD);
        getContentPane().add(vueD.conteneurGlobal);
    }

    public static void main(String[] args) throws IOException {
        // new Launcher().launch();
        Launcher l = new Launcher();
        l.createWinwow();
        Joueurs j = new Joueurs();
        j.addPlayer(j.new Joueur("Bob", false, false));
        j.addPlayer(j.new Joueur("Paul", false, false));
        // l.launchDominos(j, "p5");
        l.launchCarcassonne(j, "p5");
    }
}
