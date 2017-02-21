package View;

import Model.Case;
import javafx.scene.Parent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * Created by Fabien on 15/02/2017.
 */
public class VueCase extends Parent{
    private Case aCase;
    private Color color;
    private Color[] palette;
    private Rectangle apparence;
    private static int nbCouleur = 6;

    public static int getLenght() {
        return lenght;
    }

    private static int lenght = 15;



    public VueCase(Case aCase){
        this.aCase = aCase;
        this.apparence = new Rectangle(lenght,lenght,Color.WHITE);
        this.apparence.setStroke(Color.BLACK);

        this.getChildren().add(this.apparence);
    }

    public VueCase(Case aCase, Color color){
        this.aCase = aCase;
        this.apparence = new Rectangle(lenght,lenght,color);
        this.apparence.setStroke(Color.BLACK);

        this.getChildren().add(this.apparence);
    }


}
