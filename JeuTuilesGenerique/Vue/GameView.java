package JeuTuilesGenerique.Vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.io.Serializable;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.lang.Object;

import JeuTuilesGenerique.Modele.Partie;
import JeuTuilesGenerique.Modele.Tuile;

public class GameView implements Serializable{

    // Launcher fenetreGraphique ;
    
    public Partie partie;
    Launcher fenetre ;
    

    public JPanel conteneurGlobal;
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
    JPanel conteneurGrille;
    JPanel grille;
    JPanel conteneurInfos;
    JPanel conteneurInfosCoup;
    JPanel conteneurTuileAJouer;
    JButton retourMenu ;
    JButton quitter ;
    JButton defausser ;
    JButton rotationDroite ;
    JButton rotationGauche ;
    JPanel conteneurButtonsRotate ;
    JPanel conteneurInfosCoupMilieu;
    JPanel conteneurInfosCoupMilieuBordureGauche;
    JPanel conteneurInfosCoupMilieuBordureHaut;
    JPanel conteneurInfosCoupMilieuBordureDroit;
    JPanel conteneurInfosCoupMilieuBordureBas;
    JPanel conteneurInfosCoupGauche;
    JPanel conteneurInfosCoupDroite;
    JLabel tuilesRestantes;
    JPanel conteneurInfosCoupMilieuCentre;
    // JPanel conteneurPieceAjouerMilieu;
    // JPanel conteneurPieceAjouerHaut;
    // JPanel conteneurPieceAjouerGauche;
    // JPanel conteneurPieceAjouerDroite;
    // JPanel conteneurPieceAjouerBas;

