package Blokus.Model;

import Base.Model.Coordonee;
import Base.Model.Direction;
import Base.Model.Grille;
import Base.Model.Piece;

import java.util.ArrayList;

/**
 * Classe de la grille du Blokus
 * Construit la grille et effectue les controles par rapport a la piece
 * Created by Fabien on 22/03/2017.
 */
public class Grille_Blokus extends Grille {
    private ArrayList<Piece> pieces;
    private ArrayList<Coordonee> coordDispo;
    private Player[] players;
    int i_playerActif;

    /**
     * Constructeur de la grille du blokus
     */
    public Grille_Blokus() {
        super(20, 20);
        pieces = new ArrayList<Piece>();
    }


    /**
     * Generation du Deck avec toutes les pièces
     *
     * @return ArrayList<Piece> deck
     */
    public static ArrayList<Piece> generateDeck() {
        ArrayList<Piece> deck = new ArrayList<Piece>();
        for (TypePiece typePiece : TypePiece.values()) {
            Piece piece = new BlokusPiece(typePiece);
            deck.add(piece);
        }
        return deck;
    }

    /**
     * Supprime une piece de deck
     *
     * @param deck     ArrayList<Piece>
     * @param position indice de la piece dans l'ArrayList<Piece>
     */
    public void pieceDrop(ArrayList<Piece> deck, int position) {
        deck.remove(position);
    }

    public void changeJoueur(Joueur joueur) {
        this.i_playerActif = joueur.ordinal();
    }

    public void putPiece(Piece piece, Coordonee coordonee) {
        coordonee = coordPerPlayer(piece, i_playerActif);
        super.putPiece(piece, coordonee);
    }

    private Coordonee coordPerPlayer(Piece piece, int i_playerActif) {
        Player player = players[i_playerActif];
        return player.lastOccurOne(piece);
    }


    /**
     * Controle les cases autour d'une coordonne en hauteur et largeur
     * Si une case est active soit à droite ou à gauche ou en haut, ou en bas
     * La fonction retourne faux
     *
     * @param x int abcisse de la position
     * @param y int ordonnee de la position
     * @return Boolean
     */
    public Boolean controlCaseAround(int x, int y) {
        Boolean haut = true, bas = true, droite = true, gauche = true, verif = true;
        if (x + 1 >= longueur)
            verif = true;
        else if (cases[x + 1][y].getActif())
            droite = false;
        if (x - 1 < 0)
            verif = true;
        else if (cases[x - 1][y].getActif())
            gauche = false;
        if (y + 1 >= hauteur)
            verif = true;
        else if (cases[x][y + 1].getActif())
            bas = false;
        if (y - 1 < 0)
            verif = true;
        else if (cases[x][y - 1].getActif())
            haut = false;
        verif = bas & haut & gauche & droite;
        return verif;
    }


    @Override
    public Boolean controlFin(Piece piece, Direction direction) {
        return !piece.isAlive();
    }

