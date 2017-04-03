package Blokus;

import Blokus.View.VueBlokus;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;

/**
 * Created by Fabien on 22/03/2017.
 */
public class Blokus_App {
    public static String title = "Blokus";

    public static Scene launchBlokus() {
        VueBlokus blokus = new VueBlokus();
        int longueur = blokus.getLongueur();
        int largeur = blokus.getLargeur();
        BackgroundImage myBI = new BackgroundImage(new Image("/img/fond.jpg", longueur, largeur, false, true),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        Pane pane = new Pane();
        pane.setBackground(new Background(myBI));
        pane.getChildren().add(blokus);
        Scene root = new Scene(pane, longueur, largeur);
        root.setOnKeyPressed(blokus.getOnKeyPressed());
        return root;
    }


}
