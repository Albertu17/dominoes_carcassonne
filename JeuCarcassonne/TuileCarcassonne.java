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
import javax.swing.text.StyledEditorKit.BoldAction;

import JeuTuilesGenerique.Modele.Bord;
import JeuTuilesGenerique.Modele.Tuile;

public class TuileCarcassonne extends Tuile {
    
    Bord centre;
    transient BufferedImage image;
    String nom ;
    Pion pion ;
    boolean choixPion = false ;
    // permet de retrouver la rotation initiale quand on recharge une partie
    int rotation ;
    private class Pion extends JPanel{
        Color color ;

        Pion(Color c){
            setColor(c);
        }
        
        protected void paintComponent( Graphics g ){
            int rayon = Math.min(this.getWidth(),this.getHeight()) ;
            g.setColor(color);
            // met le pion en plein milieu de la case
            g.fillOval( (this.getWidth() -rayon)/2 , (this.getHeight()-rayon)/2, rayon, rayon) ;
        }

        // rend le pion un peu transparent
        public void setColor(Color c ){
            color = new Color(c.getRed(), c.getGreen(), c.getBlue(), 150) ;
        }
    }


    public void placerPion(){
        int[] t = new int[9] ;
        if (((BordCarcassonne)nord).isPion()) t[1] = 1 ;
        else if (((BordCarcassonne)est).isPion()) t[5] = 1 ;
        else if (((BordCarcassonne)ouest).isPion()) t[3] = 1 ;
        else if (((BordCarcassonne)centre).isPion()) t[4] = 1 ;
        else if (((BordCarcassonne)sud).isPion()) t[7] = 1 ;
        // ferme la fonction si il n'y a pas de pion ;
        else return ;
        // t[1] = 1  ;


        setLayout(new  GridLayout(3,3));
      

        pion = new Pion(environnement.getPartie().getJoueurs().joueurAuTrait().getCouleur()); 
        
        pion.setVisible(true) ;

        
        for(int i = 0 ; i < t.length ; i++){
            if (t[i] == 1){
                this.add(pion) ;
            }else{

                // ajoute des paneaux vide et transparents qui permette d'ajuster le layout
                newPanelInsivisble() ;
            }
        }

    }

    private void newPanelInsivisble(){
        JPanel p = new JPanel() ;
        p.setBackground(new Color(0, 0,0,0));
        p.setVisible(true) ;
        this.add(p) ;
    }

    private void newBoutonPlacerPion(Bord bord){
        JButton b = new JButton("") ;
        b.setBackground(new Color(0, 0,0,50));
        b.setVisible(true);
        this.add(b);
        b.addActionListener(event ->{
            System.out.println();
            ((BordCarcassonne)bord).setPion(true);
            removeBoutonPlacagePion();
            placerPion();
            environnement.getPartie().tourSuivant();
        });

    }

    public void BoutonsAjouterPion(){
        choixPion = true ;
        resizeImage();
        setLayout(new  GridLayout(3,3));
        
        // ajouter des boutons pour choisir ou placer le pion
        newPanelInsivisble();
        newBoutonPlacerPion(nord);
        newPanelInsivisble();
        newBoutonPlacerPion(ouest);
        newBoutonPlacerPion(centre);
        newBoutonPlacerPion(est);
        newPanelInsivisble();
        newBoutonPlacerPion(sud);
        newPanelInsivisble();
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
        nom = description ;
    }
    
    private void setImage(String description) throws IOException{   
        image = ImageIO.read(new File("JeuCarcassonne/ImagesTuiles/Tuile-" + description + ".png"));
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
        // rend l'image carré si elle est est dans la partie droite de l'écran
        if (environnement.getPartie().aJouer.equals(this) && ! choixPion){
            this.setSize(Math.min(this.getWidth(), this.getHeight()), Math.min(this.getWidth(), this.getHeight()));
        }
        resizeImage();
        super.paintComponent(g);
        g.drawImage(image, 0, 0, this);
    }

    // permet d'adapter l'image à la taille de la tuille
    public void resizeImage(){
        int newHeigh = Math.max(10, this.getHeight());
        int newWidth = Math.max(this.getWidth(), 10) ;
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

        System.out.println("photo touner");

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
    public void rotateOnlyBordCarca(boolean sensHoraire){
        super.rotate(sensHoraire);
        memoireRotate(sensHoraire);
    }

    private void memoireRotate(boolean sensHoraire){
        if (sensHoraire) rotation += 1 ;
        else rotation -= 1 ;
        // permet de garder l'entier entre 0 et 3
        rotation+= 4 ;
        rotation = rotation%4 ;
    }
    public void rotateOnlyPicture(){
        int holdrota = rotation ;
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
        setImage(nom);
        // rend l'image carrée avant de la tourner
        this.setSize(Math.min(this.getWidth(), this.getHeight()), Math.min(this.getWidth(), this.getHeight()));
        
        rotateOnlyPicture();
    }

    private void readObjectNoData() throws ObjectStreamException{ }
}
