# Projet de POOIG

Lancement du jeu

Le jeu se lance depuis la classe Launcher.java. Lancer l'intégralité de l'interface de jeu (avec menu) est
fait en appelant la fonction launcher() de cette classe depuis une fonction main.
Le menu est constistué d'une interface permettant le choix du jeu (Carcassonne ou Dominos), d'une interface permettant de charger une nouvelle partie (en indiquant son nom) ou une partie sauvegardée, et d'une interface permettant d'ajouter des joueurs avec leur nom puis de lancer une partie du jeu choisi.
Pour lancer directement une partie Carcassonne, une partie Dominos ou une partie sauvegardée sans passer par les paramétrages du menu, il est possible d'appeler les méthodes launchCarcassone(), launchDomino() ou 
launchRunningGame() de Launcher.java en leur passant les arguments nécéssaires (nom de la partie et liste de joueurs si la partie est nouvelle).
Par défaut, la fonction main de Launcher.java appelle launcher(), il suffit donc d'éxécuter Launcher.java
pour lancer l'intégralité de l'interface de jeu.

Utilisation du jeu

Une fois sur l'interface d'une partie, il est possible de la sauvegarder en appuyant sur le bouton "save"
(clore la fenêtre en appuyant sur la croix noire le fait par défaut). Il sera ensuite possible de recharger
cette partie en la selectionnant dans le menu déroulant du menu.

