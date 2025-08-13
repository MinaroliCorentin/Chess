package src.chess.model.pieces;

import src.chess.factory.Board;

import java.util.ArrayList;
import java.util.List;

public class Rook extends Pieces {

    private boolean leftRookMoved ;
    private boolean rightRookMoved;

    public Rook(Color color) {
        super(color);
        this.leftRookMoved = false ;
        this.rightRookMoved = false ;
    }

    /**
     * Used to know if the Castling is still possible for the left rook
     * @return Getter for leftRookMoved
     */
    public boolean isLeftRookMoved() {
        return leftRookMoved;
    }

    /**
     * Setter set at false if the rook moved. Used to prevent castling
     * @param leftRookMoved Setter for leftRookMoved
     */
    public void setLeftRookMoved(boolean leftRookMoved) {
        this.leftRookMoved = leftRookMoved;
    }


    /**
     * Used to know if the Castling is still possible for the right rook
     * @return Getter for rightRookMoved
     */
    public boolean isRightRookMoved() {
        return rightRookMoved;
    }

    /**
     * Setter set at false if the rook moved. Used to prevent castling
     * @param rightRookMoved Setter for rightRookMoved
     */
    public void setRightRookMoved(boolean rightRookMoved) {
        this.rightRookMoved = rightRookMoved;
    }

    /**
     * Used for the override
     * @return False cause Rook isn't a Bishop
     */
    @Override
    public boolean isBishop(){
        return false ;
    }

    /**
     * Used for the override
     * @return False cause Rook isn't a King
     */
    @Override
    public boolean isKing(){
        return false ;
    }

    /**
     * Used for the override
     * @return False cause Rook isn't a Knight
     */
    @Override
    public boolean isKnight(){
        return false ;
    }

    /**
     * Used for the override
     * @return False cause Rook isn't a Pawn
     */
    @Override
    public boolean isPawn(){
        return false;
    }

    /**
     * Used for the override
     * @return False cause Rook isn't a Queen
     */
    @Override
    public boolean isQueen(){
        return false;
    }

    /**
     * Used for the override
     * @return True cause Rook is a Rook
     */
    @Override
    public boolean isRook(){
        return true;
    }

    /**
     * Returns all possible moves for the Rook.
     * The Rook can move vertically and horizontally until blocked.
     * @param x row
     * @param y column
     * @param board the chess board
     * @return list of possible move locations
     */
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

    /**
     * Returns all squares attacked by the Rook.
     * @param x row
     * @param y column
     * @param board the chess board
     * @return list of squares the Rook can attack
     */
    @Override
    public List<Localisation> getAttackSquares(int x, int y, Board board) {
        return this.movements(x, y, board);
    }


    /**
     * Returns the Unicode symbol of the Rook depending on its color.
     * @return Unicode string of the Rook
     */
    @Override
    public String getSymbol(){
        if (this.getColor() == Color.BLACK){
            return UnicodePieces.ROOK_BLACK;
        } else {
            return UnicodePieces.ROOK_WHITE;
        }
    }

}
