package com.example.sudoku.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class GameStage extends Stage {
    private static GameStage instance;

    private GameStage() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/sudoku/FXML/game-view.fxml"));
        Scene scene = new Scene(loader.load());
        this.setScene(scene);
        this.setTitle("Sudoku Game");
        this.show();
    }

    public static GameStage getInstance() {
        try {
            if (instance == null) instance = new GameStage();
            return instance;
        } catch (IOException e) { return null; }
    }
}