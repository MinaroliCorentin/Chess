package src.chess.model.pieces;

import src.chess.factory.Board;

import java.util.ArrayList;
import java.util.List;

public class Knight extends Pieces {

    public Knight(PiecesColor piecesColor) {
        super(piecesColor);
    }

    /**
     * Boolean used to identify the pieces due the extends Pieces
     * @return false cause the Knight isn't a bishop
     */
    @Override
    public boolean isBishop(){
        return false ;
    }

    /**
     * Boolean used to identify the pieces due the extends Pieces
     * @return false cause the Knight isn't a King
     */
    @Override
    public boolean isKing(){
        return false ;
    }

    /**
     * Boolean used to identify the pieces due the extends Pieces
     * @return true cause the Knight is a Knight
     */
    @Override
    public boolean isKnight(){
        return true ;
    }

    /**
     * Boolean used to identify the pieces due the extends Pieces
     * @return false cause the Knight isn't a pawn
     */
    @Override
    public boolean isPawn(){
        return false;
    }

    /**
     * Boolean used to identify the pieces due the extends Pieces
     * @return false cause the Knight isn't a queen
     */
    @Override
    public boolean isQueen(){
        return false;
    }

    /**
     * Boolean used to identify the pieces due the extends Pieces
     * @return false cause the Knight isn't a rook
     */
    @Override
    public boolean isRook(){
        return false;
    }

    /**
     * Return all the moves available for the Knight.
     * @param x row
     * @param y column
     * @param board the chess board
     * @return list of possible move locations
     */
    @Override
    public List<Localisation> movements(int x, int y, Board board) {

        List<Localisation> moves = new ArrayList<>();

        int[][] directions = {
                { 2,  1},  // Right 2, Down 1
                { 1,  2},  // Right 1, Down 2
                {-1,  2},  // Left 1, Down 2
                {-2,  1},  // Left 2, Down 1
                {-2, -1},  // Left 2, Up 1
                {-1, -2},  // Left 1, Up 2
                { 1, -2},  // Right 1, Up 2
                { 2, -1},  // Right 2, Up 1
        };

        for (int[] dir : directions) {
            int newX = x + dir[0];
            int newY = y + dir[1];

            if (board.isBound(newX, newY)) {
                if (board.isEmpty(newX, newY) || board.hasOpponentPieceOrNull(newX, newY, this.getColor())) {
                    moves.add(new Localisation(newX, newY));
                }
            }
        }
        return moves;
    }

    /**
     * Return all squares attacked by the Knight. Needed only for king.
     * @param x row
     * @param y column
     * @param board the chess board
     * @return list of squares the Knight can attack
     */
    @Override
    public List<Localisation> getAttackSquares(int x, int y, Board board) {
        return this.movements(x, y, board);
    }

    /**
     * Return the Unicode symbol of the Knight depending on its color.
     * @return Unicode string of the Knight
     */
    @Override
    public String getSymbol(){
        if ( this.getColor() == PiecesColor.BLACK){
            return UnicodePieces.KNIGHT_BLACK;
        } else {
            return UnicodePieces.KNIGHT_WHITE;
        }
    }

    @Override
    public String toString() {
        return "Knight";
    }

}
