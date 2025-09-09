package src.chess.model.pieces;

import src.chess.factory.Board;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class King extends Pieces {

    private boolean hasMoved ;

    /**
     * Create a king with the boolean hasMoved at False. hasMoved is set as true when moved or used in Castling
     * @param piecesColor
     */
    public King(PiecesColor piecesColor) {
        super(piecesColor);
        this.hasMoved = false;
    }

    /**
     * Getter of hasMoved, used for the Castling
     * @return hasMoved
     */
    public boolean isMoved() {
        return hasMoved;
    }

    /**
     * Setter of hasMoved, used for the Castling
     * @param hasMoved hasMoved
     */
    public void setHasMoved(boolean hasMoved) {
        this.hasMoved = hasMoved;
    }

    /**
     * Boolean used to idenfity the pieces due the extends Pieces
     * @return false cause the King isn't a bishop
     */
    @Override
    public boolean isBishop(){
        return false ;
    }

    /**
     * Boolean used to idenfity the pieces due the extends Pieces
     * @return true cause the King is a King
     */
    @Override
    public boolean isKing(){
        return true ;
    }

    /**
     * Boolean used to idenfity the pieces due the extends Pieces
     * @return false cause the King isn't a Knight
     */
    @Override
    public boolean isKnight(){
        return false ;
    }

    /**
     * Boolean used to idenfity the pieces due the extends Pieces
     * @return false cause the King isn't a Pawn
     */
    @Override
    public boolean isPawn(){
        return false;
    }

    /**
     * Boolean used to idenfity the pieces due the extends Pieces
     * @return false cause the King isn't a Queen
     */
    @Override
    public boolean isQueen(){
        return false;
    }

    /**
     * Boolean used to idenfity the pieces due the extends Pieces
     * @return false cause the King isn't a Rook
     */
    @Override
    public boolean isRook(){
        return false;
    }

    /**
     * Return all the moves available as long it is save for the king. the moves also include the move available with Castling
     * @param x row
     * @param y col
     * @param board board
     * @return all the available moves for the king
     */
    @Override
    public List<Localisation> movements(int x, int y, Board board) {

        List<Localisation> moves = new ArrayList<>();
        PiecesStatus status = new PiecesStatus(board);

        int[][] directions = {
                {0, -1}, {1, -1}, {1, 0}, {1, 1},
                {0, 1}, {-1, 1}, {-1, 0}, {-1, -1}
        };

        for (int[] dir : directions) {
            int newX = x + dir[0];
            int newY = y + dir[1];

            if (!board.isBound(newX, newY)) {
                continue;
            }

            Pieces target = board.getPiece(newX, newY);

            if (target == null || target.getColor() != this.getColor()) {

                board.setPiece(x, y, null);
                Pieces oldTarget = board.getPiece(newX, newY);
                board.setPiece(newX, newY, this);

                boolean safe = true;
                for (Map.Entry<Localisation, Pieces> entry : board.getPiecesMap().entrySet()) {
                    Pieces piece = entry.getValue();
                    if (piece.getColor() != this.getColor()) {
                        List<Localisation> attacks = piece.getAttackSquares(entry.getKey().getX(), entry.getKey().getY(), board);
                        for (Localisation loc : attacks) {
                            if (loc.getX() == newX && loc.getY() == newY) {
                                safe = false;
                                break;
                            }
                        }
                    }
                    if (!safe){
                        break;
                    }
                }

                board.setPiece(x, y, this);
                board.setPiece(newX, newY, oldTarget);

                if (safe) moves.add(new Localisation(newX, newY));
            }
        }

        // White
        if (this.getColor() == PiecesColor.WHITE && !this.isMoved() && x == 7 && y == 4) {
            if (status.canCastleWhiteRightSide()) {
                moves.add(new Localisation(7, 6));
            }
            if (status.canCastleWhiteLeftSide()) {
                moves.add(new Localisation(7, 2));
            }
        }

        // Black
        if (this.getColor() == PiecesColor.BLACK && !this.isMoved() && x == 0 && y == 4) {
            if (status.canCastleBlackRightSide()) {
                moves.add(new Localisation(0, 6));
            }
            if (status.canCastleBlackLeftSide()) {
                moves.add(new Localisation(0, 2));
            }
        }

        return moves;
    }

    /**
     * return all the moves possible but don't check if the check is in danger.
     * This method was mode for kingInCheck to avoid StackOverflow when using movements
     * @param x row
     * @param y col
     * @param board board
     * @return return all the moves available
     */
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
            if (board.isBound(newX, newY)) {
                attacks.add(new Localisation(newX, newY));
            }
        }

        return attacks;
    }

    /**
     * @return Return the unicode Symbol of the Pieces
     */
    @Override
    public String getSymbol(){
        if ( this.getColor() == PiecesColor.BLACK){
            return UnicodePieces.KING_BLACK;
        } else {
            return UnicodePieces.KING_WHITE;
        }
    }

    /**
     * ToString of the King
     * @return "King"
     */
    @Override
    public String toString() {
        return "King";
    }

}