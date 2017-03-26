package Blokus.Model;

import Base.Model.Piece;
import Tetris.Model.Type_Piece;

/**
 * Created by Fabien on 22/03/2017.
 */
public class BlokusPiece extends Piece {
    protected TypePiece typePiece;

    public BlokusPiece() {
        super();
    }

    public BlokusPiece(TypePiece typePiece) {
        super();
        this.typePiece = typePiece;
        switch (typePiece) {
            case IS:
                makeIS();
                break;
            case N:
                makeN();
                break;
            case VS:
                makeV5();
                break;
            case T5:
                makeT5();
                break;
            case U:
                makeU();
                break;
            case L5:
                makeL5();
                break;
            case Y:
                makeY();
                break;
            case Z5:
                makeZ5();
                break;
            case W:
                makeW();
                break;
            case P:
                makeP();
                break;
            case X:
                makeX();
                break;
            case F:
                makeF();
                break;
            case Z4:
                makeZ4();
                break;
            case I4:
                makeI4();
                break;
            case L4:
                makeL4();
                break;
            case O:
                make_O();
                break;
            case T4:
                makeT4();
                break;
            case I3:
                makeI3();
                break;
            case V3:
                makeV3();
                break;
            case p2:
                make2();
                break;
            case p1:
                make1();
                break;
        }
        setCases(cases[0].length, cases[0].length);
    }

    private void makeIS() {
        this.cases = new int[][][]
                {
                        {
                                {1, 0, 0, 0, 0},
                                {1, 0, 0, 0, 0},
                                {1, 0, 0, 0, 0},
                                {1, 0, 0, 0, 0},
                                {1, 0, 0, 0, 0}
                        },
                        {
                                {1, 1, 1, 1, 1},
                                {0, 0, 0, 0, 0},
                                {0, 0, 0, 0, 0},
                                {0, 0, 0, 0, 0},
                                {0, 0, 0, 0, 0}
                        },
                        {
                                {0, 0, 0, 0, 1},
                                {0, 0, 0, 0, 1},
                                {0, 0, 0, 0, 1},
                                {0, 0, 0, 0, 1},
                                {0, 0, 0, 0, 1}
                        },
                        {
                                {0, 0, 0, 0, 0},
                                {0, 0, 0, 0, 0},
                                {0, 0, 0, 0, 0},
                                {0, 0, 0, 0, 0},
                                {1, 1, 1, 1, 1}
                        }
                }
        ;
    }

    private void makeN() {
        this.cases = new int[][][]
                {
                        {
                                {1, 0, 0, 0},
                                {1, 0, 0, 0},
                                {1, 1, 0, 0},
                                {0, 1, 0, 0}
                        },
                        {
                                {0, 1, 1, 1},
                                {1, 1, 0, 0},
                                {0, 0, 0, 0},
                                {0, 0, 0, 0}
                        },
                        {
                                {0, 0, 1, 0},
                                {0, 0, 1, 1},
                                {0, 0, 0, 1},
                                {0, 0, 0, 1}
                        },
                        {
                                {0, 0, 0, 0},
                                {0, 0, 0, 0},
                                {0, 0, 1, 1},
                                {1, 1, 1, 0}
                        }
                }
        ;
    }

    private void makeV5() {
        this.cases = new int[][][]
                {
                        {
                                {1, 1, 1},
                                {1, 0, 0},
                                {1, 0, 0}
                        },
                        {
                                {1, 0, 0},
                                {1, 0, 0},
                                {1, 1, 1}
                        },
                        {
                                {0, 0, 1},
                                {0, 0, 1},
                                {1, 1, 1}
                        },
                        {
                                {1, 1, 1},
                                {0, 0, 1},
                                {0, 0, 1}
                        }
                }
        ;
    }

    private void makeT5() {
        this.cases = new int[][][]
                {
                        {
                                {1, 1, 1},
                                {0, 1, 0},
                                {0, 1, 0}
                        },
                        {
                                {1, 0, 0},
                                {1, 1, 1},
                                {1, 0, 0}

                        },
                        {
                                {0, 1, 0},
                                {0, 1, 0},
                                {1, 1, 1}
                        },
                        {
                                {0, 0, 1},
                                {1, 1, 1},
                                {0, 0, 1}
                        }
                }
        ;
    }

    private void makeU() {
        this.cases = new int[][][]
                {
                        {
                                {1, 1, 1},
                                {1, 0, 1},
                                {0, 0, 0}
                        },
                        {
                                {1, 1, 0},
                                {1, 0, 0},
                                {1, 1, 0}
                        },
                        {
                                {0, 0, 0},
                                {1, 0, 1},
                                {1, 1, 1}
                        },
                        {
                                {0, 1, 1},
                                {0, 0, 1},
                                {0, 1, 1}
                        }
                }
        ;
    }

    private void makeL5() {
        this.cases = new int[][][]
                {
                        {
                                {1, 0, 0, 0},
                                {1, 0, 0, 0},
                                {1, 0, 0, 0},
                                {1, 1, 0, 0}
                        },
                        {
                                {1, 1, 1, 1},
                                {1, 0, 0, 0},
                                {0, 0, 0, 0},
                                {0, 0, 0, 0}
                        },
                        {
                                {0, 0, 1, 1},
                                {0, 0, 0, 1},
                                {0, 0, 0, 1},
                                {0, 0, 0, 1}
                        },
                        {
                                {0, 0, 0, 0},
                                {0, 0, 0, 0},
                                {0, 0, 0, 1},
                                {1, 1, 1, 1}
                        }
                }
        ;
    }

