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

    private static int lenght;



    public VueCase(Case aCase){
        lenght = 15;
        this.aCase = aCase;
        remplirPalette();
        this.apparence = new Rectangle(lenght,lenght,Color.RED);
        if(aCase.getActif()){
            this.apparence.setFill(palette[choixCouleur()]);
        }
        else this.apparence.setFill(Color.WHITE);
        this.apparence.setStroke(Color.BLACK);
        this.getChildren().add(this.apparence);
    }

    public void remplirPalette(){
        palette = new Color[nbCouleur];
        palette[0] = Color.CYAN;
        palette[1] = Color.BLUE;
        palette[2] = Color.ORANGE;
        palette[3] = Color.LIME;
        palette[4] = Color.PURPLE;
        palette[5] = Color.RED;
    }

    public int choixCouleur(){
        int i= (int)Math.random()*(nbCouleur-1);
        return i;
    }
}
