package src.chess.model.pieces;

import src.chess.factory.Board;

import java.util.ArrayList;
import java.util.List;

public class Queen extends Pieces{

    public Queen(Color color) {
        super(color);
    }

    /**
     * Used for the override
     * @return False cause Queen isn't a Bishop
     */
    @Override
    public boolean isBishop(){
        return false ;
    }

    /**
     * Used for the override
     * @return False cause Queen isn't a King
     */
    @Override
    public boolean isKing(){
        return false ;
    }

    /**
     * Used for the override
     * @return False cause Queen isn't a Knight
     */
    @Override
    public boolean isKnight(){
        return false ;
    }

    /**
     * Used for the override
     * @return False cause Queen isn't a Pawn
     */
    @Override
    public boolean isPawn(){
        return false;
    }

    /**
     * Used for the override
     * @return True cause Queen is a Queen
     */
    @Override
    public boolean isQueen(){
        return true;
    }

    /**
     * Used for the override
     * @return False cause Queen isn't a Rook
     */
    @Override
    public boolean isRook(){
        return false;
    }

    /**
     * Returns all possible moves for the Queen.
     * The Queen can move diagonally, vertically, and horizontally until blocked.
     * @param x row
     * @param y column
     * @param board the chess board
     * @return list of possible move locations
     */
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

    /**
     * Returns all squares attacked by the Queen. This method is only used by the king
     * @param x row
     * @param y column
     * @param board the chess board
     * @return list of squares the Queen can attack
     */
    @Override
    public List<Localisation> getAttackSquares(int x, int y, Board board) {
        return this.movements(x, y, board);
    }


    /**
     * Returns the Unicode symbol of the Queen depending on its color.
     * @return Unicode string of the Queen
     */
    @Override
    public String getSymbol(){
        if (this.getColor() == Color.BLACK){
            return UnicodePieces.QUEEN_BLACK;
        } else {
            return UnicodePieces.QUEEN_WHITE;
        }
    }




}