    private void makeY() {
        this.cases = new int[][][]
                {
                        {
                                {1, 0, 0, 0},
                                {1, 0, 0, 0},
                                {1, 1, 0, 0},
                                {1, 0, 0, 0}
                        },
                        {
                                {1, 1, 1, 1},
                                {0, 1, 0, 0},
                                {0, 0, 0, 0},
                                {0, 0, 0, 0}
                        },
                        {
                                {0, 0, 0, 1},
                                {0, 0, 1, 1},
                                {0, 0, 0, 1},
                                {0, 0, 0, 1}
                        },
                        {
                                {0, 0, 0, 0},
                                {0, 0, 0, 0},
                                {0, 0, 1, 0},
                                {1, 1, 1, 1}
                        }
                }
        ;
    }

    private void makeZ5() {

        this.cases = new int[][][]
                {
                        {
                                {1, 0, 0},
                                {1, 1, 1},
                                {0, 0, 1}
                        },
                        {
                                {0, 1, 1},
                                {0, 1, 0},
                                {1, 1, 0}
                        },
                        {
                                {1, 0, 0},
                                {1, 1, 1},
                                {0, 0, 1}
                        },
                        {
                                {0, 1, 1},
                                {0, 1, 0},
                                {1, 1, 0}
                        }
                }
        ;
    }

    private void makeW() {
        this.cases = new int[][][]
                {
                        {
                                {0, 1, 1},
                                {1, 1, 0},
                                {1, 0, 0}
                        },
                        {
                                {1, 0, 0},
                                {1, 1, 0},
                                {0, 1, 1}

                        },
                        {
                                {0, 0, 1},
                                {0, 1, 1},
                                {1, 1, 0}
                        },
                        {
                                {1, 1, 0},
                                {0, 1, 1},
                                {0, 0, 1}
                        }
                }
        ;
    }

    private void makeP() {
        this.cases = new int[][][]
                {
                        {
                                {1, 1, 1},
                                {0, 1, 1},
                                {0, 0, 0}
                        },
                        {
                                {1, 1, 0},
                                {1, 1, 0},
                                {1, 0, 0}
                        },
                        {
                                {0, 0, 0},
                                {1, 1, 0},
                                {1, 1, 1}
                        },
                        {
                                {0, 0, 1},
                                {0, 1, 1},
                                {0, 1, 1}
                        }
                }
        ;
    }

    private void makeX() {
        this.cases = new int[][][]
                {
                        {
                                {0, 1, 0},
                                {1, 1, 1},
                                {0, 1, 0}
                        },
                        {
                                {0, 1, 0},
                                {1, 1, 1},
                                {0, 1, 0}
                        },
                        {
                                {0, 1, 0},
                                {1, 1, 1},
                                {0, 1, 0}
                        },
                        {
                                {0, 1, 0},
                                {1, 1, 1},
                                {0, 1, 0}
                        }
                }
        ;
    }

    private void makeF() {
        this.cases = new int[][][]
                {
                        {
                                {0, 1, 0},
                                {1, 1, 1},
                                {1, 0, 0}
                        },
                        {
                                {1, 1, 0},
                                {0, 1, 1},
                                {0, 1, 0}
                        },
                        {
                                {0, 0, 1},
                                {1, 1, 1},
                                {0, 1, 0}
                        },
                        {
                                {0, 1, 0},
                                {1, 1, 0},
                                {0, 1, 1}
                        }
                }
        ;
    }

    private void makeZ4() {
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

    private void makeI4() {
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


    private void makeL4() {
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

    private void makeT4() {
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

    private void makeI3() {
        this.cases = new int[][][]
                {
                        {
                                {1, 1, 1},
                                {0, 0, 0},
                                {0, 0, 0}
                        },
                        {
                                {1, 0, 0},
                                {1, 0, 0},
                                {1, 0, 0}
                        },
                        {
                                {0, 0, 0},
                                {0, 0, 0},
                                {1, 1, 1}
                        },
                        {
                                {0, 0, 1},
                                {0, 0, 1},
                                {0, 0, 1}
                        }
                }
        ;
    }

    private void makeV3() {
        this.cases = new int[][][]
                {
                        {
                                {1, 1},
                                {0, 1}
                        },
                        {
                                {1, 1},
                                {1, 0}
                        },
                        {
                                {1, 0},
                                {1, 1}
                        },
                        {
                                {0, 1},
                                {1, 1}
                        }
                }
        ;
    }

    private void make2() {
        this.cases = new int[][][]
                {
                        {
                                {1, 1},
                                {0, 0}
                        },
                        {
                                {1, 1},
                                {0, 0}
                        },
                        {
                                {0, 0},
                                {1, 1}
                        },
                        {
                                {0, 0},
                                {1, 1}
                        }
                }
        ;
    }

    private void make1() {
        this.cases = new int[][][]
                {
                        {
                                {1}
                        },
                        {
                                {1}
                        },
                        {
                                {1}
                        },
                        {
                                {1}
                        }
                }
        ;
    }

    @Override
    public Enum getTypePiece() {
        return typePiece;
    }
}
