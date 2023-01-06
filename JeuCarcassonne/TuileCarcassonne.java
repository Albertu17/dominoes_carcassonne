package JeuCarcassonne;

import JeuTuilesGenerique.Modele.Bord;
import JeuTuilesGenerique.Modele.Tuile;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectStreamException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;

public class TuileCarcassonne extends Tuile {
    
    Bord centre;
    transient BufferedImage image;
    String nom ;
    Pion pion ;
    // permet de retrouver la rotation initiale quand on recharge une partie
    int rotation = 0 ;
    private class Pion extends JPanel{
        
        // protected void paintComponent( Graphics g ){
        //     setBackground(Color.BLUE);
        //     g.fillOval( 20, 20, 75, 75 );
        // }



        public void ajusterTaille(Tuile t){
            Point p  = t.getLocation() ;
            int HeightTuile = t.getHeight() ;
            int WidthTuile = t.getWidth() ;
            
            System.out.println(((TuileCarcassonne)t).nom);
            System.out.println(HeightTuile);
            System.out.println(t.getSize());
            setPreferredSize(new Dimension(WidthTuile/10, HeightTuile/10));
            setMinimumSize(getPreferredSize());

        }

        public void ajusterPositionnement(Tuile t){
            Point p  = t.getLocation() ;
            int HeightTuile = t.getHeight() ;
            int WidthTuile = t.getWidth() ;
            // System.out.println(((TuileCarcassonne)t).nom);
            // System.out.println(p);

            setLocation(new Point ((int) p.getX() + HeightTuile/2, (int)p.getY()+30));
            setMinimumSize(getPreferredSize());
        }
    }


    

    

    public TuileCarcassonne(BordCarcassonne[] bords, String chemin) throws IOException {
        super(bords[0], bords[1], bords[2], bords[3]);
        this.centre = bords[4];
        setImage(chemin);

        // setLayout(new  BorderLayout());
        // setLayout(null);

        // p = new Pion();
        // this.add(p, BorderLayout.NORTH) ;
        // // this.add(new JPanel());
        // // this.add(p) ;
        // p.setVisible(true);
        // // p.ajusterTaille(this);
        // // p.ajusterPositionnement(this);
        
        // p.repaint();
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
        if (environnement.getPartie().aJouer.equals(this)){
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
    public void Rotate(boolean sensHoraire){
        // tourne les bords
        super.Rotate(sensHoraire);

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
        rotation = rotation%4 ;
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
        // permet de mettre l'image dans la rotation avant enregistrement
        for (int i = 0 ; i < rotation ; i++){
            Rotate(true);
        }
    }

    private void readObjectNoData() throws ObjectStreamException{ }
}
