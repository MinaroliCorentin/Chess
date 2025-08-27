package src.chess.GameManagement;

import src.chess.factory.Board;
import src.chess.model.players.Player;

public abstract class GameManagement {

    private Board board;
    private Player white;
    private Player black;
    private int rounds;

    public GameManagement(Board board, Player white, Player black) {
        this.board = board;
        this.white = white;
        this.black = black;
        this.rounds = 0;
    }

    public Board getBoard() {
        return board;
    }

    public int getRounds() {
        return rounds;
    }

    public Player getWhite() {
        return white;
    }

    public Player getBlack() {
        return black;
    }

    public void setRounds(int rounds) {
        this.rounds = rounds;
    }

    public String getPlayerNameBasedOnRound() {
        return (rounds % 2 == 0) ? white.getPlayerName() : black.getPlayerName();
    }

}
