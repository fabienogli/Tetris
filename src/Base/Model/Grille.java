package Base.Model;


import java.util.Observable;

/**
 * Hérité d'observable pour envoyer des notifications à la VUE.
 * Created by Fabien on 14/02/2017.
 */
public class Grille extends Observable {

    protected int longueur, hauteur;
    protected Case[][] cases;
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


    public Case[][] getCases(Piece.Vecteur vec2d) {
        Case[][] bout_grille = new Case[vec2d.getX()][vec2d.getY()];
        int i = 0;
        int j = 0;
        for (int ligne = 0; ligne < vec2d.getX(); ligne++) {
            for (int colonne = 0; colonne < vec2d.getY(); colonne++) {
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
        Coordonee coordonee1 = new Coordonee(coordonee.getX(), coordonee.getY());
        piece.setCoordonee(coordonee1);
        if (controlCases(piece, null)) {
            setChanged();
            notifyObservers(piece);
        } else {
            grilleRemplie = true;
            setChanged();
            notifyObservers(piece);
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
                    break;
                case Haut:
                    piece.move(Direction.BAS);
                    break;
                default:
                    break;
            }
        }
        setChanged();
        notifyObservers(piece);


    }


    public Boolean controlLigne() {
        return null;
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
                    break;
            }
        }
    }

    /**
     * @param piece
     * @return boolean return
     */
    public boolean controlCases(Piece piece, Direction direction) {
        boolean verif, fin;
        verif = controlGrille(piece);
        fin = controlFin(piece, direction);
        if (fin) {
            stopPiece(piece);
        }

        return verif;
    }

    public Boolean controlGrille(Piece piece) {
        int[][] pc = piece.getCases();
        int x = piece.getCoordonee().getX();
        int y = piece.getCoordonee().getY();
        Piece.Vecteur dimension = piece.getDimension();
        Boolean verif = true;
        if (verif) {
            for (int ligne = 0; ligne < dimension.getX(); ligne++) {
                for (int colonne = 0; colonne < dimension.getY(); colonne++) {
                    if (pc[ligne][colonne] == 1) {
                        if (colonne + x < 0 || colonne + x >= longueur || ligne + y < 0 || ligne + y >= hauteur) {
                            verif = false;
                        } else if (this.cases[colonne + x][ligne + y].getActif()) {
                            verif = false;
                        } else if (ligne + y <= hauteur - 1) {
                            if (this.cases[colonne + x][ligne + y].getActif()) {
                                verif = false;
                            }
                        }

                    }
                }
            }
        }
        return verif;
    }


    public Boolean controlFin(Piece piece, Direction direction) {
        return false;
    }


    public void stopPiece(Piece piece) {
        int[][] pc = piece.getCases();
        int x = piece.getCoordonee().getX();
        int y = piece.getCoordonee().getY();
        Piece.Vecteur dimension = piece.getDimension();
        for (int ligne = 0; ligne < dimension.getX(); ligne++) {
            for (int colonne = 0; colonne < dimension.getY(); colonne++) {
                if (pc[ligne][colonne] == 1)
                    this.cases[colonne + x][ligne + y - 1].caseActiv();
                if (ligne == dimension.getX() - 1 && dimension.getY() == colonne + 1)
                    piece.kill();
            }

        }
    }

    public Piece generateRandomPiece() {
        return null;
    }


    public boolean isGrilleRemplie() {
        return grilleRemplie;
    }
}