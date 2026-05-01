package com.example.sudoku.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class MenuStage extends Stage {
    private static MenuStage instance;

    private MenuStage() {
        try {
            // Carga el archivo FXML del menú [cite: 64, 65]
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/sudoku/FXML/menu-view.fxml"));
            Scene scene = new Scene(loader.load());

            this.setScene(scene);
            this.setTitle("Sudoku - Menu Principal");
            this.setResizable(false);
            this.show();
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

    public static void deleteInstance() {
        if (instance != null) {
            instance.close();
            instance = null;
        }
    }
}