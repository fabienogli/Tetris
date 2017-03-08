import Model.Piece;
import Model.TypePiece;
import View.VueGrille;
import View.VuePiece;
import View.VuePlateau;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;

import java.util.Arrays;
import java.util.Collections;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        VuePlateau vuePlateau = new VuePlateau();
        int longueur = vuePlateau.getLongueur();
        int largeur = vuePlateau.getLargeur();
        //VuePiece vuePiece = new VuePiece(new Piece(TypePiece.Tetrimino_I));
        /**
         * Test begin
         */
        Text text = new Text("This is a test");
        text.setX(10);
        text.setY(50);
        text.setFont(new Font(20));

        text.getTransforms().add(new Rotate(90, 50, 50));
        HBox hBox = new HBox();
        hBox.getChildren().add(text);
        /**
         * Test end
         */
        Scene root = new Scene(vuePlateau, longueur, largeur);
        root.setOnKeyPressed(vuePlateau.getOnKeyPressed());
        //Ajout de la gestion d'évènement clavier a la scene
        //root.setOnKeyPressed(vuePiece.getOnKeyPressed());
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(root);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
