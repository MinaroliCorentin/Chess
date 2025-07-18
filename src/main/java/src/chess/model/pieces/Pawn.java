package src.chess.model.pieces;

import src.chess.factory.Board;

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
    public boolean isKnight(){
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

        if ( this.getColor() == Color.WHITE){
            direction = -1 ;
            startRow = 6 ;
        } else {
            direction = 1 ;
            startRow = 1 ;
        }

        if (board.isEmpty(x + direction, y)) {
            moves.add(new Localisation(x + direction, y));

            if (x == startRow && board.isEmpty(x + 2 * direction, y)) {
                moves.add(new Localisation(x + 2 * direction, y));
            }
        }

        if (y > 0 && board.hasOpponentPiece(x + direction, y - 1, this.getColor())) {
            moves.add(new Localisation(x + direction, y - 1));
        }

        if (y < 7 && board.hasOpponentPiece(x + direction, y + 1, this.getColor())) {
            moves.add(new Localisation(x + direction, y + 1));
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
