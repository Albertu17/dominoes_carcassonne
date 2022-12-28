package GUI;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Flow;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.text.FlowView;
import javax.swing.text.AttributeSet.ColorAttribute;

import Modele.Commun.Joueur;
import Modele.Commun.Modele;

import  java.awt.*;
import java.io.IOException;

public class Menu {
    
    Gameview pane ;
    JPanel container ;
    private int widthFrame ; 
    private int heightFrame ; 
    private boolean carcassonneBoolean ;

    // toutes les interfaces du menu
    SelectGame selectGame ;
    SelectSave selectSave ;
    ManagePlayer managePlayer ;


    Menu(Gameview pane){
        this.pane = pane ;
        pane.setBackground(Color.GREEN);

        widthFrame = pane.getWidth() ;
        heightFrame = pane.getHeight() ;
        // System.out.println(widthFrame +" "+ heightFrame);

        container = new JPanel() ;
        container.setVisible(true);
        // container.setLayout(new BorderLayout());
        container.setLayout(null);
        pane.getContentPane().add(container);
        // container.setBackground(Color.RED);


        if (selectGame == null)  selectGame = new SelectGame() ;
    }


    public void play(){
        // lancer la partie
    }

    

    class SelectGame{
        
        // JLabel indication ;
        JButton carcassonne ;
        JButton domino ;


        SelectGame(){
            
            
            // indication = new JLabel() ;
            carcassonne = new JButton() ;
            domino = new JButton() ;

            // texte
            carcassonne.setText("Carcassonne");
            domino.setText("Domino");


            // définition de la taille
            carcassonne.setBounds(0, 0, 200, 40);
            domino.setBounds(0, 0, 200, 40);
        

            // action 
            carcassonne.addActionListener(event -> {
                carcassonneBoolean = true ;
                nextInterfaceMenu() ;
            });
            
            domino.addActionListener(event -> {
                carcassonneBoolean = false ;
                nextInterfaceMenu() ;
            });
            
            // ajout
            container.add(carcassonne) ;
            container.add(domino) ;
            
            setLocationObjet();
            changevisibility(true);
        }

        private void setLocationObjet(){
            // carcassonne.setAlignmentX(widthFrame/2 -carcassonne.getWidth()/2);
            // carcassonne.setAlignmentY(heightFrame/2 - 50);
            carcassonne.setLocation(widthFrame/2 -carcassonne.getWidth()/2, heightFrame/2 - 50);
            domino.setLocation(widthFrame/2 -domino.getWidth()/2, heightFrame/2 + 50);
            
            // domino.setAlignmentX(widthFrame/2 -domino.getWidth()/2);
            // domino.setAlignmentY(heightFrame/2 +50);
        }

        public void changevisibility(boolean visibility){
            carcassonne.setVisible(visibility);
            domino.setVisible(visibility);
        }

        public void nextInterfaceMenu(){
            changevisibility(false);
            if (selectSave == null) selectSave = new SelectSave() ;
            else selectSave.changevisibility(true);
        }

    }

    class SelectSave{
        JButton newGame ;

        JPanel conteneurSelectionPartie ;
        JButton lancerlaPartie ;
        JList<String> listsave  ;

        JButton retour ;
        
        SelectSave(){

            // set button retour
            retour = new JButton();
            try {
                Image img;
                img = ImageIO.read(getClass().getResource("retour50p.png"));
                retour.setIcon(new ImageIcon(img));
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            retour.setBounds(5,5, 50 , 50);
            retour.setOpaque(false);
            retour.setContentAreaFilled(false);
            retour.setBorderPainted(false);
            retour.setVisible(true);
            container.add(retour) ; 
    
            retour.addActionListener(event -> {
                carcassonneBoolean = true ;
                previousInterfaceMenu() ;
            });



            // autre fonction
            newGame = new JButton() ;
            lancerlaPartie = new JButton();

            newGame.setText("Rentrer le nom de votre nouvelle partie");
            lancerlaPartie.setText("Lancer la partie");

            conteneurSelectionPartie = new JPanel() ;

            newGame.setBounds(0,0, 200, 40);


            container.add(newGame) ;


            
            // action 
            newGame.addActionListener(event -> {
                pane.setModele(new Modele(carcassonneBoolean));
                play();
            });
            
            lancerlaPartie.addActionListener(event -> {
                if (true){
                    // pane.setModele(null);
                    nextInterfaceMenu() ;
                }
            });


            // affichage possition 
            displayAllSave();
            setLocationObjet();
            changevisibility(true);
        }

        private boolean isFree(String nom){
            return false ;
        }

        private void displayAllSave(){
            // faire en focntion du mode de jeu 
            List<String> list = new ArrayList<String>() ;

            if (carcassonneBoolean){
                // cherche dans caracasson 

            }else{
                // cherche dans domino 
            }

            // listsave  = new JList<String>(list.toArray()) ;
            
        }

        private void setLocationObjet(){

            newGame.setLocation(widthFrame/2 -newGame.getWidth()/2, heightFrame/2 - 50);
            
            lancerlaPartie.setAlignmentX(widthFrame/2 -lancerlaPartie.getWidth()/2);
            lancerlaPartie.setAlignmentY(heightFrame/2 +50);

            // ajouter listsave
        }

        public void changevisibility(boolean visibility){
            newGame.setVisible(visibility);
            conteneurSelectionPartie.setVisible(visibility);
            retour.setVisible(visibility);

            displayAllSave();
            

        }

        public void previousInterfaceMenu(){
            changevisibility(false);
            
            selectGame.changevisibility(true);
            
            
        }
        
        public void nextInterfaceMenu(){
            changevisibility(false);
            managePlayer = new ManagePlayer() ;
        }
        
    }
    
