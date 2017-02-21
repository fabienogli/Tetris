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
    private GrilleControler controler;

    public VueGrille(double Xpos, double Ypos){
        //Initialisation de la grille
        gridPane = new GridPane();

        //Controller de la grille
        controler = new GrilleControler();
        controler.setVueGrille(this);

        //Récupération de la grille
        grille = controler.getGrille();

        //Recuperation de la longueur de la grille
        int longueur = grille.getX();

        //recuperation de la hauteur de la grille
        int hauteur = grille.getY();

        System.out.println("hauteur: "+hauteur+" et longueur: "+ longueur);
        //Definit les contraintes des lignes


        for(int i=0; i<hauteur; i++){
            RowConstraints rowConstraints = new RowConstraints(VueCase.getLenght());
            rowConstraints.setFillHeight(true);
            rowConstraints.setVgrow(Priority.ALWAYS);
            gridPane.getRowConstraints().add(rowConstraints);

        }
        for(int j =0; j<longueur ;j++){
            ColumnConstraints columnConstraints = new ColumnConstraints(VueCase.getLenght());
            columnConstraints.setFillWidth(true);
            columnConstraints.setHgrow(Priority.ALWAYS);
            gridPane.getColumnConstraints().add(columnConstraints);
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
    }

    /**
     * @return GrilleControler
     */
    public GrilleControler getControler() {
        return controler;
    }

    /**
     * @return grille
     */
    public Grille getGrille() {
        return grille;
    }

    /**
     * @return gridPane Représentation graphique de la grille
     */
    public GridPane getGridPane() {
        return gridPane;
    }
}
