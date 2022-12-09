public class Tuiles {
    private Face haut;
    private Face droite;
    private Face bas;
    private Face gauche;

    Tuiles(int[][] allside) {

        haut = new Face(allside[0]);
        droite = new Face(allside[1]);
        bas= new Face(allside[2]);
        gauche = new Face(allside[3]);

    }

    public Face getHaut() {
        return haut;
    }

    public Face getDroite() {
        return droite;
    }

    public Face getBas() {
        return bas;
    }

    public Face getGauche() {
        return gauche;
    }

    
}