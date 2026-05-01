package com.example.sudoku.model;

import java.util.Random;

public class GameModel {
    private int[][] board;
    private int[][] solution;
    private final int ROWS = 6;
    private final int COLS = 6;

    public GameModel() {
        board = new int[ROWS][COLS];
        solution = new int[ROWS][COLS];
    }

    public void generateNewGame() {
        fillBoard(0, 0);
        copyToSolution();
        removeNumbers();
    }

    private boolean fillBoard(int row, int col) {
        if (col == COLS) {
            row++;
            col = 0;
        }
        if (row == ROWS) return true;

        Integer[] nums = {1, 2, 3, 4, 5, 6};
        shuffleArray(nums);

        for (int num : nums) {
            if (isValidMove(row, col, num, solution)) {
                solution[row][col] = num;
                if (fillBoard(row, col + 1)) return true;
                solution[row][col] = 0;
            }
        }
        return false;
    }

    public boolean isValidMove(int row, int col, int num, int[][] grid) {
        // Row and Column check
        for (int i = 0; i < 6; i++) {
            if (grid[row][i] == num || grid[i][col] == num) return false;
        }
        // Block check (2x3)
        int boxRow = (row / 2) * 2;
        int boxCol = (col / 3) * 3;
        for (int i = boxRow; i < boxRow + 2; i++) {
            for (int j = boxCol; j < boxCol + 3; j++) {
                if (grid[i][j] == num) return false;
            }
        }
        return true;
    }

    private void removeNumbers() {
        // Requirements: 2 visible per row/col approx.
        Random rand = new Random();
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) board[i][j] = solution[i][j];
            int toRemove = 4; // Keeps 2 visible
            while (toRemove > 0) {
                int c = rand.nextInt(COLS);
                if (board[i][c] != 0) {
                    board[i][c] = 0;
                    toRemove--;
                }
            }
        }
    }

    private void copyToSolution() {
        for (int i = 0; i < ROWS; i++) {
            System.arraycopy(solution[i], 0, solution[i], 0, COLS);
        }
    }

    private void shuffleArray(Integer[] arr) {
        Random rand = new Random();
        for (int i = arr.length - 1; i > 0; i--) {
            int j = rand.nextInt(i + 1);
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }
    }

    public int getValue(int r, int c) { return board[r][c]; }
    public void setValue(int r, int c, int v) { board[r][c] = v; }
}