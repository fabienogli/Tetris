package Blokus.View;

import Base.Controler.GrilleControler;
import Base.Model.Piece;
import Base.View.VuePlateau;
import Blokus.Model.TypePiece;
import Tetris.View.VueGrille_Tetris;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.image.ImageView;
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
        super("Blokus",600,600, Color.DIMGREY,10,40);
        Menu nbJoueur = new Menu("Lancer Partie");
        MenuItem Djoueurs = new MenuItem("2 joueurs");
        MenuItem Qjoueurs = new MenuItem("4 joueurs");
        nbJoueur.getItems().addAll(Djoueurs, Qjoueurs);
        Djoueurs.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                ((VueGrilleBlokus)vueGrille).setNbJoueur(2);
                ((VueGrilleBlokus)vueGrille).setActif();
            }
        });
        Qjoueurs.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                ((VueGrilleBlokus)vueGrille).setNbJoueur(4);
                ((VueGrilleBlokus)vueGrille).setActif();
            }
        });
        menuBar.getMenus().add(nbJoueur);
        this.getChildren().add(menuBar);
        setImage(250,30);


    }

    @Override
    protected void initiateGrille() {
        double XposGrille = hauteur/3 -20;
        double YposGrille = longueur/4;
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

    @Override
    public void setImage(int xPos, int yPos) {
        this.imageTitre = new ImageView("/img/Blokus.png");
        this.imageTitre.setFitHeight(75);
        this.imageTitre.setFitWidth(95);
        super.setImage(xPos, yPos);
    }
}
