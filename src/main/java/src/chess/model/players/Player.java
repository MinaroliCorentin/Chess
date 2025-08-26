package src.chess.model.players;

import src.chess.factory.Board;
import src.chess.game.Gameplay;
import src.chess.model.pieces.*;

public abstract class Player {

    private Board board;
    private PiecesColor piecesColor;
    private String playerName;

    public Player(Board board, PiecesColor piecesColor, String playerName) {

        this.board = board;
        this.piecesColor = piecesColor;
        this.playerName = playerName;

    }

    public String getPlayerName() {
        return playerName;
    }

    /**
     * @return Getter for the piecesColor of the player, white or black
     */
    public PiecesColor getColor() {
        return piecesColor;
    }

    /**
     * Setter for the piecesColor of the player, white or black
     * @param piecesColor White or Black only. Defined by the enum
     */
    public void setColor(PiecesColor piecesColor) {
        this.piecesColor = piecesColor;
    }


    public void play(String beginning, String ending) {

        Gameplay gameplay = new Gameplay(board, piecesColor);
        gameplay.play(beginning,ending);

    }

    public String toString(){

        return "Player : " + getColor() ;

    }

}
