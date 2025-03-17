package com.example.demo1.Student;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class StudentLoginFX extends Application {
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Student Login");

        // Main layout with background image
        BackgroundImage backgroundImage = new BackgroundImage(
                new Image(getClass().getResource("/com/example/demo1/background.jpg").toExternalForm()),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER, new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, false, false));
        VBox mainLayout = new VBox();
        mainLayout.setBackground(new Background(backgroundImage));

        // Logo
        ImageView logo = new ImageView(new Image(getClass().getResource("/com/example/demo1/UMaT logo.jpg").toExternalForm()));
        logo.setFitWidth(80);
        logo.setFitHeight(80);

        Label title = new Label("University of Mines and Technology (UMaT)");
        title.setFont(Font.font("Arial", 18));



        TextField usernameField = new TextField();
        usernameField.setPromptText("Username");
        usernameField.setPrefWidth(350);

        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Password");
        passwordField.setPrefWidth(350);

        Hyperlink forgotPassword = new Hyperlink("Forgot Password?");
        forgotPassword.setTextFill(Color.BLUE);

        Button signInButton = new Button("Sign In");
        signInButton.setStyle("-fx-background-color: #1E90FF; -fx-text-fill: white;");

        Hyperlink signUpLink = new Hyperlink("Don't have an account? Register now");
        signUpLink.setTextFill(Color.BLUE);

        HBox footer = new HBox(15);
        footer.setAlignment(Pos.CENTER);
        footer.getChildren().addAll(new Hyperlink("Terms"), new Hyperlink("Plans"), new Hyperlink("Contact Us"));

        VBox loginBox = new VBox(10, logo, title, usernameField, passwordField, forgotPassword, signInButton, signUpLink);
        loginBox.setAlignment(Pos.CENTER);
        loginBox.setPadding(new Insets(20));
        loginBox.setStyle("-fx-background-color: white; -fx-border-color: lightgray; -fx-border-width: 1; -fx-border-radius: 10;");
        loginBox.setMaxWidth(450);

        StackPane centerPane = new StackPane(loginBox);
        centerPane.setAlignment(Pos.CENTER);
        mainLayout.getChildren().add(centerPane);

        Scene scene = new Scene(mainLayout, 800, 600);
        primaryStage.setMaximized(false);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
