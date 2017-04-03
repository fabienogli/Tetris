package Tetris;

import Tetris.View.VueTetris;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;

/**
 * Created by Fabien on 22/03/2017.
 */
public class Tetris_App {
    public static String title = "Tetris";

    public static Scene launchTetris() {

        VueTetris tetris = new VueTetris();
        int longueur = tetris.getLongueur();
        int largeur = tetris.getLargeur();
        BackgroundImage myBI = new BackgroundImage(new Image("/img/fond.jpg", longueur, largeur, false, true),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        Pane pane = new Pane();
        pane.setBackground(new Background(myBI));
        pane.getChildren().add(tetris);
        Scene root = new Scene(pane, longueur, largeur);
        //Ajout de la gestion d'évènement clavier a la scene
        root.setOnKeyPressed(tetris.getOnKeyPressed());
        return root;
    }


}
