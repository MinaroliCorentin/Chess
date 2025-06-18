package src.chess.Factory;

import src.chess.Model.pieces.*;

public abstract class Board {

    private Pieces[][] board;

    public Board() {

        this.board = new Pieces[8][8];
        this.BoardInitialize();

    }

    public abstract void BoardInitialize();

    public void setBoard(Pieces[][] board) {
        this.board = board;
    }

    public Pieces[][] getBoard() {
        return board;
    }

    public Pieces getPiece(int x, int y) {
        return board[y][x];
    }

    public boolean isEmpty(int x, int y) {
        return isBound(x,y) && board[y][x] == null;
    }

    public boolean isBound(int x, int y) {
        return x >= 0 && x < 8 && y >= 0 && y < 8;
    }

    public boolean hasOpponentPiece( int x, int y, Color color){
        return isBound(x,y) && board[x][y] != null && board[x][y].getColor() != color;
    }

    public void setPiece(int x, int y,Pieces piece) {
        board[y][x] = piece;
    }

    public void display () {

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {

                Pieces temporaryPieces = board[i][j];
                if (temporaryPieces != null) {
                    System.out.print(temporaryPieces.getSymbol() + " ");
                } else {
                    System.out.print(". ");
                }
            }
            System.out.println();
        }
    }
}
