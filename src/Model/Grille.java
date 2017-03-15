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
    private int ligneASupprimer;

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
        if(controlCases(piece)){
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
            if(controlCases(piece)){
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

    /**
     * Controle si toutes les colonnes des lignes sont occupés.
     * parcourt de la grille
     * décale les lignes précédentes la ligne à supprimer d'Y+1
     * @return
     */
    public boolean controlLigne(){
        boolean verif = false;
        int ligne = hauteur-1;
        while(ligne> 0 && !verif){
            boolean tmp = this.cases[0][ligne].getActif();
            for (int colonne = 0; colonne < longueur; colonne++) {
                tmp = this.cases[colonne][ligne].getActif();
                if(colonne == longueur-1){
                    if(tmp){
                        ligneASupprimer = ligne;
                        verif = true;
                    }
                }
            }
            ligne--;
        }
//        if(verif){
//            for(int y =ligneASupprimer-1; y>0; y--){
//                for(int x =0; x< longueur; x++){
//                    this.cases[x][y] = this.cases[x][y-1];
//                    this.cases[x][y].setCoordonee(new Coordonee(x,y-1));
//                    if( y == 1){
//                        this.cases[x][0] = new Case(new Coordonee(x,0));
//                    }
//                }
//            }
//        }
        if(verif)
        System.out.println(ligneASupprimer);
        return verif;
    }


    /**
     * @param piece
     * @param direction
     */
    public void rotate_piece(Piece piece, Direction direction){
        piece.rotation(direction);
        if(controlCases(piece)){
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

    /**
     * @param piece
     * @return boolean return
     */
    public boolean controlCases(Piece piece){
        int[][] pc = piece.getCases();
        int x = piece.getCoordonee().getX();
        int y= piece.getCoordonee().getY();
        Vec2d dimension = piece.getDimension();
        Boolean fin = false;
        boolean verif = true;
        if(verif){
            for(int ligne =0; ligne <(int)dimension.x; ligne++){
                for (int colonne=0; colonne<(int)dimension.y; colonne++){
                    if(pc[ligne][colonne] ==1) {
                        if (colonne + x < 0 || colonne + x >= longueur || ligne + y < 0 || ligne + y >= hauteur) {
                            verif = false;
                        } else if (ligne + y < hauteur - 1){
                            if (this.cases[colonne + x][ligne + y + 1].getActif())
                                fin = true;
                        }
                        if(ligne+y == hauteur-1)
                            fin= true;
                    }
                }
            }
        }
        if(fin&&verif){
            for(int ligne =0; ligne <(int)dimension.x; ligne++){
                for (int colonne=0; colonne<(int)dimension.y; colonne++){
                    if(pc[ligne][colonne] ==1)
                        this.cases[colonne+x][ligne+y].caseActiv();
                    if(ligne == dimension.x -1 && dimension.y == colonne+1)
                        piece.kill();
                    }

            }

        }
        return verif;
    }

    public int getLigneASupprimer() {
        return ligneASupprimer;
    }
}