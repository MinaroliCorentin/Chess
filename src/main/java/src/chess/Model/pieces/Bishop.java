package src.chess.Model.pieces;

import src.chess.Model.Board;

import java.util.List;

public class Bishop extends Pieces{

    public Bishop(Color color) {
        super(color);
    }

    @Override
    public boolean isBishop(){
        return true ;
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
        return false;
    }

    @Override
    public List<Localisation> movements(int x, int y, Board board){

    }

    @Override
    public String getSymbol(){
        if ( this.getColor() == Color.BLACK){
            return UnicodePieces.BISHOP_BLACK;
        } else {
            return UnicodePieces.BISHOP_WHITE;
        }
    }

}
