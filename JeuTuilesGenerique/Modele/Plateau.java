package JeuTuilesGenerique.Modele;

public class Plateau {

    Tuile[][] plateau;
    
    public Plateau (int lignes, int colonnes) {
        plateau = new Tuile[lignes][colonnes];
    }
}
