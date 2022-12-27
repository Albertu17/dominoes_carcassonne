package JeuTuilesGenerique.Modele;

import JeuTuilesGenerique.Vue.GameView;

public class Controleur {

    public static void initialisation() {
        Pioche pioche = new Pioche(72);
        Joueurs joueurs = new Joueurs(2);
        Plateau plateau = new Plateau(3,3);
        Partie partie = new Partie(joueurs, plateau, pioche);
        GameView gV = new GameView(partie);
    }

    public static void main(String[] args) {
        initialisation();
    }
}