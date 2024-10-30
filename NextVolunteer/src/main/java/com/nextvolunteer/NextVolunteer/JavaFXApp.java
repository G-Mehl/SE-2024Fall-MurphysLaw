package com.nextvolunteer.NextVolunteer;

import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button; // Imports all classes in javafx.stage
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label; // Imports all classes in javafx.scene
import javafx.scene.control.TextField; // Imports all controls like Button, TextField, Label, etc.
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

    //Top header elements

        // Create header rectangle
        Rectangle headerTop = new Rectangle(screenWidth * 0.8, 50);
        headerTop.setFill(Color.valueOf("#172D13"));
        headerTop.setLayoutX(0);  // Align header at top-left
        headerTop.setLayoutY(0);
        root.getChildren().add(headerTop);

        // Top left logo
        Image logo = new Image(getClass().getResourceAsStream("/images/logo.png"));
        ImageView logoView = new ImageView(logo);
        logoView.setFitWidth(60);  // Set image width
        logoView.setFitHeight(60);  // Set image height
        logoView.setLayoutX(5);  // Set image position
        logoView.setLayoutY(0);  // Position inside the header
        root.getChildren().add(logoView);

        // Labels for now till files are made for redirects
        Label lblHome = new Label("Home");
        lblHome.getStyleClass().add("nav-item");
        lblHome.setLayoutX(100);
        lblHome.setLayoutY(5);
        root.getChildren().add(lblHome);

        Label lblAbout = new Label("About");
        lblAbout.getStyleClass().add("nav-item");
        lblAbout.setLayoutX(200);
        lblAbout.setLayoutY(5);
        root.getChildren().add(lblAbout);

        Label lblProfile = new Label("Profile");
        lblProfile.getStyleClass().add("nav-item");
        lblProfile.setLayoutX(300);
        lblProfile.setLayoutY(5);
        root.getChildren().add(lblProfile);

        Button loginButton = new Button("Login");
        loginButton.setLayoutX(850);
        loginButton.setLayoutY(7);
        loginButton.getStyleClass().add("cta-button");
        root.getChildren().add(loginButton);

        Button signUpButton = new Button("Sign Up");
        signUpButton.setLayoutX(935);
        signUpButton.setLayoutY(7);
        signUpButton.getStyleClass().add("cta-button");
        root.getChildren().add(signUpButton);

    //Middle content
        // Create subheader rectangle above filter box
        Rectangle subHeader = new Rectangle(screenWidth * 0.8, 15);
        subHeader.setFill(Color.valueOf("#C49A6A"));
        subHeader.setLayoutX(0);  // Align header at top-left
        subHeader.setLayoutY(50);
        root.getChildren().add(subHeader);

        // Create filters rectangle
        Rectangle filtersBox = new Rectangle(screenWidth * 0.7, 70);
        filtersBox.setFill(Color.valueOf("#172D13"));
        filtersBox.setArcHeight(20);
        filtersBox.setArcWidth(20);
        filtersBox.setLayoutX(65);  // Align header at top-left
        filtersBox.setLayoutY(85);
        root.getChildren().add(filtersBox);

        TextField inputLocation = new TextField();
        inputLocation.setPromptText("Enter a City");
        inputLocation.setLayoutX(95);
        inputLocation.setLayoutY(105);
        inputLocation.getStyleClass().add("rectangle-5");
        root.getChildren().add(inputLocation);

        // Create a ComboBox for the dropdown list
        ComboBox<String> dropdownInterests = new ComboBox<>();
        dropdownInterests.getItems().addAll("Option 1", "Option 2", "Option 3");
        dropdownInterests.setPromptText("Areas of Interest");
        dropdownInterests.setLayoutX(280);
        dropdownInterests.setLayoutY(105);
        dropdownInterests.getStyleClass().add("dropdown");
        root.getChildren().add(dropdownInterests);

        // Create a ComboBox for the dropdown list
        ComboBox<String> dropdownDistance = new ComboBox<>();
        dropdownDistance.getItems().addAll("10 mi", "25 mi", "50 mi");
        dropdownDistance.setPromptText("Distance (mi)");
        dropdownDistance.setLayoutX(520);
        dropdownDistance.setLayoutY(105);
        dropdownDistance.getStyleClass().add("dropdown");
        root.getChildren().add(dropdownDistance);

        TextField inputKeyword = new TextField();
        inputKeyword.setPromptText("Search by Keyword");
        inputKeyword.setLayoutX(750);
        inputKeyword.setLayoutY(105);
        inputKeyword.getStyleClass().add("rectangle-6");
        root.getChildren().add(inputKeyword);

        // Create line rectangle below filter boxes
        Rectangle subline = new Rectangle(screenWidth * 0.75, 3);
        subline.setFill(Color.valueOf("#C49A6A"));
        subline.setLayoutX(30);  // Align header at top-left
        subline.setLayoutY(185);
        root.getChildren().add(subline);

        //Results text
        Label lblResults = new Label("Results");
        lblResults.getStyleClass().add("results");
        lblResults.setUnderline(true);
        lblResults.setLayoutX(30);
        lblResults.setLayoutY(195);
        root.getChildren().add(lblResults);

        // Create ad rectangle
        Rectangle freeAd = new Rectangle(250, 275);
        freeAd.setFill(Color.valueOf("#172D13"));
        freeAd.setLayoutX(700);  // Align header at top-left
        freeAd.setLayoutY(200);
        root.getChildren().add(freeAd);

        //Results text
        Label lblad = new Label("This will be free \n" + "advertising for \n" + "non-profits");
        lblad.getStyleClass().add("freeAd");
        lblad.setLayoutX(775);
        lblad.setLayoutY(175);
        root.getChildren().add(lblad);

    //Bottom header and labels
        Rectangle headerBottom = new Rectangle(screenWidth * 0.8, 50);
        headerBottom.setFill(Color.valueOf("#172D13"));
        headerBottom.setLayoutX(0);  // Align header at top-left
        headerBottom.setLayoutY(490);
        root.getChildren().add(headerBottom);

        // Labels for now till files are made for redirects
        Label lblHomeBottom = new Label("Home");
        lblHomeBottom.getStyleClass().add("nav-item");
        lblHomeBottom.setLayoutX(10);
        lblHomeBottom.setLayoutY(495);
        root.getChildren().add(lblHomeBottom);

        Label lblAboutBottom = new Label("About");
        lblAboutBottom.getStyleClass().add("nav-item");
        lblAboutBottom.setLayoutX(110);
        lblAboutBottom.setLayoutY(495);
        root.getChildren().add(lblAboutBottom);

        // Bottom Center logo
        Image logoBottom = new Image(getClass().getResourceAsStream("/images/logo.png"));
        ImageView logoViewBottom = new ImageView(logoBottom);
        logoViewBottom.setFitWidth(60);  // Set image width
        logoViewBottom.setFitHeight(60);  // Set image height
        logoViewBottom.setLayoutX(500);  // Set image position
        logoViewBottom.setLayoutY(485);  // Position inside the header
        root.getChildren().add(logoViewBottom);

        Label lblProfileBottom = new Label("Profile");
        lblProfileBottom.getStyleClass().add("nav-item");
        lblProfileBottom.setLayoutX(950);
        lblProfileBottom.setLayoutY(495);
        root.getChildren().add(lblProfileBottom);

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