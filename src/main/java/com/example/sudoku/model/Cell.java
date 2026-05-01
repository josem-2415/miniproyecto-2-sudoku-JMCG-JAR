package com.example.sudoku.model;

public class Cell {
    private int row;
    private int column;
    private int value;
    private boolean fixed;

    public Cell(int row, int column, int value, boolean fixed) {
        this.row = row;
        this.column = column;
        this.value = value;
        this.fixed = fixed;
    }

    public int getRow() { return row; }
    public int getColumn() { return column; }
    public int getValue() { return value; }
    public void setValue(int value) { this.value = value; }
    public boolean isFixed() { return fixed; }
    public void setFixed(boolean fixed) { this.fixed = fixed; }
}
