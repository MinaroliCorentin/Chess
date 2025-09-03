package src.chess.model.pieces;

import src.chess.factory.Board;

import java.util.List;
import java.util.Map;

public class PiecesStatus {

    private Board board;

    public PiecesStatus(Board board) {
        this.board = board;
    }

    /**
     * Verify is the King of the param piecesColor is in Check
     *
     * @param board The board the player are using
     * @param piecesColor The piecesColor we want ot verify
     * @return True if the king of @param piecesColor is in danger
     */
    public boolean isKingInCheck(Board board, PiecesColor piecesColor) {

        Localisation kingLoc = null;
        Map<Localisation, Pieces> allPieces = board.getPiecesMap();

        // Find the king
        for (Map.Entry<Localisation, Pieces> entry : allPieces.entrySet()) {
            Pieces piece = entry.getValue();
            if (piece.isKing() && piece.getColor().equals(piecesColor)) {
                kingLoc = entry.getKey();
                break;
            }
        }

        if (kingLoc == null) {
            return false;
        }

        for (Map.Entry<Localisation, Pieces> entry : allPieces.entrySet()) {
            Pieces piece = entry.getValue();
            if (!piece.getColor().equals(piecesColor)) {
                List<Localisation> moves = piece.movements(entry.getKey().getX(), entry.getKey().getY(), board);
                for (Localisation loc : moves) {
                    if (loc.equals(kingLoc)) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    /**
     * Verify if the king is in check using the position X and Y
     *
     * @param posX  The position X of the king
     * @param posY  The position Y of the King
     * @param piecesColor The piecesColor of the king
     * @return True if the king in @param PiecesColor is in danger
     */
    public boolean isKingInCheckPos(int posX, int posY, PiecesColor piecesColor) {
        Map<Localisation, Pieces> allPieces = board.getPiecesMap();

        for (Map.Entry<Localisation, Pieces> entry : allPieces.entrySet()) {
            Pieces piece = entry.getValue();
            if (!piece.getColor().equals(piecesColor)) {
                List<Localisation> attackSquares = piece.getAttackSquares(entry.getKey().getX(), entry.getKey().getY(), board);
                for (Localisation loc : attackSquares) {
                    if (loc.getX() == posX && loc.getY() == posY) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * Verify if the Black King can do a Casting Left Side
     *
     * @return True if the castling is possible and if he rook.isLeftRookMOved and the king.IsMoved are false
     */
    public boolean canCastleBlackLeftSide() {

        Pieces pieces = board.getPiece(0, 0);
        Pieces pieces2 = board.getPiece(0, 4);

        for (int i = 1; i < 4; i++) {
            Pieces emptyPiece = board.getPiece(0, i);
            if (emptyPiece != null) {
                return false;
            }
        }

        if (pieces != null && pieces.isRook() && pieces2 != null && pieces2.isKing()) {
            Rook rook = (Rook) board.getPiece(0, 0);
            King king = (King) board.getPiece(0, 4);
            return !rook.isLeftRookMoved() && !king.isMoved();
        }
        return false;
    }

    /**
     * Verify if the White King can do a Casting Left Side
     *
     * @return True if the castling is possible and if he rook.isLeftRookMOved and the king.IsMoved are false
     */
    public boolean canCastleWhiteLeftSide() {

        Pieces pieces = board.getPiece(7, 0);
        Pieces pieces2 = board.getPiece(7, 4);

        for (int i = 1; i < 4; i++) {
            Pieces emptyPiece = board.getPiece(7, i);
            if (emptyPiece != null) {
                return false;
            }
        }

        if (pieces != null && pieces.isRook() && pieces2 != null && pieces2.isKing()) {
            Rook rook = (Rook) board.getPiece(7, 0);
            King king = (King) board.getPiece(7, 4);
            return !rook.isLeftRookMoved() && !king.isMoved();
        }
        return false;
    }

    /**
     * Verify if the Black King can do a Casting Right Side
     *
     * @return True if the castling is possible and if he rook.isRightRookMOved and the king.IsMoved are false
     */
    public boolean canCastleBlackRightSide() {

        Pieces pieces = board.getPiece(0, 7);
        Pieces pieces2 = board.getPiece(0, 4);

        for (int i = 6; i > 4; i--) {
            Pieces emptyPiece = board.getPiece(0, i);
            if (emptyPiece != null) {
                return false;
            }
        }

        if (pieces != null && pieces.isRook() && pieces2 != null && pieces2.isKing()) {
            Rook rook = (Rook) board.getPiece(0, 7);
            King king = (King) board.getPiece(0, 4);
            return !rook.isLeftRookMoved() && !king.isMoved();
        }
        return false;
    }

    /**
     * Verify if the White King can do a Casting Right  Side
     *
     * @return True if the castling is possible and if he rook.isRightRookMoved and the king.IsMoved are false
     */
    public boolean canCastleWhiteRightSide() {

        Pieces pieces = board.getPiece(7, 7);
        Pieces pieces2 = board.getPiece(7, 4);

        for (int i = 6; i > 4; i--) {
            Pieces emptyPiece = board.getPiece(7, i);
            if (emptyPiece != null) {
                return false;
            }
        }

        if (pieces != null && pieces.isRook() && pieces2 != null && pieces2.isKing()) {
            Rook rook = (Rook) board.getPiece(7, 7);
            King king = (King) board.getPiece(7, 4);
            return !rook.isLeftRookMoved() && !king.isMoved();
        }
        return false;
    }

    public boolean stalemate(Board board, PiecesColor piecesColor) {

        if (isKingInCheck(board, piecesColor)) {
            return false;
        }

        for (Map.Entry<Localisation, Pieces> entry : board.getPiecesMap().entrySet()) {
            Pieces piece = entry.getValue();
            if (piece.getColor() == piecesColor) {
                int x = entry.getKey().getX();
                int y = entry.getKey().getY();
                List<Localisation> moves = piece.movements(x, y, board);

                for (Localisation move : moves) {

                    Pieces target = board.getPiece(move.getX(), move.getY());

                    board.setPiece(move.getX(), move.getY(), piece);
                    board.setPiece(x, y, null);

                    boolean kingSafe = !isKingInCheck(board, piecesColor);

                    board.setPiece(x, y, piece);
                    board.setPiece(move.getX(), move.getY(), target);

                    if (kingSafe) {
                        return false;
                    }
                }
            }
        }
        return true;
    }


}