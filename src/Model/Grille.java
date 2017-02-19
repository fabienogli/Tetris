package Model;


import com.sun.javafx.geom.Vec2d;

/**
 * Created by Fabien on 14/02/2017.
 */
public class Grille {
    private int x;
    private int y;
    private Case[][] cases;
    private static int nbrePiece;

    public Grille(int x, int y) {
        this.x = x;
        this.y = y;
        this.cases = new Case[x][y];
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                cases[i][j] = new Case();
                cases[i][j].setCoordonee(new Coordonee(i, j));
            }
        }
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

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

    public void putPiece(Piece piece){
        int x = piece.getCoordonee().getX();
        int y= piece.getCoordonee().getY();
        Vec2d dimension = piece.getDimension();
        Case[][] piece_c = piece.getCases();
        for(int ligne =0; ligne <(int)dimension.x; ligne++){
            int z = y;
            for (int colonne=0; colonne<(int)dimension.y; colonne++){
                if(piece_c[ligne][colonne] ==null)  {}else {
                    this.cases[x][z] = piece_c[ligne][colonne];
                    this.cases[x][z].getCoordonee().setPiece(true, piece.getTypePiece());
                }
                z++;
            }
            x++;
        }
    }


}