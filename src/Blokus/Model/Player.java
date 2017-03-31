package Blokus.Model;

import Base.Model.Coordonee;
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
        this.name = name;
        nbPieceDrop =0;
        deck = Grille_Blokus.generateDeck();
    }


    public Player(Joueur joueur) {
        pieceDrop = new ArrayList<Piece>();
        this.joueur = joueur;
        switch (joueur) {
            case JOUEUR1:
                coordoneeDepart = new Coordonee(0,0);
                break;
            case JOUEUR2:
                coordoneeDepart = new Coordonee(19,19);
                break;
            case JOUEUR3:
                coordoneeDepart = new Coordonee(0,19);
                break;
            case JOUEUR4:
                coordoneeDepart = new Coordonee(19,0);
                break;
        }
        nbPieceDrop =0;
        deck = Grille_Blokus.generateDeck();
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

    public ArrayList<Piece> getDeck() {
        return deck;
    }

    public ArrayList<Piece> getPieceDrop() {
        return pieceDrop;
    }

    public Coordonee lastOccurOne(Piece piece){
        Coordonee coordonee = new Coordonee(coordoneeDepart.getX(), coordoneeDepart.getY());
        switch (joueur) {
            case JOUEUR1:
                break;
            case JOUEUR2:
                int i=0,j=0;
                for(int x = 0; x<piece.getCases().length;x++){
                    for(int y =0; y<piece.getCases().length;y++){
                        if(piece.getCase(x,y) ==1){
                            i=x;
                            j=y;
                        }
                    }
                }
                coordonee = new Coordonee(coordonee.getX()-i,coordonee.getY() -j);

                break;
            case JOUEUR3:
                int j2=0;
                for(int x = 0; x<piece.getCases().length;x++){
                    for(int y =0; y<piece.getCases().length;y++){
                        if(piece.getCase(x,y) ==1){
                            j2=y;
                        }
                    }
                }
                coordonee = new Coordonee(coordonee.getX(),coordonee.getY() - j2);

                break;
            case JOUEUR4:
                int x1=0;
                for(int x = 0; x<piece.getCases().length;x++){
                    for(int y =0; y<piece.getCases().length;y++){
                        if(piece.getCase(x,y) ==1){
                            x1=x;
                        }
                    }
                }
                coordonee = new Coordonee(coordonee.getX()-x1,coordonee.getY());
                break;
        }
        return coordonee;
    }
}
