package Controler;

import Model.*;

import java.util.ArrayList;

/**
 * Created by Fabien on 15/02/2017.
 */
public class GrilleControler {
    Grille grille;
    ArrayList<Piece> pieces;
    Piece piece;
    PieceControler pieceControler;

    public GrilleControler() {
        grille = new Grille(10,20);
        pieceControler = new PieceControler(TypePiece.Tetrimino_J);
        piece = pieceControler.getPiece();
        piece.setCoordonee(new Coordonee(0,0));
        putPiece();
        //pieces = new ArrayList<Piece>();
        //pieces.add(piece);

    }

    public void putPiece(){
        grille.putPiece(piece);
    }

    public void refreshPiece(Piece piece){

    }

    public Grille getGrille(){
        return grille;
    }


}
