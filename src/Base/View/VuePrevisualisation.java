package Base.View;

import Base.Model.Case;
import Base.Model.Coordonee;
import Base.Model.Piece;
import javafx.scene.Parent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

/**
 * Created by Fabien on 25/03/2017.
 */
public class VuePrevisualisation extends Parent {

    protected Piece piece;
    protected GridPane gridPane;
    protected Color color;

    public VuePrevisualisation(Piece piece, Color color){
        this.color = color;
        this.piece = piece;
        gridPane = new GridPane();

        VueCase[][] cases = makecasePrevisu(piece, color);

        VueGrille.columnConstraint(piece.getDimension().getX(), gridPane);
        VueGrille.rowConstraints(piece.getDimension().getY(),gridPane);

        VuePiece vuePiece = intiateVuePiece(piece);

        for(int x =0; x<gridPane.getColumnConstraints().size();x++)
            for(int y=0 ; y < gridPane.getRowConstraints().size();y++){
                if(y>=piece.getDimension().getY()||x>=piece.getDimension().getX())    cases[x][y].changerCouleur(color);
                else {
                    if(piece.getCase(x,y)==1){
                        cases[x][y].changerCouleur(vuePiece.getColor());
                    }
                    else
                        cases[x][y].changerCouleur(color);
                }
            }
        this.getChildren().add(gridPane);
    }

    protected VuePiece intiateVuePiece(Piece piece) {
        return null;
    }

    public VueCase[][] makecasePrevisu(Piece piece, Color color){
        //Initialisation de la prévisualisation de la piece
        int x =piece.getDimension().getX();
        int y = piece.getDimension().getY();
        VueCase[][] cases = new VueCase[x][y];
        for(int i=0; i<x;i++){
            for(int j=0; j<y;j++){
                VueCase vueCase = new VueCase(new Case(new Coordonee(i,j)), color );
                vueCase.eraseStroke();
                cases[i][j] = vueCase;
            }
        }
        return cases;
    }

}
