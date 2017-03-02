package View;

import Controler.PieceControler;
import Model.Case;
import Model.Direction;
import Model.Piece;
import javafx.animation.RotateTransition;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.util.Duration;


/**
 * Created by Fabien on 21/02/2017.
 */
public class VuePiece extends Parent{
    private int caseLenght = VueCase.getLenght();
    private VueCase[][] vueCases;
    private int longueur;
    private int hauteur;
    private GridPane structurePiece;
    private PieceControler controler;
    private Color[] palette;
    private int nbCouleur = 6;
    private int xPos,yPos;

    /**
     * Constructeur VuePiece
     * @param piece Piece
     */
    public VuePiece(Piece piece){
        //On initalise le controleur
        this.controler = new PieceControler(piece.getTypePiece());
        //on set VuePiece au controleur
        this.controler.setVuePiece(this);

        //On recupère la position de la piece
        xPos = piece.getCoordonee().getX();
        yPos = piece.getCoordonee().getY();

        //Affecte les dimensions de la piece à longueur et hauteur
        longueur = (int)piece.getDimension().x;
        hauteur = (int)piece.getDimension().y;

        //Initialisation du tableau VueCase en fonction de ses dimension
        vueCases = new VueCase[longueur][hauteur];

        //On remplie la palette de couleur
        remplirPalette();
        int couleur = choixCouleur();

        //Creation de la structure de la piece
        structurePiece = new GridPane();

        //Construction de la piece
        for(int x =0; x<longueur;x++ ){
            for(int y =0; y< hauteur; y++){
                if (piece.getCase(x,y).getActif()){
                    vueCases[x][y] = new VueCase(piece.getCase(x,y), palette[couleur]);
                    structurePiece.add(vueCases[x][y],x,y);
                }
            }
        }
        //On definit la position de la piece
        structurePiece.setTranslateX(xPos);
        structurePiece.setTranslateY(yPos);

        //On ajoute la structure de la piece
        this.getChildren().add(structurePiece);


        //Gestion des actions du clavier
        EventHandler<KeyEvent> handler =new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                switch (event.getCode()){
                    case RIGHT:
                        controler.movePiece(Direction.DROITE);
                        break;
                    case LEFT:
                        controler.movePiece(Direction.GAUCHE);
                        break;
                    case DOWN:
                        controler.movePiece(Direction.BAS);
                        break;
                    case E:
                        rotate(Direction.DROITE);
                        break;
                    case A:
                        rotate(Direction.GAUCHE);
                        break;
                    default:
                        break;
                }
            }
        };
        //this.setOnKeyPressed(handler);
        this.requestFocus();
        this.setFocusTraversable(true);
    }

    /**
     * @return xPos indice abscisse dans la grille
     */
    public int getxPos() {
        return xPos;
    }

    /**
     * @return yPos indice ordonne dans la grille
     */
    public int getyPos() {
        return yPos;
    }

    public void rotate(Direction direction){
        RotateTransition rt = new RotateTransition(Duration.millis(200), this);

        switch (direction){
            case DROITE:
                rt.setByAngle(90);
                rt.setAutoReverse(true);
                rt.play();
                break;
            case GAUCHE:
                rt.setByAngle(-90);
                rt.setAutoReverse(false);
                rt.play();
                break;
                default:
                    break;
        }
    }
    /**
     * Positionne la Piece dans la grille
     * @param xPos
     */
    public void setxPos(int xPos){
        this.xPos = xPos;
        setTranslateX(xPos*VueCase.getLenght());
    }

    public void setyPos(int yPos){
        this.yPos = yPos;
        this.setTranslateY(yPos*VueCase.getLenght());
    }

    /**
     * Remplie la palette de couleur
     */
    public void remplirPalette(){
        palette = new Color[nbCouleur];
        palette[0] = Color.CYAN;
        palette[1] = Color.BLUE;
        palette[2] = Color.ORANGE;
        palette[3] = Color.LIME;
        palette[4] = Color.PURPLE;
        palette[5] = Color.RED;
    }

    /**
     * Tirage aleatoire de la couleur
     * @return indice de la couleur
     */
    public int choixCouleur(){
        double i= Math.random()*(nbCouleur-1);
        return (int)i;
    }

    public PieceControler getControler() {
        return controler;
    }
}
