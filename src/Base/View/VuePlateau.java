package Base.View;

import Base.Controler.PlateauController;

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


/**
 * Created by Fabien on 18/02/2017.
 */
public class VuePlateau extends Parent{
    protected int longueur;
    protected int hauteur;
    private Text titre;
    private Rectangle fond_plateau;
    protected VueGrille vueGrille;
    protected PlateauController controller;
    protected Timeline timeline;
    protected boolean pause = true;
    protected EventHandler<KeyEvent> handler;

    /**
     * Constructeur VuePlateau
     */
    public VuePlateau(String nomJeu, int longueur, int hauteur, Color couleurFond, int XposPrev, int YposPrev) {

        this.hauteur = hauteur;
        this.longueur = longueur;
        creationFond(couleurFond);
        creationTitre(nomJeu);
        creationGrille(XposPrev, YposPrev);


        //Gestion du controleur
        controller = new PlateauController();
        controller.setGrilleControler(vueGrille.getControler());
        controller.setVuePlateau(this);

        //Gestion des actions du clavier
        setControlClavier();

    }

    protected void setControlClavier() {
        this.setOnKeyPressed(handler);
    }

    /**
     * Creation du Fond du plateau
     */
    protected void creationFond(Color color) {
        fond_plateau = new Rectangle();
        fond_plateau.setWidth(longueur);
        fond_plateau.setHeight(hauteur);
        fond_plateau.setFill(color);
        this.getChildren().add(fond_plateau);
    }

    /**
     * Ajout du titre selon le type de jeu
     * @param string String
     */
    public void creationTitre(String string){
                titre = new Text(hauteur/2-60,50,string);
                titre.setFont(new Font(40));
        this.getChildren().add(titre);
    }


    /**
     * Creation de la grille
     */
    public void creationGrille(double Xpos, double Ypos){
        initiateGrille();
        this.getChildren().add(vueGrille);

        //Ajout prévisualisation de la piece

        GridPane gridPane = vueGrille.getPrevisualisationPiece();
        gridPane.setTranslateX(Xpos);
        gridPane.setTranslateY(Ypos);
        this.getChildren().add(gridPane);

        //Ajout score
        vueGrille.setScorePos(Xpos,Ypos+100);
    }

    protected void initiateGrille() {}


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
        controller.generatePiece();
    }



}
