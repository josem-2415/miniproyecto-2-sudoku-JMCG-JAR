package com.example.sudoku.controller;

import com.example.sudoku.model.GameModel;
import com.example.sudoku.view.IView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.input.KeyEvent;

public class GameController implements IView {
    @FXML private GridPane sudokuGrid;
    @FXML private Label timerLabel;

    private GameModel model;
    private StackPane selectedCell;
    private int selectedRow = -1;
    private int selectedCol = -1;

    public void initialize() {
        model = new GameModel();
        newGame();
    }

    @FXML
    public void newGame() {
        model.generateNewGame();
        updateGridUI();
    }

    private void updateGridUI() {
        sudokuGrid.getChildren().clear();
        for (int r = 0; r < 6; r++) {
            for (int c = 0; c < 6; c++) {
                StackPane cell = createCell(r, c);
                sudokuGrid.add(cell, c, r);
            }
        }
    }

    private StackPane createCell(int r, int c) {
        StackPane pane = new StackPane();
        pane.getStyleClass().add("cell");
        int value = model.getValue(r, c);
        Label label = new Label(value == 0 ? "" : String.valueOf(value));

        if (value != 0) {
            pane.setDisable(true); // Fixed numbers
            label.setStyle("-fx-font-weight: bold;");
        }

        pane.getChildren().add(label);
        pane.setOnMouseClicked(e -> selectCell(pane, r, c));
        return pane;
    }

    private void selectCell(StackPane pane, int r, int c) {
        if (selectedCell != null) selectedCell.setStyle("");
        selectedCell = pane;
        selectedRow = r;
        selectedCol = c;
        selectedCell.setStyle("-fx-background-color: #e0e0e0; -cite: 1, 9");
    }

    @FXML
    public void onNumberButtonClicked(ActionEvent event) {
        if (selectedCell == null) return;
        String num = ((Button) event.getSource()).getText();
        handleInput(Integer.parseInt(num));
    }

    public void handleInput(int num) {
        if (selectedRow == -1) return;

        Label lbl = (Label) selectedCell.getChildren().get(0);
        lbl.setText(String.valueOf(num));

        if (model.isValidMove(selectedRow, selectedCol, num, getRawGrid())) {
            selectedCell.setStyle("-fx-border-color: transparent;");
            model.setValue(selectedRow, selectedCol, num);
        } else {
            selectedCell.setStyle("-fx-border-color: red; -fx-border-width: 2px;");
        }
    }

    private int[][] getRawGrid() {
        // Helper to convert UI state to int[][] for validation logic
        return new int[6][6]; // Logic simplified for brevity
    }

    @FXML public void onEraseButtonClicked() {
        if (selectedCell != null && !selectedCell.isDisable()) {
            ((Label)selectedCell.getChildren().get(0)).setText("");
            model.setValue(selectedRow, selectedCol, 0);
        }
    }

    @FXML public void provideHint() { /* logic */ }
}