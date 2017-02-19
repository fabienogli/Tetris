package Model;

import javafx.scene.Parent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * Created by Fabien on 14/02/2017.
 */
public class Case{
    private Boolean actif;
    private Boolean caseSuivante;
    private Coordonee coordonee;

    public void setCoordonee(Coordonee coordonee) {
        this.coordonee = coordonee;
    }

    public Coordonee getCoordonee() {

        return coordonee;
    }

    public Case(){
        this.actif = false;
        coordonee = new Coordonee();

    }

    public void caseActiv(){
        this.actif = true;
    }

    public Boolean getActif() {
        return actif;
    }

    public void caseInactiv(){
        this.actif = false;
    }




}
