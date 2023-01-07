package JeuCarcassonne;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectStreamException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JPanel;

import JeuTuilesGenerique.Modele.Bord;
import JeuTuilesGenerique.Modele.Tuile;

public class TuileCarcassonne extends Tuile {
    
    Bord centre;
    transient BufferedImage image;
    String description;
    Pion pion;
    boolean choixPion = false;
    // Permet de retrouver la rotation initiale quand on recharge une partie.
    int rotation;

    public void placerPion(){
        int[] t = new int[9] ;
        if (((BordCarcassonne)nord).contientPion()) t[1] = 1 ;
        else if (((BordCarcassonne)est).contientPion()) t[5] = 1 ;
        else if (((BordCarcassonne)ouest).contientPion()) t[3] = 1 ;
        else if (((BordCarcassonne)centre).contientPion()) t[4] = 1 ;
        else if (((BordCarcassonne)sud).contientPion()) t[7] = 1 ;
        // ferme la fonction si il n'y a pas de pion ;
        else return;
        // Layout de la tuile
        setLayout(new  GridLayout(3,3));
      
        pion = new Pion(environnement.getPartie().getJoueurs().joueurAuTrait().getCouleur()); 
        pion.setVisible(true) ;

        for(int i = 0 ; i < t.length ; i++){
            if (t[i] == 1) this.add(pion);
            else this.add(PanelInsivisble()); // ajoute des paneaux vides et transparents qui permettent d'ajuster le layout.
        }
    }

    private JPanel PanelInsivisble(){
        JPanel p = new JPanel() ;
        p.setBackground(new Color(0, 0,0,0));
        p.setVisible(true) ;
        return p;
    }

    private JButton BoutonPlacerPion(Bord bord){
        JButton b = new JButton();
        b.setBackground(new Color(0, 0,0,50));
        b.setVisible(true);
        b.addActionListener(event ->{
            ((BordCarcassonne)bord).setPion(true);
            removeBoutonPlacagePion();
            placerPion();
            environnement.getPartie().getJoueurs().joueurAuTrait().enleverUnPiont();
            environnement.getPartie().tourSuivant();
        });
        return b;
    }

    public void BoutonsAjouterPion(){
        choixPion = true ;
        resizeImage();
        setLayout(new  GridLayout(3,3));
        
        // ajouter des boutons pour choisir où placer le pion
        add(PanelInsivisble());
        add(BoutonPlacerPion(nord));
        add(PanelInsivisble());
        add(BoutonPlacerPion(ouest));
        if (description.equals("CCCCV")) add(BoutonPlacerPion(centre));
        else add(PanelInsivisble());
        add(BoutonPlacerPion(est));
        add(PanelInsivisble());
        add(BoutonPlacerPion(sud));
        add(PanelInsivisble());
    }

    public void removeBoutonPlacagePion(){
        this.removeAll();
        choixPion = false ;
    }

    

    public TuileCarcassonne(BordCarcassonne[] bords, String chemin) throws IOException {
        super(bords[0], bords[1], bords[2], bords[3]);
        rotation = 0 ;
        this.centre = bords[4];
        setImage(chemin);
    }


    // Prend en argument une string de type VRRVV
    public TuileCarcassonne(String description) throws IOException {
        this(stringToTile(description), description);
        this.description = description;
    }
    
    private void setImage(String description){  
        try {    
            image = ImageIO.read(new File("JeuCarcassonne/ImagesTuiles/Tuile-" + description + ".png"));
        } catch (Exception e) {
            System.out.println(e);
        } 
    }

