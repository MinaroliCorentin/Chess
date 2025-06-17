package src.chess.Model.pieces;

import src.chess.Model.Board;

public class Queen extends Pieces{

    public Queen(Color color) {
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
        return false;
    }

    @Override
    public boolean isQueen(){
        return true;
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
            return UnicodePieces.QUEEN_BLACK;
        } else {
            return UnicodePieces.QUEEN_WHITE;
        }
    }


}