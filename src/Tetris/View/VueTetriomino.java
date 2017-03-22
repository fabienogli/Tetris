package Tetris.View;

import Tetris.Model.Tetriomino;
import Base.View.VuePiece;
import javafx.scene.paint.Color;

/**
 * Created by Fabien on 22/03/2017.
 */
public class VueTetriomino extends VuePiece {

    public VueTetriomino(Tetriomino piece) {
        super(piece);
        switch (piece.getTypePiece()){
            case Tetrimino_T:
                color = Color.PURPLE;
                break;
            case Tetrimino_O:
                color = Color.YELLOW;
                break;
            case Tetrimino_S:
                color = Color.LIME;
                break;
            case Tetrimino_L:
                color = Color.ORANGE;
                break;
            case Tetrimino_J:
                color = Color.BLUE;
                break;
            case Tetrimino_I:
                color = Color.CYAN;
                break;
            case Tetrimino_Z:
                color = Color.RED;
                break;
        }

        }
}
