package com.example.sudoku.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Represents the main menu view.
 */
public class    MenuStage {
    public MenuStage(Stage stage){
        try{
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/com/example/sudoku/FXML/menu-view.fxml")
            );
            Parent root = loader.load();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Sudoku");
            stage.setResizable(false);
            stage.show();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}

