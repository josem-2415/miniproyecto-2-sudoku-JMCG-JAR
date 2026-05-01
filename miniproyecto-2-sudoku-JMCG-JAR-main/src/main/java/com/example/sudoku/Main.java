package com.example.sudoku;

import com.example.sudoku.view.MenuStage;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        // Inicia la ventana del menú principal
        MenuStage.getInstance();
    }

    public static void main(String[] args) {
        launch(args);
    }
}