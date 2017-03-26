package Blokus.Model;

import Base.Model.Coordonee;
import Base.Model.Grille;
import Base.Model.Piece;

import java.util.ArrayList;

/**
 * Created by Fabien on 25/03/2017.
 */
public class Player {
    private Coordonee coordoneeDepart;
    private String name;
    static int nbJoueur ;
    private Joueur joueur;
    private int nbPieceDrop;
    private ArrayList<Piece> pieceDrop;
    private ArrayList<Piece> deck;

    public Player(String name) {
        this();
        this.name = name;
        nbPieceDrop =0;
        deck = Grille_Blokus.generateDeck();
        pieceDrop = new ArrayList<Piece>();
    }


    public Player() {
        joueur = Joueur.values()[nbJoueur];
        switch (joueur) {
            case JOUEUR1:
                coordoneeDepart = new Coordonee(0,0);
                break;
            case JOUEUR2:
                coordoneeDepart = new Coordonee(19,0);
                break;
            case JOUEUR3:
                coordoneeDepart = new Coordonee(0,19);
                break;
            case JOUEUR4:
                coordoneeDepart = new Coordonee(19,19);
                break;
        }
        nbJoueur++;
    }

    public Coordonee getCoordoneeDepart() {
        return coordoneeDepart;
    }

    public String getName() {
        return name;
    }

    public void setCoordoneeDepart(Coordonee coordoneeDepart) {
        this.coordoneeDepart = coordoneeDepart;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void dropPiece(){
        nbPieceDrop++;
    }

    public int getNbPieceDrop(){
        return nbPieceDrop;
    }

    public Joueur getJoueur() {
        return joueur;
    }

    public void pieceDrop(Piece piece){
        pieceDrop.add(piece);
        deck.remove(piece.getTypePiece().ordinal());
    }


    public ArrayList<Piece> getPieceDrop() {
        return pieceDrop;
    }
}
