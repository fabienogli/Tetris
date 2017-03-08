package View;

import Controler.GrilleControler;
import Model.*;
import com.sun.javafx.geom.Vec2d;
import javafx.event.EventHandler;
import javafx.geometry.Dimension2D;
import javafx.scene.Parent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Rotate;

import java.security.Key;
import java.util.Observable;
import java.util.Observer;

/**
 * Vue de la grille
 * Created by Fabien on 14/02/2017.
 */
public class VueGrille extends Parent implements Observer {
    private Grille grille;
    private GridPane gridPane;
    private GrilleControler controler;
    private VueCase[][] cases;
    private Piece PieceCourante;
    private Vec2d dimensionPrec;
    private Coordonee coordoneePrec;

    public VueGrille(double Xpos, double Ypos){
        //Initialisation de la grille
        gridPane = new GridPane();

        //Controller de la grille
        controler = new GrilleControler();
        controler.setVueGrille(this);

        //Récupération de la grille
        grille = new Grille(10,20);

        //Ajout de l'observer
        grille.addObserver(this);
        controler.setGrille(grille);

        //Recuperation de la longueur de la grille
        int longueur = grille.getX();

        //recuperation de la hauteur de la grille
        int hauteur = grille.getY();

        //Creation de la vue des cases
        cases = new VueCase[longueur][hauteur];

        //Definit les contraintes des lignes
        for(int i=0; i<hauteur; i++){
            RowConstraints rowConstraints = new RowConstraints(VueCase.getLenght());
            rowConstraints.setFillHeight(true);
            rowConstraints.setVgrow(Priority.ALWAYS);
            gridPane.getRowConstraints().add(rowConstraints);

        }
        //Définit les contraintes des colonnes
        for(int j =0; j<longueur ;j++){
            ColumnConstraints columnConstraints = new ColumnConstraints(VueCase.getLenght());
            columnConstraints.setFillWidth(true);
            columnConstraints.setHgrow(Priority.ALWAYS);
            gridPane.getColumnConstraints().add(columnConstraints);
        }

        //Rend visible les lignes de la grille
        gridPane.setGridLinesVisible(true);

        //Ajout de VueCase dans la grille
        for(int i=0; i<longueur;i++){
            for(int j=0; j<hauteur;j++){
                cases[i][j] =new VueCase(grille.getCase(i,j));
                gridPane.add(cases[i][j], i, j);
            }
        }

        //Determine la position de la grille
        this.gridPane.setTranslateX(Xpos);
        this.gridPane.setTranslateY(Ypos);

        //Ajout de la grille
        this.getChildren().add(gridPane);
    }

    /**
     * @return GrilleControler
     */
    public GrilleControler getControler() {
        return controler;
    }

    /**
     * @return grille
     */
    public Grille getGrille() {
        return grille;
    }

    /**
     * @return gridPane Représentation graphique de la grille
     */
    public GridPane getGridPane() {
        return gridPane;
    }

    @Override
    public void update(Observable observable, Object o) {
        this.PieceCourante = (Piece) o;
        Boolean efface = false;
        Coordonee coordonee = PieceCourante.getCoordonee();
        Vec2d dimension = PieceCourante.getDimension();
        if(coordoneePrec != null){
            while (!efface)
                for(int i =0; i< dimensionPrec.x;i++){
                    for(int j =0; j<dimensionPrec.y;j++){
                        //Effacé la piece à la position précédente
                        cases[i+coordoneePrec.getX()][j+coordoneePrec.getY()].changerCouleur(Color.WHITE);
                    }
                    if(i== dimension.x-1)
                        efface = true;
                }
        }
        for(int x= 0; x< dimension.x;x++){
            for(int y= 0; y< dimension.y;y++){
                if (PieceCourante.getCase(x,y)!=null && PieceCourante.getCase(x,y).getActif()){
                    cases[x+coordonee.getX()][y+coordonee.getY()].changerCouleur(PieceCourante.getColor());
                }
            }
        }
        coordoneePrec = new Coordonee(coordonee.getX(),coordonee.getY());
        dimensionPrec = new Vec2d(PieceCourante.getDimension().x, PieceCourante.getDimension().y);
    }

    public void rotatePiece(Piece piece, Direction direction){
        Coordonee coordonee = piece.getCoordonee();
        Vec2d dimension = piece.getDimension();
        double pivotX = piece.getPivotX();
        double pivotY = piece.getPivotY();
        double angle;
        switch (direction){
            case DROITE:
                angle = 90;
                break;
            case GAUCHE:
                angle = -90;
                break;
            default:
                angle = 0;
                break;
        }
        Rotate rotation = new Rotate(angle, pivotX,pivotY);
        for(int x= 0; x< dimension.x;x++){
            for(int y= 0; y< dimension.y;y++){
                if (piece.getCase(x,y)!=null && piece.getCase(x,y).getActif()){
                    cases[x+coordonee.getX()][y+coordonee.getY()].getTransforms().add(rotation);
                }
            }
        }
    }
}
