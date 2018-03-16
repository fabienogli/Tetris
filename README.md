**A propos**  
Le sujet du projet consiste en la création d’une application graphique java (en utilisant JavaFX) des applications Tetris, Blokus et Puzzle.
Cette conception doit respecter le modèle Modèle-Vue-Contrôleur Strict. De plus la conception doit être proposé de façon modulaire.
Au cours de la réalisation de ce projet, j’ai tout d’abord étudié les fonctionnalités nécessaires au bon fonctionnement du logiciel via la réalisation du diagramme d’activité en cours. Ensuite je suis passé à la réalisation du diagramme de classe permettant de répondre aux différentes fonctionnalités. Au préalable de la conception de ce projet, je me suis renseigné sur les différentes fonctionnalités que proposaient JavaFX sur le site d’OpenClassroom et via l’application de calculette proposée par notre professeur.

**Tetris**  
On lance le jeu en appuyant sur la touche entrée ou via le menu en haut de la fenêtre dans l’onglet partie. La pièce apparaît en haut de la grille, à la moitié de sa largeur.
Le score est affiché sur le côté. Chaque ligne effacé rajoute 100 points au score. Lorsqu’une pièce ne peut plus apparaître en haut de la grille, le joueur perd et une fenêtre le signale. 

**Blokus**  
Pour lancer le jeu, il faut tout d’abord sélectionner le nombre de joueur dans le menu dans l’onglet « Lancer Partie ». Le numéro du joueur est affiché en haut à droite de la fenêtre. Le joueur n’a plus qu’à sélectionner une pièce en cliquant sur la boutons comportant le nom de la pièce. La pièce sera affichée dans le prévisualisation de la pièce en haut à gauche de la fenêtre. Pour la déposer dans la grille, il doit appuyer sur Entrée pour l’afficher dans la grille. Pour la déposer définitivement, le joueur doit appuyer sur la touche P, c’est ensuite au tour du joueur suivant de poser sa pièce.
Un joueur n’ayant pas encore posé de pièce ne peut pas poser sa pièce autre part que dans son coin. La pièce apparaît automatiquement dans son coin lorsqu’il l’affiche dans la grille.
Un joueur qui a déjà déposé une pièce doit nécessairement poser sa pièce à l’angle d’une de ses pièces.
