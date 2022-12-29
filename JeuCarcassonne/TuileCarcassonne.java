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

    public TuileCarcassonne(BordCarcassonne nord, BordCarcassonne est, BordCarcassonne sud, BordCarcassonne ouest, BordCarcassonne centre, String chemin) throws IOException {
        super(nord, est, sud, ouest);
        this.centre = centre;
        image = ImageIO.read(new File(chemin));
    }

    public TuileCarcassonne(String description) {
        String chemin = "ImagesTuiles/Tuile-" + description + ".png";
        if (description.contains("b"))
        BordCarcassonne.Structure[] structures = new BordCarcassonne.Structure[5];
        for (int i = 0; i < description.length(); i++) {
            switch(description.charAt(i)) {
                case 'V':

            }
        }
    }
}
