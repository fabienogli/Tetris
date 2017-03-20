package Model;


import com.sun.javafx.geom.Vec2d;

import java.util.Observable;

/**
 * Hérité d'observable pour envoyer des notifications à la VUE.
 * Created by Fabien on 14/02/2017.
 */
public class Grille extends Observable {

    private int longueur;
    private int hauteur;
    private Case[][] cases;
    private int ligneASupprimer;
    private boolean grilleRemplie;


    /**
     * Constructeur de la grille
     *
     * @param longueur
     * @param hauteur
     */
    public Grille(int longueur, int hauteur) {
        this.longueur = longueur;
        this.hauteur = hauteur;
        this.cases = new Case[longueur][hauteur];
        for (int i = 0; i < longueur; i++) {
            for (int j = 0; j < hauteur; j++) {
                //On crée une case avec l'indice correpondant
                cases[i][j] = new Case(new Coordonee(i, j));
            }
        }
        this.grilleRemplie = false;
    }

    /**
     * @return x longueur
     */
    public int getX() {
        return longueur;
    }

    /**
     * @return y hauteur
     */
    public int getY() {
        return hauteur;
    }

    /**
     * @param x abscisse
     * @param y ordonne
     * @return case à l'indice (x,y)
     */
    public Case getCase(int x, int y) {
        return this.cases[x][y];
    }


    public Case[][] getCases(Vec2d vec2d) {
        Case[][] bout_grille = new Case[(int) vec2d.x][(int) vec2d.y];
        int i = 0;
        int j = 0;
        for (int ligne = 0; ligne < (int) vec2d.x; ligne++) {
            for (int colonne = 0; colonne < (int) vec2d.y; colonne++) {
                bout_grille[i][j] = cases[ligne][colonne];
            }
        }
        return bout_grille;
    }

    /**
     * Permet d'ajouter une piece dans la grille
     *
     * @param piece
     */
    public void putPiece(Piece piece, Coordonee coordonee) {
        Coordonee coordonee1 = new Coordonee(coordonee.getX(),coordonee.getY());
        piece.setCoordonee(coordonee1);
        if (controlCases(piece, null)) {
            setChanged();
            notifyObservers(piece);
        }
        else {
            grilleRemplie = true;
            notifyObservers();
        }
    }

    /**
     * Methode qui fait bouger la piece dans la grille
     *
     * @param piece
     * @param direction
     */
    public void movePiece(Piece piece, Direction direction) {
        piece.move(direction);
        if (!controlCases(piece, direction)) {
            switch (direction) {
                case GAUCHE:
                    piece.move(Direction.DROITE);
                    break;
                case DROITE:
                    piece.move(Direction.GAUCHE);
                    break;
                case BAS:
                    piece.move(Direction.Haut);
            }
        }
        setChanged();
        notifyObservers(piece);


    }

    /**
     * Controle si toutes les colonnes des lignes sont occupés.
     * parcourt de la grille
     * décale les lignes précédentes la ligne à supprimer d'Y+1
     *
     * @return
     */
    public boolean controlLigne() {
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


    /**
     * @param piece
     * @param direction
     */
    public void rotate_piece(Piece piece, Direction direction) {
        piece.rotation(direction);
        if (controlCases(piece, direction)) {
            setChanged();
            notifyObservers(piece);
        } else {
            switch (direction) {
                case GAUCHE:
                    piece.rotation(Direction.DROITE);
                    break;
                case DROITE:
                    piece.rotation(Direction.GAUCHE);
                    break;
                case BAS:
                    piece.rotation(Direction.Haut);
            }
        }
    }

    /**
     * @param piece
     * @return boolean return
     */
    public boolean controlCases(Piece piece, Direction direction) {
        int[][] pc = piece.getCases();
        int x = piece.getCoordonee().getX();
        int y = piece.getCoordonee().getY();
        Vec2d dimension = piece.getDimension();
        Boolean fin = false;
        boolean verif = true;
        if (verif) {
            for (int ligne = 0; ligne < (int) dimension.x; ligne++) {
                for (int colonne = 0; colonne < (int) dimension.y; colonne++) {
                    if (pc[ligne][colonne] == 1) {
                        if (colonne + x < 0 || colonne + x >= longueur || ligne + y < 0) {
                            verif = false;
                        } else if (ligne + y >= hauteur) {
                            fin = true;
                            verif = false;
                        } else if (this.cases[colonne + x][ligne + y].getActif()) {
                            verif = false;
                            if(direction == Direction.BAS)
                                fin =true;
                        } else if (ligne + y <= hauteur - 1) {
                            if (this.cases[colonne + x][ligne + y].getActif()) {
                                fin = true;
                                verif = false;
                            }
                        }

                    }
                }
            }
        }
        if (fin) {
            for (int ligne = 0; ligne < (int) dimension.x; ligne++) {
                for (int colonne = 0; colonne < (int) dimension.y; colonne++) {
                    if (pc[ligne][colonne] == 1)
                        this.cases[colonne + x][ligne + y - 1].caseActiv();
                    if (ligne == dimension.x - 1 && dimension.y == colonne + 1)
                        piece.kill();
                }

            }

        }
        return verif;
    }

    public int getLigneASupprimer() {
        return ligneASupprimer;
    }

    public boolean isGrilleRemplie() {
        return grilleRemplie;
    }
}