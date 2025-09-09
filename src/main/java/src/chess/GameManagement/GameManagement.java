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

    /**
     * Getter board
     * @return Board
     */
    public Board getBoard() {
        return board;
    }

    /**
     * Getter rounds
     * @return Rounds
     */
    public int getRounds() {
        return rounds;
    }

    /**
     * Getter White player
     * @return White
     */
    public Player getWhite() {
        return white;
    }

    /**
     * Getter Black player
     * @return Black
     */
    public Player getBlack() {
        return black;
    }

    /**
     * Setter Rounds
     * @param rounds rounds
     */
    public void setRounds(int rounds) {
        this.rounds = rounds;
    }

    /**
     * Based on the rounds, return the player name
     * @return Player name
     */
    public String getPlayerNameBasedOnRound() {
        return (rounds % 2 == 0) ? white.getPlayerName() : black.getPlayerName();
    }

    /**
     * Based of the rounds, return the player
     * @return Player white or black
     */
    public Player getPlayerBaseOnRound() {
        return getRounds() % 2 == 0 ? getBlack() : getWhite();
    }

}
