package View;

import Controler.PlateauController;
import Model.Direction;
import Model.Piece;
import Model.TypePiece;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;


/**
 * Created by Fabien on 18/02/2017.
 */
public class VuePlateau extends Parent{
    int longueur;
    int hauteur;
    private Text titre;
    private Rectangle fond_plateau;
    private VueGrille vueGrille;
    private PlateauController controller;

    /**
     * Constructeur VuePlateau
     */
    public VuePlateau() {
        //determination de la longueur de la fenêtre
        longueur = 600;
        hauteur = 600;
        creationFond();
        creationTitre(TypeJeu.Tetris);
        creationGrille();


        //Gestion du controleur
        controller = new PlateauController();
        controller.setGrilleControler(vueGrille.getControler());
        controller.setVuePlateau(this);

        //Gestion des actions du clavier
        EventHandler<KeyEvent> handler =new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                switch (event.getCode()){
                    case ENTER:
                        startGame();
                        break;
                    case RIGHT:
                        controller.getGrilleControler().movePiece(Direction.DROITE);
                        break;
                    case LEFT:
                        controller.getGrilleControler().movePiece(Direction.GAUCHE);
                        break;
                    case DOWN:
                        controller.getGrilleControler().movePiece(Direction.BAS);
                        break;
//                    case E:
//                        rotate(Direction.DROITE);
//                        break;
//                    case A:
//                        rotate(Direction.GAUCHE);
//                        break;
                    default:
                        break;
                }
            }
        };
        this.setOnKeyPressed(handler);
    }

    /**
     * Creation du Fond du plateau
     */
    private void creationFond() {
        fond_plateau = new Rectangle();
        fond_plateau.setWidth(longueur);
        fond_plateau.setHeight(hauteur);
        fond_plateau.setFill(Color.DEEPSKYBLUE);
        this.getChildren().add(fond_plateau);
    }

    /**
     * Ajout du titre selon le type de jeu
     * @param typeJeu TypeJeu
     */
    public void creationTitre(TypeJeu typeJeu){
        switch (typeJeu){
            case Tetris:
                titre = new Text(hauteur/2-60,50,"Tetris");
                titre.setFont(new Font(40));
        }
        this.getChildren().add(titre);
    }


    /**
     * Creation de la grille
     */
    public void creationGrille(){
        double XposGrille = hauteur/3;
        double YposGrille = longueur/4;
        vueGrille = new VueGrille(XposGrille,YposGrille);
        this.getChildren().add(vueGrille);
        //Permet de faire fonctionner la gestion du clavier dans la classe VueGrille
//        vueGrille.setFocusTraversable(true);
    }

    public Text getTitre() {
        return titre;
    }

    /**
     * @return longueur Plateau
     */
    public int getLongueur() {
        return longueur;
    }

    /**
     * @return hauteur Plateau
     */
    public int getLargeur() {
        return hauteur;
    }

    /**
     * @return vueGrille
     */
    public VueGrille getVueGrille() {
        return vueGrille;
    }

    /**
     * Ajout d'un element a la grille
     */
    public void setPiece(Node node){
        this.getChildren().add(node);
    }

    public void generatePiece(){
        controller.getGrilleControler().putPiece(new Piece(TypePiece.Tetrimino_J));
    }

    public void startGame(){
        //Generer Piece et la controler
        controller.gestionJeu();
    }
}
