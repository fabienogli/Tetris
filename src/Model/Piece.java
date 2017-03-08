package Model;

import com.sun.javafx.geom.Matrix3f;
import com.sun.javafx.geom.Vec2d;
import javafx.scene.paint.Color;
import javafx.scene.transform.MatrixType;

/**
 * Created by Fabien on 14/02/2017.
 */
public class Piece implements moveAble {
    private Coordonee coordonee;
    private TypePiece typePiece;
    private Case[][] cases;
    private Vec2d dimension;
    private int xpos, ypos;
    private Color color;
    private int pivotX, PivotY;

    /**
     * Setteur de coordonnee
     *
     * @param coordonee Coordonne de la Piece
     */
    public void setCoordonee(Coordonee coordonee) {
        this.coordonee = coordonee;
    }

    /**
     * Getteur coordonnee
     *
     * @return coordonee de la piece
     */
    public Coordonee getCoordonee() {
        return coordonee;
    }

    /**
     * Getteur dimension
     *
     * @return dimension de la Piece
     */
    public Vec2d getDimension() {
        return dimension;
    }

    /**
     * Getteur du type de la piece
     *
     * @return typePiece
     */
    public TypePiece getTypePiece() {
        return typePiece;
    }


    public Piece() {
        this.coordonee = new Coordonee();
    }

    /**
     * Creer la piece selon son type
     *
     * @param typePiece
     */
    public void setTypePiece(TypePiece typePiece) {
        this.typePiece = typePiece;
        switch (this.typePiece) {
            case Tetrimino_I:
                setCases(4, 1);
                make_I();
                break;
            case Tetrimino_J:
                setCases(3, 2);
                make_J();
                break;
            case Tetrimino_L:
                setCases(3, 2);
                make_L();
                break;
            case Tetrimino_O:
                setCases(2, 2);
                make_O();
                break;
            case Tetrimino_S:
                setCases(3, 2);
                make_S();
                break;
            case Tetrimino_T:
                setCases(3, 2);
                make_T();
                break;
            case Tetrimino_Z:
                setCases(3, 2);
                make_Z();
                break;
            default:
                break;
        }
    }

    /**
     * Initialise les dimensions et le tableau de case de la Piece
     *
     * @param xpos longueur
     * @param ypos hauteur
     */
    public void setCases(int xpos, int ypos) {
        this.xpos = xpos;
        this.ypos = ypos;
        this.cases = new Case[xpos][ypos];
        this.dimension = new Vec2d(this.xpos, this.ypos);
    }

    /**
     * Constructeur de la piece
     *
     * @param typePiece type de la Piece
     */
    public Piece(TypePiece typePiece) {
        this();
        setTypePiece(typePiece);
    }

    /**
     * Constructeur du Tetriminos Z
     */
    private void make_Z() {
        color = Color.RED;
        for (int j = 0; j < xpos - 1; j++) {
            this.cases[j][0] = new Case();
            this.cases[j][0].caseActiv();
            this.cases[j + 1][1] = new Case();
            this.cases[j + 1][1].caseActiv();
        }

    }

    /**
     * Constructeur du Tetriminos T
     */
    private void make_T() {
        color = Color.PURPLE;
        this.cases[1][0] = new Case();
        this.cases[1][0].caseActiv();
        for (int colonne = 0; colonne < this.xpos; colonne++) {
            this.cases[colonne][1] = new Case();
            this.cases[colonne][1].caseActiv();
        }
    }

    /**
     * Constructeur du Tetriminos S
     */
    private void make_S() {
        color = Color.LIME;
        for (int j = 0; j < xpos - 1; j++) {
            this.cases[j][1] = new Case();
            this.cases[j][1].caseActiv();
            this.cases[j + 1][0] = new Case();
            this.cases[j + 1][0].caseActiv();
        }
    }

    /**
     * Constructeur du Tetriminos O
     */
    private void make_O() {
        color = Color.YELLOW;
        for (int ligne = 0; ligne < 2; ligne++) {
            for (int colonne = 0; colonne < 2; colonne++) {
                this.cases[ligne][colonne] = new Case();
                this.cases[ligne][colonne].caseActiv();
            }
        }
    }

