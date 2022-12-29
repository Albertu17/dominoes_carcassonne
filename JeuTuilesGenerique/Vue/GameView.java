package JeuTuilesGenerique.Vue;

import javax.swing.*;
import java.awt.*;

import JeuTuilesGenerique.Modele.Partie;
import JeuTuilesGenerique.Modele.Tuile;
import JeuTuilesGenerique.Modele.Joueurs.Joueur;

public class GameView extends JFrame{
    
    public Partie partie;
    JPanel conteneurGlobal;
    JPanel bandeauSup;
    JPanel conteneurTitre;
    public JLabel titre;
    JPanel conteneurBoutons;
    JButton infos;
    JButton sauvegarder;
    JPanel bordureGauche;
    JPanel bordureDroite;
    JPanel bandeauInf;
    JLabel credit;
    JPanel coeur;
    JPanel grille;
    JPanel conteneurInfos;
    JPanel conteneurInfosCoup;
    JPanel conteneurPieceAJouer;
    // JPanel conteneurPieceAjouerMilieu;
    // JPanel conteneurPieceAjouerHaut;
    // JPanel conteneurPieceAjouerGauche;
    // JPanel conteneurPieceAjouerDroite;
    // JPanel conteneurPieceAjouerBas;

    public GameView (Partie partie) {
        this.partie = partie;

        // Fenêtre
        setVisible(true);
        setTitle("JEU");
        Dimension size = Toolkit.getDefaultToolkit().getScreenSize(); // Récupère taille de l'écran utilisateur.
        setSize((int) size.getWidth(), (int) size.getHeight()); // Met la fenêtre en plein écran.
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // JPanel conteneurGlobal
        conteneurGlobal = new JPanel();
        conteneurGlobal.setLayout(new BorderLayout());
        this.getContentPane().add(conteneurGlobal);

        // JPanel BandeauSup (nom du jeu + 2 boutons)
        bandeauSup = new JPanel();
        bandeauSup.setLayout(new BoxLayout(bandeauSup, BoxLayout.LINE_AXIS));
        bandeauSup.setBorder(BorderFactory.createEmptyBorder(20,0,10,0));
        conteneurGlobal.add(bandeauSup, BorderLayout.PAGE_START);

            // JPanel conteneurTitre et JLabel titre
            conteneurTitre = new JPanel();
            titre = new JLabel("Jeu à Tuiles");
            titre.setFont(new Font("Arial", Font.BOLD, 36));
            conteneurTitre.add(titre);
            bandeauSup.add(conteneurTitre);
            bandeauSup.add(Box.createHorizontalGlue());

            // JPanel conteneurBouttons, JButton infos et sauvegarder
            conteneurBoutons = new JPanel();
            bandeauSup.add(conteneurBoutons);
            infos = new JButton("?");
            conteneurBoutons.add(infos);
            sauvegarder = new JButton("save");
            conteneurBoutons.add(sauvegarder);
        
        // JPanel bordureGauche
        bordureGauche = new JPanel();
        conteneurGlobal.add(bordureGauche, BorderLayout.LINE_START);

        // JPanel bordureDroite
        bordureDroite = new JPanel();
        conteneurGlobal.add(bordureDroite, BorderLayout.LINE_END);

        // JPanel bandeauInf (sert de bordure en bas + crédits)
        bandeauInf = new JPanel();
        bandeauInf.setLayout(new FlowLayout(FlowLayout.RIGHT));
        credit = new JLabel("Credit: A. Tomasi et T. Poux");
        bandeauInf.add(credit);
        conteneurGlobal.add(bandeauInf, BorderLayout.PAGE_END);
        
        // JPanel coeur
        coeur = new JPanel();
        coeur.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.weighty = 1;
        gbc.fill = GridBagConstraints.BOTH;
        conteneurGlobal.add(coeur, BorderLayout.CENTER);

            // JPanel grille
            grille = new JPanel();
            grille.setLayout(new GridLayout(partie.plateau.hauteur, partie.plateau.largeur, -1, -1));
            grille.setBorder(BorderFactory.createEmptyBorder(2,2,2,2));
            gbc.weightx = 3; // Grandit 3 fois plus vite qu'un weigthx = 1.
            coeur.add(grille, gbc);

            // Remplissage de la grille avec tuiles du plateau.
            for (int i = 0; i < (partie.plateau.hauteur); i++) {
                for (int j = 0; j < partie.plateau.largeur; j++) {
                    grille.add(partie.plateau.plateau[i][j]);
                    // Dès qu'une tuile est ajoutée au GUI, on lui définit son environnement (GameView)
                    // et ses coordonnées si c'est une tuile de la grille.
                    partie.plateau.plateau[i][j].setEnvironnement(this);
                    partie.plateau.plateau[i][j].setCoordonnées(i, j);
                }
            }

            // JPanel conteneurInfos
            conteneurInfos = new JPanel();
            conteneurInfos.setLayout(new GridLayout(partie.joueurs.nbJoueurs() + 1,1,-1,-1));
                // Les différents PanelJoueur
                for (int i = 0; i < partie.joueurs.nbJoueurs(); i++) {
                    conteneurInfos.add(new PanelJoueur(partie.joueurs.players.get(i)));
                }

                // TODO bon visuel de conteneurInfosCoup
                // JPanel conteneurInfosCoup
                conteneurInfosCoup = new JPanel();
                conteneurInfosCoup.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                conteneurInfosCoup.setLayout(new BoxLayout(conteneurInfosCoup, BoxLayout.PAGE_AXIS));
                conteneurInfosCoup.add(new JButton("Turn"));
                Tuile t = partie.aJouer;
                // t.setPreferredSize(new Dimension(20,20));
                t.setBackground(Color.green);
                conteneurInfosCoup.add(t);
                t.setEnvironnement(this);
                // conteneurPieceAJouer.add(new JPanel(), BorderLayout.LINE_START);
                conteneurInfos.add(conteneurInfosCoup);

            gbc.weightx = 1;
            coeur.add(conteneurInfos, gbc);
    }

    // Affiche visuellement la tuile qui est à jouer aux coordonnées indiquées.
    public void updateGrille(Tuile t, int x, int y) {
        grille.remove(x*partie.plateau.largeur+y); // Enlève tuile vide.
        grille.add(t, x*partie.plateau.largeur+y); // Remplace par la tuile jouée.
        grille.repaint(); // Repeint GUI.
        grille.revalidate(); // Revalide GUI.
    }

    public void updateTuileAJouer() {
        partie.nouvelleTuileAjouer();
        Tuile t = partie.aJouer;
        conteneurInfosCoup.add(t);
        t.setEnvironnement(this);
    }

    public class PanelJoueur extends JPanel {
        
        Joueur joueur;

        public PanelJoueur(Joueur joueur) {
            this.joueur = joueur;
            this.add(new JLabel(joueur.nom + " : " + String.valueOf(joueur.score) + "pts"));
            this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        }

    }

    
}
