package Blokus.Model;

import Base.Model.Grille;
import Base.Model.Piece;
import Base.View.VuePiece;
import javafx.scene.control.ScrollPane;

import java.util.ArrayList;

/**
 * Created by Fabien on 22/03/2017.
 */
public class Grille_Blokus extends Grille {

    public Grille_Blokus() {
        super(20, 20);
    }

    public static ArrayList<Piece> generateDeck(){
        ArrayList<Piece> deck = new ArrayList<Piece>();
        int nbTypePiece = TypePiece.values().length;
        for(TypePiece typePiece : TypePiece.values()){
            Piece piece = new BlokusPiece(typePiece);
            deck.add(piece);
        }
        return deck;
    }

    public void pieceDrop(ArrayList<Piece> deck, int position){
        deck.remove(position);
    }


    public Piece getPiece(ArrayList<Piece> deck, int position){
        return deck.get(position);
    }



}
