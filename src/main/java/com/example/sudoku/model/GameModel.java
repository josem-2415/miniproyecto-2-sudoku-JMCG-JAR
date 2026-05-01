package com.example.sudoku.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Modelo del juego Sudoku 6x6. Maneja la lógica y reglas sin dependencias de JavaFX.
 * Se utilizan ArrayList en lugar de matrices bidimensionales.
 */
public class GameModel {
    private static final int SIZE = 6;
    private static final int BLOCK_ROWS = 2;
    private static final int BLOCK_COLS = 3;

    private List<Cell> board;
    private List<Cell> solution;

    public GameModel() {
        board = new ArrayList<>();
        solution = new ArrayList<>();
        generateNewGame();
    }

    /**
     * Genera una nueva partida asegurando que cada bloque tenga exactamente 2 números visibles.
     */
    public void generateNewGame() {
        board.clear();
        solution.clear();

        for (int r = 0; r < SIZE; r++) {
            for (int c = 0; c < SIZE; c++) {
                solution.add(new Cell(r, c, 0, false));
                board.add(new Cell(r, c, 0, false));
            }
        }

        generateSolution(0, 0);
        generateInitialNumbers();
    }

    private boolean generateSolution(int row, int col) {
        if (row == SIZE) return true;

        int nextRow = (col == SIZE - 1) ? row + 1 : row;
        int nextCol = (col == SIZE - 1) ? 0 : col + 1;

        List<Integer> numbers = new ArrayList<>();
        for (int i = 1; i <= SIZE; i++) numbers.add(i);
        Collections.shuffle(numbers);

        for (int num : numbers) {
            if (canPlace(solution, row, col, num)) {
                getCell(solution, row, col).setValue(num);
                if (generateSolution(nextRow, nextCol)) return true;
                getCell(solution, row, col).setValue(0);
            }
        }
        return false;
    }

    private void generateInitialNumbers() {
        for (int blockR = 0; blockR < SIZE; blockR += BLOCK_ROWS) {
            for (int blockC = 0; blockC < SIZE; blockC += BLOCK_COLS) {
                List<Cell> blockCells = new ArrayList<>();
                for (int r = blockR; r < blockR + BLOCK_ROWS; r++) {
                    for (int c = blockC; c < blockC + BLOCK_COLS; c++) {
                        blockCells.add(getCell(solution, r, c));
                    }
                }
                Collections.shuffle(blockCells);

                // Set exactly 2 cells as fixed per block
                for (int i = 0; i < 2; i++) {
                    Cell c = blockCells.get(i);
                    Cell boardCell = getCell(board, c.getRow(), c.getColumn());
                    boardCell.setValue(c.getValue());
                    boardCell.setFixed(true);
                }
            }
        }
    }

    private Cell getCell(List<Cell> list, int row, int col) {
        for (Cell cell : list) {
            if (cell.getRow() == row && cell.getColumn() == col) {
                return cell;
            }
        }
        return null;
    }

    public boolean canPlace(List<Cell> checkBoard, int row, int col, int value) {
        for (Cell cell : checkBoard) {
            if (cell.getValue() == value && cell.getValue() != 0) {
                if (cell.getRow() == row || cell.getColumn() == col) return false;
                if ((cell.getRow() / BLOCK_ROWS == row / BLOCK_ROWS) &&
                        (cell.getColumn() / BLOCK_COLS == col / BLOCK_COLS)) return false;
            }
        }
        return true;
    }

    public boolean isValidMove(int row, int col, int value) {
        Cell cell = getCell(board, row, col);
        if (cell == null || cell.isFixed()) return false;

        int temp = cell.getValue();
        cell.setValue(0);
        boolean valid = canPlace(board, row, col, value);
        cell.setValue(temp);

        return valid;
    }

    public void setMove(int row, int col, int value) {
        Cell cell = getCell(board, row, col);
        if (cell != null && !cell.isFixed()) {
            cell.setValue(value);
        }
    }

    public Cell getHint() {
        for (Cell cell : board) {
            if (cell.getValue() == 0) {
                Cell solCell = getCell(solution, cell.getRow(), cell.getColumn());
                cell.setValue(solCell.getValue());
                cell.setFixed(true);
                return cell;
            }
        }
        return null;
    }

    public boolean checkWin() {
        for (Cell cell : board) {
            if (cell.getValue() == 0) return false;
            Cell solCell = getCell(solution, cell.getRow(), cell.getColumn());
            if (cell.getValue() != solCell.getValue()) return false;
        }
        return true;
    }

    public List<Cell> getBoard() { return board; }
}