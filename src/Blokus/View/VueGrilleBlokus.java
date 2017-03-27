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
import Tetris.Model.Grille_Tetris;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

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
    private Text affichageJoueur;


    public VueGrilleBlokus(double Xpos, double Ypos) {
        super(Xpos, Ypos, 10, 10);
        //setScrollPane();

        previsualisationPiece = makePrevisualisationPiece(5, 5);
        previsualisationPiece.setTranslateX(10);
        previsualisationPiece.setTranslateY(40);
        this.getChildren().add(previsualisationPiece);
        test();
        this.setOnKeyPressed(keyBoard());
        affichageJoueur = new Text();


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
        if(piece.isAlive()){
            PieceCourante = piece;
            vuePieceCourante = initiateVuePiece(piece);
            affichage_deplacementPiece();
        }else{
            coordoneePrec = null;
        }
        }

    public void setNbJoueur(int nbJoueur) {
        this.nbJoueur = nbJoueur;
        setJoueurActif(Joueur.JOUEUR1);
        setAffichageJoueur();
        affichageJoueur.setTranslateX(400);
        affichageJoueur.setTranslateY(50);
        this.getChildren().add(affichageJoueur);

        ((Grille_Blokus)grille).setNbJoueur(nbJoueur);
        players = ((Grille_Blokus) grille).getPlayers();
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

                    pieceSuivante = ((Grille_Blokus)grille).getPiecefromDeck(joueurActif, tp.ordinal());
                    vuePieceCourante = initiateVuePiece(pieceSuivante);
                    showPieceSuivante(pieceSuivante, previsualisationPiece);
                    //affichage_deplacementPiece();
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
                        break;
                    case E:
                        controler.rotatePiece(Direction.GAUCHE);
                        break;
                    case K:
                        PieceCourante.kill();
                        grille.controlCases(PieceCourante, null);
                        switchPlayer();
                        break;
                    case UP:
                        controler.movePiece(Direction.Haut);
                        break;
                    case DOWN:
                        controler.movePiece(Direction.BAS);
                        break;
                    case LEFT:
                        controler.movePiece(Direction.GAUCHE);
                        break;
                    case RIGHT:
                        controler.movePiece(Direction.DROITE);
                        }
            }
        };
        return handler;
    }



    public void switchPlayer(){
        int i = joueurActif.ordinal();
        i++;
        if(i>=nbJoueur)
            i =0;
        joueurActif = Joueur.values()[i];
        ((Grille_Blokus) grille).changeJoueur(Joueur.values()[i]);
        setAffichageJoueur();
    }

    public void setAffichageJoueur(){
        affichageJoueur.setText("Au tour du "+joueurActif+" de jouer !");
    }

    public Text getAffichageJoueur() {
        return affichageJoueur;
    }
}
