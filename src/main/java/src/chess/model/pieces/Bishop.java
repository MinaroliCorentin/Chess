package src.chess.model.pieces;

import src.chess.factory.Board;

import java.util.ArrayList;
import java.util.List;

public class Bishop extends Pieces{

    public Bishop(PiecesColor piecesColor) {
        super(piecesColor);
    }

    /**
     * Boolean used to idenfity the pieces due the extends Pieces
     * @return true cause the Bishop is a bishop
     */
    @Override
    public boolean isBishop(){
        return true ;
    }

    /**
     * Boolean used to idenfity the pieces due the extends Pieces
     * @return false cause the Bishop isn't a king
     */
    @Override
    public boolean isKing(){
        return false ;
    }
    /**
     * Boolean used to idenfity the pieces due the extends Pieces
     * @return false cause the Bishop isn't a Knight
     */
    @Override
    public boolean isKnight(){
        return false ;
    }

    /**
     * Boolean used to idenfity the pieces due the extends Pieces
     * @return false cause the Bishop isn't a Pawn
     */
    @Override
    public boolean isPawn(){
        return false;
    }

    /**
     * Boolean used to idenfity the pieces due the extends Pieces
     * @return false cause the Bishop isn't a queen
     */
    @Override
    public boolean isQueen(){
        return false;
    }

    /**
     * Boolean used to idenfity the pieces due the extends Pieces
     * @return false cause the Bishop isn't a rook
     */
    @Override
    public boolean isRook(){
        return false;
    }

    /**
     * Return all the move available. Used for the function play.
     * @param x row
     * @param y col
     * @param board board
     * @return all the move available
     */
    @Override
    public List<Localisation> movements(int x, int y, Board board) {

        List<Localisation> moves = new ArrayList<>();

        int[][] directions = {

                {+1, -1},   // Up-Right
                {+1, +1},    // Down-Right
                {-1, +1},   // Down-Left
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
                    if (board.hasOpponentPieceOrNull(newX, newY, this.getColor())) {
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
     * @param x row
     * @param y col
     * @param board the board
     * @return Return this.movements(x,y,board). This method is only needed for king.
     */
    @Override
    public List<Localisation> getAttackSquares(int x, int y, Board board) {
       return this.movements(x, y, board);
    }

    /**
     * @return Depending of the color of the Pieces, return the appropriate Unicode
     */
    @Override
    public String getSymbol(){
        if ( this.getColor() == PiecesColor.BLACK){
            return UnicodePieces.BISHOP_BLACK;
        } else {
            return UnicodePieces.BISHOP_WHITE;
        }
    }

    @Override
    public String toString() {
        return "Bishop";
    }
}
