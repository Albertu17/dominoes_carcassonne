package GUI;

import java.awt.Dimension;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

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

        // enregistrer un objet :
            // try
            // {  
            //     //Saving of object in a file
            //     FileOutputStream file = new FileOutputStream("MaParteie");
            //     ObjectOutputStream out = new ObjectOutputStream(file);
                
            //     // Method for serialization of object
            //     out.writeObject(modele);
                
            //     out.close();
            //     file.close();
                
            //     System.out.println("Object has been serialized");
    
            // }
            
            // catch(IOException ex)
            // {
            //     System.out.println(ex);
            // }


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
