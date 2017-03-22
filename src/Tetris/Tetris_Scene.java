package Tetris;

import Tetris.View.VueTetris;
import javafx.scene.Scene;

/**
 * Created by Fabien on 22/03/2017.
 */
public class Tetris_Scene {
    public static String title = "Tetris";

    public static Scene launchTetris(){
        VueTetris tetris = new VueTetris();
        int longueur = tetris.getLongueur();
        int largeur = tetris.getLargeur();
        Scene root = new Scene(tetris, longueur, largeur);
        //Ajout de la gestion d'évènement clavier a la scene
        root.setOnKeyPressed(tetris.getOnKeyPressed());
        return root;
    }
}
