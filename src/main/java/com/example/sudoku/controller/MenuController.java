package com.example.sudoku.controller;

import com.example.sudoku.view.GameStage;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * Controller for the main menu.
 */
public class MenuController {

    /** Button to start a new game. */
    @FXML private Button btnPlay;

    /** Button to exit the application. */
    @FXML private Button btnExit;

    /**
     * Closes the application.
     */
    @FXML
    private void exitGame() {
        Stage stage = (Stage) btnExit.getScene().getWindow();
        stage.close();
    }

    /**
     * Initializes menu actions.
     */
    @FXML
    public void initialize(){

        btnPlay.setOnAction(e -> {

            Stage stage = (Stage) btnPlay.getScene().getWindow();

            try {
                new GameStage(stage);
            } catch (Exception ex) {
                ex.printStackTrace();
            }

        });
    }
}