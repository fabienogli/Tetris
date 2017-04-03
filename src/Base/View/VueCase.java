package Base.View;

import Base.Model.Case;
import javafx.scene.Parent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * Created by Fabien on 15/02/2017.
 */
public class VueCase extends Parent {
    private Case aCase;
    private Color color, couleurPrecedente;
    private Color[] palette;
    protected Rectangle apparence;
    private static int nbCouleur = 6;

    public static int getLenght() {
        return lenght;
    }

    private static int lenght = 20;


    public VueCase(Case aCase) {
        this.aCase = aCase;
        this.apparence = new Rectangle(lenght, lenght, Color.WHITE);
        this.apparence.setStroke(Color.BLACK);

        this.getChildren().add(this.apparence);
    }

    public VueCase(Case aCase, Color color) {
        this.aCase = aCase;
        this.color = color;
        this.apparence = new Rectangle(lenght, lenght, color);
        this.apparence.setStroke(Color.WHITE);

        this.getChildren().add(this.apparence);
    }

    public void changerCouleur(Color color) {
        this.couleurPrecedente = this.color;
        this.color = color;
        this.apparence.setFill(color);
    }

    public Color getColor() {
        return color;
    }

    public Color getColourPrecedente() {
        return couleurPrecedente;
    }

    public void getCoulorBack() {
        this.color = couleurPrecedente;
    }

    public void eraseStroke() {
        this.apparence.setStroke(Color.BLACK);
    }
}
