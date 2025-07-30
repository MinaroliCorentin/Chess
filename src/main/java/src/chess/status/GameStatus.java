package src.chess.status;

import src.chess.factory.Board;
import src.chess.model.handler.PromoteHandler;
import src.chess.model.pieces.Color;
import src.chess.model.pieces.Pieces;

public class GameStatus {

    private Board board;

    public GameStatus(Board board) {

        this.board = board;

    }

    public void promoting() {

        PromoteHandler promoteHandler = new PromoteHandler(board);
        for (int i = 0; i < 8; i++) {

            if ( board.getPiece(0, i) != null && board.getPiece(0, i).isPawn() && board.getPiece(0, i).getColor() == Color.WHITE) {
                board.setPiece(0, i, null);
                promoteHandler.promotion(0, i, Color.WHITE);
            }
            if (board.getPiece(7, i)!= null && board.getPiece(7, i).isPawn() && board.getPiece(7, i).getColor() == Color.BLACK) {
                board.setPiece(7, i, null);
                promoteHandler.promotion(7, i, Color.BLACK);
            }
        }
    }
}