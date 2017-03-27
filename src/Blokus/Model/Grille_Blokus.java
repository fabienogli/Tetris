package Blokus.Model;

import Base.Model.Coordonee;
import Base.Model.Direction;
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
    private Player[] players;
    int i_playerActif;

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

    public void changeJoueur(Joueur joueur) {
        this.i_playerActif = joueur.ordinal();
    }

    public void putPiece(Piece piece, Coordonee coordonee) {
//        ArrayList<ArrayList> found = searchCoord(piece);
//        System.out.println(found.get(1).size());
//        coordonee = (Coordonee)found.get(0).get(0);
//        boolean[] positionAllow = (boolean[])found.get(1).get(0);
//        boolean tmp = false;
//        int i =0;
//        while (!tmp){
//            tmp = positionAllow[i];
//            i++;
//        }
//        piece.setPositions(i);
//        piece.setCoordonee(coordonee);
//        System.out.println(piece.getCoordonee());
//        System.out.println("Dans le put piece "+piece.getTypePiece().toString());

        coordonee = coordPerPlayer(piece, i_playerActif);
        super.putPiece(piece, coordonee);
    }

    private Coordonee coordPerPlayer(Piece piece, int i_playerActif) {
        Player player = players[i_playerActif];
        return player.lastOccurOne(piece);
    }


    public Boolean controlCaseAround(int x, int y) {
        Boolean haut = true, bas= true, droite= true, gauche= true, verif= true;
        if (x + 1 >= longueur )
            verif = true;
        else if(cases[x + 1][y].getActif())
            droite = false;
        if(x - 1 < 0 )
            verif = true;
        else if(cases[x - 1][y].getActif())
            gauche = false;
        if(y + 1 >= hauteur)
            verif = true;
        else if(cases[x][y + 1].getActif())
            bas = false;
        if(y - 1 < 0)
            verif = true;
        else if(cases[x][y - 1].getActif())
            bas = false;
        verif = bas&haut&gauche&droite;
        return verif;
    }

    public void positionPossible() {

    }

    public Piece getPiece(ArrayList<Piece> deck, int position) {
        return deck.get(position);
    }



    @Override
    public Boolean controlFin(Piece piece, Direction direction) {
        if(piece.isAlive())
            return false;
        else{
            return true;
        }

    }

    @Override
    public void stopPiece(Piece piece) {
        if(controlCaseAround(piece.getCoordonee().getX(), piece.getCoordonee().getY())){
            piece.kill();
            players[i_playerActif].pieceDrop(piece);
            int a =0,b = 0;
            for(int x =0 ; x< piece.getDimension().getX();x++){
                for(int y = 0; y< piece.getDimension().getY(); y++){
                    if(piece.getCase(x,y) == 1){
                        cases[x][y].caseActiv();
                        if(players[i_playerActif].getJoueur() == Joueur.JOUEUR1){
                            a= x+1+piece.getCoordonee().getX();
                            b = y+1+piece.getCoordonee().getY();
                        }
                    }
                }
            }
            boolean found = false;
            switch (players[i_playerActif].getJoueur()) {
                case JOUEUR2:
                    while (!found){
                        for(int x =0 ; x< piece.getDimension().getX();x++) {
                            for (int y = 0; y < piece.getDimension().getY(); y++) {
                                if(piece.getCase(x,y) == 1){
                                    a=piece.getCoordonee().getX() - x;
                                    b=piece.getCoordonee().getY() - y;
                                    found =true;
                                }
                            }
                        }
                    }
                    break;
                case JOUEUR3:
                    break;
                case JOUEUR4:
                    break;
            }
            players[i_playerActif].setCoordoneeDepart(new Coordonee(a,b));
            System.out.println(players[i_playerActif].getCoordoneeDepart());
            setChanged();
            notifyObservers(piece);
        }else {
            piece.rescucite();
        }
    }

    public void setNbJoueur(int i){
        players = new Player[i];
        int x = 0;
        for(Player player : players){
            player = new Player(Joueur.values()[x]);
            players[x] = player;
            x++;
        }
    }

    public Player[] getPlayers() {
        return players;
    }

    public Piece getPiecefromDeck(Joueur joueur, int i_piece){
        int i = joueur.ordinal();
        return players[i].getDeck().get(i_piece);
    }


    /**
     * Tentative d'algo de recherche de coordonee disponible par piece pose et par joueur
     * @param piece
     * @return
     */
    private ArrayList<ArrayList> searchCoord(Piece piece) {
        //Array list qui contient coordonee disponible et position de la piece autorisé correspondant
        ArrayList<ArrayList> found = new ArrayList<ArrayList>();
        //Array List qui contient les coordonnées disponibles
        ArrayList<Coordonee> coordDispo = new ArrayList<Coordonee>();
        //Array list qui contient les positions de la piece autorisés
        ArrayList<boolean[]> positionAllowed = new ArrayList<boolean[]>();
        if (players[i_playerActif].getPieceDrop().isEmpty()) {
            System.out.println(players[i_playerActif].getJoueur().toString()+" "+players[i_playerActif].getNbPieceDrop());
            int i =0;
            boolean[] tmpPosPiece = new boolean[4];
            Coordonee plCord = players[i_playerActif].getCoordoneeDepart();
            switch (players[i_playerActif].getJoueur()) {
                case JOUEUR1:
                    for(Coordonee coordonee :piece.getPosActif()){
                        tmpPosPiece[i] = (((coordonee.getX() == plCord.getX())&&(coordonee.getY() == plCord.getY())));
                        i++;
                    }
                    break;
                case JOUEUR2:
                    Coordonee c = new Coordonee(plCord.getX() - piece.getDimension().getX(), plCord.getY());
                    players[i_playerActif].setCoordoneeDepart(c);
                    while(i<tmpPosPiece.length){
                        tmpPosPiece[i] = (piece.getAllCases()[i][0][piece.getDimension().getX()-1] == 1);
                        i++;
                    }
                    break;
                case JOUEUR3:
                    players[i_playerActif].setCoordoneeDepart(new Coordonee(plCord.getX(), plCord.getY() - piece.getDimension().getY()));
                    while (i<tmpPosPiece.length){
                        tmpPosPiece[i] = (piece.getAllCases()[i][piece.getDimension().getY()-1][0] == 1);
                        i++;
                    }
                    break;
                case JOUEUR4:
                    players[i_playerActif].setCoordoneeDepart(new Coordonee(plCord.getX() - piece.getDimension().getX(), plCord.getY() - piece.getDimension().getY()));
                    while (i<tmpPosPiece.length){
                        tmpPosPiece[i] = (piece.getAllCases()[i][piece.getDimension().getY()-1][piece.getDimension().getX()-1] == 1);
                        i++;
                    }
                    break;
            }
            coordDispo.add(plCord);
            positionAllowed.add(tmpPosPiece);
        } else {
            for (Piece piece1 : players[i_playerActif].getPieceDrop()) {
                for (int m = 0; m < piece1.getDimension().getX(); m++)
                    for (int n = 0; n < piece1.getDimension().getY(); n++) {
                        int abs = m + piece1.getCoordonee().getX();
                        int ord = n + piece1.getCoordonee().getY();
                        if (cases[abs][ord].getActif()) {
                            int wrX = abs + 1;
                            int wrY = ord + 1;
                            int jgX = ord - 1;
                            int jgY = abs - 1;
                            /**
                             * On test si les coordonnées sont des coins de pieces
                             */
                            if (wrX < longueur && wrY < hauteur)
                                if (!cases[wrX][wrY].getActif() ) {
                                    verifPositionPieceCoordFond(wrX, wrY, piece, abs, ord, positionAllowed, coordDispo);
                                }
                            if (!(wrX >= longueur || jgY < 0))
                                if (!cases[wrX][jgY].getActif() )
                                    verifPositionPieceCoordFond(wrX, jgY, piece, abs, ord, positionAllowed, coordDispo);
                            if (!(jgX < 0 || wrY >= hauteur))
                                if (!cases[jgX][wrY].getActif() )
                                    verifPositionPieceCoordFond(jgX, wrY, piece, abs, ord, positionAllowed, coordDispo);
                            if (!(jgX < 0 || jgY < 0))
                                if (!cases[jgX][jgY].getActif() )
                                    verifPositionPieceCoordFond(jgX, jgY, piece, abs, ord, positionAllowed, coordDispo);
                        }
                    }
                /**
                 * On test si les position disponible de la piece correspondent aux coordonées trouvées
                 */

                /**
                 * Effacer les positions inutiles et noter les position possibles pour la piece
                 * + Effectuer le test controlCases pour voir si on peut poser la piece dans cette condition
                 */
            }
        }
        found.add(0, coordDispo);
        found.add(1, positionAllowed);
        return found;
    }

    public void verifPositionPieceCoordFond(int wrX, int wrY, Piece piece, int abs, int ord, ArrayList<boolean[]> positionAllowed, ArrayList<Coordonee>coordDispo){
        Coordonee cord = new Coordonee(wrX, wrY);
        System.out.println("cord dans verifPosition  "+cord);
        boolean[] tmpPos = new boolean[4];
        int i = 0;
        for(Coordonee coordonee : piece.getPosActif()){
            Coordonee cordPiece = new Coordonee(abs-coordonee.getX(), ord-coordonee.getY());
            System.out.println("cord piece  dans verif POsition "+ cordPiece);
            Piece blkTmp = new BlokusPiece((TypePiece)piece.getTypePiece());
            blkTmp.setCoordonee(cordPiece);
            tmpPos[i] = (cord.getX() == cordPiece.getX() && cord.getY() == cordPiece.getY());
        }
        int r =0;
        boolean posFound = false;
        while (r!=tmpPos.length &&!posFound){
            System.out.println("tmpPOs dans verif position "+tmpPos[r]);
            posFound = tmpPos[r];
            r++;
        }
        if(posFound){
            System.out.println(posFound);
            coordDispo.add(cord);
            positionAllowed.add(tmpPos);
        }
    }
}
