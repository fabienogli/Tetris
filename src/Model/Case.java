package Model;

import javafx.scene.Parent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * Created by Fabien on 14/02/2017.
 */
public class Case{
    //Est vrai lorsque une piece est sur une case
    private Boolean actif;
    private Boolean caseSuivante;
    //Coordonnée de la case dans la grille
    private Coordonee coordonee;

    /**
     * Change les coordonnées de la case dans la grille
     * @param coordonee
     */
    public void setCoordonee(Coordonee coordonee) {
        this.coordonee = coordonee;
    }

    /**
     * Retourne les coordonnées de la case dans la grille
     * @return coordonee
     */
    public Coordonee getCoordonee() {

        return coordonee;
    }

    /**
     * Constructeur de Case
     */
    public Case(){
        this.actif = false;
        coordonee = new Coordonee();
    }

    /**
     * Constructeur de Case
     * @param coordonee
     */
    public Case(Coordonee coordonee){
        this.actif = false;
        this.coordonee = coordonee;
    }

    /**
     * rend la case active
     */
    public void caseActiv(){
        this.actif = true;
    }

    /**
     * Rend la case inactive
     */
    public void caseInactiv(){
        this.actif = false;
    }

    /**
     * Getteur d'actif
     * @return
     */
    public Boolean getActif() {
        return actif;
    }






}
