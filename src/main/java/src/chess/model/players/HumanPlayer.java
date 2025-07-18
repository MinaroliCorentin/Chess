package src.chess.model.players;

import src.chess.factory.Board;
import src.chess.model.pieces.Color;
import src.chess.model.pieces.Pieces;

import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;

public class HumanPlayer extends Player {

    public HumanPlayer(Board board, Color color) {
        super(board,color);
    }

}
