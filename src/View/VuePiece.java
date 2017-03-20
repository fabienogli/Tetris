package View;

import Controler.PieceControler;
import Model.Case;
import Model.Direction;
import Model.Piece;
import javafx.animation.RotateTransition;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.util.Duration;


/**
 * Created by Fabien on 21/02/2017.
 */
public class VuePiece extends Parent {
    private Color color;
    private Piece piece;
    /**
     * Constructeur VuePiece
     *
     * @param piece Piece
     */
    public VuePiece(Piece piece) {
        switch (piece.getTypePiece()) {
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

    public Color getColor() {
        return color;
    }

    public Piece getPiece() {
        return piece;
    }
}