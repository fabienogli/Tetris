package Blokus.Model;

import Base.Model.Coordonee;
import Base.Model.Grille;
import Base.Model.Piece;
import Base.View.VuePiece;
import javafx.scene.control.ScrollPane;

import java.util.ArrayList;

/**
 * Created by Fabien on 22/03/2017.
 */
public class Grille_Blokus extends Grille {
    private ArrayList<Piece> pieces;
    private ArrayList<Coordonee> coordDispo;
    private Player player;

    public Grille_Blokus() {
        super(20, 20);
        pieces = new ArrayList<Piece>();
    }

    public static ArrayList<Piece> generateDeck() {
        ArrayList<Piece> deck = new ArrayList<Piece>();
        for (TypePiece typePiece : TypePiece.values()) {
            Piece piece = new BlokusPiece(typePiece);
            deck.add(piece);
        }
        return deck;
    }

    public void pieceDrop(ArrayList<Piece> deck, int position) {
        deck.remove(position);
    }

    public void changeJoueur(Player player) {
        this.player = player;
    }

    public void putPiece(Piece piece, Coordonee coordonee) {
        searchCoord(piece);

        super.putPiece(piece, coordonee);
    }

    private void searchCoord(Piece piece) {
        Coordonee plCord = player.getCoordoneeDepart();
        if (player.getNbPieceDrop() == 0) {
            coordDispo = new ArrayList<Coordonee>();
            switch (player.getJoueur()) {
                case JOUEUR1:
                    coordDispo.add(plCord);
                    break;
                case JOUEUR2:
                    player.setCoordoneeDepart(new Coordonee(plCord.getX() - piece.getDimension().getX(), plCord.getY()));
                    coordDispo.add(plCord);
                    break;
                case JOUEUR3:
                    player.setCoordoneeDepart(new Coordonee(plCord.getX(), plCord.getY() - piece.getDimension().getY()));
                    coordDispo.add(plCord);
                    break;
                case JOUEUR4:
                    player.setCoordoneeDepart(new Coordonee(plCord.getX() - piece.getDimension().getX(), plCord.getY() - piece.getDimension().getY()));
                    coordDispo.add(plCord);
                    break;
            }
        } else {
            for (Piece piece1 : player.getPieceDrop()) {
                for (int m = 0; m < piece1.getDimension().getX(); m++)
                    for (int n = 0; n < piece1.getDimension().getY(); n++) {
                        int abs = m + piece1.getCoordonee().getX();
                        int ord = n + piece1.getCoordonee().getY();
                        if (cases[abs][ord].getActif()) {
                            int wrX = abs + 1;
                            int wrY = ord + 1;
                            int jgX = abs - 1;
                            int jgY = ord - 1;
                            /**
                             * On test si les coordonnées sont des coins de pieces
                             */
                            if (!(wrX >= longueur || wrY >= hauteur))
                                if (!cases[wrX][wrY].getActif() && controlCaseAround(wrX, wrY)) {
                                    coordDispo.add(new Coordonee(wrX, wrY));
                                }
                            if (!(wrX >= longueur || jgY < 0))
                                if (!cases[wrX][jgY].getActif() && controlCaseAround(wrX, jgY))
                                    coordDispo.add(new Coordonee(wrX, jgY));
                            if (!(jgX < 0 || wrY >= hauteur))
                                if (!cases[jgX][wrY].getActif() && controlCaseAround(jgX, wrY))
                                    coordDispo.add(new Coordonee(jgX, wrY));
                            if (!(jgX < 0 || jgY < 0))
                                if (!cases[jgX][jgY].getActif() && controlCaseAround(jgX, jgY))
                                    coordDispo.add(new Coordonee(jgX, jgY));
                        }
                    }
                /**
                 * On test si les position disponible de la piece correspondent aux coordonées trouvées
                 */
                boolean[][] positionAllowed = new boolean[coordDispo.size()][4];
                int x = piece1.getCoordonee().getX();
                int y = piece1.getCoordonee().getY();
                int i =0;
                for (Coordonee coordonee1 : coordDispo) {
                    int j =0;
                    for (Coordonee coordonee : piece.getPosActif()) {
                            positionAllowed[i][j] = (coordonee1 == new Coordonee(coordonee.getX()+x,coordonee.getY()+y));
                            j++;
                    }
                    i++;
                }
                /**
                 * Effacer les positions inutiles et noter les position possibles pour la piece
                 * + Effectuer le test controlCases pour voir si on peut poser la piece dans cette condition
                 */
            }
        }
    }

    public Boolean controlCaseAround(int x, int y) {
        Boolean verif;
        if (x + 1 >= longueur || x - 1 < 0 || y + 1 >= hauteur || y - 1 < 0)
            verif = true;
        else if (cases[x + 1][y].getActif() || cases[x][y + 1].getActif() || cases[x - 1][y].getActif() || cases[x][y - 1].getActif())
            verif = false;
        else
            verif = true;
        return verif;
    }

    public void positionPossible() {

    }

    public Piece getPiece(ArrayList<Piece> deck, int position) {
        return deck.get(position);
    }


}
