package Blokus;

import Blokus.View.VueBlokus;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Created by Fabien on 22/03/2017.
 */
public class Blokus_App{
    public static  String title = "Blokus";

    public static Scene launchBlokus(){
        VueBlokus blokus= new VueBlokus();
        int longueur = blokus.getLongueur();
        int largeur = blokus.getLargeur();
        Scene root = new Scene(blokus, longueur, largeur);
        root.setOnKeyPressed(blokus.getOnKeyPressed());
        return root;
    }




}
