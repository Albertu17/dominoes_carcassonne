package JeuTuilesGenerique.Vue;

import javax.swing.JFrame;
import java.awt.* ; 

public class Fenetre extends JFrame{
    
    public Fenetre(){
        setVisible(true);
        setTitle("JEU");
        Dimension size = Toolkit.getDefaultToolkit().getScreenSize(); // Récupère taille de l'écran utilisateur.
        setSize((int) size.getWidth(), (int) size.getHeight()); // Met la fenêtre en plein écran.
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
}
