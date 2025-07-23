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

            if (newX >= 0 && newX < 8 && newY >= 0 && newY < 8) {
                if (board.isEmpty(newX, newY) || board.hasOpponentPiece(newX, newY, this.getColor())) {
                    moves.add(new Localisation(newX, newY));
                }
            }

        }

        PiecesStatus status = new PiecesStatus(board);

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
    public String getSymbol(){
        if ( this.getColor() == Color.BLACK){
            return UnicodePieces.KING_BLACK;
        } else {
            return UnicodePieces.KING_WHITE;
        }
    }

}
