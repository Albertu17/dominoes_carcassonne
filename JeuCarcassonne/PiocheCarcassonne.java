package JeuCarcassonne;

import java.io.IOException;
import JeuTuilesGenerique.Modele.Pioche;

public class PiocheCarcassonne extends Pioche{

    public PiocheCarcassonne() throws IOException {
        super(); // Création d'une arraylist de tuiles.
        // Ajout à la pioche des 24 différents types de tuiles en leur nombre correspondant.
        addSeveralTimes(new TuileCarcassonne("CCCCV"), 4); // Abbaye
        addSeveralTimes(new TuileCarcassonne("CCRCV"), 2);
        addSeveralTimes(new TuileCarcassonne("CCRRR"), 9);
        addSeveralTimes(new TuileCarcassonne("CRRRV"), 4);
        addSeveralTimes(new TuileCarcassonne("CVbCVV"), 2);
        addSeveralTimes(new TuileCarcassonne("CVCVC"), 3);
        addSeveralTimes(new TuileCarcassonne("CVCVV"), 2);
        addSeveralTimes(new TuileCarcassonne("RCRCR"), 8);
        addSeveralTimes(new TuileCarcassonne("RRRRV"), 1);
        addSeveralTimes(new TuileCarcassonne("VbCCVV"), 2);
        addSeveralTimes(new TuileCarcassonne("VbRRVR"), 2); // TODO Ville aussi au centre
        addSeveralTimes(new TuileCarcassonne("VbVCVV"), 1);
        addSeveralTimes(new TuileCarcassonne("VbVRVV"), 1);
        addSeveralTimes(new TuileCarcassonne("VCCCC"), 5);
        addSeveralTimes(new TuileCarcassonne("VCCVV"), 3);
        addSeveralTimes(new TuileCarcassonne("VCRRR"), 3);
        addSeveralTimes(new TuileCarcassonne("VRCRR"), 4);
        addSeveralTimes(new TuileCarcassonne("VRRCR"), 3);
        addSeveralTimes(new TuileCarcassonne("VRRRR"), 3);
        addSeveralTimes(new TuileCarcassonne("VRRVR"), 3); // TODO Ville aussi au centre
        addSeveralTimes(new TuileCarcassonne("VVCCC"), 2);
        addSeveralTimes(new TuileCarcassonne("VVCVV"), 3);
        addSeveralTimes(new TuileCarcassonne("VVRVV"), 1);
        addSeveralTimes(new TuileCarcassonne("VVVVVb"), 1);                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                       
    }
}
