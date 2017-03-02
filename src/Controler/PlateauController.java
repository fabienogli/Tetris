package Controler;

import Model.Piece;
import Model.TypePiece;
import View.VuePiece;
import View.VuePlateau;
import javafx.scene.Parent;

/**
 * Created by Fabien on 21/02/2017.
 */
public class PlateauController{
    private GrilleControler grilleControler;
    private PieceControler pieceControler;
    private VuePlateau vuePlateau;
    private boolean activiteJeu;

    public PlateauController(){
        //gestionJeu();
    }

    public void gestionJeu(){
        vuePlateau.generatePiece();
    }
    /**
     * @param grilleControler
     */
    public void setGrilleControler(GrilleControler grilleControler) {
        this.grilleControler = grilleControler;
    }

    /**
     * @param pieceControler
     */
    public void setPieceControler(PieceControler pieceControler) {
        this.pieceControler = pieceControler;
    }

    /**
     * @param vuePlateau
     */
    public void setVuePlateau(VuePlateau vuePlateau) {
        this.vuePlateau = vuePlateau;
    }

    /**
     * @return grilleControler
     */
    public GrilleControler getGrilleControler() {
        return grilleControler;
    }

    /**
     * @return pieceControler
     */
    public PieceControler getPieceControler() {
        return pieceControler;
    }

    /**
     * @return VuePlateau
     */
    public VuePlateau getVuePlateau() {
        return vuePlateau;
    }
}
