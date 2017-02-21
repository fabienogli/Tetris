package Controler;

import Model.Direction;
import Model.Piece;
import Model.TypePiece;
import javafx.event.EventHandler;

/**
 * Controlleur de la Piece
 * Created by Fabien on 18/02/2017.
 */
public class PieceControler {
    private Piece piece;

    public PieceControler(TypePiece typePiece){
        this.piece = new Piece(typePiece);
    }

    public Piece getPiece() {
        return piece;
    }

    public void movePiece(Direction direction){
        piece.move(direction);
    }
}
