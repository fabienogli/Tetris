package Tetris.View;

import Base.Model.Direction;
import Base.View.VuePlateau;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.util.Duration;

/**
 * Created by Fabien on 22/03/2017.
 */
public class VueTetris extends VuePlateau {

    public VueTetris() {
        super("Tetris",600,600,Color.DIMGREY,450,200);
    }

    @Override
    protected void initiateGrille() {
        double XposGrille = hauteur/3;
        double YposGrille = longueur/4;
        super.initiateGrille();
        vueGrille = new VueGrille_Tetris(XposGrille, YposGrille);
    }



    @Override
    protected void setControlClavier() {
        handler =new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                switch (event.getCode()){
                    case ENTER:
                        if(pause){
                            pause = false;
                            startGame();
                            timeline.play();
                        }
                        break;
                    case P:
                        if(pause){
                            timeline.play();
                            pause = false;

                        }else{
                            timeline.stop();
                            pause = true;
                        }
                    case RIGHT:
                        if(!pause)
                            controller.getGrilleControler().movePiece(Direction.DROITE);
                        break;
                    case LEFT:
                        if(!pause)
                            controller.getGrilleControler().movePiece(Direction.GAUCHE);
                        break;
                    case DOWN:
                        if(!pause)
                            controller.getGrilleControler().movePiece(Direction.BAS);
                        break;
                    case E:
                        if(!pause)
                            controller.getGrilleControler().rotatePiece(Direction.DROITE);
                        break;
                    case A:
                        if(!pause)
                            controller.getGrilleControler().rotatePiece(Direction.GAUCHE);
                        break;
                    default:
                        break;
                }
            }
        };
        super.setControlClavier();
    }

    public void startGame(){
        //Generer Piece et la controler
        generatePiece();
        timeline = new Timeline(new KeyFrame(
                Duration.millis(1000),
                ae->vueGrille.getControler().movePiece(Direction.BAS)
        ));
        timeline.setCycleCount(Animation.INDEFINITE);
    }
}
