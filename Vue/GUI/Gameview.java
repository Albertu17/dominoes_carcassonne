package GUI;

import java.awt.Dimension;

import javax.swing.* ;
import javax.swing.plaf.DimensionUIResource;

import Modele.Commun.Modele ;

public class Gameview extends JFrame{
    Modele modele ;
    Menu menu ;


    public Gameview(){
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH); 
        setUndecorated(true);
        setSize(400, 400);
        setVisible(true);
    

        // JPanel  container = new JPanel() ;
        // container.setVisible(true);

        // JLabel texte = new JLabel("dams");
        // texte.setLocation(150, 100);
        // setLocationRelativeTo(null);
        // container.add(texte) ;
        // container.setLayout(null);
        // container. add(texte) ;
        // this.getContentPane(). add(container) ;
        // System.out.println( texte.getLocation() ) ;
        // texte.setVisible(true);


        menu = new Menu(this);



        this.setPreferredSize(new Dimension( getWidth(), getHeight())) ;
        pack();
        
    }

    public void setModele(Modele mod){this.modele = mod ;}
    public Modele getModele(){return modele ;}



    public static void main(String[] args) {
        new Gameview() ;
    }

}
