package src.chess.gamestatus;

import src.chess.factory.Board;
import src.chess.model.pieces.Localisation;
import src.chess.model.pieces.Pieces;
import src.chess.model.pieces.PiecesColor;
import src.chess.model.pieces.PiecesStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public abstract class GameStatus {

    private Board board;
    private int drawCounter ;

    public GameStatus(Board board) {

        this.board = board;
        this.drawCounter = 0;

    }

    /**
     * Getter board
     * @return Board
     */
    public Board getBoard() {
        return board;
    }

    public abstract void promoting();

    /**
     * Getter DrawCounter
     * @return DrawCounter
     */
    public int getDrawCounter() {
        return drawCounter;
    }

    /**
     * Setter DrawCounter
     * @param drawCounter set the DrawCounter
     */
    public void setDrawCounter(int drawCounter) {
        this.drawCounter = drawCounter;
    }

    public void inscreaseDrawCounter(){
        this.drawCounter ++ ;
    }


    /**
     * @return True if the DrawCounter is == 100
     */
    public boolean isDraw(){

        return drawCounter >= 100;

    }

    /**
     * Reset the DrawCounter
     */
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

        for (Map.Entry<Localisation,Pieces> entry : board.getPiecesMap().entrySet()){

            Pieces piece = entry.getValue();
            if(piece.getColor() != piecesColor) continue ;
            int x = entry.getKey().getX();
            int y = entry.getKey().getY();
            List<Localisation> moves = piece.movements(x,y,board);

            for (Localisation move : moves){
                Pieces target = board.getPiece(move.getX(), move.getY());
                board.setPiece(move.getX(), move.getY(),piece);
                board.setPiece(x,y,null);
                boolean safe = !piecesStatus.isKingInCheck(board,piecesColor);
                board.setPiece(x,y,piece);
                board.setPiece(move.getX(), move.getY(),target);
                if (safe) return false ;
            }
        }
        return true;
    }

}
