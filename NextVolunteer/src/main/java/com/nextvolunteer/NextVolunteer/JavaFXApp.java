package com.nextvolunteer.NextVolunteer;

import java.util.ArrayList;
import java.util.List;

import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button; // Imports all classes in javafx.stage
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label; // Imports all classes in javafx.scene
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField; // Imports all controls like Button, TextField, Label, etc.
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Screen;
import javafx.stage.Stage;


public class JavaFXApp extends Application {

    private ListView<Opportunity> resultsListView;
    private TextField inputLocation;

    @Override
    public void start(Stage primaryStage) {

        resultsListView = new ListView<>();

        resultsListView.setCellFactory(listView -> new ListCell<Opportunity>() {
            @Override
            protected void updateItem(Opportunity opportunity, boolean empty) {
                super.updateItem(opportunity, empty);

                if (empty || opportunity == null) {
                    setText(null);
                    setGraphic(null);
                } else {
                    VBox vbox = new VBox();
                    Label titleLabel = new Label(opportunity.getTitle());
                    titleLabel.getStyleClass().add("event-title");
                
                    Label locationLabel = new Label(opportunity.getLocation());
                    locationLabel.getStyleClass().add("event-location");

                    Label descriptionLabel = new Label(opportunity.getDescription());
                    descriptionLabel.getStyleClass().add("event-description");
                    descriptionLabel.setWrapText(true);
                
                    vbox.getChildren().addAll(titleLabel, locationLabel, descriptionLabel);
                    setGraphic(vbox);
                }
            }
        });

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
        logoView.setLayoutY(-5);  // Position inside the header
        root.getChildren().add(logoView);

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
        filtersBox.setLayoutX(65);
        filtersBox.setLayoutY(85);
        root.getChildren().add(filtersBox);

        inputLocation = new TextField();
        inputLocation.setPromptText("Enter a City");
        inputLocation.setLayoutX(95);
        inputLocation.setLayoutY(105);
        inputLocation.getStyleClass().add("rectangle-5");
        root.getChildren().add(inputLocation);

        // Create a ComboBox for the dropdown list
        ComboBox<String> dropdownInterests = new ComboBox<>();
        dropdownInterests.getItems().addAll("Education & Literacy", "Environment & Conservation", "Animal Welfare", "Healthcare & Wellness", "Disaster Relief");
        dropdownInterests.setPromptText("Areas of Interest");
        dropdownInterests.setLayoutX(225);
        dropdownInterests.setLayoutY(105);
        dropdownInterests.getStyleClass().add("dropdown");
        root.getChildren().add(dropdownInterests);

        // Create a ComboBox for the dropdown list
        ComboBox<String> dropdownDistance = new ComboBox<>();
        dropdownDistance.getItems().addAll("10 mi", "25 mi", "50 mi");
        dropdownDistance.setPromptText("Distance (mi)");
        dropdownDistance.setLayoutX(410);
        dropdownDistance.setLayoutY(105);
        dropdownDistance.getStyleClass().add("dropdown");
        root.getChildren().add(dropdownDistance);

        TextField inputKeyword = new TextField();
        inputKeyword.setPromptText("Search by Keyword");
        inputKeyword.setLayoutX(595);
        inputKeyword.setLayoutY(105);
        inputKeyword.getStyleClass().add("rectangle-6");
        root.getChildren().add(inputKeyword);

        Button searchButton = new Button("Search");
        searchButton.setLayoutX(810);
        searchButton.setLayoutY(100);
        searchButton.getStyleClass().add("search-button");
        root.getChildren().add(searchButton);

        // Create line rectangle below filter boxes
        Rectangle subline = new Rectangle(screenWidth * 0.75, 3);
        subline.setFill(Color.valueOf("#C49A6A"));
        subline.setLayoutX(30);
        subline.setLayoutY(185);
        root.getChildren().add(subline);

        //Results text
        Label lblResults = new Label("Results");
        lblResults.getStyleClass().add("results");
        lblResults.setUnderline(true);
        lblResults.setLayoutX(30);
        lblResults.setLayoutY(195);
        root.getChildren().add(lblResults);

        resultsListView.getStyleClass().add("results-list-view");
        resultsListView.setLayoutX(30);
        resultsListView.setLayoutY(250);
        resultsListView.setPrefHeight(200);
        resultsListView.setPrefWidth(600);
        root.getChildren().add(resultsListView);

        // Create ad rectangle
        Rectangle freeAd = new Rectangle(250, 275);
        freeAd.setFill(Color.valueOf("#172D13"));
        freeAd.setLayoutX(700);
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
        headerBottom.setLayoutX(0);
        headerBottom.setLayoutY(490);
        root.getChildren().add(headerBottom);

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
        logoViewBottom.setFitWidth(60);
        logoViewBottom.setFitHeight(60);
        logoViewBottom.setLayoutX(500);
        logoViewBottom.setLayoutY(485); 
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
        primaryStage.setResizable(true);
        primaryStage.show();

        //Button actions
        loginButton.setOnAction(event -> openLoginPage(primaryStage));
        signUpButton.setOnAction(event -> openSignUpPage(primaryStage));
        searchButton.setOnAction(event -> {
            String selectedInterest = dropdownInterests.getValue();
            String locationInput = inputLocation.getText().trim();

            List<Opportunity> opportunities = new ArrayList<>();

            if (!locationInput.isEmpty()) {
                // Get opportunities by location
                opportunities = Opportunity.getOppByLocation(locationInput);
            }

            if (selectedInterest != null) {
                // Get opportunities by interest
                opportunities = Opportunity.getOppByInterest(selectedInterest);
            }
            else {
                System.out.println("Please select an interest from the dropdown.");
            }

            displayResults(opportunities);
        });
    }

    //displays results
    private void displayResults(List<Opportunity> opportunities) {
        //Clears old results
        resultsListView.getItems().clear();

        if (opportunities.isEmpty()) {
            resultsListView.getItems().add(new Opportunity(-1, "No opportunities found for this interest", "", "", ""));
        }
        else {
            resultsListView.getItems().addAll(opportunities);
        }
    }


    //methods to swap pages
    private void openLoginPage(Stage primaryStage) {
        JavaFXLogin loginPage = new JavaFXLogin();
        try {
            loginPage.start(primaryStage);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void openSignUpPage(Stage primaryStage) {
        JavaFXSignup signupPage = new JavaFXSignup();
        try {
            signupPage.start(primaryStage);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }


}