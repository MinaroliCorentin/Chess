package src.chess.model.pieces;

import src.chess.factory.Board;

import java.util.ArrayList;
import java.util.List;

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
    public boolean isKnight(){
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
    public List<Localisation> getAttackSquares(int x, int y, Board board) {
        List<Localisation> attacks = new ArrayList<>();

        int[][] directions = {
                {0, -1}, {1, -1}, {1, 0}, {1, 1},
                {0, 1}, {-1, 1}, {-1, 0}, {-1, -1}
        };

        for (int[] dir : directions) {
            int newX = x + dir[0];
            int newY = y + dir[1];

            while (board.isBound(newX, newY)) {
                attacks.add(new Localisation(newX, newY));
                if (board.getPiece(newX, newY) != null) break;
                newX += dir[0];
                newY += dir[1];
            }
        }

        return attacks;
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