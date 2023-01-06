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
import java.util.ArrayList;
import java.util.List;

import JeuTuilesGenerique.Modele.Partie;
import JeuTuilesGenerique.Modele.Tuile;
import JeuTuilesGenerique.Modele.Joueurs.Joueur.PanelJoueur;

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
    public JButton getDefausser() {
        return defausser;
    }

    JButton rotationDroite ;
    public JButton getRotationDroite() {
        return rotationDroite;
    }

    JButton rotationGauche ;
    public JButton getRotationGauche() {
        return rotationGauche;
    }

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
    public List<PanelJoueur> panelJoueurs;
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
                fenetre.remove(conteneurGlobal);
                new Menu(fenetre) ;
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
                panelJoueurs = new ArrayList<PanelJoueur>() ;
                PanelJoueur tmp;
                for (int i = 0; i < partie.joueurs.nbJoueurs(); i++) {
                    tmp =  partie.joueurs.players.get(i).new PanelJoueur();
                    conteneurInfos.add(tmp);
                    panelJoueurs.add(tmp);
                }

                // JPanel conteneurInfosCoup
                conteneurInfosCoup = new JPanel();
                conteneurInfosCoup.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                conteneurInfosCoup.setLayout(new GridLayout(1, 3));
                conteneurInfos.add(conteneurInfosCoup);

                    // JPanel conteneurInfosCoupGauche et JButtons defausser
                    conteneurInfosCoupGauche = new JPanel();
                    conteneurInfosCoupGauche.setLayout(new BorderLayout());
                    conteneurInfosCoupGauche.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                    defausser = new JButton("Jeter la tuile");
                    defausser.addActionListener(evnt -> {
                        partie.tourSuivant();
                    });            
                    conteneurInfosCoupGauche.add(defausser, BorderLayout.CENTER);
                    conteneurInfosCoup.add(conteneurInfosCoupGauche);
            
                    // JPanel conteneurInfosCoupMilieu
                    conteneurInfosCoupMilieu = new JPanel();
                    conteneurInfosCoupMilieu.setLayout(new BorderLayout());
                    conteneurInfosCoupMilieu.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                    conteneurInfosCoup.add(conteneurInfosCoupMilieu);

                        // JPanel vides servant de bords
                        conteneurInfosCoupMilieuBordureGauche = new JPanel();
                        conteneurInfosCoupMilieuBordureHaut = new JPanel();
                        conteneurInfosCoupMilieuBordureDroit = new JPanel();
                        conteneurInfosCoupMilieuBordureBas = new JPanel();
                        conteneurInfosCoupMilieu.add(conteneurInfosCoupMilieuBordureGauche, BorderLayout.LINE_START);
                        conteneurInfosCoupMilieu.add(conteneurInfosCoupMilieuBordureHaut, BorderLayout.PAGE_START);
                        conteneurInfosCoupMilieu.add(conteneurInfosCoupMilieuBordureDroit, BorderLayout.LINE_END);
                        conteneurInfosCoupMilieu.add(conteneurInfosCoupMilieuBordureBas, BorderLayout.PAGE_END);

                        // JPanel JPanel conteneurInfosCoupMilieuCentre
                        conteneurInfosCoupMilieuCentre = new JPanel();
                        conteneurInfosCoupMilieuCentre.setLayout(new BoxLayout(conteneurInfosCoupMilieuCentre, BoxLayout.PAGE_AXIS));
                        conteneurInfosCoupMilieu.add(conteneurInfosCoupMilieuCentre, BorderLayout.CENTER);

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
                            conteneurButtonsRotate.add(rotationGauche);
                            conteneurButtonsRotate.add(rotationDroite);
                            conteneurInfosCoupMilieuCentre.add(conteneurButtonsRotate); 

                            // JPanel conteneurTuileAJouer
                            conteneurTuileAJouer = new JPanel();
                            conteneurTuileAJouer.setLayout(new BoxLayout(conteneurTuileAJouer, BoxLayout.X_AXIS));
                            repaintTuileAJouer();
                            conteneurInfosCoupMilieuCentre.add(conteneurTuileAJouer); 

                    // JPanel conteneurInfosCoupDroite et JLabel tuiles restantes
                    conteneurInfosCoupDroite = new JPanel();
                    conteneurInfosCoupDroite.setLayout(new BorderLayout());
                    conteneurInfosCoupDroite.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                    tuilesRestantes = new JLabel();
                    updateTuilesRestantes();
                    conteneurInfosCoupDroite.add(tuilesRestantes, BorderLayout.CENTER);
                    conteneurInfosCoup.add(conteneurInfosCoupDroite);

                    conteneurInfos.setPreferredSize(conteneurInfos.getSize());
                    conteneurInfos.setMaximumSize(conteneurInfos.getPreferredSize());



                    // si premier joueur IA on lance son tour
                    if (partie.getJoueurs().joueurAuTrait().isIA()){
                        partie.TourIA(); ;
                    }   
    }

    public Partie getPartie() {return partie;}
    public void setLauncher(Launcher l) {fenetre = l;}
    public Launcher getLauncher() {return fenetre;}

    // Affiche visuellement la tuile qui est à jouer aux coordonnées indiquées.
    public void updateGrille(Tuile t, int x, int y) {
        grille.remove((x-1)*(partie.plateau.largeur-2)+(y-1)); // Enlève tuile vide.
        grille.add(t, (x-1)*(partie.plateau.largeur-2)+(y-1)); // Remplace par la tuile jouée.
        grille.repaint(); // Repeint GUI.
        grille.revalidate(); // Revalide GUI.
    }

    // Enlève toutes les tuiles de la grille et les remet suivant celles se trouvant dans le plateau.
    public void repaintGrille() {
        conteneurGrille.removeAll();
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

    public void updatePanelJoueurs() {
        for (int i = 0; i < partie.joueurs.nbJoueurs(); i++) {
            panelJoueurs.get(i).updatePanel();
        }
        conteneurInfos.repaint(); // Repeint GUI.
        conteneurInfos.revalidate(); // Revalide GUI.
    }

    public void repaintTuileAJouer() {
        conteneurTuileAJouer.removeAll();
        conteneurTuileAJouer.add(partie.aJouer);
        partie.aJouer.setEnvironnement(this);
        conteneurTuileAJouer.repaint(); // Repeint GUI.
        conteneurTuileAJouer.revalidate(); // Revalide GUI.
    }

    public void updateTuilesRestantes() {
        tuilesRestantes.setText(String.valueOf(partie.pioche.pioche.size()));
        conteneurInfosCoupDroite.repaint(); // Repeint GUI.
        conteneurInfosCoupDroite.revalidate(); // Revalide GUI.
    }
}
