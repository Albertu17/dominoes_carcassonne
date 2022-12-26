package JeuTuilesGenerique.Vue;

import javax.swing.*;
import java.awt.*;

import JeuTuilesGenerique.Modele.Partie;

public class GameView extends JFrame{
    
    Partie partie;
    JPanel conteneur;

    public GameView (Partie partie) {
        this.partie = partie;

        // Fenêtre
        setVisible(true);
        setTitle("Jeu");
        Dimension size = Toolkit.getDefaultToolkit().getScreenSize(); // Récupère taille de l'écran utilisateur.
        setSize((int) size.getWidth(), (int) size.getHeight());
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // JPanel conteneur
        conteneur = new JPanel();
        conteneur.setLayout(new BorderLayout()); // Important car si aucun layout n'est précisé, les éléments
        // ajoutés se placent en haut au centre de l'écran.
        this.getContentPane().add(conteneur);
    }
}