    /**
     * Constructeur du Tetriminos L
     */
    private void make_L() {
        color = Color.ORANGE;
        for (int x = 0; x < xpos; x++) {
            this.cases[x][0] = new Case();
            this.cases[x][0].caseActiv();
        }
        this.cases[0][1] = new Case();
        this.cases[0][1].caseActiv();
        setPivotX(1);
        setPivotY(1);
    }

    /**
     * Constructeur du Tetriminos J
     */
    private void make_J() {
        color = Color.BLUE;
        for (int x = 0; x < xpos; x++) {
            this.cases[x][0] = new Case();
            this.cases[x][0].caseActiv();
        }
        this.cases[xpos - 1][1] = new Case();
        this.cases[xpos - 1][1].caseActiv();
    }

    /**
     * Constructeur du Tetriminos I
     */
    private void make_I() {
        color = Color.CYAN;
        for (int x = 0; x < xpos; x++) {
            this.cases[x][0] = new Case();
            this.cases[x][0].caseActiv();
        }
    }

    /**
     * Getteur des cases de la piece
     *
     * @return cases
     */
    public Case[][] getCases() {
        return cases;
    }

    /**
     * Methode de deplacement de la piece
     *
     * @param direction de la piece
     */
    @Override
    public void move(Direction direction) {
        switch (direction) {
            case BAS:
                coordonee.setY(coordonee.getY() + 1);
                break;
            case DROITE:
                coordonee.setX(coordonee.getX() + 1);
                break;
            case GAUCHE:
                coordonee.setX(coordonee.getX() - 1);
            default:
                break;
        }
    }


    private void setPivotX(int pivotX) {
        this.pivotX = pivotX;
    }

    private void setPivotY(int pivotY) {
        PivotY = pivotY;
    }

    public int getPivotX() {
        return pivotX;
    }

    public int getPivotY() {
        return PivotY;
    }

    /**
     * Retourne la case à la coordonnee correspondante
     *
     * @param i indice abscisse
     * @param j indice ordonnee
     * @return
     */
    public Case getCase(int i, int j) {
        return this.cases[i][j];
    }

    public Color getColor() {
        return color;
    }

    public void rotation(Direction direction){
        System.out.println("dimension, x= "+dimension.x+" y= "+dimension.y);
        switch (direction){
            case DROITE:
                rorateArray_Clock(cases);
                break;
            case GAUCHE:
                this.cases = rotateCases_CounterClock(cases);
                break;
            default:
                break;
        }

    }

    public Case[][] rotateCases_CounterClock(Case[][] arr){
        Case[][] rotateArray = new Case[arr[0].length][arr.length];
        double a = this.dimension.x;
        this.dimension.x = this.dimension.y;
        this.dimension.y = a;
        for(int j=arr.length-1; j>=0; j--){
            for(int i=0; i<arr[0].length; i++){
                rotateArray[i][j] = arr[j][i];
            }
        }
        return rotateArray;
    }

    public void rorateArray_Clock(Case[][] arr){

        //first change the dimensions vertical length for horizontal length
        //and viceversa
        Case[][] newArray = new Case[(int)this.dimension.y][(int)this.dimension.x];
        int a, b;
        a = (int)this.dimension.x;
        b = (int)this.dimension.y;
        //invert values 90 degrees clockwise by starting from button of
        //array to top and from left to right
        for(int i = 0; i<a;i++){
            for(int j = b-1 ; j>=0;j--){
                 newArray[j][i]= arr[i][j];
            }
        }
        dimension.set(b,a);

//        int jj = 0;
//        for(int j=arr.length-1; j>=0; j--){
//            int ii = 0;
//            for(int i=0; i<arr[0].length; i++){
//                newArray[ii][jj] = arr[i][j];
//                jj++;
//            }
//            ii++;
//        }
        arr = null;
        arr = newArray;
    }
}
