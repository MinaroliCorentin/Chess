package src.chess.factory;

import src.chess.model.pieces.*;

public abstract class Board {

    private Pieces[][] board;

    public Board() {

        this.board = new Pieces[8][8];
        this.BoardInitialize();

    }

    public abstract void BoardInitialize();

    public void reset(){
        for ( int i = 0 ; i < 8 ; i++){
            for ( int j = 0 ; j < 8 ; j++){
                this.board[i][j] = null;
            }
        }
        this.BoardInitialize();
    }

    public void setBoard(Pieces[][] board) {
        this.board = board;
    }

    public Pieces[][] getBoard() {
        return board;
    }

    public Pieces getPiece(int x, int y) {
        return board[x][y];
    }

    public void setPiece(int x, int y, Pieces piece) {
        board[x][y] = piece;
    }

    public void swapPiece(int x, int y, Pieces piece) {

        board[x][y] = null;
        board[x][y] = piece;

    }

    public boolean isEmpty(int x, int y) {
        return isBound(x,y) && board[x][y] == null;
    }

    public boolean isBound(int x, int y) {
        return x >= 0 && x < 8 && y >= 0 && y < 8;
    }

    public boolean hasOpponentPiece( int x, int y, Color color){
        return isBound(x,y) && board[x][y] != null && board[x][y].getColor() != color;
    }

    public void display() {
        for (int i = 0; i < 8; i++) {
            System.out.print((8 - i) + "    ");
            for (int j = 0; j < 8; j++) {
                Pieces piece = board[i][j];
                System.out.print(piece != null ? piece.getSymbol() + " " : ". ");
            }
            System.out.println();
        }
        System.out.println("     A B C D E F G H\n");
    }


    public void displayWithIndices() {

        for (int row = 0; row < 8; row++) {

            System.out.print(row + " | ");

            for (int col = 0; col < 8; col++) {
                Pieces piece = getPiece(row, col);
                if (piece == null) {
                    System.out.print(". ");
                } else {
                    System.out.print(piece.getSymbol() + " ");
                }
            }
            System.out.println();
        }

        System.out.print("    ");
        for (int col = 0; col < 8; col++) {
            System.out.print(col + " ");
        }
        System.out.println("\n");
    }

}
