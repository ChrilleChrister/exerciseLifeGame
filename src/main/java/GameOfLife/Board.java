package GameOfLife;

public class Board {

    private int[][] gameBoard;

    public Board(int rows, int cols){
        this.gameBoard = new int[rows][cols];
    }

    public int getCell(int row, int col) {
        return  gameBoard[row][col];
    }

    public void setCell(int row, int col, int value) {
        gameBoard[row][col] = value;
    }

    public int getRows() {
        return gameBoard.length;
    }

    public int getColumns(){
        return gameBoard[0].length;
    }

}
