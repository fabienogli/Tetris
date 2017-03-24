
import Blokus.Blokus_Scene;
import Tetris.Tetris_Scene;
import Tetris.View.VueTetris;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle(Tetris_Scene.title);
        primaryStage.setScene(Blokus_Scene.launchBlokus());
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
