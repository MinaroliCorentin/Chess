package src.chess.Model.Players;

import src.chess.Factory.Board;
import src.chess.Model.pieces.Color;
import src.chess.Model.pieces.Localisation;
import src.chess.Model.pieces.Pieces;

import java.util.List;

public abstract class Player {

    private int score ;
    private Board board;
    private Color color;

    public Player(Board board, Color color) {

        this.score = 0;
        this.board = board;
        this.color = color;

    }

    public void setScore(int score){
        this.score = score;
    }

    public int getScore(){
        return score ;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public void evaluate(){

    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void play(String beginning, String ending) {

        char strA = beginning.toLowerCase().charAt(0);
        char strB = beginning.charAt(1);
        char strC = ending.toLowerCase().charAt(0);
        char strD = ending.charAt(1);

        if (strA < 'a' || strA > 'h'){
            throw new IllegalStateException("Out of bounds");
        }
        if (strC < 'a' || strC > 'h'){
            throw new IllegalStateException("Out of bounds");
        }
        if (strB < '1' || strB > '8'){
            throw new IllegalStateException("Out of bounds");
        }
        if (strD < '1' || strD > '8'){
            throw new IllegalStateException("Out of bounds");
        }

        int x = 8 - (strB - '0');
        int y = strA - 'a';
        int newX = 8 - (strD - '0');
        int newY = strC - 'a';

        if (!board.isBound(x, y) || !board.isBound(newX, newY)) {
            throw new IllegalArgumentException("Out of bounds");
        }

        Pieces movingPiece = board.getPiece(x, y);
        Pieces destinationPiece = board.getPiece(newX, newY);

        if (movingPiece == null) {
            throw new IllegalStateException("No piece at source location");
        }

        if (destinationPiece != null && movingPiece.getColor() == destinationPiece.getColor()) {
            throw new IllegalStateException("You can't attack your own piece");
        }

        if (movingPiece.getColor() != this.color) {
            throw new IllegalStateException("You can only move your own pieces");
        }

        List<Localisation> allMoves = movingPiece.movements(x, y, board);

        for (Localisation localisation : allMoves) {
            if (localisation.getX() == newX && localisation.getY() == newY) {
                board.setPiece(newX, newY, movingPiece);
                board.setPiece(x, y, null);
                return;
            }
        }
    }


    public String toString(){

        return "";

    }

}
