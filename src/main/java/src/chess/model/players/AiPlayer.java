package src.chess.model.players;

import src.chess.factory.Board;
import src.chess.model.pieces.Color;
import src.chess.model.pieces.Pieces;

import java.util.ArrayList;

public class AiPlayer extends Player{

    public AiPlayer(Board board, Color color) {
        super(board,color);
    }

}
