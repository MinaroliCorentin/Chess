package src.chess.gamestatus;

import src.chess.factory.Board;
import src.chess.model.pieces.Localisation;
import src.chess.model.pieces.Pieces;
import src.chess.model.pieces.PiecesColor;
import src.chess.model.pieces.PiecesStatus;

import java.util.ArrayList;
import java.util.LinkedList;
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

    /**
     * @return True if the DrawCounter is == 50
     */
    public boolean isDraw(){

        return drawCounter == 50;

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

        List<Localisation> threateningPieces = PiecesThreateningKing(piecesColor);

        if ( threateningPieces.size() == 1 ){
            return canParryKingThreat(piecesColor, threateningPieces);
        }

        return true;
    }


    public Boolean canParryKingThreat(PiecesColor piecesColor, List<Localisation> threateningPieces){

        assert ( threateningPieces.size() == 1 );

        for (Map.Entry<Localisation, Pieces> entry : board.getPiecesMap().entrySet()) {
            Pieces piece = entry.getValue();
            if ( piece.getColor() == piecesColor){
                List<Localisation> moves = piece.movements(entry.getKey().getX(), entry.getKey().getY(),board);
                if ( moves.contains(threateningPieces.getFirst())){
                    return true ;
                }
            }
        }
        return false ;
    }


    /**
     * @param piecesColor The king color
     * @return The locations of all enemy pieces that threaten the king
     */
    public List<Localisation> PiecesThreateningKing(PiecesColor piecesColor) {
        List<Localisation> threateningPieces = new ArrayList<>();

        Localisation kingLoc = null;
        for (Map.Entry<Localisation, Pieces> entry : board.getPiecesMap().entrySet()) {
            Pieces piece = entry.getValue();
            if (piece.isKing() && piece.getColor() == piecesColor) {
                kingLoc = entry.getKey();
                break;
            }
        }


        for (Map.Entry<Localisation, Pieces> entry : board.getPiecesMap().entrySet()) {
            Pieces piece = entry.getValue();
            if (piece.getColor() != piecesColor) {
                List<Localisation> moves = piece.movements(entry.getKey().getX(), entry.getKey().getY(), board);

                for (Localisation move : moves) {
                    if (move.equals(kingLoc)) {
                        threateningPieces.add(entry.getKey());
                    }
                }
            }
        }

        return threateningPieces;
    }


}
