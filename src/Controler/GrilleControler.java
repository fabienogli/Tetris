package Controler;

import Model.*;
import View.VueGrille;

import java.util.ArrayList;
import java.util.Observer;

/**
 * Controller de la grille
 * Created by Fabien on 15/02/2017.
 */
public class GrilleControler {
    private Grille grille;
    private ArrayList<Piece> pieces;
    private Piece piece;
    private PieceControler pieceControler;
    private VueGrille vueGrille;

    /**
     * Constructeur de GrilleController
     */
    public GrilleControler(){
    }

    public Piece getPiece() {
        return piece;
    }

    public void putPiece(Piece piece){
        this.piece = piece;
        grille.putPiece(piece);
    }

    public void movePiece(Direction direction){
        grille.movePiece(piece,direction);
    }

    public void setVueGrille(VueGrille vueGrille) {
        this.vueGrille = vueGrille;
    }

    public void refreshPiece(Piece piece){
    }

    public Grille getGrille(){
        return grille;
    }
    public void setGrille(Grille grille){
        this.grille = grille;
    }


}
