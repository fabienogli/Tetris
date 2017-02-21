package Model;

/**
 * Created by Fabien on 14/02/2017.
 */
public class Coordonee {
    private int x,y;

    //Les deux paramètre suivant = pas super utile
    private boolean piece;
    private TypePiece typePiece;

    /**
     * Constructeur de coordonne
     * @param x indice axe abscisse
     * @param y indice axe ordonee
     */
    public Coordonee(int x, int y) {
        this.x = x;
        this.y = y;
        this.piece = false;
    }

    public Coordonee() {
        this.piece = false;
    }

    /**
     * Setteur abscisse
     * @param x
     */
    public void setX(int x) {   this.x = x;
    }

    /**
     * Setteur ordonee
     * @param y
     */
    public void setY(int y) {
        this.y = y;
    }

    public void setPiece(boolean piece, TypePiece typePiece) {
        this.piece = piece;
        this.typePiece = typePiece;
    }

    /**
     * Getteur indice abscisse
     * @return x
     */
    public int getX() {
        return x;
    }

    /**
     * Getteur indice ordonnee
     * @return y
     */
    public int getY() { return y; }
}
