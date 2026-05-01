package com.example.sudoku;

import javafx.application.Application;
import javafx.stage.Stage;
import com.example.sudoku.view.MenuStage;

/**
 * Punto de entrada de la aplicación.
 */
public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        MenuStage.getInstance().showWindow();
    }

    public static void main(String[] args) {
        launch(args);
    }
}