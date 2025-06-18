package src.chess;

import src.chess.Factory.Board;
import src.chess.Factory.StandartBoard;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainTerminal {

    public static void main(String[] args) {

        Board board = new StandartBoard();
        board.display();


        int cores = Runtime.getRuntime().availableProcessors();
        System.out.println("Nombre de cœurs : " + cores);

        ExecutorService executor = Executors.newFixedThreadPool(cores);

        executor.submit(() -> {

        });
        executor.submit(() -> {

        });

        executor.shutdown();


    }
}
