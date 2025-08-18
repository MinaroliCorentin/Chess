package src.chess.model.players;

import src.chess.factory.Board;
import src.chess.game.Gameplay;
import src.chess.model.handler.CastlingHandler;
import src.chess.model.pieces.*;
import src.chess.status.PiecesStatus;

import java.util.List;

public abstract class Player {

    private Board board;
    private Color color;

    public Player(Board board, Color color) {

        this.board = board;
        this.color = color;

    }

    /**
     * @return Getter for the color of the player, white or black
     */
    public Color getColor() {
        return color;
    }

    /**
     * Setter for the color of the player, white or black
     * @param color White or Black only. Defined by the enum
     */
    public void setColor(Color color) {
        this.color = color;
    }


    public void play(String beginning, String ending) {

        Gameplay gameplay = new Gameplay(board,color);
        gameplay.play(beginning,ending);

    }

    public String toString(){

        return "Player : " + getColor() ;

    }

}