    // Une fonction annexe doit être créée car un appel au constructeur this() doit être la première
    // ligne d'un autre constructeur.
    public static BordCarcassonne[] stringToTile(String description) {
        BordCarcassonne[] bords = new BordCarcassonne[5];
        if (description.contains("b")) {
            switch (description.indexOf("b")) {
                case 1:
                    bords[0] = new BordCarcassonne(description.substring(0, 2));
                    description = description.substring(2);
                    break;
                case 2:
                    bords[1] = new BordCarcassonne(description.substring(1, 3));
                    description = description.substring(0, 1) + description.substring(3);
                    break; 
                case 3:
                    bords[2] = new BordCarcassonne(description.substring(2, 4));
                    description = description.substring(0, 2) + description.substring(4);
                    break;
                case 4:
                    bords[3] = new BordCarcassonne(description.substring(3, 5));
                    description = description.substring(0, 3) + description.substring(5);
                    break;
                case 5:
                    bords[4] = new BordCarcassonne(description.substring(4, 6));
                    description = description.substring(0, 4);
                    break;                                                                               
            }
        }
        for (int i = 0; i < description.length(); i++) {
            if (bords[i] == null) bords[i] = new BordCarcassonne(String.valueOf(description.charAt(i)));
            else bords[i+1] = new BordCarcassonne(String.valueOf(description.charAt(i)));
        }
        return bords;
    }

    // Rajoute l'image récupérée sur la tuile, sans même qu'on ait à appeler cette fonction.
    protected void paintComponent(Graphics g) {
        // Rend l'image carrée si elle est est dans la partie droite de l'écran (tuile à jouer).
        if (environnement.getPartie().aJouer.equals(this) && !choixPion){
            this.setSize(Math.min(this.getWidth(), this.getHeight()), Math.min(this.getWidth(), this.getHeight()));
        }
        resizeImage();
        super.paintComponent(g);
        g.drawImage(image, 0, 0, this);
    }

    // permet d'adapter l'image à la taille de la tuille
    public void resizeImage(){
        System.out.println();
        int newHeigh = Math.max(100, this.getHeight());
        int newWidth = Math.max(this.getWidth(), 100) ;
        Image temp = image.getScaledInstance(newWidth, newHeigh, Image.SCALE_SMOOTH);
        image = new BufferedImage(newWidth, newHeigh, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = image.createGraphics();
        g2d.drawImage(temp, 0, 0, null);
        g2d.dispose();
        repaint();
    }

    // tourner l'image dans le GUI
    public void rotate(boolean sensHoraire){
        // tourne les bords
        super.rotate(sensHoraire);

        // pour la sauvegarde
        memoireRotate(sensHoraire);

        int newHeigh = image.getWidth();
        int newWidth = image.getHeight();
        int typeOfImage = image.getType();

        BufferedImage temp = new BufferedImage(newHeigh, newWidth, typeOfImage);
        Graphics2D graphics2D = temp.createGraphics();
        graphics2D.rotate( sensHoraire ? Math.PI/2 : - Math.PI/2,  newHeigh / 2, newWidth / 2);
        graphics2D.drawImage(image, null, 0, 0);
        image = temp ;
        resizeImage();
    }

    private void memoireRotate(boolean sensHoraire){
        if (sensHoraire) rotation += 1 ;
        else rotation -= 1 ;
        // permet de garder l'entier entre 0 et 3
        rotation+= 4 ;
        rotation = rotation%4 ;
    }

    public void remettreImage(){
        setImage(description);
        int holdrota = rotation ;
        // rend l'image carrée avant de la tourner
        this.setSize(Math.min(this.getWidth(), this.getHeight()), Math.min(this.getWidth(), this.getHeight()));
        // permet de mettre l'image dans la rotation avant enregistrement
        for (int i = 0 ; i < holdrota ; i++){
            rotate(true);
        }
        rotation = holdrota ;
    }

    // enregistrement spécial (Serializable), pour eviter les problème et réduire la taille de sauvegarde
    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        setImage(description);
        int holdRota = rotation ;
        // rend l'image carrée avant de la tourner
        this.setSize(Math.min(this.getWidth(), this.getHeight()), Math.min(this.getWidth(), this.getHeight()));
        // permet de mettre l'image dans la rotation avant enregistrement
        for (int i = 0 ; i < holdRota ; i++){
            rotate(true);
        }
        rotation = holdRota ;
    }
}
