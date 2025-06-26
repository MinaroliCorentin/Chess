package src.chess.Model.pieces;

import src.chess.Factory.Board;

import java.util.List;

public abstract class Pieces {

    private Color color;
    
    public Pieces(Color color){
        this.color = color;
    }

    public Color getColor(){
        return color;
    }
    
    public boolean isBishop(){
        return false ;
    }

    public boolean isKing(){
        return false ;
    }

    public boolean isKnight(){
        return false ;
    }

    public boolean isPawn(){
        return false;
    }

    public boolean isQueen(){
        return false;
    }

    public boolean isRook(){
        return false;
    }

    public abstract List<Localisation> movements(int x, int y, Board board);
    public abstract String getSymbol();


}
