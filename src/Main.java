
import Base.View.VuePlateau;
import Blokus.Blokus_App;
import Blokus.View.VueBlokus;
import Tetris.Tetris_App;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import javax.swing.*;

public class Main extends Application {
    BackgroundImage myBI;

    @Override
    public void start(Stage primaryStage) throws Exception {
        Image image = new Image(getClass().getResourceAsStream("img/ico_fsociety_by_luigieiro-da7idgq.png"));
        Image image1 = new Image(getClass().getResourceAsStream("img/tn_fsociety_by_luigieiro-da7idgq.png"));
        myBI = new BackgroundImage(new Image("/img/fond.jpg", 400, 100, false, true),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);

        Button tetris = new Button("Tetris");
        Button blokus = new Button("Blokus");
        Button puzzle = new Button("Puzzle");
        Button[] buttons = {
                tetris,
                blokus,
                puzzle
        };
        tetris.setPrefSize(100, 50);
        blokus.setPrefSize(100, 50);
        puzzle.setPrefSize(100, 50);
        Stage sTetris, sBlokus, sPuzzle;
        sTetris = new Stage();
        sTetris.setScene(Tetris_App.launchTetris());
        sTetris.setTitle(Tetris_App.title);
        sBlokus = new Stage();
        sBlokus.setScene(Blokus_App.launchBlokus());
        sBlokus.setTitle(Blokus_App.title);
        sTetris.getIcons().addAll(image, image1);
        sBlokus.getIcons().addAll(image, image1);

        tetris.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                sTetris.showAndWait();
            }
        });
        blokus.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                sBlokus.showAndWait();
            }
        });
        puzzle.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

            }
        });
        primaryStage.getIcons().addAll(image, image1);
        GridPane gridPane = new GridPane();
        gridPane.setBackground(new Background(myBI));
        gridPane.setPadding(new Insets(10));
        gridPane.setVgap(10);
        gridPane.setHgap(10);
        gridPane.add(tetris, 0, 0);
        gridPane.add(blokus, 1, 0);
        gridPane.add(puzzle, 2, 0);
        Scene scene = new Scene(gridPane, 350, 100);

        primaryStage.setScene(scene);
        primaryStage.setTitle("Choix du Jeu");

        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
