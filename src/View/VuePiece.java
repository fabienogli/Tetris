package View;

import Controler.PieceControler;
import Model.Case;
import Model.Piece;
import javafx.scene.Parent;
import javafx.scene.control.Control;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;


/**
 * Created by Fabien on 21/02/2017.
 */
public class VuePiece extends Parent{
    private int caseLenght = VueCase.getLenght();
    private VueCase[][] vueCases;
    private int longueur;
    private int hauteur;
    private GridPane structurePiece;

    /**
     * Constructeur VuePiece
     * @param piece Piece
     */
    public VuePiece(Piece piece){
        //Affecte les dimensions de la piece à longueur et hauteur
        longueur = (int)piece.getDimension().x;
        hauteur = (int)piece.getDimension().y;
        System.out.println(longueur+hauteur);
        //Initialisation du tableau VueCase en fonction de ses dimension
        vueCases = new VueCase[longueur][hauteur];

        /**
         * Debut test
         */
        structurePiece = new GridPane();
        /**
         * fin test
         */
        //Construction de la piece
        for(int x =0; x<longueur;x++ ){
            for(int y =0; y< hauteur; y++){
                if (piece.getCase(x,y).getActif()){
                    vueCases[x][y] = new VueCase(piece.getCase(x,y), Color.RED);
                    structurePiece.add(vueCases[x][y],x,y);
                }
            }
        }
        this.getChildren().add(structurePiece);

//        if(aCase.getActif()){
//            this.apparence.setFill(palette[choixCouleur()]);
//        }
//        else this.apparence.setFill(Color.WHITE);
//        this.apparence.setStroke(Color.BLACK);
    }

//    public void remplirPalette(){
//        palette = new Color[nbCouleur];
//        palette[0] = Color.CYAN;
//        palette[1] = Color.BLUE;
//        palette[2] = Color.ORANGE;
//        palette[3] = Color.LIME;
//        palette[4] = Color.PURPLE;
//        palette[5] = Color.RED;
//    }
//
//    public int choixCouleur(){
//        int i= (int)Math.random()*(nbCouleur-1);
//        return i;
//    }
}
