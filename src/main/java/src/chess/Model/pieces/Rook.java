package src.chess.Model.pieces;

import src.chess.Model.Board;

public class Rook extends Pieces {

    public Rook(Color color) {
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
        return false;
    }

    @Override
    public boolean isRook(){
        return true;
    }
    
    @Override
    public void movements(){
        
    }

    @Override
    public String getSymbol(){
        if (this.getColor() == Color.BLACK){
            return UnicodePieces.ROOK_BLACK;
        } else {
            return UnicodePieces.ROOK_WHITE;
        }
    }

}