    public GameView(Partie partie){

        this.partie = partie;
        partie.setGui(this);
        // JPanel conteneurGlobal
        conteneurGlobal = new JPanel();
        conteneurGlobal.setLayout(new BorderLayout());

        // JPanel BandeauSup (nom du jeu + 2 boutons)
        bandeauSup = new JPanel();
        bandeauSup.setLayout(new BoxLayout(bandeauSup, BoxLayout.LINE_AXIS));
        bandeauSup.setBorder(BorderFactory.createEmptyBorder(20,0,10,0));
        conteneurGlobal.add(bandeauSup, BorderLayout.PAGE_START);

        // Bouton quiter, retour 
            quitter = new ButtonImage("croix.png", new Rectangle(40,40, 0, 0 )); 
            retourMenu = new ButtonImage("retour50p.png", new Rectangle(40,40, 0, 0 ));
            bandeauSup.add(quitter);
            bandeauSup.add(retourMenu);

            // actions JButtons quitter et menu 
            quitter.addActionListener(event -> {
                partie.save();
                System.exit(0);
            });
            retourMenu.addActionListener(event -> {
                partie.save() ;
                fenetre.removeAll();
                new Menu(fenetre) ;
                // conteneurGlobal.setVisible(false);
                // new Menu(conteneurGlobal.getRootPane()) ; // TODO décommenter ?
            });

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

            // ajout de l'action de sauvegarder :
                sauvegarder.addActionListener(event -> {
                    partie.save() ;
                });
        
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

            // JPanel conteneurGrille
            conteneurGrille = new JPanel();
            conteneurGrille.setLayout(new BoxLayout(conteneurGrille, BoxLayout.X_AXIS));
            conteneurGrille.setBorder(BorderFactory.createEmptyBorder(2,2,2,2));
            gbc.weightx = 3; // Grandit 3 fois plus vite qu'un weigthx = 1.
            coeur.add(conteneurGrille, gbc);

            // JPanel grille
            repaintGrille();

            // JPanel conteneurInfos
            conteneurInfos = new JPanel();
            conteneurInfos.setLayout(new GridLayout(partie.joueurs.nbJoueurs() + 1,1,-1,-1));
            gbc.weightx = 1;
            coeur.add(conteneurInfos, gbc);

                // Les différents PanelJoueur
                repaintPanelJoueurs();

                // JPanel conteneurInfosCoup
                conteneurInfosCoup = new JPanel();
                conteneurInfosCoup.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                conteneurInfosCoup.setLayout(new GridBagLayout());
                conteneurInfos.add(conteneurInfosCoup);
                GridBagConstraints gbc2 = new GridBagConstraints();
                gbc2.fill = GridBagConstraints.BOTH;

                    // JPanel conteneurInfosCoupGauche et JButtons defausser
                    conteneurInfosCoupGauche = new JPanel();
                    conteneurInfosCoupGauche.setLayout(new FlowLayout());
                    conteneurInfosCoupGauche.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                    defausser = new JButton("Jeter la tuile");
                    defausser.addActionListener(evnt -> {
                        // TODO au tour du suivant
                    });            
                    conteneurInfosCoupGauche.add(defausser);
                    gbc2.weighty = 1;
                    gbc2.anchor = GridBagConstraints.LINE_START;
                    conteneurInfosCoup.add(conteneurInfosCoupGauche, gbc2);
            
                    // JPanel conteneurInfosCoupMilieu
                    conteneurInfosCoupMilieu = new JPanel();
                    conteneurInfosCoupMilieu.setLayout(new BorderLayout());
                    conteneurInfosCoupMilieu.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                    gbc2.weighty = 3;
                    gbc2.anchor = GridBagConstraints.CENTER;
                    conteneurInfosCoup.add(conteneurInfosCoupMilieu, gbc2);

                        // JPanel JPanel conteneurInfosCoupMilieuCentre
                        conteneurInfosCoupMilieuCentre = new JPanel();
                        conteneurInfosCoupMilieuCentre.setLayout(new BoxLayout(conteneurInfosCoupMilieuCentre, BoxLayout.PAGE_AXIS));
                        JPanel conteneurInfosCoupMilieuCentreBordureGauche = new JPanel();
                        JPanel conteneurInfosCoupMilieuCentreBordureHaut = new JPanel();
                        JPanel conteneurInfosCoupMilieuCentreBordureDroite = new JPanel();
                        JPanel conteneurInfosCoupMilieuCentreBordureBas = new JPanel();
                        conteneurInfosCoupMilieu.add(conteneurInfosCoupMilieuCentre, BorderLayout.CENTER);
                        conteneurInfosCoupMilieu.add(conteneurInfosCoupMilieuCentreBordureGauche, BorderLayout.LINE_START);
                        conteneurInfosCoupMilieu.add(conteneurInfosCoupMilieuCentreBordureHaut, BorderLayout.PAGE_START);
                        conteneurInfosCoupMilieu.add(conteneurInfosCoupMilieuCentreBordureDroite, BorderLayout.LINE_END);
                        conteneurInfosCoupMilieu.add(conteneurInfosCoupMilieuCentreBordureBas, BorderLayout.PAGE_END);

                            // JPanel conteneurButtonsRotate et JButtons rotationDroite et rotationGauche
                            conteneurButtonsRotate = new JPanel() ;
                            conteneurButtonsRotate.setLayout(new FlowLayout());
                            rotationDroite = new JButton("Rotation à droite");
                            rotationGauche = new JButton("Rotation à gauche");
                            rotationDroite.addActionListener(event -> {
                                partie.aJouer.Rotate(true) ;
                            });
                            rotationGauche.addActionListener(event -> {
                                partie.aJouer.Rotate(false) ;
                            });
                            conteneurButtonsRotate.add(rotationDroite);
                            conteneurButtonsRotate.add(rotationGauche);
                            conteneurInfosCoupMilieuCentre.add(conteneurButtonsRotate); 

                            // JPanel conteneurTuileAJouer
                            conteneurTuileAJouer = new JPanel();
                            conteneurTuileAJouer.setLayout(new BoxLayout(conteneurTuileAJouer, BoxLayout.X_AXIS));
                            repaintTuileAJouer();
                            conteneurInfosCoupMilieuCentre.add(conteneurTuileAJouer); 

                    // JPanel conteneurInfosCoupDroite et JLabel tuiles restantes
                    conteneurInfosCoupDroite = new JPanel();
                    conteneurInfosCoupDroite.setLayout(new FlowLayout());
                    conteneurInfosCoupDroite.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                    tuilesRestantes = new JLabel();
                    updateTuilesRestantes();
                    conteneurInfosCoupDroite.add(tuilesRestantes);
                    gbc2.weighty = 1;
                    gbc2.anchor = GridBagConstraints.LINE_END;
                    conteneurInfosCoup.add(conteneurInfosCoupDroite, gbc2);



            // TODO rechanger plus atrd quand ça sera implanter
            // Tuile t = partie.aJouer;
            // partie.aJouer = partie.pioche.pickOne();
            // // t.setPreferredSize(new Dimension(20,20));
            // conteneurInfosCoup.add(partie.aJouer);
            // partie.aJouer.setEnvironnement(this);
            // // conteneurPieceAJouer.add(new JPanel(), BorderLayout.LINE_START);
            // conteneurInfos.add(conteneurInfosCoup);
                // // TODO bon visuel de conteneurInfosCoup
                // // JPanel conteneurInfosCoup
                // conteneurInfosCoup = new JPanel();
                // conteneurInfosCoup.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                // conteneurInfosCoup.setLayout(new BoxLayout(conteneurInfosCoup, BoxLayout.PAGE_AXIS));
                // conteneurInfosCoup.add(new JButton("Turn"));
                // // TODO rechanger plus atrd quand ça sera implanter
                // // Tuile t = partie.aJouer;
                // Tuile t = partie.pioche.pickOne();
                // // t.setPreferredSize(new Dimension(20,20));
                // conteneurInfosCoup.add(t);
                // t.setEnvironnement(this);
                // // conteneurPieceAJouer.add(new JPanel(), BorderLayout.LINE_START);
                // conteneurInfos.add(conteneurInfosCoup);
    }

