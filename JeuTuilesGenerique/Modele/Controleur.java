package JeuTuilesGenerique.Modele;

import JeuTuilesGenerique.Vue.Menu;

import javax.security.auth.callback.LanguageCallback;

import JeuCarcassonne.PartieCarcassonne;
import JeuTuilesGenerique.Vue.GameView;

public class Controleur {

    // public static void initialisation() {
    //     Pioche pioche = new Pioche(72);
    //     Joueurs joueurs = new Joueurs(2);
    //     Plateau plateau = new Plateau(5,5);
    //     Partie partie = new Partie(joueurs, plateau, pioche);
    //     new GameView(partie);
    // }

    public static  void launchWithOutMenu(){


        GameView pane = new GameView() ;
        PartieCarcassonne partie = new PartieCarcassonne("TestCarcassonne") ;
        partie.getJoueurs().addPlayer("Pierre", false, true);
        partie.getJoueurs().addPlayer("Damiens", false, true);
        pane.setPartie(partie);

        pane.setGameView();
        
    }

    public static void start(){
        new Menu(new GameView()  ) ;
    }

    public static void main(String[] args) {
        // initialisation();
        // start();
        launchWithOutMenu() ;
        // je sais pas pk tu dosi appuier sur save pour que ça s'affiche
    }
}