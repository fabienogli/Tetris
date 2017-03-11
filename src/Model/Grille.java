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
    private static int nbrePiece;

    /**
     * Constructeur de la grille
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
    public Case getCase(int x, int y){
        return this.cases[x][y];
    }


    public Case[][] getCases(Vec2d vec2d) {
        Case[][] bout_grille = new Case[(int)vec2d.x][(int)vec2d.y];
        int i=0;
        int j=0;
        for(int ligne=0; ligne<(int)vec2d.x; ligne++){
            for(int colonne=0; colonne<(int)vec2d.y;colonne++){
                bout_grille[i][j] = cases[ligne][colonne];
            }
        }
        return bout_grille;
    }

    /**
     * Permet d'ajouter une piece dans la grille
     * @param piece
     */
    public void putPiece(Piece piece){
        if(!controlCases(piece)){
            setChanged();
            notifyObservers(piece);
        }
    }

    /**
     * Methode qui fait bouger la piece dans la grille
     * @param piece
     * @param direction
     */
    public void movePiece(Piece piece, Direction direction){
        piece.move(direction);
        if(!controlCases(piece)){
            setChanged();
            notifyObservers(piece);
        }
        else {
            switch (direction){
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
    }

    public void rotate_piece(Piece piece, Direction direction){
        piece.rotation(direction);
        if(!controlCases(piece)){
            setChanged();
            notifyObservers(piece);
        }
        else {
            switch (direction){
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

    public boolean controlCases(Piece piece){
        int[][] pc = piece.getCases();
//


        int x = piece.getCoordonee().getX();
        int y= piece.getCoordonee().getY();
        Vec2d dimension = piece.getDimension();
        Boolean fin = false;
        boolean verif = false;
        if(!verif){
            for(int ligne =0; ligne <(int)dimension.x; ligne++){
                for (int colonne=0; colonne<(int)dimension.y; colonne++){
                    if(pc[ligne][colonne] ==1){
                        if(ligne+y == hauteur-1)
                            fin= true;
                        if(colonne+x<0||colonne+x>=longueur||ligne+y<0||ligne+y>=hauteur)
                            verif = true;
                        else if(this.cases[colonne+x][ligne+y].getActif()){
                            verif = true;

                        }
                    }
                }
            }
        }
        if(fin){
            for(int ligne =0; ligne <(int)dimension.x; ligne++){
                for (int colonne=0; colonne<(int)dimension.y; colonne++){
                    System.out.println("fin");
                    this.cases[colonne+x][ligne+y].caseActiv();
                    }
            }
            piece.kill();
        }
        return verif;
    }


}