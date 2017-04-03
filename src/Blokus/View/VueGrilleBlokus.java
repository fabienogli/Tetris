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
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
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
    private boolean actif;


    public VueGrilleBlokus(double Xpos, double Ypos) {
        super(Xpos, Ypos, 10, 10);

        //Construit la grille de prévisualisation
        previsualisationPiece = makePrevisualisationPiece(5, 5);
        previsualisationPiece.setTranslateX(10);
        previsualisationPiece.setTranslateY(40);
        this.getChildren().add(previsualisationPiece);
        addButtons();

        //Ajoute les controle clavier
        this.setOnKeyPressed(keyBoard());

        //Affiche à qui le tour
        affichageJoueur = new Text();
        affichageJoueur.setFill(Color.WHITE);
        affichageJoueur.setFont(Font.font(null, FontWeight.BOLD, 20));


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
        //Initie la vue d'une piece
        VuePiece vuePiece = new VuePieceBlokus(piece, joueurActif);
        return vuePiece;
    }

    @Override
    public void gestionGrille(Piece piece) {
        //Si la piece est en vie, on change l'Etat de la piece courante
        if (piece.isAlive()) {
            PieceCourante = piece;
            vuePieceCourante = initiateVuePiece(piece);
            //Methode d affichage et de deplacement de la piece
            affichage_deplacementPiece();
        } else {
            //Sinon on met les coordonee precedente a nul ce qui permet de ne pas effacer
            //la piece courante
            coordoneePrec = null;
        }
    }

    /**
     * Defini le nombre de joueur
     *
     * @param nbJoueur int
     */
    public void setNbJoueur(int nbJoueur) {
        this.nbJoueur = nbJoueur;
        setJoueurActif(Joueur.JOUEUR1);
        setAffichageJoueur();
        affichageJoueur.setTranslateX(400);
        affichageJoueur.setTranslateY(50);
        this.getChildren().add(affichageJoueur);

        ((Grille_Blokus) grille).setNbJoueur(nbJoueur);
        players = ((Grille_Blokus) grille).getPlayers();
    }


    /**
     * Methode qui ajoute les boutons de selection de piece
     * Affiche la piece selectionnee dans previsualisation piece
     * Definit la piece suivante selon la selection
     */
    public void addButtons() {
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
                    if (actif) {
                        pieceSuivante = ((Grille_Blokus) grille).getPiecefromDeck(joueurActif, tp.ordinal());
                        vuePieceCourante = initiateVuePiece(pieceSuivante);
                        showPieceSuivante(pieceSuivante, previsualisationPiece);
                        //affichage_deplacementPiece();
                    }
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

    /**
     * Definit le joueur actif
     *
     * @param joueurActif Joueur
     */
    public void setJoueurActif(Joueur joueurActif) {
        this.joueurActif = joueurActif;
    }


    /**
     * Retourne la gestion du clavier
     *
     * @return EventHandler<KeyEvent>
     */
    public EventHandler<KeyEvent> keyBoard() {
        EventHandler<KeyEvent> handler = new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (actif) {
                    switch (event.getCode()) {
                        case ENTER:
                            controler.putPiece(pieceSuivante, players[joueurActif.ordinal()].getCoordoneeDepart());
                            break;
                        case A:
                            controler.rotatePiece(Direction.DROITE);
                            break;
                        case E:
                            controler.rotatePiece(Direction.GAUCHE);
                            break;
                        case P:
                            PieceCourante.kill();
                            grille.controlCases(PieceCourante, null);
                            if (!PieceCourante.isAlive())
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

            }
        };
        return handler;
    }


    /**
     * Attribue au joueurActif le joueur suivant
     */
    public void switchPlayer() {
        int i = joueurActif.ordinal();
        i++;
        if (i >= nbJoueur)
            i = 0;
        joueurActif = Joueur.values()[i];
        ((Grille_Blokus) grille).changeJoueur(Joueur.values()[i]);
        setAffichageJoueur();
        pieceSuivante = players[joueurActif.ordinal()].getDeck().get(players[joueurActif.ordinal()].getNbPieceDrop());
        showPieceSuivante(pieceSuivante, previsualisationPiece);

    }


    public void setAffichageJoueur() {
        affichageJoueur.setText("Au tour du " + joueurActif);
    }

    public Text getAffichageJoueur() {
        return affichageJoueur;
    }

    public void setActif() {
        actif = true;
    }
}
