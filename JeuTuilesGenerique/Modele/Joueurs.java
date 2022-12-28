package JeuTuilesGenerique.Modele;

public class Joueurs {
    
    public Joueur[] joueurs;

    public Joueurs (int nbJoueurs) {
        nbJoueurs = Math.max(nbJoueurs, 2); // Nombre de joueurs min: 2.
        nbJoueurs = Math.min(nbJoueurs, 5); // Nombre de joueurs max: 5.
        joueurs = new Joueur[nbJoueurs];
        for (int i = 0; i < nbJoueurs; i++) {
            joueurs[i] = new Joueur(Character.toString(65+i));
        }
    }

    public int nbJoueurs() {
        return joueurs.length;
    }

    public class Joueur {

        public int nbPoints;
        public String nom;
    
        public Joueur (String nom) {
            nbPoints = 0;
            this.nom = nom;
        }
    }

}
