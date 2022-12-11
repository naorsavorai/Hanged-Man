package com.example.maman13;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class HangedManUI extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HangedManUI.class.getResource("Main.fxml"));
        String css = Objects.requireNonNull(this.getClass().getResource("styles.css")).toExternalForm();
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        scene.getStylesheets().add(css);
        stage.setTitle("Hanged Man");
        stage.setScene(scene);
        stage.setHeight(400);
        stage.setWidth(800);
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}