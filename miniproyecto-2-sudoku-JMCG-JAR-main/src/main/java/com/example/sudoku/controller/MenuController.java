package com.example.sudoku.controller;

import com.example.sudoku.view.GameStage;
import javafx.fxml.FXML;
import javafx.stage.Stage;
import javafx.scene.control.Button;

public class MenuController {
    @FXML private Button btnPlay;

    @FXML
    public void initialize() {
        // En tu FXML, el botón Play llama a initialize(),
        // lo ideal es que llame a handlePlay()
    }

    @FXML
    public void handlePlay() {
        GameStage.getInstance();
        ((Stage) btnPlay.getScene().getWindow()).close();
    }

    @FXML
    public void exitGame() {
        System.exit(0);
    }
}