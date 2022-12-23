package GUI;

import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

public class Menu {
    
    Gameview pane ;
    private int widthFrame ; 
    private int heightFrame ; 
    private boolean carcassonneBoolean ;

    // toutes les interfaces du menu
    SelectGame selectGame ;
    SelectSave selectSave ;
    ManagePlayer managePlayer ;


    Menu(Gameview pane){
        this.pane = pane ;

        widthFrame = pane.getWidth() ;
        heightFrame = pane.getHeight() ;
        System.out.println(widthFrame +" "+ heightFrame);

        if (selectGame == null)  selectGame = new SelectGame() ;
    }

    class SelectGame{
        JLabel indication ;
        JButton carcassonne ;
        JButton domino ;

        SelectGame(){
            indication = new JLabel() ;
            carcassonne = new JButton() ;
            domino = new JButton() ;

            // texte
            carcassonne.setText("Carcassonne");
            domino.setText("Domino");

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
            pane.add(carcassonne) ;
            pane.add(domino) ;
            
            setLocationObjet();
            changevisibility(true);
        }

        private void setLocationObjet(){
            carcassonne.setAlignmentX(widthFrame/2 -carcassonne.getWidth()/2);
            carcassonne.setAlignmentY(heightFrame/2 - 50);
            
            domino.setAlignmentX(widthFrame/2 -domino.getWidth()/2);
            domino.setAlignmentY(heightFrame/2 +50);
        }

        public void changevisibility(boolean visibility){
            carcassonne.setVisible(visibility);
            domino.setVisible(visibility);
        }

        public void nextInterfaceMenu(){
            changevisibility(false);
        }

    }

    class SelectSave{
        JButton newGame ;
        JButton lancerlaPartie ;
        JList<String> listsave  ;

        SelectSave(){
            newGame = new JButton() ;
            lancerlaPartie = new JButton();

            newGame.setText("Nouvelle partie");
            lancerlaPartie.setText("Lancer la partie");


            displayAllSave();
            changevisibility(true);
            setLocationObjet();
        }

        private void displayAllSave(){
            // faire en focntion du mode de jeu 
            List<String> list = new ArrayList<String>() ;

            if (carcassonneBoolean){
                // cherche dans caracasson 

            }else{
                // cherche dans domino 
            }

            listsave  = new JList<String>(list.toArray()) ;
            
        }

        private void setLocationObjet(){
            newGame.setAlignmentX(widthFrame/2 -newGame.getWidth()/2);
            newGame.setAlignmentY(heightFrame/2 - 50);
            
            lancerlaPartie.setAlignmentX(widthFrame/2 -lancerlaPartie.getWidth()/2);
            lancerlaPartie.setAlignmentY(heightFrame/2 +50);

            // ajouter listsave
        }

        public void changevisibility(boolean visibility){
            newGame.setVisible(visibility);
            lancerlaPartie.setVisible(visibility);
            listsave.setVisible(visibility);
        }

        public void nextInterfaceMenu(){
            changevisibility(false);
        }

    }

    class ManagePlayer{

    }
}
