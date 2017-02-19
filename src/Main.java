import View.VueGrille;
import View.VuePlateau;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        VuePlateau vuePlateau = new VuePlateau();
        int longueur = vuePlateau.getLongueur();
        int largeur = vuePlateau.getLargeur();

        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(vuePlateau, longueur, largeur));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
