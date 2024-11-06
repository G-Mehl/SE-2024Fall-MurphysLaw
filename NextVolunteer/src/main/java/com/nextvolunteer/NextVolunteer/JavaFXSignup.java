package com.nextvolunteer.NextVolunteer;

import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane; // Imports all classes in javafx.stage
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle; // Imports all classes in javafx.scene
import javafx.stage.Screen; // Imports all controls like Button, TextField, Label, etc.
import javafx.stage.Stage;

public class JavaFXSignup extends Application {
    @Override
    public void start(Stage primaryStage) {
        // Get the screen dimensions
        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        double screenWidth = screenBounds.getWidth();
        double screenHeight = screenBounds.getHeight();
        double screenWidthfixed = screenWidth * 0.8;
        double screenHeightfixed = screenHeight * 0.8;

        // Create a Pane
        Pane root = new Pane();
        root.getStyleClass().add("container");

        // Create signup box rectangle
        Rectangle fullBox = new Rectangle(screenWidthfixed / 3, screenHeightfixed * 0.9);
        fullBox.setFill(Color.valueOf("#172D13"));
        fullBox.setArcHeight(20);
        fullBox.setArcWidth(20);
        fullBox.setLayoutX(335);  // Align header at top-left
        fullBox.setLayoutY(30);
        root.getChildren().add(fullBox);

        //Sign up label
        Label lblSignup = new Label("Sign up");
        lblSignup.getStyleClass().add("results");
        lblSignup.setLayoutX(450);
        lblSignup.setLayoutY(50);
        root.getChildren().add(lblSignup);

        // Create line rectangle below filter boxes
        Rectangle subline = new Rectangle(225, 1);
        subline.setFill(Color.valueOf("#C49A6A"));
        subline.setLayoutX(395);  // Align header at top-left
        subline.setLayoutY(100);
        root.getChildren().add(subline);

        Label lblemail = new Label("Enter Email");
        lblemail.getStyleClass().add("nav-item");
        lblemail.setLayoutX(455);
        lblemail.setLayoutY(100);
        root.getChildren().add(lblemail);

        //input email
        TextField inputEmail = new TextField();
        inputEmail.setPromptText("person@gmail.com");
        inputEmail.setLayoutX(395);
        inputEmail.setLayoutY(140);
        inputEmail.getStyleClass().add("rectangle-7");
        root.getChildren().add(inputEmail);

        Label lblusername = new Label("Enter Username");
        lblusername.getStyleClass().add("nav-item");
        lblusername.setLayoutX(435);
        lblusername.setLayoutY(180);
        root.getChildren().add(lblusername);

        //username input
        TextField inputUsername = new TextField();
        inputUsername.setPromptText("person123");
        inputUsername.setLayoutX(395);
        inputUsername.setLayoutY(220);
        inputUsername.getStyleClass().add("rectangle-7");
        root.getChildren().add(inputUsername);

        Label lblpassword = new Label("Enter Password");
        lblpassword.getStyleClass().add("nav-item");
        lblpassword.setLayoutX(440);
        lblpassword.setLayoutY(260);
        root.getChildren().add(lblpassword);
        
        //password input
        TextField inputPassword = new TextField();
        inputPassword.setPromptText("pasword!");
        inputPassword.setLayoutX(395);
        inputPassword.setLayoutY(300);
        inputPassword.getStyleClass().add("rectangle-7");
        root.getChildren().add(inputPassword);

        Label lblpasswordConfrim = new Label("Confrim Password");
        lblpasswordConfrim.getStyleClass().add("nav-item");
        lblpasswordConfrim.setLayoutX(425);
        lblpasswordConfrim.setLayoutY(340);
        root.getChildren().add(lblpasswordConfrim);

        //password input
        TextField inputPasswordConfirm = new TextField();
        inputPasswordConfirm.setPromptText("pasword!");
        inputPasswordConfirm.setLayoutX(395);
        inputPasswordConfirm.setLayoutY(380);
        inputPasswordConfirm.getStyleClass().add("rectangle-7");
        root.getChildren().add(inputPasswordConfirm);

        Button BtnSubmit = new Button("Submit");
        BtnSubmit.setLayoutX(395);
        BtnSubmit.setLayoutY(440);
        BtnSubmit.getStyleClass().add("submit-button");
        root.getChildren().add(BtnSubmit);

        // Set Scene to screen size
        Scene scene = new Scene(root, screenWidthfixed, screenHeightfixed);
        scene.getStylesheets().add(getClass().getResource("/style.css").toExternalForm()); // Load CSS

        // Set title and make stage resizable
        primaryStage.setTitle("Sign up");
        primaryStage.setScene(scene);
        primaryStage.setResizable(true);  // Allow resizing
        primaryStage.show();
    }
}