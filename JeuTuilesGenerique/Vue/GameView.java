package JeuTuilesGenerique.Vue;

import javax.swing.*;
import java.awt.*;

import JeuTuilesGenerique.Modele.Partie;

public class GameView extends JFrame{
    
    Partie partie;
    JPanel conteneur;
    JPanel conteneurHaut;
    JPanel conteneurTitre;
    JLabel titre;
    JPanel conteneurBoutons;
    JButton infos;
    JButton sauvegarder;
    JPanel conteneurGauche;
    JPanel conteneurDroite;
    JPanel conteneurBas;
    JLabel credit;
    JPanel conteneurMilieu;
    JPanel conteneurMilieuGauche;
    JPanel conteneurMilieuDroite;

    public GameView (Partie partie) {
        this.partie = partie;

        // Fenêtre
        setVisible(true);
        setTitle("JEU");
        Dimension size = Toolkit.getDefaultToolkit().getScreenSize(); // Récupère taille de l'écran utilisateur.
        setSize((int) size.getWidth(), (int) size.getHeight()); // Met la fenêtre en plein écran.
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // JPanel conteneur global
        conteneur = new JPanel();
        conteneur.setLayout(new BorderLayout());
        this.getContentPane().add(conteneur);

        // JPanel conteneur haut de la page (nom du jeu + 2 boutons)
        conteneurHaut = new JPanel();
        conteneurHaut.setLayout(new BoxLayout(conteneurHaut, BoxLayout.LINE_AXIS));
        conteneurHaut.setBorder(BorderFactory.createEmptyBorder(20,0,10,0));
        conteneur.add(conteneurHaut, BorderLayout.PAGE_START);

            // JPanel conteneurTitre et JLabel titre
            conteneurTitre = new JPanel();
            titre = new JLabel("Jeu à Tuiles");
            titre.setFont(new Font("Arial", Font.BOLD, 36));
            conteneurTitre.add(titre);
            conteneurHaut.add(conteneurTitre);
            conteneurHaut.add(Box.createHorizontalGlue());

            // JPanel conteneurBouttons, JButton infos et sauvegarder
            conteneurBoutons = new JPanel();
            conteneurHaut.add(conteneurBoutons);
            infos = new JButton("?");
            conteneurBoutons.add(infos);
            sauvegarder = new JButton("save");
            conteneurBoutons.add(sauvegarder);
        

        // JPanel conteneurGauche (sert de bordure)
        conteneurGauche = new JPanel();
        conteneur.add(conteneurGauche, BorderLayout.LINE_START);

        // JPanel conteneurDroite (sert de bordure)
        conteneurDroite = new JPanel();
        conteneur.add(conteneurDroite, BorderLayout.LINE_END);

        // JPanel conteneurBas (sert de bordure)
        conteneurBas = new JPanel();
        conteneurBas.setLayout(new FlowLayout(FlowLayout.RIGHT));
        credit = new JLabel("Credit: A. Tomasi et T. Poux");
        conteneurBas.add(credit);
        conteneur.add(conteneurBas, BorderLayout.PAGE_END);
        
        // JPanel conteneurMilieu
        conteneurMilieu = new JPanel();
        conteneurMilieu.setLayout(new GridLayout());
        conteneur.add(conteneurMilieu, BorderLayout.CENTER);

            // JPanel conteneurMilieuGauche
            conteneurMilieuGauche = new JPanel();
            conteneurMilieuGauche.setLayout(new GridLayout(partie.plateau.hauteur, partie.plateau.largeur, -1, -1));
            conteneurMilieuGauche.setBorder(BorderFactory.createEmptyBorder(2,2,2,2));
            conteneurMilieu.add(conteneurMilieuGauche);

            for (int i = 0; i < (partie.plateau.hauteur * partie.plateau.largeur); i++) {
                final JPanel panel = new JPanel();
                panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                conteneurMilieuGauche.add(panel);
            }

            // JPanel conteneurMilieuDroite
            conteneurMilieuDroite = new JPanel();
            conteneurMilieu.add(conteneurMilieuDroite);
    }
}
