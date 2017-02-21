package View;

import Model.TypePiece;
import javafx.scene.Parent;
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
    Text titre;
    Rectangle fond_plateau;

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
     * Creationd de la grille
     */
    public void creationGrille(){
        double XposGrille = hauteur/3;
        double YposGrille = longueur/4;
        VueGrille vueGrille = new VueGrille(XposGrille,YposGrille);
        this.getChildren().add(vueGrille);
        //Permet de faire fonctionner la gestion du clavier dans la classe VueGrille
        vueGrille.setFocusTraversable(true);
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
}
