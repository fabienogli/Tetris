
import Blokus.Blokus_App;
import Tetris.Tetris_App;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

        Button tetris =new Button("Tetris");
        Button blokus = new Button("Blokus");
        Button puzzle =new Button("Puzzle");
        Button[] buttons = {
                tetris,
                blokus,
                puzzle
        };
        tetris.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                primaryStage.setScene(Tetris_App.launchTetris());
                primaryStage.setTitle(Tetris_App.title);
            }
        });
        blokus.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                primaryStage.setScene(Blokus_App.launchBlokus());
                primaryStage.setTitle(Blokus_App.title);
            }
        });
        puzzle.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

            }
        });
        primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("img/ico_fsociety_by_luigieiro-da7idgq.png")));
        primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("img/tn_fsociety_by_luigieiro-da7idgq.png")));
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(10));
        gridPane.setVgap(10);
        gridPane.setHgap(10);
        gridPane.add(tetris,0,0);
        gridPane.add(blokus,1,0);
        gridPane.add(puzzle,2,0);
        Scene scene = new Scene(gridPane,400,100);
        primaryStage.setTitle("Choix du Jeu");
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
