package com.nextvolunteer.NextVolunteer;

import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.animation.PauseTransition;

public class JavaFXLogin extends Application {
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
        Rectangle fullBox = new Rectangle(screenWidthfixed / 3, screenHeightfixed * 0.5);
        fullBox.setFill(Color.valueOf("#172D13"));
        fullBox.setArcHeight(20);
        fullBox.setArcWidth(20);
        fullBox.setLayoutX(335);
        fullBox.setLayoutY(160);
        root.getChildren().add(fullBox);

        //Sign up label
        Label lblLogin = new Label("Login");
        lblLogin.getStyleClass().add("results");
        lblLogin.setLayoutX(465);
        lblLogin.setLayoutY(165);
        root.getChildren().add(lblLogin);

        // Create line rectangle below filter boxes
        Rectangle subline = new Rectangle(225, 1);
        subline.setFill(Color.valueOf("#C49A6A"));
        subline.setLayoutX(395);
        subline.setLayoutY(215);
        root.getChildren().add(subline);


        Label lblusername = new Label("Enter Username");
        lblusername.getStyleClass().add("nav-item");
        lblusername.setLayoutX(435);
        lblusername.setLayoutY(215);
        root.getChildren().add(lblusername);

        //input username
        TextField inputusername = new TextField();
        inputusername.setPromptText("person123");
        inputusername.setLayoutX(395);
        inputusername.setLayoutY(255);
        inputusername.getStyleClass().add("rectangle-7");
        root.getChildren().add(inputusername);

        Label lblPassword = new Label("Enter Password");
        lblPassword.getStyleClass().add("nav-item");
        lblPassword.setLayoutX(440);
        lblPassword.setLayoutY(285);
        root.getChildren().add(lblPassword);

        //input username
        TextField inputPassword = new TextField();
        inputPassword.setPromptText("password!");
        inputPassword.setLayoutX(395);
        inputPassword.setLayoutY(325);
        inputPassword.getStyleClass().add("rectangle-7");
        root.getChildren().add(inputPassword);

        Button BtnSubmit = new Button("Submit");
        BtnSubmit.setLayoutX(395);
        BtnSubmit.setLayoutY(375);
        BtnSubmit.getStyleClass().add("submit-button");
        root.getChildren().add(BtnSubmit);

        Button btnBack = new Button("<-");
        btnBack.setLayoutX(20);
        btnBack.setLayoutY(20);
        btnBack.getStyleClass().add("back-button");
        root.getChildren().add(btnBack);

        // Set Scene to screen size
        Scene scene = new Scene(root, screenWidthfixed, screenHeightfixed);
        scene.getStylesheets().add(getClass().getResource("/style.css").toExternalForm()); // Load CSS

        // Set title and make stage resizable
        primaryStage.setTitle("Login");
        primaryStage.setScene(scene);
        primaryStage.setResizable(true);
        primaryStage.show();

        btnBack.setOnAction(event -> openBasePage(primaryStage));
        BtnSubmit.setOnAction(event -> {
            String username = inputusername.getText();
            String password = inputPassword.getText();
            submitClicked(primaryStage, username, password, root);
        });
    }

    //methods to swap pages
    private void openBasePage(Stage primaryStage) {
        JavaFXApp basePage = new JavaFXApp();
        try {
            basePage.start(primaryStage);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    Label failLabel = new Label();

    private void submitClicked(Stage primaryStage, String username, String password, Pane root) {

        // Clear any existing failLabel from previous attempts
        if (root.getChildren().contains(failLabel)) {
            root.getChildren().remove(failLabel);
        }
        //instantiate User object to be used for register() method
        User user = new User(username, password);
        if (user.login(password)) {
            Label successLabel = new Label("Login Successful!");
            successLabel.setTextFill(Color.GREEN);
            successLabel.setLayoutX(455);
            successLabel.setLayoutY(435);
            root.getChildren().add(successLabel);
            Label returnLabel = new Label("Returning to home page");
            returnLabel.setTextFill(Color.GREEN);
            returnLabel.setLayoutX(440);
            returnLabel.setLayoutY(455);
            root.getChildren().add(returnLabel);

            //pause transition for 5 seconds before going to the base page
            PauseTransition pause = new PauseTransition(Duration.seconds(5));
            pause.setOnFinished(event -> {
                root.getChildren().remove(successLabel); // Remove the success message
                openBasePage(primaryStage); // Navigate to the base page
            });
            pause.play();
        } else {
            failLabel = new Label("Login failed. Please check username or password");
            failLabel.setTextFill(Color.RED);
            failLabel.setLayoutX(380);
            failLabel.setLayoutY(435);
            root.getChildren().add(failLabel);
        }
    }
}
