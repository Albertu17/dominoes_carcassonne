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
}