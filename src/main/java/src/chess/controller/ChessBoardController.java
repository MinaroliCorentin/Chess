package src.chess.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import src.chess.factory.Board;
import src.chess.factory.StandartBoard;
import src.chess.game.Gameplay;
import src.chess.model.pieces.Localisation;
import src.chess.model.pieces.Pieces;
import src.chess.model.pieces.PiecesColor;

import java.util.List;

public class ChessBoardController implements Observer {

    @FXML
    private GridPane chessGrid;

    private Board board;
    private StackPane[][] cells = new StackPane[8][8];

    private int fromRow = -1;
    private int fromCol = -1;

    private int toRow = -1 ;
    private int toCol = -1;

    @FXML
    public void initialize() {

        board = new StandartBoard();
        initializeBoard();
        board.addObserver(this);

    }

    private void initializeBoard() {

        chessGrid.getChildren().clear();
        Pieces[][] piecesArray = board.getBoard();
        int counter = 0;

        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {

                StackPane cell = new StackPane();
                cell.setPrefSize(120, 120);
                Color baseColor = (counter % 2 == 0) ? Color.BURLYWOOD : Color.SADDLEBROWN;
                cell.setBackground(new Background(new BackgroundFill(baseColor, null, null)));

                Pieces piece = piecesArray[row][col];
                if (piece != null) {
                    Label label = new Label(piece.getSymbol());
                    label.setFont(new Font(30));
                    cell.getChildren().add(label);
                }

                final int r = row;
                final int c = col;
                cell.setOnMouseClicked(event -> handleLeftClick(r, c));

                cells[row][col] = cell;
                chessGrid.add(cell, col, row);

                counter++;
            }
            counter++;
        }
    }

    private void handleLeftClick(int row, int col) {

        Pieces clickedPiece = board.getPiece(row, col);

        System.out.println("AVANT : fromRow : " + fromRow + " fromCol : " + fromCol + " toRow : " + toRow + " toCol : " + toCol);

        // No piece selected
        if (fromRow == -1 && fromCol == -1) {
            if (clickedPiece != null) {
                fromRow = row;
                fromCol = col;
                highlightCell(row, col);
                pieceMovement(row, col);
            }
            return;
        }

        // User click on the piece he already clicked on
        if (row == fromRow && col == fromCol) {
            applyBoardDefaultColor(row,col);
            resetFromTo();
            return;
        }

        // Select another piece
        if (clickedPiece != null && clickedPiece.getColor() == board.getPiece(fromRow, fromCol).getColor()) {
            applyBoardDefaultColor(fromRow, fromCol);
            resetFromTo();
            fromRow = row;
            fromCol = col;
            highlightCell(row, col);
            pieceMovement(row, col);
            return;
        }

        // User already selected a piece and want to move it
        if (fromRow != -1 && fromCol != -1 && toRow == -1 && toCol == -1) {
            Pieces pieces = board.getPiece(fromRow,fromCol);
            this.toRow = row ;
            this.toCol = col ;
            System.out.println("TEST1");
            playMove(pieces.getColor());
        }

    }

    public String toChessNotation(int row, int col) {
        char file = (char) ('a' + col);
        char rank = (char) ('0' + (8 - row));
        return "" + file + rank;
    }

    public void playMove(PiecesColor piecesColor) {

        Gameplay gameplay = new Gameplay(board, piecesColor);
        String beginning = toChessNotation(fromRow,fromCol);
        System.out.println("beginning : " + beginning);
        String ending = toChessNotation(toRow,toCol);
        System.out.println("ending " + ending);
        gameplay.play(beginning,ending);
        System.out.println("APRES : fromRow : " + fromRow + " fromCol : " + fromCol + " toRow : " + toRow + " toCol : " + toCol);
        resetFromTo();
        System.out.println("TEST2");
        board.notifyObservers();

    }

    public void resetFromTo(){

        this.fromRow = -1;
        this.fromCol = -1;
        this.toRow = -1;
        this.toCol = -1;

    }

    public void highlightCell(int row, int col) {

        StackPane cell = cells[row][col];
        cell.setBackground(new Background(new BackgroundFill(Color.YELLOW, null, null)));

    }

    private void applyBoardDefaultColor(int row, int col) {

        StackPane cell ;
        int sum ;
        Color baseColor ;

        if ( board.getPiece(row, col) != null ) {

            List<Localisation> cleaningCells = board.getPiece(row,col).movements(row,col,board);
            cleaningCells.add(new Localisation(row,col));

            for ( Localisation loc : cleaningCells ) {
                cell = cells[loc.getX()][loc.getY()];
                sum = loc.getX() + loc.getY();
                baseColor = (sum % 2 == 0) ? Color.BURLYWOOD : Color.SADDLEBROWN;
                cell.setBackground(new Background(new BackgroundFill(baseColor, null, null)));
            }
        }
    }

    private void pieceMovement(int x, int y){

        Pieces piece = board.getBoard()[x][y];

        if ( piece == null) return ;

        List<Localisation> moves = piece.movements(x,y,board);
        for ( Localisation loc : moves){
            StackPane cell = cells[loc.getX()][loc.getY()];
            cell.setBackground(new Background(new BackgroundFill(Color.RED,null,null)));
        }

    }

    @Override
    public void react() {

        System.out.println("TEST3");
        initializeBoard();

    }
}