package JeuCarcassonne;

import java.io.IOException;

import JeuCarcassonne.BordCarcassonne.Structure;
import JeuTuilesGenerique.Modele.Pioche;

public class PiocheCarcassonne extends Pioche{

    PiocheCarcassonne() throws IOException {
        super(); // Création d'une arraylist de tuiles.
        // Ajout à la pioche des 24 différents types de tuiles en leur nombre correspondant.

        // Tuile CCCCV
        addSeveralTimes(new TuileCarcassonne(new BordCarcassonne(new Structure.Champ()), 
                                             new BordCarcassonne(new Structure.Champ()),
                                             new BordCarcassonne(new Structure.Champ()),
                                             new BordCarcassonne(new Structure.Champ()),
                                             new BordCarcassonne(new Structure.Ville()), 
                                             "ImagesTuiles/Tuile-CCCCV.png"), 2);
        // Tuile CCRCV
        addSeveralTimes(new TuileCarcassonne(new BordCarcassonne(new Structure.Champ()), 
                                             new BordCarcassonne(new Structure.Champ()),
                                             new BordCarcassonne(new Structure.Route()),
                                             new BordCarcassonne(new Structure.Champ()),
                                             new BordCarcassonne(new Structure.Ville()), 
                                             "ImagesTuiles/Tuile-CCRCV.png"), 2);
        // Tuile CCRRR
        addSeveralTimes(new TuileCarcassonne(new BordCarcassonne(new Structure.Champ()), 
                                             new BordCarcassonne(new Structure.Champ()),
                                             new BordCarcassonne(new Structure.Route()),
                                             new BordCarcassonne(new Structure.Route()),
                                             new BordCarcassonne(new Structure.Route()), 
                                             "ImagesTuiles/Tuile-CCRRR.png"), 2);
        // Tuile CRRRV
        addSeveralTimes(new TuileCarcassonne(new BordCarcassonne(new Structure.Champ()), 
                                             new BordCarcassonne(new Structure.Route()),
                                             new BordCarcassonne(new Structure.Route()),
                                             new BordCarcassonne(new Structure.Route()),
                                             new BordCarcassonne(new Structure.Ville()), 
                                             "ImagesTuiles/Tuile-CRRRV.png"), 2);
        // Tuile CVCVC
        addSeveralTimes(new TuileCarcassonne(new BordCarcassonne(new Structure.Champ()), 
                                             new BordCarcassonne(new Structure.Ville()),
                                             new BordCarcassonne(new Structure.Champ()),
                                             new BordCarcassonne(new Structure.Ville()),
                                             new BordCarcassonne(new Structure.Champ()), 
                                             "ImagesTuiles/Tuile-CVCVC.png"), 2);
        // Tuile CVCVV
        addSeveralTimes(new TuileCarcassonne(new BordCarcassonne(new Structure.Champ()), 
                                             new BordCarcassonne(new Structure.Ville()),
                                             new BordCarcassonne(new Structure.Champ()),
                                             new BordCarcassonne(new Structure.Ville()),
                                             new BordCarcassonne(new Structure.Ville()), 
                                             "ImagesTuiles/Tuile-CVCVV.png"), 2);
        // Tuile CVCVVb
        addSeveralTimes(new TuileCarcassonne(new BordCarcassonne(new Structure.Champ()), 
                                             new BordCarcassonne(new Structure.Ville()),
                                             new BordCarcassonne(new Structure.Champ()),
                                             new BordCarcassonne(new Structure.Ville()),
                                             new BordCarcassonne(new Structure.Ville()), 
                                             "ImagesTuiles/Tuile-CVCVVb.png"), 2);
        // Tuile CCCC
        addSeveralTimes(new TuileCarcassonne(new BordCarcassonne(new Structure.Champ()), 
                                             new BordCarcassonne(new Structure.Champ()),
                                             new BordCarcassonne(new Structure.Champ()),
                                             new BordCarcassonne(new Structure.Champ()),
                                             new BordCarcassonne(new Structure.Ville()), 
                                             "ImagesTuiles/Tuile-CCCCV.png"), 2);
        // Tuile CCCC
        addSeveralTimes(new TuileCarcassonne(new BordCarcassonne(new Structure.Champ()), 
                                             new BordCarcassonne(new Structure.Champ()),
                                             new BordCarcassonne(new Structure.Champ()),
                                             new BordCarcassonne(new Structure.Champ()),
                                             new BordCarcassonne(new Structure.Ville()), 
                                             "ImagesTuiles/Tuile-CCCCV.png"), 2);
    }
    
}
