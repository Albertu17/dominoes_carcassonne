package JeuTuilesGenerique.Modele;

import java.io.Serializable;

public class Bord implements Serializable{

    // TODO voir si pas moyen de faire autrement que de créer d'abord les attributs ici
    public int n1,n2,n3;

    public int[] getNumeros() {return new int[0];}

    public boolean estCompatibleAvec(Bord b) {
        return false;
    }
}
