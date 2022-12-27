package JeuTuilesGenerique.Modele;

public class Joueurs {
    
    Joueur[] joueurs;

    public Joueurs (int nbJoueurs) {
        joueurs = new Joueur[nbJoueurs];
        for (int i = 0; i < nbJoueurs; i++) {
            joueurs[i] = new Joueur(Character.toString(65+i));
        }
    }

    public class Joueur {

        int nbPoints;
        String nom;
    
        public Joueur (String nom) {
            nbPoints = 0;
            this.nom = nom;
        }
    }

}