    public Partie getPartie() {return partie;}

    // Affiche visuellement la tuile qui est à jouer aux coordonnées indiquées.
    public void updateGrille(Tuile t, int x, int y) {
        grille.remove((x-1)*(partie.plateau.largeur-2)+(y-1)); // Enlève tuile vide.
        grille.add(t, (x-1)*(partie.plateau.largeur-2)+(y-1)); // Remplace par la tuile jouée.
        grille.repaint(); // Repeint GUI.
        grille.revalidate(); // Revalide GUI.
    }

    // Enlève toutes les tuiles de la grille et les remets suivant celles se trouvant dans le plateau.
    public void repaintGrille() {
        grille = new JPanel();
        grille.setLayout(new GridLayout(partie.plateau.hauteur-2, partie.plateau.largeur-2, -1, -1));
        conteneurGrille.add(grille);
        // Remplissage de la grille avec tuiles non-extérieures du plateau.
        for (int i = 1; i < (partie.plateau.hauteur - 1); i++) {
            for (int j = 1; j < (partie.plateau.largeur - 1); j++) {
                grille.add(partie.plateau.plateau[i][j]);
                // Dès qu'une tuile est ajoutée au GUI, on lui définit son environnement (GameView)
                // et ses coordonnées si c'est une tuile de la grille.
                partie.plateau.plateau[i][j].setEnvironnement(this);
                partie.plateau.plateau[i][j].setCoordonnées(i, j);
            }
        }
        grille.repaint(); // Repeint GUI.
        grille.revalidate(); // Revalide GUI.
    }

    public void repaintPanelJoueurs() {
        conteneurInfos.removeAll();
        for (int i = 0; i < partie.joueurs.nbJoueurs(); i++) {
            conteneurInfos.add(partie.joueurs.players.get(i).new PanelJoueur());
            grille.repaint(); // Repeint GUI.
            grille.revalidate(); // Revalide GUI.
        }
    }

    public void repaintTuileAJouer() {
        conteneurTuileAJouer.removeAll();
        conteneurTuileAJouer.add(partie.aJouer.clone());
        partie.aJouer.setEnvironnement(this);
        grille.repaint(); // Repeint GUI.
        grille.revalidate(); // Revalide GUI.
    }

    public void updateTuilesRestantes() {
        tuilesRestantes.setText(String.valueOf(partie.pioche.pioche.size()));
        grille.repaint(); // Repeint GUI.
        grille.revalidate(); // Revalide GUI.
    }

    public void setFenetre(Launcher fenetre) {
        this.fenetre = fenetre;
    }

    public Launcher getFenetre() {
        return fenetre;
    }
}
