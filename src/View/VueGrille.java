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
 * Vue de la grille
 * Created by Fabien on 14/02/2017.
 */
public class VueGrille extends Parent {
    private Grille grille;
    GridPane gridPane;

    public VueGrille(double Xpos, double Ypos){
        //Initialisation de la grille
        gridPane = new GridPane();

        //Controller de la grille
        GrilleControler grilleControler = new GrilleControler();
        Grille grille = grilleControler.getGrille();

        //Recuperation de la longeur de la grille
        int longueur = grille.getX();

        //recuperation de la hauteur de la grille
        int hauteur = grille.getY();

        //Definit les contrainte des lignes
        for(int i =0; i<longueur; i++){
            RowConstraints rowConstraints = new RowConstraints(VueCase.getLenght());
            gridPane.getRowConstraints().add(rowConstraints);

        }

        //Rend visible les lignes de la grille
        gridPane.setGridLinesVisible(true);

        //Ajout de VueCase dans la grille
        for(int i=0; i<longueur;i++){
            for(int j=0; j<hauteur;j++){
                gridPane.add(new VueCase(grille.getCase(i,j)),i,j);
            }
        }

        //Determine la position de la grille
        this.gridPane.setTranslateX(Xpos);
        this.gridPane.setTranslateY(Ypos);

        //Ajout de la grille
        this.getChildren().add(gridPane);
        grilleControler.setVueGrille(this);

        //Gestion des actions du clavier
        this.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                switch (event.getCode()){
                    case RIGHT:
                        grilleControler.movePiece(Direction.DROITE);
                        break;
                    case LEFT:
                        grilleControler.movePiece(Direction.GAUCHE);
                        break;
                    default:
                        break;
                }
            }
        });

    }

    /**
     * @return gridPane Représentation graphique de la grille
     */
    public GridPane getGridPane() {
        return gridPane;
    }
}