    @Override
    public void stopPiece(Piece piece) {
        boolean verif = false;
        boolean posBonne = false;
        Joueur joueur = players[i_playerActif].getJoueur();

        //Si le joueur n a pas encore deposer de piece
        //Alors il doit la deposer dans son coin
        //verif et posBonne sont la pour s assurer de la bonne position de la Piece
        if (players[i_playerActif].getNbPieceDrop() == 0) {
            int x = players[i_playerActif].getCoordoneeDepart().getX();
            int y = players[i_playerActif].getCoordoneeDepart().getY();
            for (int i = 0; i < piece.getDimension().getX(); i++)
                for (int j = 0; j < piece.getDimension().getY(); j++) {
                    if (piece.getCase(i, j) == 1 && piece.getCoordonee().getX() + i == x && piece.getCoordonee().getY() + j == y) {
                        verif = true;
                        posBonne = true;
                    }

                }
        } else {
            verif = true;
            posBonne = true;
        }
        //On controle quand même les cases autour pour voir si il n y a pas de conflit
        int a = 0, b = 0;
        for (int x = 0; x < piece.getDimension().getX(); x++) {
            for (int y = 0; y < piece.getDimension().getY(); y++) {
                int abs = piece.getCoordonee().getX() + x;
                int ord = piece.getCoordonee().getY() + y;
                if (piece.getCase(x, y) == 1)
                    posBonne = posBonne && controlCaseAround(abs, ord);
            }
        }
        if (verif && posBonne) {
            //On attribue une nouvelle coordonee de depart selon la piece et le joueur
            for (int x = 0; x < piece.getDimension().getX(); x++) {
                for (int y = 0; y < piece.getDimension().getY(); y++) {
                    if (piece.getCase(x, y) == 1) {
                        cases[x + piece.getCoordonee().getX()][y + piece.getCoordonee().getY()].caseActiv();
                        if (joueur == Joueur.JOUEUR1 || joueur == Joueur.JOUEUR3) {
                            a = x + 1 + piece.getCoordonee().getX();
                            if (joueur == Joueur.JOUEUR1)
                                b = y + 1 + piece.getCoordonee().getY();

                        }
                    }
                }
            }
            if (joueur == Joueur.JOUEUR2 || joueur == Joueur.JOUEUR3 || joueur == Joueur.JOUEUR4) {
                int x = 0, y = 0;
                while (piece.getCase(x, y) != 1) {
                    y++;
                    if (y == piece.getDimension().getY()) {
                        x++;
                        y = 0;
                    }
                }
                if (joueur == Joueur.JOUEUR4 || joueur == Joueur.JOUEUR2)
                    a = piece.getCoordonee().getX() - x - 1;
                if (joueur == Joueur.JOUEUR3 || joueur == Joueur.JOUEUR2)
                    b = piece.getCoordonee().getY() - y - 1;
            }
            players[i_playerActif].setCoordoneeDepart(new Coordonee(a, b));
            players[i_playerActif].dropPiece();
            //Je voulais enlever ensuite la piece de poser du deck du joueur
            //Seulement l indice pour la selection des pieces n etaient pas bon
            //players[i_playerActif].pieceDrop(piece);
            //on notifie l observer
            setChanged();
            notifyObservers(piece);
        } else {
            //Si les controles ne sont pas bon alors la piece est encore en vie
            piece.rescucite();
        }
    }

    /**
     * On definit le nombre de joueur et on les initialise
     *
     * @param i int nombre de joueur
     */
    public void setNbJoueur(int i) {
        players = new Player[i];
        int x = 0;
        for (Player player : players) {
            player = new Player(Joueur.values()[x]);
            players[x] = player;
            x++;
        }
    }

    /**
     * Getteur de joueurs present dans la partie
     *
     * @return Player[] tableau des joueurs
     */
    public Player[] getPlayers() {
        return players;
    }

    /**
     * Retourne la piece du deck du joueur
     *
     * @param joueur  Joueur
     * @param i_piece int indice de la piece
     * @return Piece du deck
     */
    public Piece getPiecefromDeck(Joueur joueur, int i_piece) {
        int i = joueur.ordinal();
        return players[i].getDeck().get(i_piece);
    }


