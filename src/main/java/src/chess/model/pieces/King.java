package src.chess.model.pieces;

import src.chess.factory.Board;
import src.chess.status.PiecesStatus;

import java.util.ArrayList;
import java.util.List;

public class King extends Pieces {

    private boolean hasMoved ;

    public King(Color color) {
        super(color);
        this.hasMoved = false;
    }

    public boolean isMoved() {
        return hasMoved;
    }

    public void setHasMoved(boolean hasMoved) {
        this.hasMoved = hasMoved;
    }

    @Override
    public boolean isBishop(){
        return false ;
    }

    @Override
    public boolean isKing(){
        return true ;
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
        return false;
    }

    @Override
    public boolean isRook(){
        return false;
    }

    @Override
    public List<Localisation> movements(int x, int y, Board board) {

        List<Localisation> moves = new ArrayList<>();
        PiecesStatus status = new PiecesStatus(board);

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

            if (board.isBound(newX, newY)) {

                Pieces target = board.getPiece(newX, newY);

                if (target == null || target.getColor() != this.getColor()) {

                    board.setPiece(x, y, null);
                    Pieces oldTarget = board.getPiece(newX, newY);
                    board.setPiece(newX, newY, this);

                    boolean safe = !status.isKingInCheckPos(newX, newY, this.getColor());

                    board.setPiece(x, y, this);
                    board.setPiece(newX, newY, oldTarget);

                    if (safe) {
                        moves.add(new Localisation(newX, newY));
                    }
                }
            }

        }


        // White
        if (this.getColor() == Color.WHITE && !this.isMoved() && x == 7 && y == 4) {
            if (status.canCastleWhiteRightSide()) {
                moves.add(new Localisation(7, 6));
            }
            if (status.canCastleWhiteLeftSide()) {
                moves.add(new Localisation(7, 2));
            }
        }

        // Black
        if (this.getColor() == Color.BLACK && !this.isMoved() && x == 0 && y == 4) {
            if (status.canCastleBlackRightSide()) {
                moves.add(new Localisation(0, 6));
            }
            if (status.canCastleBlackLeftSide()) {
                moves.add(new Localisation(0, 2));
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
            if (board.isBound(newX, newY)) {
                attacks.add(new Localisation(newX, newY));
            }
        }

        return attacks;
    }



    @Override
    public String getSymbol(){
        if ( this.getColor() == Color.BLACK){
            return UnicodePieces.KING_BLACK;
        } else {
            return UnicodePieces.KING_WHITE;
        }
    }

}