package com.example.sudoku.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class PauseController {

    private Runnable onResume;

    @FXML
    private Button resumeButton;

    public void setOnResume(Runnable onResume) {
        this.onResume = onResume;
    }

    @FXML
    public void handleResume(ActionEvent event) {

        if (onResume != null) {
            onResume.run();
        }

        Stage stage =
                (Stage) resumeButton.getScene().getWindow();

        stage.close();
    }
}