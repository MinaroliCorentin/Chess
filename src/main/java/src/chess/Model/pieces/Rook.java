package src.chess.Model.pieces;

import src.chess.Factory.Board;

import java.util.ArrayList;
import java.util.List;

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
        return true;
    }

    @Override
    public List<Localisation> movements(int x, int y, Board board) {

        List<Localisation> moves = new ArrayList<>();

        int[][] directions = {

                {+1, 0},   // Right
                {-1, 0},  // Left
                {0, +1},   // Down
                {0, -1}   // Up
        };

        for (int[] dir : directions) {
            int dx = dir[0];
            int dy = dir[1];

            int newX = x + dx;
            int newY = y + dy;

            while (newX >= 0 && newX < 8 && newY >= 0 && newY < 8) {

                if (board.isEmpty(newX, newY)) {
                    moves.add(new Localisation(newX, newY));
                } else {
                    if (board.hasOpponentPiece(newX, newY, this.getColor())) {
                        moves.add(new Localisation(newX, newY));
                    }
                    break;
                }

                newX += dx;
                newY += dy;
            }
        }

        return moves;
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
