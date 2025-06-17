package src.chess.Model.pieces;

import src.chess.Model.Board;

public class King extends Pieces {

    public King(Color color) {
        super(color);
    }

    @Override
    public boolean isBishop(){
        return false ;
    }

    @Override
    public boolean isKing(){
        return true ;
    }

    @Override
    public boolean isKnigth(){
        return false ;
    }

    @Override
    public boolean isPawn(){
        return false;
    }

    @Override
    public boolean isQueen(){
        return false;
    }

    @Override
    public boolean isRook(){
        return false;
    }

    @Override
    public void movements(){

    }

    @Override
    public String getSymbol(){
        if ( this.getColor() == Color.BLACK){
            return UnicodePieces.KING_BLACK;
        } else {
            return UnicodePieces.KING_WHITE;
        }
    }

}
