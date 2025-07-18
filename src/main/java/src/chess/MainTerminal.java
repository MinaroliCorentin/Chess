package src.chess;

import src.chess.factory.Board;
import src.chess.factory.StandartBoard;

public class MainTerminal {

    public static void main(String[] args) {

        Board board = new StandartBoard();
        board.display();


    }
}
