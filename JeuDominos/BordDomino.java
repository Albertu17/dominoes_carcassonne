import JeuTuilesGenerique.Modele.Bord;

public class BordDomino extends Bord{
    
    int n1, n2, n3;

    public BordDomino(int n1, int n2, int n3) {
        this.n1 = n1;
        this.n2 = n2;
        this.n3 = n3;
    }

    public int[] getNumeros() {
        int[] numeros = {n1,n2,n3};
        return numeros;
    }
}