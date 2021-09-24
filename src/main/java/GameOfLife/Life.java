package GameOfLife;

public class Life {


    public void initializeGameBoard(Board board) {
        for (int i = 0; i < board.getRows(); i++) {
            for (int j = 0; j < board.getColumns(); j++) {
                int randomValue = (int) (Math.random() * 2);
                if (randomValue == 1)
                    board.setCell(i, j, 1);
            }
        }
    }

    public String[][] createGameBoardWithCells(Board board) {
        String[][] cellBoard = new String[board.getRows()][board.getColumns()];
        for (int i = 0; i < board.getRows(); i++) {
            for (int j = 0; j < board.getColumns(); j++) {
                if (board.getCell(i, j) == 0) {
                    cellBoard[i][j] = ".";
                } else {
                    cellBoard[i][j] = "0";
                }
            }
        }
        return cellBoard;
    }


    public void calculateNextGeneration(Board board, Board nextBoard) {
        for (int i = 0; i < board.getRows(); i++) {
            for (int j = 0; j < board.getColumns(); j++) {
                int neighbourCount = countNeighbours(i, j, board);
                if (board.getCell(i, j) == 1 && neighbourCount < 2) {
                    nextBoard.setCell(i, j, 0);
                } else if (board.getCell(i, j) == 1 && neighbourCount < 4) {
                    nextBoard.setCell(i, j, 1);
                } else if (board.getCell(i, j) == 1 && neighbourCount > 3) {
                    nextBoard.setCell(i, j, 0);
                } else if (board.getCell(i, j) == 0 && neighbourCount == 3) {
                    nextBoard.setCell(i, j, 1);
                } else {
                    nextBoard.setCell(i, j, 0);
                }
            }
        }
    }

    public int countNeighbours(int row, int column, Board board) {
        int count = 0;

        for (int i = row - 1; i <= row + 1; i++) {
            for (int j = column - 1; j <= column + 1; j++) {
                if (i >= 0 && i < board.getRows() &&
                        j >= 0 && j < board.getColumns() &&
                        !(i == row && j == column) && board.getCell(i, j) == 1) {
                    count++;
                }
            }
        }
        return count;
    }


    public void transferNextGenBoardToCurrentBoard(Board board, Board nextBoard) {
        for (int i = 0; i < board.getRows(); i++) {
            for (int j = 0; j < board.getColumns(); j++) {
                board.setCell(i, j, nextBoard.getCell(i, j));
            }
        }
    }

}
