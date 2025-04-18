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

public class JavaFXSignup extends Application {

    private TextField inputEmail;
    private TextField inputUsername;
    private TextField inputPassword;
    private TextField inputPasswordConfirm;

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
        fullBox.setLayoutX(335);
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
        subline.setLayoutX(395);
        subline.setLayoutY(100);
        root.getChildren().add(subline);

        Label lblemail = new Label("Enter Email");
        lblemail.getStyleClass().add("nav-item");
        lblemail.setLayoutX(455);
        lblemail.setLayoutY(100);
        root.getChildren().add(lblemail);

        //for validation
        Label lblemailErr = new Label();
        lblemailErr.setTextFill(Color.RED);
        lblemailErr.setLayoutX(455);
        lblemailErr.setLayoutY(100);
        root.getChildren().add(lblemailErr);

        //input email
        inputEmail = new TextField();
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
        inputUsername = new TextField();
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
        inputPassword = new TextField();
        inputPassword.setPromptText("password!");
        inputPassword.setLayoutX(395);
        inputPassword.setLayoutY(300);
        inputPassword.getStyleClass().add("rectangle-7");
        root.getChildren().add(inputPassword);

        Label lblpasswordConfirm = new Label("Confirm Password");
        lblpasswordConfirm.getStyleClass().add("nav-item");
        lblpasswordConfirm.setLayoutX(425);
        lblpasswordConfirm.setLayoutY(340);
        root.getChildren().add(lblpasswordConfirm);

        //for validation password
        Label lblpassErr = new Label();
        lblpassErr.setTextFill(Color.RED);
        lblpassErr.setLayoutX(440);
        lblpassErr.setLayoutY(340);
        root.getChildren().add(lblpassErr);


        //password input
        inputPasswordConfirm = new TextField();
        inputPasswordConfirm.setPromptText("password!");
        inputPasswordConfirm.setLayoutX(395);
        inputPasswordConfirm.setLayoutY(380);
        inputPasswordConfirm.getStyleClass().add("rectangle-7");
        root.getChildren().add(inputPasswordConfirm);

        Button BtnSubmit = new Button("Submit");
        BtnSubmit.setLayoutX(395);
        BtnSubmit.setLayoutY(440);
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
        primaryStage.setTitle("Sign up");
        primaryStage.setScene(scene);
        primaryStage.setResizable(true);
        primaryStage.show();

        btnBack.setOnAction(event -> openBasePage(primaryStage));
        BtnSubmit.setOnAction(event -> submitClicked(primaryStage));

        //email validation
        inputEmail.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.isEmpty()) {
                lblemailErr.setText("");
                lblemail.setText("Enter Email");
            }
            else if (!newValue.matches("^[\\w._%+-]+@[\\w.-]+\\.[a-zA-Z]{2,6}$")) {
                lblemail.setText("");
                lblemailErr.setText("Invalid email format.");
            }
            else {
                lblemailErr.setText("");
                lblemail.setText("Enter Email");
            }
        });

        inputPasswordConfirm.textProperty().addListener((observable, oldValue, newValue) -> {
            validatePasswordMatch(inputPassword, inputPasswordConfirm, lblpassErr, lblpasswordConfirm);
        });
    }

    //methods to swap pages
    private void openBasePage(Stage primaryStage) {
        JavaFXLogin loginPage = new JavaFXLogin();
        try {
            loginPage.start(primaryStage);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void submitClicked(Stage primaryStage) {
        String email = inputEmail.getText().trim();
        String username = inputUsername.getText().trim(); 
        String password = inputPassword.getText();
        String confirmPassword = inputPasswordConfirm.getText();

        // Validate inputs
        if (email.isEmpty() || username.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            System.out.println("All fields must be filled out.");
            return;
        }

        if (!password.equals(confirmPassword)) {
            System.out.println("Passwords do not match.");
            return;
        }

        // Instantiate User object
        User newUser = new User(1, username, password, email, null, null);

        // Call the register method
        if (newUser.register()) {
            System.out.println("User registered successfully!");
            primaryStage.close(); // Close the signup window
            openBasePage(primaryStage); // Navigate to the base page
        } else {
            System.out.println("User registration failed. Please check the console for details.");
        }
    }

    //email validation method
    private void validatePasswordMatch(TextField passwordField, TextField confirmPasswordField, Label lblpassErr, Label lblpasswordConfirm) {
        String password = passwordField.getText();
        String confirmPassword = confirmPasswordField.getText();

        if (confirmPassword.isEmpty()) {
            lblpassErr.setText("");
            lblpasswordConfirm.setText("Confirm Password");
        }
        else if (!password.equals(confirmPassword)) {
            lblpassErr.setText("Passwords do not match");
            lblpasswordConfirm.setText("");
        }
        else {
            lblpassErr.setText("");
            lblpasswordConfirm.setText("Confirm Password");
        }
    }

}