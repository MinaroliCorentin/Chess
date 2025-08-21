package src.chess.controller;


import javafx.fxml.FXML;
import javafx.scene.control.Cell;
import javafx.scene.control.Label;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import src.chess.factory.Board;
import src.chess.factory.StandartBoard;
import src.chess.model.pieces.Localisation;
import src.chess.model.pieces.Pieces;

import java.util.LinkedList;
import java.util.List;

public class ChessBoardController implements Observer {

    @FXML
    private GridPane chessGrid;

    private Board board;
    private StackPane[][] cells = new StackPane[8][8];

    private int selectedRow = -1;
    private int selectedCol = -1;

    @FXML
    public void initialize() {
        board = new StandartBoard();
        initializeBoard();
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

        if (selectedRow == row && selectedCol == col) {
            resetCellColor(row, col);
            selectedRow = -1;
            selectedCol = -1;
            return;
        }

        if (selectedRow != -1 && selectedCol != -1) {
            resetCellColor(selectedRow, selectedCol);
        }

        selectedRow = row;
        selectedCol = col;
        highlightCell(row, col);
    }

    private void highlightCell(int row, int col) {

        StackPane cell = cells[row][col];
        cell.setBackground(new Background(new BackgroundFill(Color.YELLOW, null, null)));

    }

    private void resetCellColor(int row, int col) {

        StackPane cell = cells[row][col];
        int sum = row + col;
        Color baseColor = (sum % 2 == 0) ? Color.BURLYWOOD : Color.SADDLEBROWN;
        cell.setBackground(new Background(new BackgroundFill(baseColor, null, null)));

    }

    @Override
    public void react() {



    }
}