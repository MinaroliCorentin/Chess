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
     * @param color color of the pawn
     */
    public void promotion(int x, int y, Color color){

        Scanner scanner =  new Scanner(System.in);
        System.out.println(
                """
                A promotion has been detected
                Please select your new Pieces : 
                1. Rook
                2. Knigth 
                3. Bishop
                4. Queen
                """
        );
        String str = scanner.nextLine();

        switch (str){
            case "1":
                board.swapPiece(x,y,new Rook(color));
                break;
            case "2":
                board.swapPiece(x,y,new Knight(color));
                break;
            case "3":
                board.swapPiece(x,y,new Bishop(color));
                break;
            case "4":
                board.swapPiece(x,y,new Queen(color));
                break;
            default:

        }
    }
}