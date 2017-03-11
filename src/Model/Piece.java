package Model;

import com.sun.javafx.geom.Matrix3f;
import com.sun.javafx.geom.Vec2d;
import javafx.scene.paint.Color;
import javafx.scene.transform.MatrixType;

/**
 * Created by Fabien on 14/02/2017.
 */
public class Piece implements moveAble {
    private boolean alive;
    private Coordonee coordonee;
    private TypePiece typePiece;
    private int[][][] cases;
    private boolean[] positions = new boolean[4];
    private int positionActive;
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
        this.alive = true;
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
                setCases(4, 4);
                make_I();
                break;
            case Tetrimino_J:
                setCases(3, 3);
                make_J();
                break;
            case Tetrimino_L:
                setCases(3, 3);
                make_L();
                break;
            case Tetrimino_O:
                setCases(2, 2);
                make_O();
                break;
            case Tetrimino_S:
                setCases(3, 3);
                make_S();
                break;
            case Tetrimino_T:
                setCases(3, 3);
                make_T();
                break;
            case Tetrimino_Z:
                setCases(3, 3);
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
        this.dimension = new Vec2d(this.xpos, this.ypos);
        for (int i = 0; i < positions.length; i++)
            positions[i] = false;
        positionActive = 0;
        positions[0] = true;
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
        this.cases = new int[][][]
                {
                        {
                                {0, 0, 0},
                                {1, 1, 0},
                                {0, 1, 1}
                        },
                        {
                                {0, 0, 1},
                                {0, 1, 1},
                                {0, 1, 0}

                        },
                        {
                                {1, 1, 0},
                                {0, 1, 1},
                                {0, 0, 0}
                        },
                        {
                                {0, 1, 0},
                                {1, 1, 0},
                                {1, 0, 0}
                        }
                }
        ;
    }

    /**
     * Constructeur du Tetriminos T
     */
    private void make_T() {
        color = Color.PURPLE;
        this.cases = new int[][][]
                {
                        {
                                {1, 1, 1},
                                {0, 1, 0},
                                {0, 0, 0}
                        },
                        {
                                {1, 0, 0},
                                {1, 1, 0},
                                {1, 0, 0}

                        },
                        {
                                {0, 0, 0},
                                {0, 1, 0},
                                {1, 1, 1}
                        },
                        {
                                {0, 0, 1},
                                {0, 1, 1},
                                {0, 0, 1}
                        }
                }
        ;
    }



    /**
     * Constructeur du Tetriminos O
     */
    private void make_O() {
        color = Color.YELLOW;
        this.cases = new int[][][]
                {
                        {
                                {1, 1},
                                {1, 1}
                        }
                }
        ;
    }

    /**
     * Constructeur du Tetriminos S
     */
    private void make_S() {
        color = Color.LIME;
        this.cases = new int[][][]
                {
                        {
                                {0, 1, 1},
                                {1, 1, 0},
                                {0, 0, 0}
                        },
                        {
                                {1, 0, 0},
                                {1, 1, 0},
                                {0, 1, 0}

                        },
                        {
                                {0, 0, 0},
                                {0, 1, 1},
                                {1, 1, 0}
                        },
                        {
                                {0, 1, 0},
                                {0, 1, 1},
                                {0, 0, 1}
                        }
                }
        ;
    }

    /**
     * Constructeur du Tetriminos L
     */
    private void make_L() {
        color = Color.ORANGE;
        this.cases = new int[][][]
                {
                        {
                                {1, 1, 1},
                                {1, 0, 0},
                                {0, 0, 0}
                        },
                        {
                                {1, 0, 0},
                                {1, 0, 0},
                                {1, 1, 0}
                        },
                        {
                                {0, 0, 0},
                                {0, 0, 1},
                                {1, 1, 1}
                        },
                        {
                                {0, 1, 1},
                                {0, 0, 1},
                                {0, 0, 1}
                        }
                }
        ;
    }

    /**
     * Constructeur du Tetriminos J
     */
    private void make_J() {
        color = Color.BLUE;
        this.cases = new int[][][]
                {
                        {
                                {1, 1, 1},
                                {0, 0, 1},
                                {0, 0, 0}
                        },
                        {
                                {1, 1, 0},
                                {1, 0, 0},
                                {1, 0, 0}
                        },
                        {
                                {0, 0, 0},
                                {1, 0, 0},
                                {1, 1, 1}
                        },
                        {
                                {0, 0, 1},
                                {0, 0, 1},
                                {0, 1, 1}
                        }
                }
        ;
    }

    /**
     * Constructeur du Tetriminos I
     */
    private void make_I() {
        color = Color.CYAN;
        this.cases = new int[][][]
                {
                        {
                                {1, 0, 0, 0},
                                {1, 0, 0, 0},
                                {1, 0, 0, 0},
                                {1, 0, 0, 0}
                        },
                        {
                                {1, 1, 1, 1},
                                {0, 0, 0, 0},
                                {0, 0, 0, 0},
                                {0, 0, 0, 0}
                        }
                }
        ;
    }

    /**
     * Getteur des cases de la piece
     *
     * @return cases
     */
    public int[][] getCases() {
        return cases[positionActive];
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
                break;
            case Haut:
                coordonee.setY(coordonee.getY() - 1);
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
    public int getCase(int i, int j) {
        return getCases()[j][i];
    }

    public Color getColor() {
        return color;
    }

    public void rotation(Direction direction) {
        switch (direction) {
            case DROITE:
                rotate_Clockwise();
                break;
            case GAUCHE:
                rotate_CounterClockwise();
                break;
            default:
                break;
        }

    }

    private void setPositionActive(int i) {
        for (int j = 0; j < positions.length; j++)
            positions[j] = false;
        positionActive = i;
        if(typePiece == TypePiece.Tetrimino_O)  positionActive =0;
        positions[positionActive] = true;
    }

    public void rotate_Clockwise() {
        if(typePiece == TypePiece.Tetrimino_I && positionActive ==0)
            setPositionActive(1);
        else if (positionActive == 0)
            setPositionActive(3);
        else setPositionActive(positionActive - 1);
    }

    public void rotate_CounterClockwise() {
        if(typePiece == TypePiece.Tetrimino_I && positionActive ==1)
            setPositionActive(0);
        else if (positionActive == 3)
            setPositionActive(0);
        else setPositionActive(positionActive + 1);
    }

    public void kill(){
        this.alive = false;
    }

    public boolean isAlive() {
        return alive;
    }
}
