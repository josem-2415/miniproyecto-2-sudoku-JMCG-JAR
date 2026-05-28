package com.example.sudoku.controller;

import com.example.sudoku.model.Cell;
import com.example.sudoku.model.GameModel;
import com.example.sudoku.view.GameStage;
import com.example.sudoku.view.MenuStage;
import com.example.sudoku.view.PauseStage;
import javafx.animation.AnimationTimer;
import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.util.Duration;
import com.example.sudoku.model.Move;
import java.util.Stack;
import java.util.HashMap;
import java.util.Map;

public class GameController {

    @FXML
    private GridPane sudokuGrid;
    @FXML
    private Label timerLabel;

    private GameModel model;
    private Button selectedButton = null;
    private Map<Button, Cell> buttonCellMap;

    private long startTime;
    private AnimationTimer timer;
    private boolean paused = false;
    private long pausedTime = 0;
    private Stack<Move> moveHistory;

    @FXML
    public void initialize() {
        model = new GameModel();
        buttonCellMap = new HashMap<>();
        moveHistory = new Stack<>();
        setupGrid();
        startTimer();

        // Manejar eventos de teclado a nivel del contenedor principal
        sudokuGrid.sceneProperty().addListener((obs, oldScene, newScene) -> {
            if (newScene != null) {
                newScene.setOnKeyPressed(this::handleKeyPress);
            }
        });
    }

    private void setupGrid() {
        sudokuGrid.getChildren().clear();
        for (Cell cell : model.getBoard()) {
            Button btn = new Button();
            btn.setPrefSize(66, 66);
            btn.getStyleClass().add("sudoku-cell");

            if (cell.getValue() != 0) {
                btn.setText(String.valueOf(cell.getValue()));

                if (cell.isFixed()) {
                    btn.setStyle(
                            "-fx-font-weight: bold; " +
                                    "-fx-text-fill: black;"
                    );
                } else {
                    btn.setStyle(
                            "-fx-font-weight: normal; " +
                                    "-fx-text-fill: #1f3252;"
                    );
                }
            }

            btn.setOnMouseClicked(e -> handleCellSelection(e, btn));
            buttonCellMap.put(btn, cell);
            sudokuGrid.add(btn, cell.getColumn(), cell.getRow());
        }
    }

    private void handleCellSelection(MouseEvent event, Button btn) {

        selectedButton = btn;

        Cell selectedCell =
                buttonCellMap.get(selectedButton);

        highlightRelatedCells(selectedCell);
    }

    private void highlightRelatedCells(Cell selectedCell) {

        // Limpiar estilos anteriores
        for (Button button : buttonCellMap.keySet()) {

            button.getStyleClass().removeAll(
                    "selected-cell",
                    "related-cell",
                    "same-number-cell"
            );
        }

        int selectedValue = selectedCell.getValue();

        for (Map.Entry<Button, Cell> entry : buttonCellMap.entrySet()) {

            Button button = entry.getKey();

            Cell cell = entry.getValue();

            // MISMA FILA
            boolean sameRow =
                    cell.getRow() == selectedCell.getRow();

            // MISMA COLUMNA
            boolean sameColumn =
                    cell.getColumn() == selectedCell.getColumn();

            // MISMO BLOQUE
            boolean sameBlock =
                    (cell.getRow() / 2 ==
                            selectedCell.getRow() / 2)
                            &&
                            (cell.getColumn() / 3 ==
                                    selectedCell.getColumn() / 3);

            // Agregar sombreado relacionado
            if (sameRow || sameColumn || sameBlock) {

                button.getStyleClass().add("related-cell");
            }

            // MISMO NÚMERO
            if (
                    selectedValue != 0
                            &&
                            cell.getValue() == selectedValue
            ) {

                button.getStyleClass().add("same-number-cell");
            }
        }

        // Celda seleccionada encima de todo
        selectedButton.getStyleClass().add("selected-cell");
    }

    private void handleKeyPress(KeyEvent event) {
        if (selectedButton == null) return;
        Cell cell = buttonCellMap.get(selectedButton);
        if (cell.isFixed()) return;

        String key = event.getText();
        if (key.matches("[1-6]")) {
            processInput(Integer.parseInt(key));
        } else if (event.getCode().toString().equals("BACK_SPACE") || event.getCode().toString().equals("DELETE")) {
            processInput(0);
        }
    }

    @FXML
    public void handleKeypadInput(ActionEvent event) {
        if (selectedButton == null) return;
        Button clickedBtn = (Button) event.getSource();
        String text = clickedBtn.getText();
        if (text.matches("[1-6]")) {
            processInput(Integer.parseInt(text));
        }
    }

