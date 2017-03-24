package Blokus.View;

import Base.Model.Piece;
import Base.View.VuePlateau;
import Tetris.View.VueGrille_Tetris;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
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

    @Override
    protected void setControlClavier() {
        handler = new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                switch (event.getCode()) {
                    case ENTER:
                        if (pause) {
                            pause = false;
                            startGame();
                            timeline.play();
                        }
                        break;
                }
            }
        };
        super.setControlClavier();
    }



    private void startGame() {

    }
}
