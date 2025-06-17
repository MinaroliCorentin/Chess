package src.chess.Model.pieces;

import src.chess.Model.Board;

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

    public boolean isKnigth(){
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

    public abstract void movements();

    public abstract String getSymbol();


}