    class ManagePlayer{
        ConteneurAddPlayer conteneurAddPlayer ;
        
        private class ConteneurAddPlayer extends JPanel{
            JButton add;
            JTextField nom ;
            JButton IA ;
            boolean isIA ;

            ConteneurAddPlayer(){

            // définir les J
            IA  = new JButton("IA") ;
            isIA = false ;
            setColor();
            

            nom = new JTextField("Entrer le nom du joueur") ;

            add = new JButton("+") ;

            // action des boutons

            IA.addActionListener(event -> {
                isIA = !isIA ;
                setColor();
            });

            add.addActionListener(event -> {
                pane.getModele().getPlayers().remove(joueur) ;
                dispPlayer.remove(this);
            });

            // placer
            setLayout(new FlowLayout()) ;

            add(IA) ;
            add(nom) ;
            add(add) ;
            

            // rendre visible 
            IA.setVisible(true);
            nom.setVisible(true);
            add.setVisible(true);
            setVisible(true );
        }

        public void setColor(){
            Color color =  isIA ? Color.GREEN : Color.RED ;
            IA.setBackground(color) ;
        }

        }
        
        JPanel dispPlayer;
        
        private class ConteneurPlayer extends JPanel{
            JButton remove;
            JLabel nom ;
            JButton IA ;

            ConteneurPlayer(int indexPlayer){
                Joueur joueur = pane.getModele().getPlayers(indexPlayer) ;


                // définir les J
                IA  = new JButton("IA") ;
                setColor(joueur.isIA()) ;

                nom = new JLabel(joueur.getName()) ;

                remove = new JButton("-") ;

                // action des boutons

                IA.addActionListener(event -> {
                    joueur.setIA(! joueur.isIA());
                    setColor(joueur.isIA());
                });

                remove.addActionListener(event -> {
                    pane.getModele().getPlayers().remove(joueur) ;
                    dispPlayer.remove(this);
                });

                // placer
                setLayout(new FlowLayout()) ;

                add(IA) ;
                add(nom) ;
                add(remove) ;
                

                // rendre visible 
                IA.setVisible(true);
                nom.setVisible(true);
                remove.setVisible(true);
                setVisible(true );
            }

            public void setColor(boolean IA_IsON){
                Color color =  IA_IsON ? Color.GREEN : Color.RED ;
                IA.setBackground(color) ;
            }
        }

        
        JButton retour ;

        ManagePlayer(){


            // les jouers deja present
            dispPlayer = new JPanel() ;
            dispPlayer.setLayout(new BoxLayout(dispPlayer, PAGE_AXIS));

            for (int i = 0 ; i < pane.getModele().getPlayers().size() ; i++){
                new ConteneurPlayer(i) ;
            }


        // bouton retour
            retour = new JButton();
            try {
                Image img;
                img = ImageIO.read(getClass().getResource("retour50p.png"));
                retour.setIcon(new ImageIcon(img));
            } catch (IOException e) {
                
                e.printStackTrace();
            }
            retour.setBounds(5,5, 50 , 50);
            retour.setOpaque(false);
            retour.setContentAreaFilled(false);
            retour.setBorderPainted(false);
            retour.setVisible(true);
            container.add(retour) ; 

            retour.addActionListener(event -> {
                carcassonneBoolean = true ;
                previousInterfaceMenu() ;
            });

        }
        
        public void previousInterfaceMenu(){
            changevisibility(false);
            
            selectSave.changevisibility(true);
            
        }
    
        public void nextInterfaceMenu(){
            changevisibility(false);

            // lancement du jeu 
            play();
        }

        public void changevisibility(boolean visibility){
            dispPlayer.setVisible(visibility);
            conteneurAddPlayer.setVisible(visibility);
            retour.setVisible(visibility);
        }
    }
}
