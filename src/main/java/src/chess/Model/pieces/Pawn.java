package src.chess.Model.pieces;

import src.chess.Model.Board;

public class Pawn extends Pieces {

    public Pawn(Color color) {
        super(color);
    }

    @Override
    public boolean isBishop(){
        return false ;
    }

    @Override
    public boolean isKing(){
        return false ;
    }

    @Override
    public boolean isKnigth(){
        return false ;
    }

    @Override
    public boolean isPawn(){
        return true;
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
        if (this.getColor() == Color.BLACK){
            return UnicodePieces.PAWN_BLACK;
        } else {
            return UnicodePieces.PAWN_WHITE;
        }
    }
}
