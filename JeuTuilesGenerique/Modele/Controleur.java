package JeuTuilesGenerique.Modele;

import JeuTuilesGenerique.Vue.GameView;

public class Controleur {

    public static void initialisation() {
        Pioche pioche = new Pioche(72);
        Joueur jA = new Joueur("A");
        Joueur jB = new Joueur("B");
        Joueur[] joueurs = new Joueur[2];
        joueurs[0] = jA;
        joueurs[1] = jB;
        Plateau plateau = new Plateau(3,3);
        Partie partie = new Partie(joueurs, plateau, pioche);
        GameView gV = new GameView(partie);
    }

    public static void main(String[] args) {
        initialisation();
    }
}