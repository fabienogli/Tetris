package Blokus.View;

import Base.Model.Piece;
import Base.View.VuePiece;
import Blokus.Model.Joueur;
import javafx.scene.paint.Color;

/**
 * Created by Fabien on 22/03/2017.
 */
public class VuePieceBlokus extends VuePiece {

    private Joueur joueur;

    /**
     * Constructeur de la vue d'une piece du blokus
     * Lui attribue une couleur selon le joueur
     *
     * @param piece  Piece
     * @param joueur Joueur
     */
    public VuePieceBlokus(Piece piece, Joueur joueur) {
        super(piece);
        this.joueur = joueur;
        switch (joueur) {
            case JOUEUR1:
                color = Color.RED;
                break;
            case JOUEUR2:
                color = Color.YELLOW;
                break;
            case JOUEUR3:
                color = Color.BLUE;
                break;
            case JOUEUR4:
                color = Color.GREEN;
                break;
        }
    }
}