    /**
     * Tentative d'algo de recherche de coordonee disponible par piece pose et par joueur
     *
     * @param piece Piece
     * @return ArrayList<ArrayList> qui contient les coordonnes disponible pour un joueur
     * et les positions disponibles par piece
     */
    private ArrayList<ArrayList> searchCoord(Piece piece) {
        //Array list qui contient coordonee disponible et position de la piece autorisé correspondant
        ArrayList<ArrayList> found = new ArrayList<ArrayList>();
        //Array List qui contient les coordonnées disponibles
        ArrayList<Coordonee> coordDispo = new ArrayList<Coordonee>();
        //Array list qui contient les positions de la piece autorisés
        ArrayList<boolean[]> positionAllowed = new ArrayList<boolean[]>();
        if (players[i_playerActif].getPieceDrop().isEmpty()) {
            System.out.println(players[i_playerActif].getJoueur().toString() + " " + players[i_playerActif].getNbPieceDrop());
            int i = 0;
            boolean[] tmpPosPiece = new boolean[4];
            Coordonee plCord = players[i_playerActif].getCoordoneeDepart();
            switch (players[i_playerActif].getJoueur()) {
                case JOUEUR1:
                    for (Coordonee coordonee : piece.getPosActif()) {
                        tmpPosPiece[i] = (((coordonee.getX() == plCord.getX()) && (coordonee.getY() == plCord.getY())));
                        i++;
                    }
                    break;
                case JOUEUR2:
                    Coordonee c = new Coordonee(plCord.getX() - piece.getDimension().getX(), plCord.getY());
                    players[i_playerActif].setCoordoneeDepart(c);
                    while (i < tmpPosPiece.length) {
                        tmpPosPiece[i] = (piece.getAllCases()[i][0][piece.getDimension().getX() - 1] == 1);
                        i++;
                    }
                    break;
                case JOUEUR3:
                    players[i_playerActif].setCoordoneeDepart(new Coordonee(plCord.getX(), plCord.getY() - piece.getDimension().getY()));
                    while (i < tmpPosPiece.length) {
                        tmpPosPiece[i] = (piece.getAllCases()[i][piece.getDimension().getY() - 1][0] == 1);
                        i++;
                    }
                    break;
                case JOUEUR4:
                    players[i_playerActif].setCoordoneeDepart(new Coordonee(plCord.getX() - piece.getDimension().getX(), plCord.getY() - piece.getDimension().getY()));
                    while (i < tmpPosPiece.length) {
                        tmpPosPiece[i] = (piece.getAllCases()[i][piece.getDimension().getY() - 1][piece.getDimension().getX() - 1] == 1);
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
                                if (!cases[wrX][wrY].getActif()) {
                                    verifPositionPieceCoordFond(wrX, wrY, piece, abs, ord, positionAllowed, coordDispo);
                                }
                            if (!(wrX >= longueur || jgY < 0))
                                if (!cases[wrX][jgY].getActif())
                                    verifPositionPieceCoordFond(wrX, jgY, piece, abs, ord, positionAllowed, coordDispo);
                            if (!(jgX < 0 || wrY >= hauteur))
                                if (!cases[jgX][wrY].getActif())
                                    verifPositionPieceCoordFond(jgX, wrY, piece, abs, ord, positionAllowed, coordDispo);
                            if (!(jgX < 0 || jgY < 0))
                                if (!cases[jgX][jgY].getActif())
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

    /**
     * Verifie la position de la piece
     *
     * @param wrX             int abscisse de la coordonee trouvee
     * @param wrY             int ordonee de la coordonee trouvee
     * @param piece           Piece
     * @param abs             int
     * @param ord             int
     * @param positionAllowed ArrayList<boolean[]> des positions possible, un tableau correspondant a une coordonne
     * @param coordDispo      ArrayList<Coordonee> coordonnee trouvee
     */
    public void verifPositionPieceCoordFond(int wrX, int wrY, Piece piece, int abs, int ord, ArrayList<boolean[]> positionAllowed, ArrayList<Coordonee> coordDispo) {
        Coordonee cord = new Coordonee(wrX, wrY);
        System.out.println("cord dans verifPosition  " + cord);
        boolean[] tmpPos = new boolean[4];
        int i = 0;
        for (Coordonee coordonee : piece.getPosActif()) {
            Coordonee cordPiece = new Coordonee(abs - coordonee.getX(), ord - coordonee.getY());
            System.out.println("cord piece  dans verif POsition " + cordPiece);
            Piece blkTmp = new BlokusPiece((TypePiece) piece.getTypePiece());
            blkTmp.setCoordonee(cordPiece);
            tmpPos[i] = (cord.getX() == cordPiece.getX() && cord.getY() == cordPiece.getY());
        }
        int r = 0;
        boolean posFound = false;
        while (r != tmpPos.length && !posFound) {
            System.out.println("tmpPOs dans verif position " + tmpPos[r]);
            posFound = tmpPos[r];
            r++;
        }
        if (posFound) {
            coordDispo.add(cord);
            positionAllowed.add(tmpPos);
        }
    }


    public Piece getPiece(ArrayList<Piece> deck, int position) {
        return deck.get(position);
    }
}
