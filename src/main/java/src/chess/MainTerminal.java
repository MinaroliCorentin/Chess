package src.chess;

import src.chess.factory.Board;
import src.chess.factory.StandartBoard;
import src.chess.gamemanagement.GameManagementTerminal;
import src.chess.gamestatus.GameStatus;
import src.chess.gamestatus.GameStatusTerminal;
import src.chess.model.pieces.PiecesColor;
import src.chess.model.players.HumanPlayer;
import src.chess.model.players.Player;

public class MainTerminal {

    public static void main(String[] args) {

        Board board = new StandartBoard();
        GameStatus gameStatusTerminal = new GameStatusTerminal(board);
        Player white = new HumanPlayer(board, PiecesColor.WHITE,"White",gameStatusTerminal);
        Player black = new HumanPlayer(board, PiecesColor.BLACK,"Black",gameStatusTerminal);
        GameManagementTerminal gameManagementTerminal = new GameManagementTerminal(board,white,black,gameStatusTerminal);
        gameManagementTerminal.start();

    }
}
