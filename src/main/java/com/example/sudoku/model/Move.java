package com.example.sudoku.model;

public class Move {

    private int row;
    private int column;

    private int previousValue;
    private int newValue;

    public Move(
            int row,
            int column,
            int previousValue,
            int newValue
    ) {

        this.row = row;
        this.column = column;
        this.previousValue = previousValue;
        this.newValue = newValue;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public int getPreviousValue() {
        return previousValue;
    }

    public int getNewValue() {
        return newValue;
    }
}