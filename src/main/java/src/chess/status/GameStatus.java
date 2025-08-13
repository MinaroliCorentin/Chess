package src.chess.status;

import src.chess.factory.Board;
import src.chess.model.handler.PromoteHandler;
import src.chess.model.pieces.Color;
import src.chess.model.pieces.Localisation;
import src.chess.model.pieces.Pieces;
import java.util.List;

public class GameStatus {

    private Board board;

    public GameStatus(Board board) {

        this.board = board;

    }

    /**
     * Verify if a Pawn is located the Top or Bottom of the map depending of his color.
     * Then promoteHandler to promote the pawn.
     */
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

    /**
     * Verify if the White is in CheckMate thanks to the method isKingInCheck in PiecesStatus.java
     * @return True if the White is in CheckMate ( Black Wins )
     */
    public boolean whiteCheckmate(){

        PiecesStatus piecesStatus = new PiecesStatus(board);
        boolean checkWhite = piecesStatus.isKingInCheck(board,Color.WHITE);
        if ( checkWhite ){

            for ( int i = 0 ; i < 8 ; i++){
                for ( int j = 0 ; j < 8 ; j++){
                    if ( board.getPiece(i, j) != null && board.getPiece(i, j).isKing()){

                        Pieces king = board.getPiece(i, j);
                        List<Localisation> kingmove = king.movements(i,j,board);
                        if ( kingmove.isEmpty()){
                            System.out.println("Black wins");
                            return true;
                        }

                    }
                }
            }
        }
        return false ;
    }


    /**
     * Verify if the Black is in CheckMate thanks to the method isKingInCheck in PiecesStatus.java
     * @return True if the Black is in CheckMate ( White Wins )
     */
    public boolean blackCheckmate(){

        PiecesStatus piecesStatus = new PiecesStatus(board);
        boolean checkBlack = piecesStatus.isKingInCheck(board,Color.BLACK);
        if ( checkBlack ){

            for ( int i = 0 ; i < 8 ; i++){
                for ( int j = 0 ; j < 8 ; j++){
                    if ( board.getPiece(i, j) != null && board.getPiece(i, j).isKing()){

                        Pieces king = board.getPiece(i, j);
                        List<Localisation> kingmove = king.movements(i,j,board);
                        if ( kingmove.isEmpty()){
                            System.out.println("White wins");
                            return true;
                        }

                    }
                }
            }
        }
        return false ;
    }

}