package Base.Model;

/**
 * Created by Fabien on 14/02/2017.
 */
public class Piece implements moveAble {
    private boolean alive;
    private Coordonee coordonee;
    protected int[][][] cases;
    private boolean[] positions = new boolean[4];
    private int positionActive;
    protected Vecteur dimension;
    private int xpos, ypos;
    private int pivotX, PivotY;


    public class Vecteur{
        private int x,y;

        public Vecteur(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        public void setX(int x) {
            this.x = x;
        }

        public void setY(int y) {
            this.y = y;
        }
    }

    /**
     * Setteur de coordonnee
     *
     * @param coordonee Coordonne de la Piece
     */
    public void setCoordonee(Coordonee coordonee) {
        this.coordonee = coordonee;
    }

    /**
     * Getteur coordonnee
     *
     * @return coordonee de la piece
     */
    public Coordonee getCoordonee() {
        return coordonee;
    }

    /**
     * Getteur dimension
     *
     * @return dimension de la Piece
     */
    public Vecteur getDimension() {
        return dimension;
    }



    public Piece() {
        this.alive = true;
        this.coordonee = new Coordonee();
    }

    public Enum getTypePiece(){
        return null;
    }


    /**
     * Initialise les dimensions et le tableau de case de la Piece
     *
     * @param xpos longueur
     * @param ypos hauteur
     */
    public void setCases(int xpos, int ypos) {
        this.xpos = xpos;
        this.ypos = ypos;
        this.dimension = new Vecteur(this.xpos,this.ypos);
        for (int i = 0; i < positions.length; i++)
            positions[i] = false;
        positionActive = 0;
        positions[0] = true;
    }



    /**
     * Getteur des cases de la piece
     *
     * @return cases
     */
    public int[][] getCases() {
        return cases[positionActive];
    }

    /**
     * Methode de deplacement de la piece
     *
     * @param direction de la piece
     */
    @Override
    public void move(Direction direction) {
        switch (direction) {
            case BAS:
                coordonee.setY(coordonee.getY() + 1);
                break;
            case DROITE:
                coordonee.setX(coordonee.getX() + 1);
                break;
            case GAUCHE:
                coordonee.setX(coordonee.getX() - 1);
                break;
            case Haut:
                coordonee.setY(coordonee.getY() - 1);
            default:
                break;
        }
    }


    private void setPivotX(int pivotX) {
        this.pivotX = pivotX;
    }

    private void setPivotY(int pivotY) {
        PivotY = pivotY;
    }

    public int getPivotX() {
        return pivotX;
    }

    public int getPivotY() {
        return PivotY;
    }

    /**
     * Retourne la case à la coordonnee correspondante
     *
     * @param i indice abscisse
     * @param j indice ordonnee
     * @return
     */
    public int getCase(int i, int j) {
        return getCases()[j][i];
    }


    public void rotation(Direction direction) {
        switch (direction) {
            case DROITE:
                rotate_Clockwise();
                break;
            case GAUCHE:
                rotate_CounterClockwise();
                break;
            default:
                break;
        }

    }

    private void setPositionActive(int i) {
        for (int j = 0; j < positions.length; j++)
            positions[j] = false;
        positionActive = i;
        positions[positionActive] = true;
    }

    public void rotate_Clockwise() {
        if (positionActive == 0)
            setPositionActive(3);
        else setPositionActive(positionActive - 1);
    }

    public void rotate_CounterClockwise() {
        if (positionActive == 3)
            setPositionActive(0);
        else setPositionActive(positionActive + 1);
    }

    public void kill(){
        this.alive = false;
    }

    public boolean isAlive() {
        return alive;
    }

    /**
     * On cherche la coordonée où le premier 1 apparait dans chaque position
     * Soit le depart de la piece
     * @return
     */
    public Coordonee[] getPosActif(){
        Coordonee[] coords = new Coordonee[positions.length];
        for(int i =0; i<positions.length; i++){
            int [][] cas = cases[i];
            int x = 0 ,y =0;
            boolean found = false;
            while (!found){
                if(cas[y][x] ==1){
                    found =true;
                    coords[i] = new Coordonee(y,x);
                }
                else{
                    y++;
                    if(y == dimension.getX()){
                        x++;
                        y = 0;
                    }
                }
            }
        }
        return coords;
    }


}
