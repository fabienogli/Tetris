package Tetris.Model;

import Base.Model.*;

/**
 * Created by Fabien on 22/03/2017.
 */
public class Grille_Tetris extends Grille{

    protected static int ligneASupprimer;


    public Grille_Tetris() {
        super(10, 20);
    }

    @Override
    public Piece generateRandomPiece() {
        Piece piece;
        Type_Piece typePiece;
        int r = (int)(Math.random()*(7));
        switch (r){
            case 0:
                typePiece = Type_Piece.Tetrimino_I;
                break;
            case 1:
                typePiece = Type_Piece.Tetrimino_O;
                break;
            case 2:
                typePiece = Type_Piece.Tetrimino_J;
                break;
            case 3:
                typePiece = Type_Piece.Tetrimino_L;
                break;
            case 4:
                typePiece = Type_Piece.Tetrimino_S;
                break;
            case 5:
                typePiece = Type_Piece.Tetrimino_Z;
                break;
            case 6:
                typePiece = Type_Piece.Tetrimino_T;
                break;
            default:
                typePiece = Type_Piece.Tetrimino_O;
                break;
        }
        piece = new Tetriomino(typePiece);
        return piece;
    }


    @Override
    public Boolean controlFin(Piece piece, Direction direction) {
        int[][] pc = piece.getCases();
        int x = piece.getCoordonee().getX();
        int y = piece.getCoordonee().getY();
        Piece.Vecteur dimension = piece.getDimension();
        boolean fin = false;
        for (int ligne = 0; ligne < dimension.getX(); ligne++) {
            for (int colonne = 0; colonne < (int) dimension.getY(); colonne++) {
                if (!(colonne + x < 0 || colonne + x >= longueur || ligne + y < 0)) {
                    if (pc[ligne][colonne] == 1) {
                        if (ligne + y >= hauteur) {
                            fin = true;
                        } else if (this.cases[colonne + x][ligne + y].getActif()) {
                            if (direction == Direction.BAS)
                                fin = true;
                        } else if (ligne + y <= hauteur - 1) {
                            if (this.cases[colonne + x][ligne + y].getActif()) {
                                fin = true;
                            }
                        }

                    }
                }
            }
        }
        return fin;
    }

    /**
     * Controle si toutes les colonnes des lignes sont occupés.
     * parcourt de la grille
     * décale les lignes précédentes la ligne à supprimer d'Y+1
     *
     * @return
     */
    @Override
    public Boolean controlLigne() {
        boolean verif = false;
        int ligne = hauteur - 1;
        while (ligne > 0 && !verif) {
            boolean tmp = this.cases[0][ligne].getActif();
            for (int colonne = 0; colonne < longueur; colonne++) {
                tmp = tmp & this.cases[colonne][ligne].getActif();
                if (colonne == longueur - 1) {
                    if (tmp) {
                        ligneASupprimer = ligne;
                        verif = true;
                    }
                }
            }
            ligne--;
        }
        if (verif) {
            for (int y = ligneASupprimer; y > 0; y--) {
                for (int x = 0; x < longueur; x++) {
                    this.cases[x][y] = this.cases[x][y - 1];
                    this.cases[x][y].setCoordonee(new Coordonee(x, y - 1));
                    if (y == 1) {
                        this.cases[x][0] = new Case(new Coordonee(x, 0));
                    }
                }
            }
        }
        /**
         * Pour débuguer
         */
//        for (int x = 0; x < hauteur; x++) {
//            for (int y = 0; y < longueur; y++) {
//                if (cases[y][x].getActif())
//                    System.out.print(1);
//                else System.out.print(0);
//            }
//            System.out.println();
//        }
//        System.out.println("---------------------------------");
        return verif;
    }


    public static int getLigneASupprimer() {
        return ligneASupprimer;
    }

}
