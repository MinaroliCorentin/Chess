package src.chess.model.handler;

import src.chess.factory.Board;
import src.chess.model.pieces.*;

import java.util.Scanner;

public class PromoteHandler {

    private Board board;

    public PromoteHandler(Board board) {
        this.board = board;
    }

    /**
     * Deal with the promotion.
     * Let the user decide what he want with a scanner, have to be Rook,Knight,Bishop or Queen
     * @param x row
     * @param y col
     * @param piecesColor piecesColor of the pawn
     */
    public void promotion(int x, int y, PiecesColor piecesColor){

        Scanner scanner =  new Scanner(System.in);
        System.out.println(
                """
                A promotion has been detected
                Please select your new Pieces : 
                1. Rook
                2. Knight 
                3. Bishop
                4. Queen
                """
        );
        String str = scanner.nextLine();
        while ( !str.matches("[1-4]") ) {
            System.out.println("Invalid input. Please try again.");
            str = scanner.nextLine();
        }

        switch (str){
            case "1":
                board.setPiece(x,y,new Rook(piecesColor));
                break;
            case "2":
                board.setPiece(x,y,new Knight(piecesColor));
                break;
            case "3":
                board.setPiece(x,y,new Bishop(piecesColor));
                break;
            case "4":
                board.setPiece(x,y,new Queen(piecesColor));
                break;

        }
    }
}