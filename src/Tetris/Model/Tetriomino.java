package Tetris.Model;

import Base.Model.Piece;

/**
 * Created by Fabien on 22/03/2017.
 */
public class Tetriomino extends Piece {
    private Type_Piece typePiece;

    public Tetriomino() {
        super();
    }

    public Tetriomino(Type_Piece type_piece) {
        this();
        this.typePiece = type_piece;
        setTypePiece();
    }

    /**
     * Creer la piece selon son type
     */
    public void setTypePiece() {
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
     * Constructeur du Tetriminos Z
     */
    private void make_Z() {
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
        this.cases = new int[][][]
                {
                        {
                                {1, 1},
                                {1, 1}
                        },
                        {
                                {1, 1},
                                {1, 1}
                        },
                        {
                                {1, 1},
                                {1, 1}
                        },
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
                        },
                        {
                                {0, 0, 0, 1},
                                {0, 0, 0, 1},
                                {0, 0, 0, 1},
                                {0, 0, 0, 1}
                        },
                        {
                                {0, 0, 0, 0},
                                {0, 0, 0, 0},
                                {0, 0, 0, 0},
                                {1, 1, 1, 1}
                        }
                }
        ;
    }

    @Override
    public Type_Piece getTypePiece() {
        return typePiece;
    }
}
