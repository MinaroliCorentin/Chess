package src.chess.factory;

import src.chess.model.pieces.*;

public abstract class Board {

    private Pieces[][] board;

    /**
     * Standart board
     */
    public Board() {

        this.board = new Pieces[8][8];
        this.BoardInitialize();

    }

    /**
     * Create the board
     */
    public abstract void BoardInitialize();

    /**
     * Reset the board
     */
    public void reset(){
        for ( int i = 0 ; i < 8 ; i++){
            for ( int j = 0 ; j < 8 ; j++){
                this.board[i][j] = null;
            }
        }
        this.BoardInitialize();
    }

    /**
     * Set a piece on the board
     * @param board the desire board
     */
    public void setBoard(Pieces[][] board) {
        this.board = board;
    }

    /**
     * Getter board
     * @return Return the board
     */
    public Pieces[][] getBoard() {
        return board;
    }

    /**
     * Getter piece
     * @param x col
     * @param y row
     * @return the piece at x,y
     */
    public Pieces getPiece(int x, int y) {
        return board[x][y];
    }

    /**
     * Set a piece at the desire col/row
     * @param x col
     * @param y row
     * @param piece Piece
     */
    public void setPiece(int x, int y, Pieces piece) {
        board[x][y] = piece;
    }

    /**
     * Delete the piece at x,y and swap it with the new piece
     * @param x col
     * @param y row
     * @param piece the new piece
     */
    public void swapPiece(int x, int y, Pieces piece) {

        board[x][y] = null;
        board[x][y] = piece;

    }

    /**
     * verify is col/row isBound and if the coordonnate are null
     * @param x col
     * @param y row
     * @return boolean, true if the col/row is null, else false
     */
    public boolean isEmpty(int x, int y) {
        return isBound(x,y) && board[x][y] == null;
    }

    /**
     *
     * @param x col
     * @param y row
     * @return return true if col/row are in bound of the board
     */
    public boolean isBound(int x, int y) {
        return x >= 0 && x < 8 && y >= 0 && y < 8;
    }

    /**
     * @param x col
     * @param y row
     * @param color ennemy color
     * @return return true if col/row is in Bound, not null and if the col/row color is different of the param color
     */
    public boolean hasOpponentPiece( int x, int y, Color color){
        return isBound(x,y) && board[x][y] != null && board[x][y].getColor() != color;
    }

    /**
     * Used to display the board if the standard coordonates
     */
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

    /**
     * Used to display the board with the IT indices
     */
    public void displayWithIndices() {

        for (int row = 0; row < 8; row++) {
            System.out.print(row + " | ");
            for (int col = 0; col < 8; col++) {
                Pieces piece = getPiece(row, col);
                System.out.print(piece != null ? piece.getSymbol() + " " : ". ");
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
