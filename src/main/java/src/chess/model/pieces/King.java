package src.chess.model.pieces;

import src.chess.factory.Board;

import java.util.ArrayList;
import java.util.List;

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
    public boolean isKnight(){
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
    public List<Localisation> movements(int x, int y, Board board) {

        List<Localisation> moves = new ArrayList<>();

        int[][] directions = {

                {0, -1},   // Up
                {+1, -1},   // Up-Right
                {+1, 0},    // Right
                {+1, +1},    // Down-Right
                {0, +1},    // Down
                {-1, +1},   // Down-Left
                {-1, 0},   // Left
                {-1, -1}   // Up-Left
        };

        for (int[] dir : directions) {

            int dx = dir[0];
            int dy = dir[1];

            int newX = x + dx;
            int newY = y + dy;

            if (newX >= 0 && newX < 8 && newY >= 0 && newY < 8) {
                if (board.isEmpty(newX, newY) || board.hasOpponentPiece(newX, newY, this.getColor())) {
                    moves.add(new Localisation(newX, newY));
                }
            }

        }

        return moves;
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
