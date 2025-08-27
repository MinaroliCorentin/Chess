package src.chess.gamestatus;

import src.chess.factory.Board;
import src.chess.model.pieces.Localisation;
import src.chess.model.pieces.Pieces;
import src.chess.model.pieces.PiecesColor;
import src.chess.status.PiecesStatus;

import java.util.List;
import java.util.Map;

public abstract class GameStatus {

    private Board board;
    private int drawCounter ;

    public GameStatus(Board board) {

        this.board = board;
        this.drawCounter = 0;

    }

    public Board getBoard() {
        return board;
    }

    public abstract void promoting();

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
     * Verify if there is a checkmate thanks to PiecesStatus.java
     * @return True @PiecesColor is in checkmate
     */
    public boolean isCheckmate(PiecesColor piecesColor) {
        PiecesStatus piecesStatus = new PiecesStatus(board);
        if (!piecesStatus.isKingInCheck(board, piecesColor)) return false;

        Map<Localisation, Pieces> allPieces = board.getPiecesMap();
        for (Map.Entry<Localisation, Pieces> entry : allPieces.entrySet()) {
            Pieces piece = entry.getValue();
            if (piece.isKing() && piece.getColor() == piecesColor) {
                List<Localisation> kingMoves = piece.movements(entry.getKey().getX(), entry.getKey().getY(), board);
                if (kingMoves.isEmpty()) {
                    return true;
                }
            }
        }
        return false;
    }



}
