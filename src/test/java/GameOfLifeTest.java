
import GameOfLife.Board;
import GameOfLife.Game;
import GameOfLife.Life;
import org.junit.Test;

import java.util.Scanner;

import static org.junit.Assert.*;

public class GameOfLifeTest {

    private static final int COLUMNS = 10;
    private static final int ROWS = 10;
    private static final Life LIFE = new Life();
    private static final int LIVING_CELL = 1;
    private static final int DEAD_CELL = 0;



    @Test
    public void testInitializeGameBoard() {
        Board bd = new Board(ROWS, COLUMNS);
        LIFE.initializeGameBoard(bd);
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                assertTrue(bd.getCell(i, j) == LIVING_CELL || bd.getCell(i, j) == DEAD_CELL);
            }
        }
    }

    @Test
    public void testCreateGameBoardWithCells() {
        Board bd = new Board(ROWS, COLUMNS);

        bd.setCell(0, 0, 1);
        bd.setCell(4, 4, 1);
        bd.setCell(5, 5, 1);
        bd.setCell(3, 5, 1);


        String[][] bc = LIFE.createGameBoardWithCells(bd);

        assertEquals("0", bc[0][0]);
        assertEquals(".", bc[1][2]);
        assertEquals("0", bc[4][4]);
        assertEquals("0", bc[5][5]);
        assertEquals(".", bc[3][3]);
        assertEquals("0", bc[3][5]);
        assertEquals(".", bc[2][5]);
    }


    @Test
    public void testCountNeighbours() {
        Board bd = new Board(ROWS, COLUMNS);
        bd.setCell(0, 0, 1);
        bd.setCell(1, 2, 1);
        bd.setCell(4, 4, 1);
        bd.setCell(5, 5, 1);
        bd.setCell(3, 3, 1);
        bd.setCell(3, 5, 1);
        bd.setCell(2, 5, 1);
        assertEquals(4, LIFE.countNeighbours(3, 4, bd));
    }


    @Test
    public void testCalculateNextGeneration() {
        Board bd = new Board(ROWS, COLUMNS);
        Board nextGenBd = new Board(ROWS, COLUMNS);
        bd.setCell(0, 0, 1);
        bd.setCell(0, 1, 1);
        bd.setCell(0,2, 1);

        bd.setCell(1,0, 1);
        bd.setCell(1,1, 1);
        bd.setCell(1,2, 1);

        bd.setCell(3, 2, 1);
        bd.setCell(4, 2, 1);
        bd.setCell(5, 2, 1);

        bd.setCell(6, 0, 1);

        bd.setCell(2, 6, 1);
        bd.setCell(2, 7, 1);
        bd.setCell(3, 7, 1);

        bd.setCell(7, 6, 1);
        bd.setCell(8, 6, 1);
        bd.setCell(9, 5, 1);

        LIFE.calculateNextGeneration(bd, nextGenBd);

        assertEquals(nextGenBd.getCell(0,0),1);
        assertEquals(nextGenBd.getCell(0,1),0);
        assertEquals(nextGenBd.getCell(0,2),1);
        assertEquals(nextGenBd.getCell(1,0),1);
        assertEquals(nextGenBd.getCell(1,1),0);
        assertEquals(nextGenBd.getCell(1,2),1);

        assertEquals(nextGenBd.getCell(2,7),1);
        assertEquals(nextGenBd.getCell(3,7),1);

        assertEquals(nextGenBd.getCell(8,6),1);

        assertEquals(nextGenBd.getCell(9,5),0);
        assertEquals(nextGenBd.getCell(6,0),0);




    }

    @Test
    public void testTransferToNextGeneration() {
        Board bd = new Board(ROWS, COLUMNS);
        Board nextGenBd = new Board(ROWS, COLUMNS);

        nextGenBd.setCell(0, 0, 1);
        nextGenBd.setCell(1, 2, 1);
        nextGenBd.setCell(4, 4, 1);
        nextGenBd.setCell(5, 5, 1);
        nextGenBd.setCell(3, 5, 1);
        nextGenBd.setCell(2, 5, 1);


        LIFE.transferNextGenBoardToCurrentBoard(bd, nextGenBd);

        assertEquals(bd.getRows(), nextGenBd.getRows());
        assertEquals(bd.getColumns(), nextGenBd.getColumns());
        assertEquals(bd.getCell(0, 0), 1);
        assertEquals(bd.getCell(1, 2), 1);
        assertEquals(bd.getCell(4, 4), 1);
        assertEquals(bd.getCell(5, 5), 1);
        assertEquals(bd.getCell(3, 3), 0);
        assertEquals(bd.getCell(3, 5), 1);
        assertEquals(bd.getCell(2, 5), 1);
        assertEquals(bd.getCell(7, 8), 0);
        assertEquals(bd.getCell(1, 5), 0);
        assertEquals(bd.getCell(9, 1), 0);
    }





}
