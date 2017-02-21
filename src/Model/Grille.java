package Model;


import com.sun.javafx.geom.Vec2d;

/**
 * Created by Fabien on 14/02/2017.
 */
public class Grille {

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
     * @param born boolean true si la piece est deja dans la grille
     */
    public void put_erasePiece(Piece piece, boolean born){
        int x = piece.getCoordonee().getX();
        int y= piece.getCoordonee().getY();
        Vec2d dimension = piece.getDimension();
        Case[][] piece_c = piece.getCases();
        for(int ligne =0; ligne <(int)dimension.x; ligne++){
            int z = y;
            for (int colonne=0; colonne<(int)dimension.y; colonne++){
                if(piece_c[ligne][colonne] !=null){
                    if (!born){
                        this.cases[x][z] = piece_c[ligne][colonne];
                        this.cases[x][z].getCoordonee().setPiece(true, piece.getTypePiece());
                    }
                    else {
                        System.out.println("ici");
                        this.cases[x][y].caseInactiv();
                        this.cases[x][z].getCoordonee().setPiece(false, piece.getTypePiece());
                    }
                }
                z++;
            }
            x++;
        }
    }

    /**
     * Methode qui fait bouger la piece dans la grille
     * @param piece
     * @param direction
     */
    public void movePiece(Piece piece, Direction direction){
        int x = piece.getCoordonee().getX();
        int y = piece.getCoordonee().getY();
        put_erasePiece(piece, true);
        piece.move(direction);
        put_erasePiece(piece,false);

    }


}