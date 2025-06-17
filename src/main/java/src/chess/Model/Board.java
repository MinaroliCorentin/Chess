package src.chess.Model;

import src.chess.Model.pieces.*;

public class Board {

    private Pieces[][] board;

    public Board() {

        this.board = new Pieces[8][8];

        this.board[0][0] = new Rook(Color.BLACK);
        this.board[0][1] = new Knight(Color.BLACK);
        this.board[0][2] = new Bishop(Color.BLACK);
        this.board[0][3] = new Queen(Color.BLACK);
        this.board[0][4] = new King(Color.BLACK);
        this.board[0][5] = new Bishop(Color.BLACK);
        this.board[0][6] = new Knight(Color.BLACK);
        this.board[0][7] = new Queen(Color.BLACK);

        for (int j = 0; j < 8; j++) {
            this.board[1][j] = new Pawn(Color.BLACK);
        }

        this.board[7][0] = new Rook(Color.WHITE);
        this.board[7][1] = new Knight(Color.WHITE);
        this.board[7][2] = new Bishop(Color.WHITE);
        this.board[7][3] = new Queen(Color.WHITE);
        this.board[7][4] = new King(Color.WHITE);
        this.board[7][5] = new Bishop(Color.WHITE);
        this.board[7][6] = new Knight(Color.WHITE);
        this.board[7][7] = new Queen(Color.WHITE);

        for (int j = 0; j < 8; j++) {
            this.board[6][j] = new Pawn(Color.WHITE);
        }

    }

    public Pieces[][] getBoard() {
        return board;
    }

    public Pieces getPiece(int x, int y) {
        return board[y][x];
    }

    public void display () {

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {

                Pieces temporaryPieces = board[i][j];
                if (temporaryPieces != null) {

                    if (temporaryPieces.isBishop()) {
                        System.out.print(temporaryPieces.getSymbol() + " ");
                    }  if (temporaryPieces.isKing()) {
                        System.out.print(temporaryPieces.getSymbol() + " ");
                    }  if (temporaryPieces.isQueen()) {
                        System.out.print(temporaryPieces.getSymbol() + " ");
                    }  if (temporaryPieces.isPawn()) {
                        System.out.print(temporaryPieces.getSymbol() + " ");
                    }  if (temporaryPieces.isRook()) {
                        System.out.print(temporaryPieces.getSymbol() + " ");
                    } if (temporaryPieces.isKnigth()) {
                        System.out.print(temporaryPieces.getSymbol() + " ");
                    }

                } else {
                    System.out.print("");
                }
            }
            System.out.println();
        }
    }
}
