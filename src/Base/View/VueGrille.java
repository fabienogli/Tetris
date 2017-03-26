package Base.View;

import Base.Controler.GrilleControler;
import Base.Model.*;
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
    protected final Text _score;
    protected Grille grille;
    protected GridPane gridPane,previsualisationPiece;
    protected GrilleControler controler;
    protected VueCase[][] cases,casesPrevisualisation;
    protected Piece PieceCourante,pieceSuivante;
    protected Coordonee coordoneePrec, coordoneeDepart;
    private int[][] casePiecePrec;
    private Boolean actif;
    private Color couleur_fond;
    protected VuePiece vuePieceCourante;
    protected int score;

    public VueGrille(double Xpos, double Ypos, int xPrev, int yPrev){

        actif = true;
        score = 0;
        //Initialisation de la grille
        this.gridPane = new GridPane();

        //Controller de la grille
        controler = new GrilleControler();
        controler.setVueGrille(this);

        initiateGrille();




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


        _score = new Text("Score : " + score);
        this.getChildren().add(_score);
    }

    protected void initiateGrille() {}

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
        gestionGrille((Piece) o);

    }

    protected VuePiece initiateVuePiece(Piece piece) {
        VuePiece vuePiece = new VuePiece(piece);
        return vuePiece;
    }


    public void gestionGrille(Piece piece){

    }






    public Boolean erasePiece(Boolean efface, Piece.Vecteur dimension){
        for(int i =0; i< casePiecePrec.length;i++){
            for(int j =0; j<casePiecePrec[0].length;j++){
                //Efface la piece à la position précédente
                if(casePiecePrec[j][i]==1)
                    cases[i+coordoneePrec.getX()][j+coordoneePrec.getY()].changerCouleur(couleur_fond);
            }
            if(i== dimension.getX()-1)
                efface = true;
        }
        return efface;
    }

    public void displayPiece(Coordonee coordonee, Piece.Vecteur dimension){
        int [][] piece = PieceCourante.getCases();
        for(int x= 0; x< dimension.getX();x++){
            for(int y= 0; y< dimension.getY();y++){
                if (piece[y][x]==1){
                    cases[x+coordonee.getX()][y+coordonee.getY()].changerCouleur(vuePieceCourante.getColor());
                }
            }
        }
    }

    public void affichage_deplacementPiece(){
        Boolean efface = false;
        Coordonee coordonee = PieceCourante.getCoordonee();
        Piece.Vecteur dimension = PieceCourante.getDimension();
        if(coordoneePrec != null){
            while (!efface)
                efface = erasePiece(efface,dimension);
        }
        displayPiece(coordonee,dimension);
        casePiecePrec = PieceCourante.getCases();
        coordoneePrec = new Coordonee(coordonee.getX(),coordonee.getY());
    }


    /**
     * On tente de faire ce code dans une nouvelle classe
     * @param
     * @param
     * @return
     */

    public void showPieceSuivante(Piece piece, GridPane previsualisationPiece){
        VuePiece vuePiece = initiateVuePiece(piece);
        for(int x =0; x<previsualisationPiece.getColumnConstraints().size();x++)
            for(int y=0 ; y < previsualisationPiece.getRowConstraints().size();y++){
                if(y>=piece.getDimension().getY()||x>=piece.getDimension().getX())    casesPrevisualisation[x][y].changerCouleur(couleur_fond);
                else {
                    if(piece.getCase(x,y)==1){
                        casesPrevisualisation[x][y].changerCouleur(vuePiece.getColor());
                    }
                    else
                        casesPrevisualisation[x][y].changerCouleur(couleur_fond);
                }
            }
    }



    public GridPane makePrevisualisationPiece(int x, int y){
        GridPane previsualisationPiece = new GridPane();

        //Initialisation de la prévisualisation de la piece
        casesPrevisualisation = makecasePrevisu(x,y);

        columnConstraint(x, previsualisationPiece);
        rowConstraints(y,previsualisationPiece);


        for(int i=0; i<x;i++){
            for(int j=0; j<y;j++){
                previsualisationPiece.add(casesPrevisualisation[i][j], i, j);
            }
        }
        return previsualisationPiece;

    }


    public VueCase[][] makecasePrevisu(int x, int y){
        //Initialisation de la prévisualisation de la piece
        VueCase[][] casesPrevisualisation = new VueCase[x][y];
        for(int i=0; i<x;i++){
            for(int j=0; j<y;j++){
                casesPrevisualisation[i][j] =new VueCase(new Case(new Coordonee(i,j)),couleur_fond);
            }
        }
        return casesPrevisualisation;
    }

    /**
     * stop
     * @return
     */

    public GridPane getPrevisualisationPiece(){
        return previsualisationPiece;
    }
    public Boolean getActif() {
        return actif;
    }

    public void setPieceSuivante(Piece pieceSuivante) {
        this.pieceSuivante = pieceSuivante;
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

    public Piece getPieceCourante() {
        return PieceCourante;
    }

    public Piece getPieceSuivante() {
        return pieceSuivante;
    }
}
