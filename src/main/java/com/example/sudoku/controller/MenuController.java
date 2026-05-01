package com.example.sudoku.controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import com.example.sudoku.view.GameStage;
import com.example.sudoku.view.MenuStage;

public class MenuController {

    @FXML
    public void initialize() {
    }

    public void startGame() {
        MenuStage.getInstance().closeWindow();
        GameStage.resetInstance();
        GameStage.getInstance().showWindow();
    }
    @FXML
    public void exitGame(ActionEvent event){
        Platform.exit();
        System.exit(0);
    }
}