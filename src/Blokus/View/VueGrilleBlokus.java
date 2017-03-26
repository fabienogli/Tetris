package Blokus.View;

import Base.Model.Coordonee;
import Base.Model.Direction;
import Base.Model.Piece;
import Base.View.VueGrille;
import Base.View.VuePiece;
import Blokus.Model.Grille_Blokus;
import Blokus.Model.Joueur;
import Blokus.Model.Player;
import Blokus.Model.TypePiece;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

/**
 * Created by Fabien on 22/03/2017.
 */
public class VueGrilleBlokus extends VueGrille {

    ScrollPane scrollPane;
    private Player[] players;
    private Coordonee[] coordoneeDepart;
    private Joueur joueurActif;
    private int nbJoueur;
    private ArrayList<ArrayList<Piece>> decks;
    private GridPane previsualisationPiece;


    public VueGrilleBlokus(double Xpos, double Ypos) {
        super(Xpos, Ypos, 10, 10);
        //setScrollPane();

        previsualisationPiece = makePrevisualisationPiece(5, 5);
        previsualisationPiece.setTranslateX(10);
        previsualisationPiece.setTranslateY(20);
        this.getChildren().add(previsualisationPiece);
        setNbJoueur(1);
        setJoueurActif(Joueur.JOUEUR1);
        test();
        this.setOnKeyPressed(keyBoard());
    }

    @Override
    public Coordonee getCoordoneeDepart() {
        return players[joueurActif.ordinal()].getCoordoneeDepart();
    }

    @Override
    protected void initiateGrille() {
        grille = new Grille_Blokus();
    }

    @Override
    protected VuePiece initiateVuePiece(Piece piece) {
        VuePiece vuePiece = new VuePieceBlokus(piece, joueurActif);
        return vuePiece;
    }

    @Override
    public void gestionGrille(Piece piece) {
        PieceCourante = piece;
        vuePieceCourante = initiateVuePiece(piece);
        affichage_deplacementPiece();
    }

    public void setNbJoueur(int nbJoueur) {
        this.nbJoueur = nbJoueur;
        players = new Player[nbJoueur];
        for (int i = 0; i < nbJoueur; i++) {
            players[i] = new Player();
            players[i].setCoordoneeDepart(new Coordonee(0, 0));

        }

        fillDecks();
    }

    private void fillDecks() {
        decks = new ArrayList<ArrayList<Piece>>();
        for (int i = 0; i < nbJoueur; i++) {
            decks.add(Grille_Blokus.generateDeck());
        }
    }

    public void setScrollPane() {
        scrollPane = new ScrollPane();
        ArrayList<Piece> deck = Grille_Blokus.generateDeck();
        VBox vBoxes = new VBox();
        for (Piece piece : deck) {
            System.out.println(piece.getTypePiece());
            vBoxes.getChildren().add(new VuePieceBlokus(piece, joueurActif));
//            for(int x = 0 ; x< piece.getCases().length;x++){
//                for(int y =0; y < piece.getCases()[0].length;y++){
//                    System.out.print(piece.getCase(y,x));
//                }
//                System.out.println();
//            }
        }
        scrollPane.setContent(vBoxes);
        scrollPane.setTranslateY(400);
        scrollPane.setTranslateY(400);
        this.getChildren().add(scrollPane);
    }

    public void test() {
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(5));
        grid.setHgap(5);
        grid.setVgap(5);
        int ligne = 0;
        int colonne = 0;
        for (int i = 0; i < TypePiece.values().length; i++) {
            Button button = new Button(TypePiece.values()[i].toString());
            TypePiece tp = TypePiece.values()[i];

            button.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    pieceSuivante = decks.get(joueurActif.ordinal()).get(tp.ordinal());
                    vuePieceCourante = initiateVuePiece(pieceSuivante);
                    showPieceSuivante(pieceSuivante, previsualisationPiece);
                    //affichage_deplacementPiece();
                    System.out.println(tp.toString());
                }
            });
            grid.add(button, colonne, ligne);
            colonne++;
            if (colonne > 3) {
                ligne++;
                colonne = 0;
            }

        }
        grid.setTranslateX(10);
        grid.setTranslateY(200);
        this.getChildren().add(grid);
    }

    public void setJoueurActif(Joueur joueurActif) {
        this.joueurActif = joueurActif;
    }

    public EventHandler<KeyEvent> keyBoard(){
        EventHandler<KeyEvent> handler = new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                switch (event.getCode()){
                    case ENTER:
                        controler.putPiece(pieceSuivante, players[joueurActif.ordinal()].getCoordoneeDepart());
                        break;
                    case A:
                        controler.rotatePiece(Direction.DROITE);
                        System.out.println("rotation");
                        break;
                    case E:
                        controler.rotatePiece(Direction.GAUCHE);
                        break;
                    case SPACE:
                        dropPiece();
                        }
            }
        };
        return handler;
    }

    private void dropPiece() {
        PieceCourante.kill();
        Coordonee coordonee = players[joueurActif.ordinal()].getCoordoneeDepart();
        int x = coordonee.getX();
        int y = coordonee.getY();
        //switch ()
    }


}
