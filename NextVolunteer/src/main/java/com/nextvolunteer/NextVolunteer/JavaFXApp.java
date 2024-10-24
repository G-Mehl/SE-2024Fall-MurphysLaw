package com.nextvolunteer.NextVolunteer;

import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class JavaFXApp extends Application {
    @Override
    public void start(Stage primaryStage) {
        // Get the screen dimensions
        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        double screenWidth = screenBounds.getWidth();
        double screenHeight = screenBounds.getHeight();

        // Create a Pane
        Pane root = new Pane();
        root.getStyleClass().add("container");

        // Create header rectangle
        Rectangle header = new Rectangle(screenWidth * 0.8, 50);
        header.setFill(Color.valueOf("#172D13"));
        header.setLayoutX(0);  // Align header at top-left
        header.setLayoutY(0);
        root.getChildren().add(header);

        // Top left logo
        Image logo = new Image(getClass().getResourceAsStream("/images/logo.png"));
        ImageView logoView = new ImageView(logo);
        logoView.setFitWidth(60);  // Set image width
        logoView.setFitHeight(60);  // Set image height
        logoView.setLayoutX(0);  // Set image position
        logoView.setLayoutY(0);  // Position inside the header
        root.getChildren().add(logoView);

        // Labels for now till files are made for redirects
        Label lblHome = new Label("Home");
        lblHome.getStyleClass().add("nav-item");
        lblHome.setLayoutX(100);
        lblHome.setLayoutY(0);
        root.getChildren().add(lblHome);

        Label lblAbout = new Label("About");
        lblAbout.getStyleClass().add("nav-item");
        lblAbout.setLayoutX(200);
        lblAbout.setLayoutY(0);
        root.getChildren().add(lblAbout);

        Label lblProfile = new Label("Profile");
        lblProfile.getStyleClass().add("nav-item");
        lblProfile.setLayoutX(300);
        lblProfile.setLayoutY(0);
        root.getChildren().add(lblProfile);

        // Set Scene to screen size
        Scene scene = new Scene(root, screenWidth * 0.8, screenHeight * 0.8); // 80% of the screen size
        scene.getStylesheets().add(getClass().getResource("/style.css").toExternalForm()); // Load CSS

        // Set title and make stage resizable
        primaryStage.setTitle("NextVolunteer");
        primaryStage.setScene(scene);
        primaryStage.setResizable(true);  // Allow resizing
        primaryStage.show();
    }
}