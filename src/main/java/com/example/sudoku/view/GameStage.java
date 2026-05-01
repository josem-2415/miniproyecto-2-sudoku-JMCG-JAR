package com.example.sudoku.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class GameStage extends Stage implements IView {
    private static GameStage instance;

    private GameStage() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/sudoku/FXML/game-view.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            this.setScene(scene);
            this.setTitle("Sudoku - Game");
            this.setResizable(false);
        } catch (Exception e) {
            System.err.println("No ha cargado correctamente el FXML");
            e.printStackTrace();
        }
    }

    public static GameStage getInstance() {
        if (instance == null) {
            instance = new GameStage();
        }
        return instance;
    }

    public static void resetInstance() {
        if (instance != null) {
            instance.close();
            instance = null;
        }
    }

    @Override
    public void showWindow() {
        this.show();
    }

    @Override
    public void closeWindow() {
        this.close();
    }
}