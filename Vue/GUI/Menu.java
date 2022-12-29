package GUI;

import javax.imageio.ImageIO;
import javax.swing.*;
import Modele.Commun.Joueur;
import Modele.Commun.Modele;

import  java.awt.*;
import  java.awt.event.FocusListener;
import  java.awt.event.FocusEvent ;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

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


    // Objet avec des défintion spécial

    private class ButtonImageRetour extends JButton{

        ButtonImageRetour(String NameImage){
            this(NameImage, new Rectangle(15,15, 50 , 50)) ;
        }

        ButtonImageRetour(String NameImage, Rectangle d){

            try {
                Image img;
                img = ImageIO.read(getClass().getResource("Image/" + NameImage));
                this.setIcon(new ImageIcon(img));
            } catch (IOException e) {
                e.printStackTrace();
            }
            this.setBounds(d);
            this.setOpaque(false);
            this.setContentAreaFilled(false);
            this.setBorderPainted(false);
            this.setVisible(true);
            container.add(this) ; 
        }
    }

    private class FocusPlaceholder implements FocusListener{
        JTextField field ; 
        String[] motplacerholder ;
        String hold ;

        FocusPlaceholder(JTextField field, String[] motplacerholder){
            this.field = field ;
            this.motplacerholder = motplacerholder.clone() ;
            field.setForeground(Color.GRAY);
            
        }

        public void focusGained(FocusEvent e) {
            for (String string : motplacerholder) {
                if (field.getText().equals(string)) {
                    hold = field.getText() ;
                    field.setText("");
                    field.setForeground(Color.BLACK);
                }
                
            }
        }
        @Override
        public void focusLost(FocusEvent e) {
            if (field.getText().isEmpty()) {
                field.setForeground(Color.GRAY);
                field.setText(hold);
            }
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
        JLabel indication ;
        

        String[] aide = new String[]{"Rentrer le nom de votre nouvelle partie", "Nom déjà utilisé"};

        SelectSave(){

            // set button retour
            retour = new ButtonImageRetour("retour50p.png") ;
            
            retour.addActionListener(event -> {
                previousInterfaceMenu() ;
            });



            // autre fonction
            newGame = new JTextField() ;
            lancerlaPartie1 = new JButton();
            lancerlaPartie2 = new JButton();

            newGame.setText("Rentrer le nom de votre nouvelle partie");
            newGame.setForeground(Color.GRAY) ;

            lancerlaPartie1.setText("Charger la partie");
            lancerlaPartie2.setText("Charger la partie");


            conteneurSelectionPartie = new JPanel() ;


            // ajout d' élement de la JComboBox
                addAllSave();

            // setLayout

            conteneurSelectionPartie.setLayout(new GridBagLayout());
            GridBagConstraints c = new GridBagConstraints();
            

            c.fill = GridBagConstraints.HORIZONTAL;
            
                c.gridwidth = 4 ; 
                c.gridx = 0;
                c.gridy = 0;
                conteneurSelectionPartie.add(newGame, c);

            
            c.fill = GridBagConstraints.HORIZONTAL;
                c.gridwidth = 4;
                c.gridx = 0;
                c.gridy = 2;
                conteneurSelectionPartie.add(listsaveComboBox, c);
            
            c.fill = GridBagConstraints.HORIZONTAL;
            c.fill = GridBagConstraints.VERTICAL ;
                c.gridwidth = 1;
                c.gridx = 5;
                c.gridy = 0;
                conteneurSelectionPartie.add(lancerlaPartie1, c);

            
                c.gridwidth = 1;
                c.gridx = 5;
                c.gridy = 2;
                conteneurSelectionPartie.add(lancerlaPartie2, c);
            
           
            // placement indication
            indication = new JLabel("Veuillez créer une partie ou en choisir une déjà existante :") ;

            indication.setSize(widthFrame/3, heightFrame/3);
            indication.setLocation(widthFrame/2 - indication.getWidth()/2, 100);
            container.add(indication) ;
            indication.setVisible(true ) ;

            // placement container

            container.add(conteneurSelectionPartie) ;
            conteneurSelectionPartie.setVisible(true);

            conteneurSelectionPartie.setSize(widthFrame/2, heightFrame/2) ;
            conteneurSelectionPartie.setLocation(widthFrame/2 - conteneurSelectionPartie.getWidth()/2, heightFrame/2- conteneurSelectionPartie.getHeight()/2) ;

            newGame.setVisible(true);
            listsaveComboBox.setVisible(true);
            lancerlaPartie1.setVisible(true);
            lancerlaPartie2.setVisible(true);

            // action 
            
            
            lancerlaPartie1.addActionListener(event -> {
                if (isFree(newGame.getText())){
                    pane.setModele(new Modele(carcassonneBoolean));
                    nextInterfaceMenu() ;
                
                }else{
                    newGame.setText("Nom déjà utilisé");
                    newGame.setForeground(Color.GRAY);
                } 

            });

            lancerlaPartie2.addActionListener(event -> {
                if (listsaveComboBox.getSelectedItem() != null ){
                    try {
                        final FileInputStream fichier = new FileInputStream("./Sauvegarde/"+ (carcassonneBoolean? "Carcassonne/" : "Domino/" ) + listsaveComboBox.getSelectedItem());
                        ObjectInputStream obj = new ObjectInputStream(fichier) ;
                        pane.setModele( (Modele) obj.readObject() );
                        obj.close();
                        nextInterfaceMenu();
                    } catch (Exception e) {
                    }
                }   

            });

            newGame.addFocusListener(new FocusPlaceholder(newGame, aide));

           


            changevisibility(true);
        }

        private boolean isFree(String nom){
            for (String string : listtouteSauvegarde) {
                if (string.equals(nom)) return false ;
            }
            for (String string : aide) {
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

        public void changevisibility(boolean visibility){
            newGame.setVisible(visibility);
            conteneurSelectionPartie.setVisible(visibility);
            retour.setVisible(visibility);
            indication.setVisible(visibility);
            

        }

        public void previousInterfaceMenu(){
            changevisibility(false);
            
            selectGame = new SelectGame() ;
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

            String[] aide = new String[]{"Nom déjà utilisé", "Le nombre maximun de joueur est atteint !", "Entrer le nom du joueur"} ;

            ConteneurAddPlayer(){

                // définir les J
                IA  = new JButton("IA") ;
                isIA = false ;
                setColor();
                

                nom = new JTextField("Entrer le nom du joueur") ;
                nom.setForeground(Color.GRAY);


                add = new JButton("+") ;

                // action des boutons

                IA.addActionListener(event -> {
                    isIA = !isIA ;
                    setColor();
                });

                add.addActionListener(event -> {
                    if (NameFree()){
                        Joueur j = new Joueur(nom.getText(), isIA, false) ;
                        if (pane.getModele().addPlayer(j)){
                            dispPlayer.add( new ConteneurPlayer(j)) ;
                            dispPlayer.revalidate();
                            dispPlayer.repaint();
                        }else{
                            nom.setText("Le nombre maximun de joueur est atteint !");
                            nom.setForeground(Color.GRAY);
                        }
                    }else{
                        nom.setText("Nom déjà utilisé");
                        nom.setForeground(Color.GRAY);
                    }
                });

                // permet l'effacement du texte pré-écrit lorsque on passe sur le texte
                nom.addFocusListener(new FocusPlaceholder(nom, aide));

    
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
            if (name.equals("")) return false ;
            

            System.out.println(pane.getModele() == null);
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

        JPanel panelmanage ;
        JLabel IndicationAjout ;
        JLabel IndicationPresent ;

        // ButtonImageRetour play ;
        JButton play ;
    

        ManagePlayer(){

            // bouton retour
                retour = new ButtonImageRetour("retour50p.png") ;
                    
                retour.addActionListener(event -> {
                    previousInterfaceMenu() ;
                });

            
            
            
            // Barre ajout de joueur :
                conteneurAddPlayer= new ConteneurAddPlayer() ;
                conteneurAddPlayer.setVisible(true);
                container.add(conteneurAddPlayer) ;
               
                conteneurAddPlayer.setSize(widthFrame/3, 100);
                conteneurAddPlayer.setLocation(widthFrame/2 -conteneurAddPlayer.getWidth()/2, heightFrame/4);
                
                conteneurAddPlayer.nom.setSize(100, conteneurAddPlayer.IA.getWidth());
                
            // les joueurs deja present :
                dispPlayer = new JPanel() ;
                dispPlayer.setLayout(new BoxLayout(dispPlayer, BoxLayout.PAGE_AXIS));
                
                for (Joueur joueur : pane.getModele().getPlayers()){
                    dispPlayer.add( new ConteneurPlayer(joueur)) ;
                }

                
                dispPlayer.setSize(widthFrame/3, 300);
                dispPlayer.setLocation(widthFrame/2 -dispPlayer.getWidth()/2, heightFrame/2);
                
                dispPlayer.setVisible(true);
                container.add(dispPlayer) ;
                
                
                
            // Texte d'aide :
                IndicationAjout = new JLabel("Ajouter un nouveau joueur :") ;
                IndicationPresent = new JLabel("Liste des joueurs de la partie : ") ;
                
                IndicationAjout.setSize(widthFrame/3, 100);
                IndicationAjout.setLocation(widthFrame/2 -IndicationAjout.getWidth()/2, heightFrame/4-100);
                
                IndicationPresent.setSize(widthFrame/3, 100);
                IndicationPresent.setLocation(widthFrame/2 -IndicationPresent.getWidth()/2, heightFrame/2-100);

                container.add(IndicationAjout) ;
                container.add(IndicationPresent) ;

            // Boutton play :
                    // play = new ButtonImageRetour("play.png", new Rectangle(50, 50, widthFrame-50 , heightFrame/2  )) ;
                    play = new JButton("play");  
                
                    play.setSize(50,50);
                    play.setLocation((widthFrame*5 )/6, heightFrame/2  );
                    container.add(play) ;

                    play.addActionListener(event -> {
                        container.setVisible(false);
                        changevisibility(false);
                        play() ;
                    });

            changevisibility(true);
        }
        
        
        public void previousInterfaceMenu(){
            changevisibility(false);
            selectSave = new SelectSave() ;
            
        }
    

        public void changevisibility(boolean visibility){
            dispPlayer.setVisible(visibility);
            conteneurAddPlayer.setVisible(visibility);
            retour.setVisible(visibility);
            IndicationAjout.setVisible(visibility);
            IndicationPresent.setVisible(visibility);
            play.setVisible(visibility);
        }
    }
}