package src.chess.Model.pieces;

import src.chess.Model.Board;

import java.util.ArrayList;
import java.util.List;

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
    public List<Localisation> movements(int x, int y, Board board){

        List<Localisation> moves = new ArrayList<>();
        int direction ;
        int startRow ;

        if ( this.color == Color.WHITE){
            direction = -1 ;
            startRow = 6 ;
        } else {
            direction = 1 ;
            startRow = 1 ;
        }

        if ( board.isEmpty(x,y + direction) ){
            moves.add(new Localisation(x, y + direction));

            if (y == startRow && board.isEmpty(x, y + ( 2 * direction) )) {
                moves.add(new Localisation(x, y + ( 2 * direction) ));
            }
        }

        if (x > 0 && board.hasOpponentPiece(x - 1, y + direction, this.getColor())) {
            moves.add(new Localisation(x - 1, y + direction));
        }

        if ( x < 7 && board.hasOpponentPiece(x + 1, y + direction, this.getColor())) {
            moves.add(new Localisation(x + 1, y + direction));
        }

        return moves;


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
