package View;

import Controler.GrilleControler;
import Model.*;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.security.Key;

/**
 * Created by Fabien on 14/02/2017.
 */
public class VueGrille extends Parent {
    private Grille grille;
    private Piece piece;
    GridPane gridPane;

    public VueGrille(double Xpos, double Ypos){
        gridPane = new GridPane();
        VueCase vueCase = new VueCase(new Case());

        GrilleControler grilleControler = new GrilleControler();
        Grille grille = grilleControler.getGrille();
        int colonne = grille.getY();
        int ligne = grille.getX();
        for(int i =0; i<ligne; i++){
            RowConstraints rowConstraints = new RowConstraints(VueCase.getLenght());
            gridPane.getRowConstraints().add(rowConstraints);

        }

        gridPane.setGridLinesVisible(true);
        for(int i=0; i<ligne;i++){
            for(int j=0; j<colonne;j++){
                gridPane.add(new VueCase(grille.getCase(i,j)),i,j);
            }
        }
        this.gridPane.setTranslateX(Xpos);
        this.gridPane.setTranslateY(Ypos);
        this.getChildren().add(gridPane);
        this.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                switch (event.getCode()){
                    case RIGHT:
                        piece.move(Direction.DROITE);
                        break;
                    case LEFT:
                        piece.move(Direction.GAUCHE);
                        break;
                    default:
                        break;
                }
            }
        });
    }

    public GridPane getGridPane() {
        return gridPane;
    }
}
