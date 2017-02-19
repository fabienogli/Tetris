package Model;

/**
 * Created by Fabien on 14/02/2017.
 */
public class Coordonee {
    private int x,y;
    private boolean piece;
    private TypePiece typePiece;

    public Coordonee(int x, int y) {
        this.x = x;
        this.y = y;
        this.piece = false;
    }

    public Coordonee() {
        this.piece = false;
    }

    public void setX(int x) {   this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setPiece(boolean piece, TypePiece typePiece) {
        this.piece = piece;
        this.typePiece = typePiece;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
