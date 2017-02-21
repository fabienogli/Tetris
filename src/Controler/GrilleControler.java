package Controler;

import Model.*;
import View.VueGrille;

import java.util.ArrayList;

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
    public GrilleControler() {
        grille = new Grille(10,20);
        pieceControler = new PieceControler(TypePiece.Tetrimino_J);
        piece = pieceControler.getPiece();
        piece.setCoordonee(new Coordonee(0,0));
        putPiece();
        //pieces = new ArrayList<Piece>();
        //pieces.add(piece);

    }

    public Piece getPiece() {
        return piece;
    }

    public void putPiece(){
        grille.put_erasePiece(piece,false);
    }

    public void movePiece(Direction direction){
        grille.put_erasePiece(piece,true);
        //grille.movePiece(piece,direction);
    }

    public void setVueGrille(VueGrille vueGrille) {
        this.vueGrille = vueGrille;
    }

    public void refreshPiece(Piece piece){
    }

    public Grille getGrille(){
        return grille;
    }


}
