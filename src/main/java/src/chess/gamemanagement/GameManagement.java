package src.chess.gamemanagement;

import src.chess.factory.Board;
import src.chess.gamestatus.GameStatus;
import src.chess.gamestatus.GameStatusFx;
import src.chess.model.pieces.PiecesColor;
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
     * Based on the rounds, return the player
     * @return White if white turn or Black if black turn
     */
    public Player getPlayerBaseOnRound() {
        return getRounds() % 2 == 0 ? getBlack() : getWhite();
    }

    /**
     * Based on the rounds, return the player that is not currently playing.
     * Used for gameover()
     * @return Black if White turn or White if Black turn
     */
    public Player getPlayerBaseOnRoundReversed() {
        return getRounds() % 2 == 0 ? getWhite() : getBlack();
    }

    /**
     * @return True if one of the player is in Checkmate or the match is a draw
     */
    public boolean isGameOver(PiecesColor piecesColor) {

        GameStatus gameStatusFx = new GameStatusFx(board);

        return gameStatusFx.isCheckmate(piecesColor) || gameStatusFx.isDraw();

    }
}
