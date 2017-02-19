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
    int largeur;
    Text titre;
    Rectangle fond_plateau;

    public VuePlateau() {
        //determination de la longueur de la fenêtre
        longueur = 600;
        largeur = 600;
        creationFond();
        creationTitre(TypeJeu.Tetris);
        creationGrille();
    }

    private void creationFond() {
        fond_plateau = new Rectangle();
        fond_plateau.setWidth(longueur);
        fond_plateau.setHeight(largeur);
        fond_plateau.setFill(Color.DEEPSKYBLUE);
        this.getChildren().add(fond_plateau);
    }

    public void creationTitre(TypeJeu typeJeu){
        switch (typeJeu){
            case Tetris:
                titre = new Text(largeur/2-60,50,"Tetris");
                titre.setFont(new Font(40));
        }
        this.getChildren().add(titre);
    }


    public void creationGrille(){
        double XposGrille = largeur/3;
        double YposGrille = longueur/4;
        VueGrille vueGrille = new VueGrille(XposGrille,YposGrille);
        this.getChildren().add(vueGrille);
    }

    public int getLongueur() {
        return longueur;
    }

    public int getLargeur() {
        return largeur;
    }
}
