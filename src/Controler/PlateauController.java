package Controler;

import View.VuePlateau;

/**
 * Created by Fabien on 21/02/2017.
 */
public class PlateauController {
    private GrilleControler grilleControler;
    private PieceControler pieceControler;
    private VuePlateau vuePlateau;

    public PlateauController(){

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
