package com.nextvolunteer.NextVolunteer;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class JavaFXApp extends Application {
    @Override
    public void start(Stage primaryStage) {
        StackPane root = new StackPane();
        root.getChildren().add(new Label("Hello, JavaFX!"));
        Scene scene = new Scene(root, 300, 250);
        primaryStage.setTitle("JavaFX with Spring Boot");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}