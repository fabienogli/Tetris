package View;

import Controler.GrilleControler;
import Model.*;
import com.sun.javafx.geom.Vec2d;
import javafx.scene.Parent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.util.Observable;
import java.util.Observer;

/**
 * Vue de la grille
 * Created by Fabien on 14/02/2017.
 */
public class VueGrille extends Parent implements Observer {
    private final Text _score;
    private Grille grille;
    private GridPane gridPane,previsualisationPiece;
    private GrilleControler controler;
    private VueCase[][] cases,casesPrevisualisation;
    private Piece PieceCourante,pieceSuivante;
    private Coordonee coordoneePrec, coordoneeDepart;
    private int[][] casePiecePrec;
    private Boolean actif;
    private Color couleur_fond;
    private VuePiece vuePieceCourante;
    private int score;

    public VueGrille(double Xpos, double Ypos){

        actif = true;
        score = 0;
        //Initialisation de la grille
        gridPane = new GridPane();

        //Controller de la grille
        controler = new GrilleControler();
        controler.setVueGrille(this);

        //Récupération de la grille
        grille = new Grille(10,20);

        //settage de la position de depart
        coordoneeDepart = new Coordonee(4,0);

        //Ajout de l'observer
        grille.addObserver(this);
        controler.setGrille(grille);

        //Recuperation de la longueur de la grille
        int longueur = grille.getX();

        //recuperation de la hauteur de la grille
        int hauteur = grille.getY();

        //Creation de la vue des cases
        cases = new VueCase[longueur][hauteur];

        rowConstraints(hauteur, gridPane);

        columnConstraint(longueur, gridPane);

        //Rend visible les lignes de la grille
        gridPane.setGridLinesVisible(true);

        //Choix couleur fond
        couleur_fond = Color.WHITE;
        //Ajout de VueCase dans la grille
        for(int i=0; i<longueur;i++){
            for(int j=0; j<hauteur;j++){
                cases[i][j] =new VueCase(grille.getCase(i,j),couleur_fond);
                gridPane.add(cases[i][j], i, j);
            }
        }

        //Determine la position de la grille
        this.gridPane.setTranslateX(Xpos);
        this.gridPane.setTranslateY(Ypos);

        //Ajout de la grille
        this.getChildren().add(gridPane);

        //Initialisation de la prévisualisation de la grille
        makePrevisualisationPiece();
        _score = new Text("Score : " + score);
        this.getChildren().add(_score);
    }

    /**
     * Definit les contraintes des lignes
     * @param hauteur int (axe y)
     * @param gridPane Gridpane (grille)
     */
    public static void rowConstraints(int hauteur, GridPane gridPane){
        for(int i=0; i<hauteur; i++){
            RowConstraints rowConstraints = new RowConstraints(VueCase.getLenght());
            rowConstraints.setFillHeight(true);
            rowConstraints.setVgrow(Priority.ALWAYS);
            gridPane.getRowConstraints().add(rowConstraints);

        }
    }

    /**
     * Définit les contraintes des colonnes
     * @param longueur int (axe x)
     * @param gridPane Gridpane (grille)
     */
    public static void columnConstraint(int longueur,  GridPane gridPane){
        for(int j =0; j<longueur ;j++){
            ColumnConstraints columnConstraints = new ColumnConstraints(VueCase.getLenght());
            columnConstraints.setFillWidth(true);
            columnConstraints.setHgrow(Priority.ALWAYS);
            gridPane.getColumnConstraints().add(columnConstraints);
        }
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
        //Si la grille n'est pas active alors le jeu continue
        if(!grille.isGrilleRemplie()){
            this.PieceCourante = (Piece) o;
            vuePieceCourante = new VuePiece(PieceCourante);
            affichage_deplacementPiece();
            if(!PieceCourante.isAlive())
            {
                coordoneePrec = null;
                ligneComplete();
                controler.putPiece(pieceSuivante, coordoneeDepart);
                System.out.println(coordoneeDepart.getY());
            }
        }
    }





    /**
     * Si le control ligne est vrai, on affecte les couleurs des casses au dessus de la ligne à effacer
     * à la ligne a effacé et ainsi de suite
     */
    public void ligneComplete(){
            while(grille.controlLigne()){
                setScore(score+100);
                int ligneDelete = grille.getLigneASupprimer();
                for(int y =ligneDelete; y>0; y--){
                    for(int x =0; x< grille.getX(); x++){
                        cases[x][y].changerCouleur(cases[x][y-1].getColor());
                    }
                }
            }

    }


    public Boolean erasePiece(Boolean efface, Vec2d dimension){
        for(int i =0; i< casePiecePrec.length;i++){
            for(int j =0; j<casePiecePrec[0].length;j++){
                //Efface la piece à la position précédente
                if(casePiecePrec[j][i]==1)
                    cases[i+coordoneePrec.getX()][j+coordoneePrec.getY()].changerCouleur(couleur_fond);
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
                    cases[x+coordonee.getX()][y+coordonee.getY()].changerCouleur(vuePieceCourante.getColor());
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

    public Piece generateRandomPiece(){
        Piece piece;
        TypePiece typePiece;
        int r = (int)(Math.random()*(7));
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

    public void showPieceSuivante(Piece piece){
        VuePiece vuePiece = new VuePiece(piece);
        for(int x =0; x<previsualisationPiece.getColumnConstraints().size();x++)
            for(int y=0 ; y < previsualisationPiece.getRowConstraints().size();y++){
                if(y>=piece.getDimension().y||x>=piece.getDimension().x)    casesPrevisualisation[x][y].changerCouleur(couleur_fond);
                else {
                    if(piece.getCase(x,y)==1)
                        casesPrevisualisation[x][y].changerCouleur(vuePiece.getColor());
                    else
                        casesPrevisualisation[x][y].changerCouleur(couleur_fond);
                }
            }
    }

    public void makePrevisualisationPiece(){
        //Initialisation de la prévisualisation de la piece
        previsualisationPiece = new GridPane();
        casesPrevisualisation = new VueCase[4][4];

        columnConstraint(4, previsualisationPiece);
        rowConstraints(4,previsualisationPiece);


        for(int i=0; i<4;i++){
            for(int j=0; j<4;j++){
                casesPrevisualisation[i][j] =new VueCase(new Case(new Coordonee(i,j)),couleur_fond);
                previsualisationPiece.add(casesPrevisualisation[i][j], i, j);
            }
        }
    }

    public GridPane getPrevisualisationPiece(){
        return previsualisationPiece;
    }
    public Boolean getActif() {
        return actif;
    }

    public void setPieceSuivante(Piece pieceSuivante) {
        this.pieceSuivante = pieceSuivante;
        showPieceSuivante(pieceSuivante);
    }

    public void setScorePos(double x, double y) {
        _score.setTranslateX(x);
        _score.setTranslateY(y);
    }

    public void setScore(int score) {
        this.score = score;
        _score.setText("Score : "+score);
    }

    public Coordonee getCoordoneeDepart() {
        return coordoneeDepart;
    }
}
