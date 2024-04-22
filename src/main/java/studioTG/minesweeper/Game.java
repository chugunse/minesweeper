package studioTG.minesweeper;

import lombok.Getter;

import java.util.Random;
import java.util.UUID;

public class Game {
    @Getter
    private final String id;
    @Getter
    private final int rows;
    @Getter
    private final int cols;
    @Getter
    private final int mines;
    private final int[][] board;
    private final Random random;
    @Getter
    private final String[][] field;
    @Getter
    private boolean completed;
    private int count;

    public Game(int rows, int cols, int mines) {
        this.id = UUID.randomUUID().toString();
        this.rows = rows;
        this.cols = cols;
        this.mines = mines;
        this.field = new String[rows][cols];
        this.board = new int[rows][cols];
        this.random = new Random();
        this.completed = false;
        this.count = rows * cols - mines;
        initBoard();
        initField();
    }

    private void initBoard() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                board[i][j] = 0;
            }
        }
        initMines();
        countСells();
    }

    private void initMines() {
        int count = 0;
        while (count < mines) {
            int x = random.nextInt(rows);
            int y = random.nextInt(cols);
            if (board[x][y] == 0) {
                board[x][y] = -1;
                count++;
            }
        }
    }

    private void countСells() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (board[i][j] == -1) {
                    continue;
                }
                for (int k = -1; k <= 1; k++) {
                    for (int l = -1; l <= 1; l++) {
                        if (i + k < 0 || i + k >= rows || j + l < 0 || j + l >= cols) {
                            continue;
                        }
                        if (board[i + k][j + l] == -1) {
                            board[i][j]++;
                        }
                    }
                }
            }
        }
    }

    private void initField() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                field[i][j] = " ";
            }
        }
    }

    public String[][] turn(int row, int col) {
        if (board[row][col] == -1) {
            openAllCells("X");
            completed = true;
        } else if (board[row][col] == 0) {
            openAdjacentCells(row, col);
        } else {
            field[row][col] = String.valueOf(board[row][col]);
            count--;
        }
        if (count == 0) {
            openAllCells("M");
            completed = true;
        }
        return field;
    }

    private void openAdjacentCells(int row, int col) {
        if (row < 0 || row >= rows || col < 0 || col >= cols
                || !field[row][col].equals(" ")
                || board[row][col] == -1) {
            return;
        }
        field[row][col] = String.valueOf(board[row][col]);
        count--;
        if (board[row][col] > 0) {
            return;
        }
        openAdjacentCells(row - 1, col - 1);
        openAdjacentCells(row - 1, col);
        openAdjacentCells(row - 1, col + 1);
        openAdjacentCells(row, col - 1);
        openAdjacentCells(row, col + 1);
        openAdjacentCells(row + 1, col - 1);
        openAdjacentCells(row + 1, col);
        openAdjacentCells(row + 1, col + 1);
    }

    private void openAllCells(String letter) {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (field[i][j].equals(" ")) {
                    if (board[i][j] == -1) {
                        field[i][j] = letter;
                    } else {
                        field[i][j] = String.valueOf(board[i][j]);
                    }
                }
            }
        }
    }
}

