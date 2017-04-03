package Base.View;

import Base.Controler.PlateauController;

import javafx.animation.Timeline;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.MenuBar;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Font;
import javafx.scene.text.Text;


/**
 * Created by Fabien on 18/02/2017.
 */
public class VuePlateau extends Parent {
    protected int longueur;
    protected int hauteur;
    private Text titre;
    protected VueGrille vueGrille;
    protected PlateauController controller;
    protected Timeline timeline;
    protected boolean pause = true;
    protected EventHandler<KeyEvent> handler;
    protected MenuBar menuBar;
    protected ImageView imageTitre;


    /**
     * Constructeur VuePlateau
     */
    public VuePlateau(String nomJeu, int longueur, int hauteur, int XposPrev, int YposPrev) {

        this.hauteur = hauteur;
        this.longueur = longueur;
        creationTitre(nomJeu);
        creationGrille(XposPrev, YposPrev);


        //Gestion du controleur
        controller = new PlateauController();
        controller.setGrilleControler(vueGrille.getControler());
        controller.setVuePlateau(this);

        //Gestion des actions du clavier
        setControlClavier();
        /**
         * TEST BAR
         */
        menuBar = new MenuBar();
        menuBar.prefWidthProperty().setValue(longueur);

        setImage(250, 30);


    }

    protected void setControlClavier() {
        this.setOnKeyPressed(handler);
    }


    /**
     * Ajout du titre selon le type de jeu
     *
     * @param string String
     */
    public void creationTitre(String string) {
        titre = new Text(hauteur / 2 - 60, 80, string);
        titre.setFont(new Font(40));
    }


    /**
     * Creation de la grille
     */
    public void creationGrille(double Xpos, double Ypos) {
        initiateGrille();
        this.getChildren().add(vueGrille);


        //Ajout score
        vueGrille.setScorePos(Xpos, Ypos + 150);
    }

    protected void initiateGrille() {
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
     * Ajout d'un element a la grille
     */
    public void setPiece(Node node) {
        this.getChildren().add(node);
    }

    public void generatePiece() {
        controller.generatePiece();
    }


    public void setImage(int xPos, int yPos) {
        imageTitre.setTranslateX(xPos);
        imageTitre.setTranslateY(yPos);
        this.getChildren().add(imageTitre);
    }


}
