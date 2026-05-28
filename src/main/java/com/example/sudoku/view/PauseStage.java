package com.example.sudoku.view;

import com.example.sudoku.controller.PauseController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class PauseStage extends Stage {

    public PauseStage(Runnable onResume) {

        try {

            FXMLLoader loader =
                    new FXMLLoader(
                            getClass().getResource(
                                    "/com/example/sudoku/FXML/pause-view.fxml"
                            )
                    );

            Parent root = loader.load();

            PauseController controller =
                    loader.getController();

            controller.setOnResume(onResume);

            Scene scene = new Scene(root);

            this.setScene(scene);

            this.setTitle("Paused");

            this.setResizable(false);

            this.setOnCloseRequest(event -> {
                event.consume();
            });

            this.initModality(Modality.APPLICATION_MODAL);

        } catch (Exception e) {

            e.printStackTrace();
        }
    }
}