package src.chess.model.pieces;

import src.chess.factory.Board;

import java.util.ArrayList;
import java.util.List;

public class Pawn extends Pieces {

    public Pawn(Color color) {
        super(color);
    }

    /**
     * Boolean used to identify the piece due to the extends Pieces
     * @return false because the Pawn isn't a bishop
     */
    @Override
    public boolean isBishop(){
        return false ;
    }

    /**
     * Boolean used to identify the piece due to the extends Pieces
     * @return false because the Pawn isn't a king
     */
    @Override
    public boolean isKing(){
        return false ;
    }

    /**
     * Boolean used to identify the piece due to the extends Pieces
     * @return false because the Pawn isn't a knight
     */
    @Override
    public boolean isKnight(){
        return false ;
    }

    /**
     * Boolean used to identify the piece due to the extends Pieces
     * @return true because the Pawn is a pawn
     */
    @Override
    public boolean isPawn(){
        return true;
    }

    /**
     * Boolean used to identify the piece due to the extends Pieces
     * @return false because the Pawn isn't a queen
     */
    @Override
    public boolean isQueen(){
        return false;
    }


    /**
     * Boolean used to identify the piece due to the extends Pieces
     * @return false because the Pawn isn't a rook
     */
    @Override
    public boolean isRook(){
        return false;
    }

    /**
     * Return all the moves available for the Pawn.
     * Includes forward movement and diagonal captures.
     * @param x row
     * @param y column
     * @param board the chess board
     * @return list of possible move locations
     */
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

        if (y > 0 && board.hasOpponentPieceOrNull(x + direction, y - 1, this.getColor())) {
            moves.add(new Localisation(x + direction, y - 1));
        }

        if (y < 7 && board.hasOpponentPieceOrNull(x + direction, y + 1, this.getColor())) {
            moves.add(new Localisation(x + direction, y + 1));
        }

        if ( y - board.getEnPassantPawnY() == 1  ){
            moves.add(new Localisation(x + direction, y - 1));
        } else if ( y - board.getEnPassantPawnY() == -1 ){
            moves.add(new Localisation(x + direction, y + 1));
        }

        return moves;
    }

    /**
     * Return all squares attacked by the Pawn. this method is only needed for King.
     * @param x row
     * @param y column
     * @param board the chess board
     * @return list of squares the Pawn can attack
     */
    @Override
    public List<Localisation> getAttackSquares(int x, int y, Board board) {
        List<Localisation> attacks = new ArrayList<>();
        int direction = (this.getColor() == Color.WHITE) ? -1 : 1;

        if (y > 0) {
            attacks.add(new Localisation(x + direction, y - 1));
        }
        if (y < 7) {
            attacks.add(new Localisation(x + direction, y + 1));
        }

        return attacks;
    }


    /**
     * Return the Unicode symbol of the Pawn depending on its color.
     * @return Unicode string of the Pawn
     */
    @Override
    public String getSymbol(){
        if (this.getColor() == Color.BLACK){
            return UnicodePieces.PAWN_BLACK;
        } else {
            return UnicodePieces.PAWN_WHITE;
        }
    }
}
