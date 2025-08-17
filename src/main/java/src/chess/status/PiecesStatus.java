package src.chess.status;

import src.chess.factory.Board;
import src.chess.model.pieces.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PiecesStatus {

    private Board board;

    public PiecesStatus(Board board) {
        this.board = board;
    }

    /**
     * Verify is the King of the param color is in Check
     *
     * @param board The board the player are using
     * @param color The color we want ot verify
     * @return True if the king of @param color is in danger
     */
    public boolean isKingInCheck(Board board, Color color) {

        Localisation kingLoc = null;
        Map<Localisation, Pieces> allPieces = board.getPiecesMap();

        // Find the king
        for (Map.Entry<Localisation, Pieces> entry : allPieces.entrySet()) {
            Pieces piece = entry.getValue();
            if (piece.isKing() && piece.getColor().equals(color)) {
                kingLoc = entry.getKey();
                break;
            }
        }

        if (kingLoc == null) {
            return false;
        }

        for (Map.Entry<Localisation, Pieces> entry : allPieces.entrySet()) {
            Pieces piece = entry.getValue();
            if (!piece.getColor().equals(color)) {
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
     * @param color The color of the king
     * @return True if the king in @param Color is in danger
     */
    public boolean isKingInCheckPos(int posX, int posY, Color color) {
        Map<Localisation, Pieces> allPieces = board.getPiecesMap();

        for (Map.Entry<Localisation, Pieces> entry : allPieces.entrySet()) {
            Pieces piece = entry.getValue();
            if (!piece.getColor().equals(color)) {
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

    public boolean stalemate(Board board, Color color) {

        Map<Localisation, Pieces> allPieces = board.getPiecesMap();
        int[][] directions = {
            {0, -1},
            {1, -1},
            {1, 0},
            {1, 1},
            {0, 1},
            {-1, 1},
            {-1, 0},
            {-1, -1}
        };

        for (Map.Entry<Localisation, Pieces> entry : allPieces.entrySet()) {

            if (entry.getValue().getColor().equals(color) && entry.getValue().isKing()) {

                int x = entry.getKey().getX();
                int y = entry.getKey().getY();

                for (int[] dir : directions) {
                    int dx = x + dir[0];
                    int dy = y + dir[1];

                    int newX = x + dx;
                    int newY = y + dy;

                    if (board.isBound(newX, newY)) {
                        if (!this.isKingInCheckPos(newX, newY, color)) {
                            return false;
                        }
                    }
                }
            }
        }
    return true;
    }
}