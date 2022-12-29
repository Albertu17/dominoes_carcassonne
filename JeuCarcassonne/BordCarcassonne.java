package JeuCarcassonne;

import JeuTuilesGenerique.Modele.Bord;

public class BordCarcassonne extends Bord{
    
    Structure structure;

    public BordCarcassonne(Structure structure) {
        this.structure = structure;
    }

    public BordCarcassonne(String initiale) {
        // S'il y a un bouclier
        if (initiale.contains("b")) this.structure = new Structure.Ville(true);
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

    public boolean estCompatibleAvec(BordCarcassonne bD) {
        if (bD == null || (bD.structure.getClass().equals(structure.getClass()))) return true;
        return false;
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