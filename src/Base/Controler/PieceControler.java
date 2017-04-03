package Base.Controler;

import Base.Model.Direction;
import Base.Model.Grille;
import Base.Model.Piece;

/**
 * Controlleur de la Piece
 * Created by Fabien on 18/02/2017.
 */
public class PieceControler {
    private Piece piece;

    public PieceControler(Piece piece) {
        this.piece = piece;
    }

    public Piece getPiece() {
        return piece;
    }

    public void putPiece(Grille grille) {

    }

    public void movePiece(Direction direction) {
        piece.move(direction);
    }


}
