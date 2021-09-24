package GameOfLife;

import java.io.IOException;
import java.util.Scanner;

public class Game {

    private static void clearConsole(){
        try {
            if (System.getProperty("os.name").contains("Windows"))
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            else
                Runtime.getRuntime().exec("clear");

        } catch (IOException | InterruptedException ex) {}

    }

    private static void speedOfGenerations(int timeDelay){
        try{
            Thread.sleep(timeDelay);

        } catch(InterruptedException ex){
            Thread.currentThread().interrupt();
        }
    }

    public static void showGameBoard(String[][] cellBoard) {
        for (int i = 0; i < cellBoard.length + 2; i++) {
            System.out.print("_");
        }
        System.out.println();
        for (int i = 0; i < cellBoard.length; i++) {
            StringBuilder line = new StringBuilder("|");
            for (int j = 0; j < cellBoard[0].length; j++) {
                if (cellBoard[i][j].equals(".")) {
                    line.append(".");
                } else {
                    line.append("0");
                }
            }
            line.append("|");
            System.out.println(line);
        }
        for (int i = 0; i < cellBoard.length + 2; i++) {
            System.out.print("-");
        }
        System.out.println();

    }
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Hello and welcome to a Conway's GameOfLife.Game of GameOfLife.Life!");
        System.out.println("Start the game by initializing a gameboard with x amount of rows and x amount of columns");
        System.out.print("Enter amount of rows: ");
        int rows = input.nextInt();
        System.out.print("Enter amount of columns: ");
        int columns = input.nextInt();
        System.out.println("Good! Now enter the amount of generations you want to go through");
        int generations = input.nextInt();
        System.out.println("Now lastly enter the delay time between generations in microseconds");
        int timeDelay = input.nextInt();
        Life life = new Life();
        Board gameBoard = new Board(rows, columns);
        Board nextGenGameBoard = new Board(rows, columns);

        life.initializeGameBoard(gameBoard);
        String[][] cellBoard = life.createGameBoardWithCells(gameBoard);

        for(int i = 0; i < generations; i++){
            clearConsole();
            showGameBoard(cellBoard);
            speedOfGenerations(timeDelay);
            life.calculateNextGeneration(gameBoard, nextGenGameBoard);
            life.transferNextGenBoardToCurrentBoard(gameBoard, nextGenGameBoard);
            cellBoard = life.createGameBoardWithCells(gameBoard);
        }
        System.out.println("Finished!");
    }


}
