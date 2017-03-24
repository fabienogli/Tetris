package Blokus.View;

import Base.Model.Piece;
import Base.View.VueGrille;
import Base.View.VuePiece;
import Blokus.Model.BlokusPiece;
import Blokus.Model.Grille_Blokus;
import javafx.scene.control.ScrollPane;

import java.util.ArrayList;

/**
 * Created by Fabien on 22/03/2017.
 */
public class VueGrilleBlokus extends VueGrille {

    ScrollPane scrollPane;

    private Joueur joueurActif;
    private int nbJoueur;
    private ArrayList<ArrayList<Piece>> decks;


    public VueGrilleBlokus(double Xpos, double Ypos) {
        super(Xpos, Ypos,10,10);
        setScrollPane();
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
        super.gestionGrille(piece);
    }

    public void setNbJoueur(int nbJoueur){
        this.nbJoueur = nbJoueur;
        fillDecks();
    }

    private void fillDecks() {
        decks = new ArrayList<ArrayList<Piece>>();
        for(int i=0; i< nbJoueur;i++){
            decks.add(Grille_Blokus.generateDeck());
        }
    }

    public void setScrollPane(){
        scrollPane = new ScrollPane();
        ArrayList<Piece> deck= Grille_Blokus.generateDeck();
        for(Piece piece : deck){
            scrollPane.setContent(new VuePiece(piece));
        }
        scrollPane.setTranslateY(400);
        scrollPane.setTranslateY(400);
        this.getChildren().add(scrollPane);
    }
}
