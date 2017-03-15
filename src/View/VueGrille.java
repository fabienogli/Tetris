package View;

import Controler.GrilleControler;
import Model.*;
import com.sun.javafx.geom.Vec2d;
import javafx.scene.Parent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
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
    private int[][] casePiecePrec;
    private Boolean actif;

    public VueGrille(double Xpos, double Ypos){

        actif = true;

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
        affichage_deplacementPiece();
        if(!PieceCourante.isAlive())
        {
            coordoneePrec = null;
            ligneComplete();
            controler.putPiece(generateRandomPiece());
        }
    }

    public void ligneComplete(){
            if(grille.controlLigne()){
                int ligneDelete = grille.getLigneASupprimer();
                for(int y =ligneDelete-1; y>0; y--){
                    for(int x =0; x< grille.getX(); x++){
                        cases[x][y].changerCouleur(cases[x][y-1].getColor());
                        if( y == 1){
                            cases[x][0] = new VueCase(grille.getCase(x,0));
                        }
                    }
                }
            }

    }


    public Boolean erasePiece(Boolean efface, Vec2d dimension){
        for(int i =0; i< casePiecePrec.length;i++){
            for(int j =0; j<casePiecePrec[0].length;j++){
                //Efface la piece à la position précédente
                if(casePiecePrec[j][i]==1)
                    cases[i+coordoneePrec.getX()][j+coordoneePrec.getY()].changerCouleur(Color.WHITE);
            }
            if(i== dimension.x-1)
                efface = true;
        }
        return efface;
    }

    public void displayPiece(Coordonee coordonee, Vec2d dimension){
        int [][] piece = PieceCourante.getCases();
        for(int x= 0; x< dimension.x;x++){
            for(int y= 0; y< dimension.y;y++){
                if (piece[y][x]==1){
                    cases[x+coordonee.getX()][y+coordonee.getY()].changerCouleur(PieceCourante.getColor());
                }
            }
        }
    }

    public void affichage_deplacementPiece(){
        Boolean efface = false;
        Coordonee coordonee = PieceCourante.getCoordonee();
        Vec2d dimension = PieceCourante.getDimension();
        if(coordoneePrec != null){
            while (!efface)
                efface = erasePiece(efface,dimension);
        }
        displayPiece(coordonee,dimension);
        casePiecePrec = PieceCourante.getCases();
        coordoneePrec = new Coordonee(coordonee.getX(),coordonee.getY());
    }

    public void generatePiece(){
        grille.putPiece(generateRandomPiece());
    }

    public Piece generateRandomPiece(){
        Piece piece;
        TypePiece typePiece;
        int r = (int)(Math.random()*(7+1));
        switch (r){
            case 0:
                 typePiece = TypePiece.Tetrimino_I;
                break;
            case 1:
                typePiece = TypePiece.Tetrimino_O;
                break;
            case 2:
                typePiece = TypePiece.Tetrimino_J;
                break;
            case 3:
                typePiece = TypePiece.Tetrimino_L;
                break;
            case 4:
                typePiece = TypePiece.Tetrimino_S;
                break;
            case 5:
                typePiece = TypePiece.Tetrimino_Z;
                break;
            case 6:
                typePiece = TypePiece.Tetrimino_T;
                break;
            default:
                typePiece = TypePiece.Tetrimino_O;
                break;
        }
        piece = new Piece(typePiece);
        return piece;
    }
    public Boolean getActif() {
        return actif;
    }


}
