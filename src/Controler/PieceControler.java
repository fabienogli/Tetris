package Controler;

import Model.Direction;
import Model.Grille;
import Model.Piece;
import Model.TypePiece;
import View.VuePiece;
import javafx.event.EventHandler;

/**
 * Controlleur de la Piece
 * Created by Fabien on 18/02/2017.
 */
public class PieceControler {
    private Piece piece;
    private VuePiece vuePiece;

    public PieceControler(TypePiece typePiece){
        this.piece = new Piece(typePiece);
    }

    public Piece getPiece() {
        return piece;
    }

    public void putPiece(Grille grille){

    }

    public void movePiece(Direction direction){
        piece.move(direction);
        vuePiece.setxPos(piece.getCoordonee().getX());
        vuePiece.setyPos(piece.getCoordonee().getY());
    }

    public void setVuePiece(VuePiece vuePiece){
        this.vuePiece = vuePiece;
    }
}
