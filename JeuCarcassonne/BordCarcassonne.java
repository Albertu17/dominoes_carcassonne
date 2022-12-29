package JeuCarcassonne;

import java.io.Serializable;

import JeuTuilesGenerique.Modele.Bord;

public class BordCarcassonne extends Bord{
    
    Structure structure;

    public BordCarcassonne(Structure structure) {
        this.structure = structure;
    }

    public BordCarcassonne(String initiale) {
        // S'il y a un bouclier
        if (initiale.length() == 2) this.structure = new Structure.Ville(true);
        else {
            switch (initiale) {
                case "V": 
                    this.structure = new Structure.Ville();
                    break;
                case "C":
                    this.structure = new Structure.Champ();
                    break;
                case "R":
                    this.structure = new Structure.Route();
                    break;
                default:
                    this.structure = null;
            }
        }
    }

    public static class Structure {
        public static class Route extends Structure {}
        public static class Champ extends Structure {}
        public static class Ville extends Structure {
            boolean bouclier;
            public Ville() {
                this.bouclier = false;}
            public Ville(boolean bouclier) {
                this.bouclier = bouclier;}
        }
    }
}
