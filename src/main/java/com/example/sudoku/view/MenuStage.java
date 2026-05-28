package com.example.sudoku.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MenuStage extends Stage implements IView {
    private static MenuStage instance;

    private MenuStage() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/sudoku/FXML/menu-view.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            this.setScene(scene);
            this.setTitle("Sudoku - Menu");
            this.setResizable(false);
            this.setOnCloseRequest(event -> {
                event.consume();
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static MenuStage getInstance() {
        if (instance == null) {
            instance = new MenuStage();
        }
        return instance;
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