    private void processInput(int value) {

        Cell cell = buttonCellMap.get(selectedButton);
        int previousValue = cell.getValue();

        // No modificar celdas fijas
        if (cell.isFixed()) return;

        if (value == 0) {
            moveHistory.push(
                    new Move(
                            cell.getRow(),
                            cell.getColumn(),
                            previousValue,
                            0
                    )
            );
            model.setMove(cell.getRow(), cell.getColumn(), 0);
            selectedButton.setText("");
            return;
        }

        if (model.isValidMove(cell.getRow(), cell.getColumn(), value)) {
            moveHistory.push(
                    new Move(
                            cell.getRow(),
                            cell.getColumn(),
                            previousValue,
                            value
                    )
            );
            model.setMove(cell.getRow(), cell.getColumn(), value);
            selectedButton.setText(String.valueOf(value));

            selectedButton.setStyle(
                    "-fx-font-weight: normal; " +
                            "-fx-text-fill: #1f3252;"
            );

            highlightRelatedCells(cell);

            checkVictory();

        } else {
            Button currentButton = selectedButton;

            currentButton.setStyle(
                    "-fx-border-color: red; " +
                            "-fx-border-width: 2px; " +
                            "-fx-background-color: #ffcccc;"
            );

            PauseTransition pause = new PauseTransition(Duration.seconds(3));

            pause.setOnFinished(e -> {

                updateCellVisuals(currentButton);
            });

            pause.play();
        }

    }

    @FXML
    public void handleHint(ActionEvent event) {
        Cell hintCell = model.getHint();
        if (hintCell != null) {
            for (Map.Entry<Button, Cell> entry : buttonCellMap.entrySet()) {
                if (entry.getValue() == hintCell) {
                    entry.getKey().setText(String.valueOf(hintCell.getValue()));
                    entry.getKey().setStyle("-fx-text-fill: green; -fx-font-weight: bold;");
                    checkVictory();
                    break;
                }
            }
        }
    }

    @FXML
    public void handleNewGame(ActionEvent event) {
        model.generateNewGame();
        selectedButton = null;
        setupGrid();
        startTimer();
    }

    private void checkVictory() {

        if (model.checkWin()) {

            timer.stop();

            String finalTime =
                    timerLabel.getText();

            Alert alert =
                    new Alert(Alert.AlertType.INFORMATION);

            alert.setTitle("Sudoku Completed");

            alert.setHeaderText("¡Felicidades!");

            alert.setContentText(
                    "Has completado el Sudoku.\n\n" +
                            "Tiempo final: " + finalTime
            );

            alert.showAndWait();
        }
    }

    private void startTimer() {

        if (timer != null) {
            timer.stop();
        }

        startTime = System.currentTimeMillis();

        timer = new AnimationTimer() {

            @Override
            public void handle(long now) {

                if (!paused) {

                    long elapsedMillis =
                            Math.max(
                                    0,
                                    System.currentTimeMillis()
                                            - startTime
                                            - pausedTime
                            );

                    long seconds =
                            (elapsedMillis / 1000) % 60;

                    long minutes =
                            (elapsedMillis / (1000 * 60)) % 60;

                    timerLabel.setText(
                            String.format(
                                    "%02d:%02d",
                                    minutes,
                                    seconds
                            )
                    );
                }
            }
        };

        timer.start();
    }

    private void updateCellVisuals(Button btn) {

        Cell cell = buttonCellMap.get(btn);

        btn.setStyle("");

        if (cell.isFixed()) {

            btn.setStyle(
                    "-fx-font-weight: bold;" +
                            "-fx-text-fill: #4e6073;"
            );

        } else {

            btn.setStyle(
                    "-fx-font-weight: normal;" +
                            "-fx-text-fill: #1f3252;"
            );
        }
    }

    @FXML
    public void handleErase(ActionEvent event) {

        if (selectedButton == null) return;

        Cell cell = buttonCellMap.get(selectedButton);

        // No permitir borrar celdas iniciales
        if (cell.isFixed()) return;

        processInput(0);
    }

    @FXML
    public void handleReturnMenu(ActionEvent event) {

        GameStage.getInstance().closeWindow();

        MenuStage.getInstance().showWindow();
    }

    @FXML
    public void handlePause(ActionEvent event) {

        if (paused) {
            return;
        }

        paused = true;

        long pauseStart =
                System.currentTimeMillis();

        PauseStage pauseStage =
                new PauseStage(() -> {

                    paused = false;

                    pausedTime +=
                            System.currentTimeMillis()
                                    - pauseStart;
                });

        pauseStage.showAndWait();
    }

    @FXML
    public void handleUndo(ActionEvent event) {

        if (moveHistory.isEmpty()) {
            return;
        }

        Move lastMove = moveHistory.pop();

        for (Map.Entry<Button, Cell> entry
                : buttonCellMap.entrySet()) {

            Cell cell = entry.getValue();

            if (cell.getRow() == lastMove.getRow()
                    &&
                    cell.getColumn() == lastMove.getColumn()) {

                Button btn = entry.getKey();

                model.setMove(
                        cell.getRow(),
                        cell.getColumn(),
                        lastMove.getPreviousValue()
                );

                if (lastMove.getPreviousValue() == 0) {

                    btn.setText("");

                } else {

                    btn.setText(
                            String.valueOf(
                                    lastMove.getPreviousValue()
                            )
                    );
                }

                updateCellVisuals(btn);

                break;
            }
        }
    }
}