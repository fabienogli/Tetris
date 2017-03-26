package Tetris.View;

import Base.Model.Piece;
import Base.View.VuePiece;
import Base.View.VuePrevisualisation;
import Tetris.Model.Tetriomino;
import javafx.scene.paint.Color;

/**
 * Created by Fabien on 25/03/2017.
 */
public class nextPiece extends VuePrevisualisation {

    public nextPiece(Piece piece, Color color) {
        super(piece, color);
        this.setTranslateX(450);
        this.setTranslateY(200);
    }

    @Override
    protected VuePiece intiateVuePiece(Piece piece) {
        return new VueTetriomino((Tetriomino)piece);
    }
}
