package Base.View;

import Base.Model.Piece;
import javafx.scene.Parent;
import javafx.scene.paint.Color;


/**
 * Created by Fabien on 21/02/2017.
 */
public class VuePiece extends Parent {
    protected Color color;
    protected Piece piece;
    /**
     * Constructeur VuePiece
     *
     * @param piece Piece
     */
    public VuePiece(Piece piece) { this.piece = piece;}

    public Color getColor() {
        return color;
    }

    public Piece getPiece() {
        return piece;
    }
}