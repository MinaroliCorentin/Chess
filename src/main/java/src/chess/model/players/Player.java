package src.chess.model.players;

import src.chess.factory.Board;
import src.chess.gameplay.Gameplay;
import src.chess.gameplay.GameplayFx;
import src.chess.gameplay.GameplayTerminal;
import src.chess.gamestatus.GameStatus;
import src.chess.gamestatus.GameStatusFx;
import src.chess.model.pieces.*;

public abstract class Player {

    private Board board;
    private PiecesColor piecesColor;
    private String playerName;
    private GameStatus gameStatus;

    public Player(Board board, PiecesColor piecesColor, String playerName,GameStatus gameStatus ) {

        this.board = board;
        this.piecesColor = piecesColor;
        this.playerName = playerName;
        this.gameStatus = gameStatus ;

    }

    /**
     * @return return player name
     */
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


    /**
     * use GameplayFx play
     * @param beginning Starting Localisation of the piece
     * @param ending Ending Localisation of the piece
     */
    public void play(String beginning, String ending) {

        Gameplay gameplay ;

        if (this.gameStatus instanceof GameStatusFx) {
            gameplay = new GameplayFx(board, piecesColor, gameStatus);
        } else {
            gameplay = new GameplayTerminal(board, piecesColor, gameStatus);
        }
        gameplay.play(beginning,ending);

    }

    /**
     * Player ToString
     * @return "Player : " + getColor()
     */
    public String toString(){

        return "Player : " + getColor() ;

    }

}
