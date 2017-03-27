package Tetris.View;

import Base.Model.Coordonee;
import Base.Model.Piece;
import Tetris.Model.Grille_Tetris;
import Tetris.Model.Tetriomino;
import Base.View.VueGrille;
import Base.View.VuePiece;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.GridPane;

/**
 * Created by Fabien on 22/03/2017.
 */
public class VueGrille_Tetris extends VueGrille {

    private GridPane previsualisationPiece;

    public VueGrille_Tetris(double Xpos, double Ypos) {
        super(Xpos, Ypos,4,4);
        //settage de la position de depart
        coordoneeDepart = new Coordonee(4,0);

        //Initialisation de la prévisualisation de la grille
        previsualisationPiece = makePrevisualisationPiece(4, 4);

        previsualisationPiece.setTranslateX(450);
        previsualisationPiece.setTranslateY(200);

        this.getChildren().add(previsualisationPiece);

    }

    @Override
    protected void initiateGrille() {
        grille = new Grille_Tetris();
    }

    @Override
    public void setPieceSuivante(Piece pieceSuivante) {
        super.setPieceSuivante(pieceSuivante);
        showPieceSuivante(pieceSuivante, previsualisationPiece);
    }

    @Override
    protected VuePiece initiateVuePiece(Piece piece) {
        VuePiece vuePiece = new VueTetriomino((Tetriomino)piece);
        return vuePiece;
    }

    @Override
    public void gestionGrille(Piece piece) {
        //Si la grille n'est pas active alors le jeu continue
        if(!grille.isGrilleRemplie()){
            this.PieceCourante = piece;
            //initiateVuePieceCourante();
            vuePieceCourante = initiateVuePiece(PieceCourante);

            if(!PieceCourante.isAlive())
            {
                System.out.println("mort");
                coordoneePrec = null;
                ligneComplete();
                controler.putPiece(pieceSuivante, coordoneeDepart);
            }else {
                affichage_deplacementPiece();
            }
        }else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("DOMMAGE");
            alert.setHeaderText(null);
            alert.setContentText("VOUS AVEZ PERDU");
            alert.showAndWait();
        }
    }

    /**
     * Si le control ligne est vrai, on affecte les couleurs des casses au dessus de la ligne à effacer
     * à la ligne a effacé et ainsi de suite
     */
    public void ligneComplete(){
        while(grille.controlLigne()){
            setScore(score+100);
            int ligneDelete = Grille_Tetris.getLigneASupprimer();
            for(int y =ligneDelete; y>0; y--){
                for(int x =0; x< grille.getX(); x++){
                    cases[x][y].changerCouleur(cases[x][y-1].getColor());
                }
            }
        }

    }



}
