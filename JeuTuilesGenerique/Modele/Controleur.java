package JeuTuilesGenerique.Modele;

import JeuTuilesGenerique.Vue.Menu;
import JeuCarcassonne.PartieCarcassonne;
import JeuTuilesGenerique.Vue.Fenetre;
import JeuTuilesGenerique.Vue.GameView;

public class Controleur {

    public static void initialisation() {
        Pioche pioche = new Pioche(72);
        Joueurs joueurs = new Joueurs(2);
        Plateau plateau = new Plateau(5,5);
        Partie partie = new Partie(joueurs, plateau, pioche);
        GameView gV = new GameView(new Fenetre());
        gV.setPartie(partie);
        gV.setGameView();
    }

    public static  void launchWithoutMenu(){
        GameView pane = new GameView(new Fenetre()) ;
        PartieCarcassonne partie = new PartieCarcassonne("TestCarcassonne") ;
        partie.getJoueurs().addPlayer("Pierre", false, true);
        partie.getJoueurs().addPlayer("Damiens", false, true);
        pane.setPartie(partie);
        pane.setGameView();
    }

    public static void start(){
        new Menu(new Fenetre());
    }

    public static void main(String[] args) {
        initialisation();
        start();
        // launchWithoutMenu();
        
    }
}