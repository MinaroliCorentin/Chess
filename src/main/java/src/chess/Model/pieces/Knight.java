package src.chess.Model.pieces;

import src.chess.Model.Board;

import javax.swing.text.Position;
import java.util.List;

public class Knight extends Pieces {

    public Knight(Color color) {
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
        return true ;
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
            return UnicodePieces.KNIGHT_BLACK;
        } else {
            return UnicodePieces.KNIGHT_WHITE;
        }
    }

}
