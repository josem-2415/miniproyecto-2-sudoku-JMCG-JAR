package com.example.sudoku;
import com.example.sudoku.view.MenuStage;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Main class that launches the application.
 */
public class Main extends Application {

    /**
     * Entry point.
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Starts the JavaFX application.
     */
    @Override
    public void start(Stage primaryStage) throws IOException {
        new MenuStage(primaryStage);
    }
}
