package Model;

import com.sun.javafx.geom.Vec2d;

/**
 * Created by Fabien on 14/02/2017.
 */
public class Piece implements moveAble{
    private Coordonee coordonee;
    private TypePiece typePiece;
    private Case[][] cases;
    private Vec2d dimension;
    private int xpos,ypos;
    private boolean change;


    public void setCoordonee(Coordonee coordonee) {
        this.coordonee = coordonee;
    }

    public Coordonee getCoordonee() {
        return coordonee;
    }

    public Vec2d getDimension() {
        return dimension;
    }

    public TypePiece getTypePiece() {
        return typePiece;
    }


    public Piece(){}

    public void setTypePiece(TypePiece typePiece){
        this.typePiece = typePiece;
        switch (this.typePiece){
            case Tetrimino_I:
                setCases(4,1);
                make_I();
                break;
            case Tetrimino_J:
                setCases(3,2);
                make_J();
                break;
            case Tetrimino_L:
                setCases(3,2);
                make_L();
                break;
            case Tetrimino_O:
                setCases(2,2);
                make_O();
                break;
            case Tetrimino_S:
                setCases(3,2);
                make_S();
                break;
            case Tetrimino_T:
                setCases(3,2);
                make_T();
                break;
            case Tetrimino_Z:
                setCases(3,2);
                make_Z();
                break;
            default:
                break;
        }
    }

    public void setCases(int xpos, int ypos) {
        this.xpos = xpos;
        this.ypos = ypos;
        this.cases = new Case[xpos][ypos];
        this.dimension = new Vec2d(this.xpos,this.ypos);
    }

    public Piece(TypePiece typePiece) {
        setTypePiece(typePiece);
    }

    private void make_Z() {
        for(int j=0 ; j<xpos-1; j++){
            this.cases[j][0] = new Case();
            this.cases[j][0].caseActiv();
            this.cases[j+1][1] = new Case();
            this.cases[j+1][1].caseActiv();
        }

    }

    private void make_T() {
        int ligne =0;
        for(int colonne = 0; colonne<this.ypos; colonne++){
            if(ligne ==0){
                this.cases[ligne][1] = new Case();
                this.cases[ligne][1].caseActiv();
                colonne =0;
                ligne =1;
            }
            this.cases[ligne][colonne] = new Case();
            this.cases[ligne][colonne].caseActiv();
        }
    }

    private void make_S() {
        for(int j=0 ; j<xpos-1; j++){
            this.cases[j][1] = new Case();
            this.cases[j][1].caseActiv();
            this.cases[j+1][0] = new Case();
            this.cases[j+1][0].caseActiv();
        }
    }

    private void make_O() {
        for(int ligne = 0; ligne<2;ligne++){
            for(int colonne =0 ; colonne<2;colonne++){
                this.cases[ligne][colonne] = new Case();
                this.cases[ligne][colonne].caseActiv();
            }
        }
    }

    private void make_L() {
        for(int x=0;x<xpos;x++){
            this.cases[x][0] =new Case();
            this.cases[x][0].caseActiv();
        }
        this.cases[0][1] = new Case();
        this.cases[0][1].caseActiv();
    }

    private void make_J() {
        for(int x=0;x<xpos;x++){
            this.cases[x][0] =new Case();
            this.cases[x][0].caseActiv();
        }
        this.cases[xpos-1][1] = new Case();
        this.cases[xpos-1][1].caseActiv();
    }

    private void make_I(){
        for(int x=0; x<xpos; x++){
            this.cases[x][0] = new Case();
            this.cases[x][0].caseActiv();
        }
    }



    public Case[][] getCases() {
        return cases;
    }

    @Override
    public void move(Direction direction) {
        switch (direction){
            case BAS:
                coordonee.setY(coordonee.getY()+1);
                break;
            case DROITE:
                coordonee.setX(coordonee.getX()+1);
                break;
            case GAUCHE:
                coordonee.setX(coordonee.getX()-1);
            default:
                break;
        }
    }
}
