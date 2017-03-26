package Blokus.View;

import Base.Controler.GrilleControler;
import Base.Model.Piece;
import Base.View.VuePlateau;
import Blokus.Model.TypePiece;
import Tetris.View.VueGrille_Tetris;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

import java.util.ArrayList;

/**
 * Created by Fabien on 22/03/2017.
 */
public class VueBlokus extends VuePlateau{

    private int nbJoueur;

    public VueBlokus() {
        super("Blokus",600,600, Color.DIMGREY,10,10);
    }

    @Override
    protected void initiateGrille() {
        double XposGrille = hauteur/3;
        double YposGrille = longueur/4;
        super.initiateGrille();
        vueGrille = new VueGrilleBlokus(XposGrille, YposGrille);
    }

//    @Override
//    protected void setControlClavier() {
//        GrilleControler grilleControler = controller.getGrilleControler();
//        handler = new EventHandler<KeyEvent>() {
//            @Override
//            public void handle(KeyEvent event) {
//                switch (event.getCode()) {
//                    case ENTER:
//                        grilleControler.putPiece(vueGrille.getPieceSuivante(),vueGrille.getCoordoneeDepart());
//                        break;
//                }
//            }
//        };
//        super.setControlClavier();
//    }




    private void startGame() {
    }
}
