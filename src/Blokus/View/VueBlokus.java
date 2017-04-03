package Blokus.View;


import Base.View.VuePlateau;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.image.ImageView;

/**
 * Created by Fabien on 22/03/2017.
 */
public class VueBlokus extends VuePlateau {


    public VueBlokus() {
        super("Blokus", 600, 600, 10, 40);
        Menu nbJoueur = new Menu("Lancer Partie");
        MenuItem Djoueurs = new MenuItem("2 joueurs");
        MenuItem Qjoueurs = new MenuItem("4 joueurs");
        nbJoueur.getItems().addAll(Djoueurs, Qjoueurs);
        Djoueurs.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                ((VueGrilleBlokus) vueGrille).setNbJoueur(2);
                ((VueGrilleBlokus) vueGrille).setActif();
            }
        });
        Qjoueurs.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                ((VueGrilleBlokus) vueGrille).setNbJoueur(4);
                ((VueGrilleBlokus) vueGrille).setActif();
            }
        });
        menuBar.getMenus().add(nbJoueur);
        this.getChildren().add(menuBar);
        setImage(250, 30);


    }

    @Override
    protected void initiateGrille() {
        double XposGrille = hauteur / 3 - 20;
        double YposGrille = longueur / 4;
        vueGrille = new VueGrilleBlokus(XposGrille, YposGrille);

    }


    @Override
    public void setImage(int xPos, int yPos) {
        this.imageTitre = new ImageView("/img/Blokus.png");
        this.imageTitre.setFitHeight(75);
        this.imageTitre.setFitWidth(95);
        super.setImage(xPos, yPos);
    }
}
