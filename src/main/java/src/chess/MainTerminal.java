package src.chess;

import src.chess.factory.Board;
import src.chess.factory.StandartBoard;
import src.chess.game.GameManagement;
import src.chess.model.pieces.PiecesColor;
import src.chess.model.players.HumanPlayer;
import src.chess.model.players.Player;

public class MainTerminal {

    public static void main(String[] args) {

        Board board = new StandartBoard();
        Player white = new HumanPlayer(board, PiecesColor.WHITE,"White");
        Player black = new HumanPlayer(board, PiecesColor.BLACK,"Black");
        GameManagement gameManagement = new GameManagement(board,white,black);
        gameManagement.chess();

    }
}
