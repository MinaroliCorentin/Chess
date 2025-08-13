package src.chess.model.handler;

import src.chess.factory.Board;
import src.chess.model.pieces.King;
import src.chess.model.pieces.Pieces;
import src.chess.model.pieces.Rook;
import src.chess.status.PiecesStatus;

public class CastlingHandler {

    private Board board;

    public CastlingHandler(Board board) {
        this.board = board;
    }

    /**
     * Verify if the Castling king side is safe for the white, then do it.
     * Set the king and the rook boolean to false, so it's not possible to do it twice.
     * @param fromX King X position
     * @param fromY King Y position
     * @param toX Rook X position
     * @param toY Rook Y postion
     */
    public void handleWhiteKingsideCastling(int fromX, int fromY, int toX, int toY) {
        PiecesStatus status = new PiecesStatus(board);

        if (fromX == 7 && fromY == 4 && toX == 7 && toY == 7 && status.canCastleWhiteRightSide()) {
            Pieces king = board.getPiece(7, 4);
            Pieces rook = board.getPiece(7, 7);

            boolean safe =  !status.isKingInCheckPos(7, 4, king.getColor()) &&
                            !status.isKingInCheckPos(7, 5, king.getColor()) &&
                            !status.isKingInCheckPos(7, 6, king.getColor()) ;

            if (rook.isRook() && king.isKing() && safe) {
                board.setPiece(7, 6, king);
                board.setPiece(7, 5, rook);
                board.setPiece(7, 4, null);
                board.setPiece(7, 7, null);
                ((Rook) rook).setRightRookMoved(true);
                ((King) king).setHasMoved(true);
            }
        }
    }

    /**
     * Verify if the Castling queen side is safe for the white, then do it.
     * Set the king and the rook boolean to false, so it's not possible to do it twice.
     * @param fromX King X position
     * @param fromY King Y position
     * @param toX Rook X position
     * @param toY Rook Y postion
     */
    public void handleWhiteQueensideCastling(int fromX, int fromY, int toX, int toY) {
        PiecesStatus status = new PiecesStatus(board);

        if (fromX == 7 && fromY == 4 && toX == 7 && toY == 0 && status.canCastleWhiteLeftSide()) {
            Pieces king = board.getPiece(7, 4);
            Pieces rook = board.getPiece(7, 0);

            boolean safe =  !status.isKingInCheckPos(7, 4, king.getColor()) &&
                            !status.isKingInCheckPos(7, 3, king.getColor()) &&
                            !status.isKingInCheckPos(7, 2, king.getColor()
                            );

            if (rook.isRook() && king.isKing() && safe) {
                board.setPiece(7, 2, king);
                board.setPiece(7, 3, rook);
                board.setPiece(7, 4, null);
                board.setPiece(7, 0, null);
                ((Rook) rook).setLeftRookMoved(true);
                ((King) king).setHasMoved(true);
            }
        }
    }

    /**
     * Verify if the Castling king side is safe for the black, then do it.
     * Set the king and the rook boolean to false, so it's not possible to do it twice.
     * @param fromX King X position
     * @param fromY King Y position
     * @param toX Rook X position
     * @param toY Rook Y postion
     */
    public void handleBlackKingsideCastling(int fromX, int fromY, int toX, int toY) {

        PiecesStatus status = new PiecesStatus(board);

        if (fromX == 0 && fromY == 4 && toX == 0 && toY == 7 && status.canCastleBlackRightSide()) {
            Pieces king = board.getPiece(0, 4);
            Pieces rook = board.getPiece(0, 7);

            boolean safe =  !status.isKingInCheckPos(0, 4, king.getColor()) &&
                            !status.isKingInCheckPos(0, 5, king.getColor()) &&
                            !status.isKingInCheckPos(0, 6, king.getColor());

            if (rook.isRook() && king.isKing() && safe) {
                board.setPiece(0, 6, king);
                board.setPiece(0, 5, rook);
                board.setPiece(0, 4, null);
                board.setPiece(0, 7, null);
                ((Rook) rook).setRightRookMoved(true);
                ((King) king).setHasMoved(true);
            }
        }
    }

    /**
     * Verify if the Castling queen side is safe for the black, then do it.
     * Set the king and the rook boolean to false, so it's not possible to do it twice.
     * @param fromX King X position
     * @param fromY King Y position
     * @param toX Rook X position
     * @param toY Rook Y postion
     */
    public void handleBlackQueensideCastling(int fromX, int fromY, int toX, int toY) {
        PiecesStatus status = new PiecesStatus(board);

        if (fromX == 0 && fromY == 4 && toX == 0 && toY == 0 && status.canCastleBlackLeftSide()) {
            Pieces king = board.getPiece(0, 4);
            Pieces rook = board.getPiece(0, 0);

            boolean safe =  !status.isKingInCheckPos(0, 4, king.getColor()) &&
                            !status.isKingInCheckPos(0, 3, king.getColor()) &&
                            !status.isKingInCheckPos(0, 2, king.getColor());

            if (rook.isRook() && king.isKing() && safe) {
                board.setPiece(0, 2, king);
                board.setPiece(0, 3, rook);
                board.setPiece(0, 4, null);
                board.setPiece(0, 0, null);
                ((Rook) rook).setLeftRookMoved(true);
                ((King) king).setHasMoved(true);
            }
        }
    }


}