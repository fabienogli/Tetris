package View;

import Controler.PlateauController;

import Model.Direction;
import Model.Piece;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;


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
    private Timeline timeline;
    private boolean pause = true;

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
        setControlClavier();

    }

    private void setControlClavier() {
        EventHandler<KeyEvent> handler =new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                switch (event.getCode()){
                    case ENTER:
                        if(pause){
                            pause = false;
                            startGame();
                            timeline.play();
                        }
                        break;
                    case P:
                        if(pause){
                            timeline.play();
                            pause = false;

                        }else{
                            timeline.stop();
                            pause = true;
                        }
                    case RIGHT:
                        if(!pause)
                            controller.getGrilleControler().movePiece(Direction.DROITE);
                        break;
                    case LEFT:
                        if(!pause)
                            controller.getGrilleControler().movePiece(Direction.GAUCHE);
                        break;
                    case DOWN:
                        if(!pause)
                            controller.getGrilleControler().movePiece(Direction.BAS);
                        break;
                    case E:
                        if(!pause)
                            controller.getGrilleControler().rotatePiece(Direction.DROITE);
                        break;
                    case A:
                        if(!pause)
                            controller.getGrilleControler().rotatePiece(Direction.GAUCHE);
                        break;
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
        fond_plateau.setFill(Color.DIMGREY);
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

        //Ajout prévisualisation de la piece
        double Xpos, Ypos;
        Ypos= hauteur/3;
        Xpos = 3*longueur/4;
        GridPane gridPane = vueGrille.getPrevisualisationPiece();
        gridPane.setTranslateX(Xpos);
        gridPane.setTranslateY(Ypos);
        this.getChildren().add(gridPane);

        //Ajout score
        vueGrille.setScorePos(Xpos,Ypos+100);
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
        controller.getGrilleControler().putPiece(vueGrille.generateRandomPiece(), vueGrille.getCoordoneeDepart());
    }

    public void startGame(){
        //Generer Piece et la controler
        controller.gestionJeu();
        timeline = new Timeline(new KeyFrame(
                Duration.millis(1000),
                ae->vueGrille.getControler().movePiece(Direction.BAS)
        ));
        timeline.setCycleCount(Animation.INDEFINITE);
    }

}
