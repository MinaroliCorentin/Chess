package src.chess;

import src.chess.Factory.Board;
import src.chess.Factory.StandartBoard;

public class MainTerminal {

    public static void main(String[] args) {

        Board board = new StandartBoard();
        board.display();


    }
}
