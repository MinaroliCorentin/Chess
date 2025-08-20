package src.chess.status;

import src.chess.factory.Board;
import src.chess.model.handler.PromoteHandler;
import src.chess.model.pieces.Color;
import src.chess.model.pieces.Localisation;
import src.chess.model.pieces.Pieces;
import java.util.List;
import java.util.Map;

public class GameStatus {

    private Board board;
    private int drawCounter ;

    public GameStatus(Board board) {

        this.board = board;
        this.drawCounter = 0;

    }

    public int getDrawCounter() {
        return drawCounter;
    }

    public void setDrawCounter(int drawCounter) {
        this.drawCounter = drawCounter;
    }

    public boolean isDraw(){

        return drawCounter == 50;

    }

    public void resetDrawCounter(){

        this.drawCounter = 0;

    }

    /**
     * Verify if a Pawn is located the Top or Bottom of the map depending of his color.
     * Then promoteHandler to promote the pawn.
     */
    public void promoting() {
        PromoteHandler promoteHandler = new PromoteHandler(board);
        Map<Localisation, Pieces> allPieces = board.getPiecesMap();

        for (Map.Entry<Localisation, Pieces> entry : allPieces.entrySet()) {
            Localisation loc = entry.getKey();
            Pieces piece = entry.getValue();

            if (piece.isPawn()) {
                if (piece.getColor() == Color.WHITE && loc.getX() == 0) {
                    board.setPiece(loc.getX(), loc.getY(), null);
                    promoteHandler.promotion(loc.getX(), loc.getY(), Color.WHITE);
                } else if (piece.getColor() == Color.BLACK && loc.getX() == 7) {
                    board.setPiece(loc.getX(), loc.getY(), null);
                    promoteHandler.promotion(loc.getX(), loc.getY(), Color.BLACK);
                }
            }
        }
    }

    /**
     * Verify if there is a checkmate thanks to PiecesStatus.java
     * @return True @Color is in checkmate
     */
    public boolean isCheckmate(Color color) {
        PiecesStatus piecesStatus = new PiecesStatus(board);
        if (!piecesStatus.isKingInCheck(board, color)) return false;

        Map<Localisation, Pieces> allPieces = board.getPiecesMap();
        for (Map.Entry<Localisation, Pieces> entry : allPieces.entrySet()) {
            Pieces piece = entry.getValue();
            if (piece.isKing() && piece.getColor() == color) {
                List<Localisation> kingMoves = piece.movements(entry.getKey().getX(), entry.getKey().getY(), board);
                if (kingMoves.isEmpty()) {
                    return true;
                }
            }
        }
        return false;
    }



}