package JeuTuilesGenerique.Modele;

public interface Jouable {
    
    // Renvoie false si le coup n'est pas autorisé, et true sinon.
    public boolean check(Tuile t, int x, int y);

    // Pareil que check mais joue le coup si c'est possible.
    public boolean jouer(Tuile t, int x, int y);

    public boolean partieFinie();
}