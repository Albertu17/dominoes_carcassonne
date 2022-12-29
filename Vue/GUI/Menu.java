package GUI;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Flow;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.MouseInputListener;
import javax.swing.text.FlowView;
import javax.swing.text.AttributeSet.ColorAttribute;

import Modele.Commun.Joueur;
import Modele.Commun.Modele;

import  java.awt.*;
import java.awt.event.MouseEvent;
import java.io.File;
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

        container = new JPanel() ;
        container.setVisible(true);
        container.setLayout(null);
        pane.getContentPane().add(container);

        if (selectGame == null)  selectGame = new SelectGame() ;
    }


    public void play(){
        // lancer la partie
    }

    private class ButtonImageRetour extends JButton{


        ButtonImageRetour(String NameImage){

            try {
                Image img;
                img = ImageIO.read(getClass().getResource("Image/" + NameImage));
                this.setIcon(new ImageIcon(img));
            } catch (IOException e) {
                e.printStackTrace();
            }
            this.setBounds(15,15, 50 , 50);
            this.setOpaque(false);
            this.setContentAreaFilled(false);
            this.setBorderPainted(false);
            this.setVisible(true);
            container.add(this) ; 
        }
    }

    

    class SelectGame{
        
        // JLabel indication ;
        JButton carcassonne ;
        JButton domino ;

        // fermer le jeu
        ButtonImageRetour fermer ;


        SelectGame(){

            // fermer le jeu
            fermer = new ButtonImageRetour("croix.png") ;
            
            fermer.addActionListener(event -> {
                System.exit(0);
            });

            
            // initialisation Button

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
            carcassonne.setLocation(widthFrame/2 -carcassonne.getWidth()/2, heightFrame/2 - 50);
            domino.setLocation(widthFrame/2 -domino.getWidth()/2, heightFrame/2 + 50);
            
        }

        public void changevisibility(boolean visibility){
            carcassonne.setVisible(visibility);
            domino.setVisible(visibility);
            fermer.setVisible(visibility);
        }

        public void nextInterfaceMenu(){
            changevisibility(false);
            if (selectSave == null) selectSave = new SelectSave() ;
            else selectSave.changevisibility(true);


            // set the backgound image 
                // Image background = Toolkit.getDefaultToolkit().createImage("Image/background_" + (carcassonneBoolean? "carcassonne.jpg": "domino.jpg"));
                // ((JFrame)pane).drawImage(background, 0, 0, container);
        }

    }

    class SelectSave{
        JTextField newGame ;

        JPanel conteneurSelectionPartie ;
        JButton lancerlaPartie1 ;
        JButton lancerlaPartie2 ;
        JComboBox<String> listsaveComboBox  ;
        
        String[] listtouteSauvegarde  ;
        
        ButtonImageRetour retour ;
        
        SelectSave(){

            // set button retour
            retour = new ButtonImageRetour("retour50p.png") ;
            
            retour.addActionListener(event -> {
                previousInterfaceMenu() ;
            });



            // autre fonction
            newGame = new JTextField() ;
            lancerlaPartie1 = new JButton();

            newGame.setText("Rentrer le nom de votre nouvelle partie");
            lancerlaPartie1.setText("Lancer la partie");


            // System.out.println(newGAme.getW);
            conteneurSelectionPartie = new JPanel() ;

            newGame.setBounds(200,400, 400, 50);


            container.add(newGame) ;


            // placement de la JComboBox
            addAllSave();
            listsaveComboBox.setBounds(200,600, 400, 100);
            listsaveComboBox.setVisible(true);
            
            // action 
            
            
            lancerlaPartie1.addActionListener(event -> {
                if (true){
                    // pane.setModele(null);
                    nextInterfaceMenu() ;
                }
            });

            newGame.addMouseListener(
                    new MouseInputListener() {
                        String[] aide = new String[]{"Rentrer le nom de votre nouvelle partie", "Nom déjà utilisé"};

                        @Override
                        public void mouseClicked(MouseEvent e) {
                            for (String string : aide) {
                                if (string.equals(newGame.getText())) newGame.setText("") ;
                            }
                            
                        }

                        @Override
                        public void mousePressed(MouseEvent e) {
                            // TODO Auto-generated method stub
                            
                        }

                        @Override
                        public void mouseReleased(MouseEvent e) {
                            // TODO Auto-generated method stub
                            
                        }

                        @Override
                        public void mouseEntered(MouseEvent e) {
                            // TODO Auto-generated method stub
                            
                        }

                        @Override
                        public void mouseExited(MouseEvent e) {
                            // TODO Auto-generated method stub
                            
                        }

                        @Override
                        public void mouseDragged(MouseEvent e) {
                            // TODO Auto-generated method stub
                            
                        }

                        @Override
                        public void mouseMoved(MouseEvent e) {
                            // TODO Auto-generated method stub
                            
                        }

                        
                        
                    }
                   
            );
            


            // affichage possition 
            // displayAllSave();
            setLocationObjet();
            changevisibility(true);
        }

        private boolean isFree(String nom){
            for (String string : listtouteSauvegarde) {
                if (string.equals(nom)) return false ;
            }
            return true ;
        }

        private void addAllSave(){
            // faire en fonction du mode de jeu 

            
            String path = "./Sauvegarde/"+ (carcassonneBoolean? "Carcassonne" : "Domino" ) ;
            
            
            try {
                File[] dir  = (new File(path)).listFiles();

                listtouteSauvegarde = new String[dir.length] ;
                for (int i = 0 ; i < listtouteSauvegarde.length ; i++){
                    listtouteSauvegarde[i] = dir[i].getName() ;
                }

            } catch (Exception e) {
                listtouteSauvegarde[0] = ("Aucun fichier n'a été trouvé !");
            }
            listsaveComboBox  = new JComboBox<String>((listtouteSauvegarde)) ;
            
        }

        private void setLocationObjet(){

            // newGame.setLocation(widthFrame/2 -newGame.getWidth()/2, heightFrame/2 - 50);
            
            // lancerlaPartie.setAlignmentX(widthFrame/2 -lancerlaPartie.getWidth()/2);
            // lancerlaPartie.setAlignmentY(heightFrame/2 +50);

            // ajouter listsave
        }

        public void changevisibility(boolean visibility){
            newGame.setVisible(visibility);
            conteneurSelectionPartie.setVisible(visibility);
            retour.setVisible(visibility);
            // if (visibility) displayAllSave();
            // else list.setVisible(visibility );
            

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
                    if (NameFree()){
                        Joueur j = new Joueur(nom.getText(), isIA, false) ;
                        if (pane.getModele().getPlayers().add(j)){
                            dispPlayer.add( new ConteneurPlayer(j)) ;
                            dispPlayer.revalidate();
                            dispPlayer.repaint();
                        }else{
                            nom.setText("Le nombre maximun de joueur est atteint !");
                        }
                    }else{
                        nom.setText("Nom déjà utilisé");
                    }
                });

                // permet l'effacement du texte pré-écrit lorsque on passe sur le texte
                nom.addMouseListener(
                    new MouseInputListener() {

                        @Override
                        public void mouseClicked(MouseEvent e) {
                            if ( (nom.getText().equals("Le nombre maximun de joueur est atteint !") || nom.getText().equals("Nom déjà utilisé") || nom.getText().equals("Entrer le nom du joueur"))){
                                nom.setText("");
                            }
                            
                        }

                        @Override
                        public void mousePressed(MouseEvent e) {
                            // TODO Auto-generated method stub
                            
                        }

                        @Override
                        public void mouseReleased(MouseEvent e) {
                            // TODO Auto-generated method stub
                            
                        }

                        @Override
                        public void mouseEntered(MouseEvent e) {
                            // TODO Auto-generated method stub
                            
                        }

                        @Override
                        public void mouseExited(MouseEvent e) {
                            // TODO Auto-generated method stub
                            
                        }

                        @Override
                        public void mouseDragged(MouseEvent e) {
                            // TODO Auto-generated method stub
                            
                        }

                        @Override
                        public void mouseMoved(MouseEvent e) {
                            // TODO Auto-generated method stub
                            
                        }

                        
                        
                    }
                   
                );

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

        private void setColor(){
            Color color =  isIA ? Color.GREEN : Color.RED ;
            IA.setBackground(color) ;
        }

        private boolean NameFree(){
            String name = nom.getText() ;
            for (Joueur j : pane.getModele().getPlayers()) {
                if (name.equals(j.getName())) return false ;
            }
            return true ;
        }

    }
        
        private class ConteneurPlayer extends JPanel{
            JButton remove;
            JLabel nom ;
            JButton IA ;

            ConteneurPlayer(Joueur joueur){
                // Joueur joueur = pane.getModele().getPlayers(indexPlayer) ;


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
                    dispPlayer.revalidate();
                    dispPlayer.repaint();
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

        
        ButtonImageRetour retour ;
        ConteneurAddPlayer conteneurAddPlayer ;
        JPanel dispPlayer;

        ManagePlayer(){

            // ajout d'élemtn test dans model
            pane.getModele().addPlayer(new Joueur("damiens", true, false)) ;
            // pane.getModele().addPlayer(new Joueur("piere", false, false));
            // pane.getModele().addPlayer(new Joueur("etienne", true, false));
            System.out.println(pane.getModele().getPlayers().size());

            // bouton retour
                retour = new ButtonImageRetour("retour50p.png") ;
                    
                retour.addActionListener(event -> {
                    previousInterfaceMenu() ;
                });

            
            // Barre ajout de joueur :
                conteneurAddPlayer= new ConteneurAddPlayer() ;
                conteneurAddPlayer.setVisible(true);
                container.add(conteneurAddPlayer) ;
               
                conteneurAddPlayer.setSize(300, 100);
                conteneurAddPlayer.setLocation(200, 300);
                
                
            // les jouers deja present :
                dispPlayer = new JPanel() ;
                dispPlayer.setLayout(new BoxLayout(dispPlayer, BoxLayout.PAGE_AXIS));
                
                for (Joueur joueur : pane.getModele().getPlayers()){
                    dispPlayer.add( new ConteneurPlayer(joueur)) ;
                }

                dispPlayer.setSize(300, 400);
                dispPlayer.setLocation(200, 400);
                dispPlayer.setVisible(true);
                container.add(dispPlayer) ;
                


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
