package JeuCarcassonne;

import JeuTuilesGenerique.Modele.Bord;
import JeuTuilesGenerique.Modele.Tuile;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class TuileCarcassonne extends Tuile {
    
    Bord centre;
    BufferedImage image;

    public TuileCarcassonne(BordCarcassonne[] bords, String chemin) throws IOException {
        super(bords[0], bords[1], bords[2], bords[3]);
        this.centre = bords[4];
        image = ImageIO.read(new File(chemin));
    }

    // Prend en argument une string de type VRRVV
    public TuileCarcassonne(String description) throws IOException {
        this(stringToTile(description), "ImagesTuiles/Tuile-" + description + ".png");
    }

    // Une fonction annexe doit être créée car un appel au constructeur this() doit être la première
    // ligne d'un autre constructeur.
    public static BordCarcassonne[] stringToTile(String description) {
        BordCarcassonne[] bords = new BordCarcassonne[5];
        if (description.contains("b")) {
            switch (description.indexOf("b")) {
                case 1:
                    bords[0] = new BordCarcassonne(description.substring(0, 1));
                    description = description.substring(2);
                    break;
                case 2:
                    bords[1] = new BordCarcassonne(description.substring(1, 2));
                    description = description.substring(0, 0) + description.substring(3);
                    break; 
                case 3:
                    bords[2] = new BordCarcassonne(description.substring(2, 3));
                    description = description.substring(0, 1) + description.substring(4);
                    break;
                case 4:
                    bords[3] = new BordCarcassonne(description.substring(3, 4));
                    description = description.substring(0, 2) + description.substring(5);
                    break;
                case 5:
                    bords[4] = new BordCarcassonne(description.substring(4, 5));
                    description = description.substring(0, 3);
                    break;                                                                               
            }
        }
        for (int i = 0; i < description.length(); i++) {
            if (bords[i] == null) bords[i] = new BordCarcassonne(String.valueOf(description.charAt(i)));
            else bords[i+1] = new BordCarcassonne(String.valueOf(description.charAt(i)));
        }
        return bords;
    }
}