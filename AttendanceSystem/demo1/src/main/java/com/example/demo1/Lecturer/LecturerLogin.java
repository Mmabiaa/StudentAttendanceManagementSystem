package com.example.demo1.Lecturer;

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
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Objects;

public class LecturerLogin extends Application {
        private Stage primaryStage;

        // Constants for JSON and file handling (make sure these match LecturerDashboard and LecturerSettings)
        private static final String LECTURER_INFO_FILE = "lecturer_info.json";
        private static final String JSON_NAME_KEY = "name";
        private static final String JSON_EMAIL_KEY = "email";
        private static final String JSON_STAFF_ID_KEY = "staffId";
        private static final String DEFAULT_LOGO_PATH = "/com/example/demo1/UMaT logo.jpg"; // Correct the path
        private static final String DEFAULT_BACKGROUND_PATH = "/com/example/demo1/background.jpg";

        @Override
        public void start(Stage primaryStage) {
                this.primaryStage = primaryStage;
                showLoginPage();
        }

        // Method to create a background layout
        private StackPane createBackgroundLayout() {
                Image backgroundImage = null;
                try {
                        backgroundImage = new Image(Objects.requireNonNull(getClass().getResource(DEFAULT_BACKGROUND_PATH)).toExternalForm());
                } catch (NullPointerException e) {
                        System.err.println("Error loading background image: " + e.getMessage());
                        // Handle the error appropriately - perhaps use a default background
                }

                BackgroundImage backgroundImg = new BackgroundImage(
                        backgroundImage != null ? backgroundImage : new Image("file:default_background.jpg"), // Fallback
                        BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
                        BackgroundPosition.CENTER,
                        new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, true, true, false, true));

                StackPane mainLayout = new StackPane();
                mainLayout.setBackground(new Background(backgroundImg));
                return mainLayout;
        }

        // Method to create a styled form container
        private VBox createFormContainer(String titleText, Control... controls) {
                Label title = new Label(titleText);
                title.setFont(Font.font("Arial", 24));
                title.setTextFill(Color.BLACK);

                ImageView logoView = null;
                try {
                        Image logoImage = new Image(Objects.requireNonNull(getClass().getResource(DEFAULT_LOGO_PATH)).toExternalForm());
                        logoView = new ImageView(logoImage);
                        logoView.setFitWidth(80);
                        logoView.setFitHeight(80);
                } catch (NullPointerException e) {
                        System.err.println("Error loading logo image: " + e.getMessage());
                        // Create a placeholder
                        logoView = new ImageView(); // Create an empty ImageView
                }

                VBox formBox = new VBox(10, logoView, title);
                formBox.getChildren().addAll(controls);
                formBox.setAlignment(Pos.CENTER);
                formBox.setPadding(new Insets(20));
                formBox.setStyle(
                        "-fx-background-color: rgba(255, 255, 255, 0.9); -fx-border-color: lightgray; -fx-border-width: 1; -fx-border-radius: 10; -fx-padding: 30px;");
                formBox.setMaxWidth(400);

                VBox wrapper = new VBox(formBox);
                wrapper.setAlignment(Pos.CENTER);
                wrapper.setPadding(new Insets(50));

                return wrapper;
        }

        // Lecturer Login Page
        private void showLoginPage() {
                primaryStage.setTitle("Lecturer Login");

                TextField staffIdField = new TextField();
                staffIdField.setPromptText("Staff ID");
                staffIdField.setPrefWidth(300);

                PasswordField passwordField = new PasswordField();
                passwordField.setPromptText("Password");
                passwordField.setPrefWidth(300);

                CheckBox rememberMeCheckBox = new CheckBox("Remember me");

                Hyperlink forgotPassword = new Hyperlink("Forgot password?");
                forgotPassword.setTextFill(Color.BLUE);
                forgotPassword.setOnAction(e -> showForgotPasswordPage());

                Button loginButton = new Button("Login");
                loginButton.setStyle(
                        "-fx-background-color: #008000; -fx-text-fill: white; -fx-font-size: 14px; -fx-padding: 8px 16px;");

                loginButton.setOnAction(e -> {
                        // **Authentication Logic (Replace with your actual authentication)**
                        String staffId = staffIdField.getText();
                        String password = passwordField.getText();

                        // **Simulated Authentication:** Replace with your database check!
                        if (authenticate(staffId, password)) {
                                // ***After successful login, redirect to Dashboard***
                                loadDashboard();
                        } else {
                                showAlert("Login Failed", "Invalid Staff ID or password.");
                        }
                });

                Hyperlink registerLink = new Hyperlink("Don’t have an account? Register now");
                registerLink.setTextFill(Color.BLUE);
                registerLink.setOnAction(e -> showRegistrationPage());

                VBox formContainer = createFormContainer("Lecturer Login", staffIdField, passwordField,
                        rememberMeCheckBox, forgotPassword, loginButton, registerLink);
                StackPane mainLayout = createBackgroundLayout();
                mainLayout.getChildren().add(formContainer);

                primaryStage.setScene(new Scene(mainLayout, 800, 600));
                primaryStage.show();
        }

        // Forgot Password Page
        private void showForgotPasswordPage() {
                primaryStage.setTitle("Forgot Password");

                TextField emailField = new TextField();
                emailField.setPromptText("Enter your email");
                emailField.setPrefWidth(300);

                Button resetButton = new Button("Send Reset Link");
                resetButton.setStyle(
                        "-fx-background-color: #008000; -fx-text-fill: white; -fx-font-size: 14px; -fx-padding: 8px 16px;");
                resetButton.setOnAction(e -> System.out.println("Reset link sent to " + emailField.getText()));

                Button backButton = new Button("Back to Login");
                backButton.setOnAction(e -> showLoginPage());

                VBox formContainer = createFormContainer("Reset Password", emailField, resetButton, backButton);
                StackPane mainLayout = createBackgroundLayout();
                mainLayout.getChildren().add(formContainer);

                primaryStage.setScene(new Scene(mainLayout, 800, 600));
        }

        // Registration Page
        private void showRegistrationPage() {
                primaryStage.setTitle("Register");

                TextField fullNameField = new TextField();
                fullNameField.setPromptText("Full Name");
                fullNameField.setPrefWidth(300);

                TextField emailField = new TextField();
                emailField.setPromptText("Email");
                emailField.setPrefWidth(300);

                TextField staffIdField = new TextField();
                staffIdField.setPromptText("Staff ID");
                staffIdField.setPrefWidth(300);

                PasswordField passwordField = new PasswordField();
                passwordField.setPromptText("Password");
                passwordField.setPrefWidth(300);

                PasswordField confirmPasswordField = new PasswordField();
                confirmPasswordField.setPromptText("Confirm Password");
                confirmPasswordField.setPrefWidth(300);

                Button registerButton = new Button("Register");
                registerButton.setStyle(
                        "-fx-background-color: #008000; -fx-text-fill: white; -fx-font-size: 14px; -fx-padding: 8px 16px;");
                registerButton.setOnAction(e -> {
                        // ** Registration Logic (Replace with your registration)**
                        String fullName = fullNameField.getText();
                        String email = emailField.getText();
                        String staffId = staffIdField.getText();
                        String password = passwordField.getText();
                        String confirmPassword = confirmPasswordField.getText();

                        // Check for password match
                        if (!password.equals(confirmPassword)) {
                                showAlert("Registration Failed", "Passwords do not match.");
                                return;
                        }

                        // **Now save the user information to lecturer_info.json
                        saveLecturerInfo(fullName, email, staffId, password); // Save password
                        // **After successful registration, go to the LoginPage to login
                        showLoginPage();
                });

                Button backButton = new Button("Back to Login");
                backButton.setOnAction(e -> showLoginPage());

                VBox formContainer = createFormContainer("Register", fullNameField, emailField, staffIdField,
                        passwordField, confirmPasswordField, registerButton, backButton);
                StackPane mainLayout = createBackgroundLayout();
                mainLayout.getChildren().add(formContainer);

                primaryStage.setScene(new Scene(mainLayout, 800, 600));
        }

        // In the Save function add a password
        private void saveLecturerInfo(String name, String email, String staffId, String password) {
                JSONObject lecturerInfo = new JSONObject();
                lecturerInfo.put(JSON_NAME_KEY, name);
                lecturerInfo.put(JSON_EMAIL_KEY, email);
                lecturerInfo.put(JSON_STAFF_ID_KEY, staffId);
                lecturerInfo.put("password", password);  // **Save the password**

                try (FileWriter fileWriter = new FileWriter(LECTURER_INFO_FILE)) {
                        fileWriter.write(lecturerInfo.toString(2));
                        System.out.println("Lecturer information saved to " + LECTURER_INFO_FILE);
                } catch (IOException e) {
                        System.err.println("Error writing lecturer info to file: " + e.getMessage());
                }
        }

        private boolean authenticate(String staffId, String password) {
                try (FileReader reader = new FileReader(LECTURER_INFO_FILE)) {
                        JSONTokener tokener = new JSONTokener(reader);
                        JSONObject root = new JSONObject(tokener);

                        String storedStaffId = root.optString(JSON_STAFF_ID_KEY, "");
                        String storedPassword = root.optString("password", ""); // **Get stored password**

                        // In real-world hashing and salt passwords!!!
                        return storedStaffId.equals(staffId) && storedPassword.equals(password);

                } catch (IOException e) {
                        System.err.println("Error reading lecturer info: " + e.getMessage());
                        return false; // Assume authentication fails if there's an error reading the file
                }
        }

        private void loadDashboard() {
                // **After successful login, load the dashboard
                LecturerDashboard dashboard = new LecturerDashboard();
                dashboard.start(primaryStage);
        }

        private void showAlert(String title, String message) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle(title);
                alert.setHeaderText(null);
                alert.setContentText(message);
                alert.showAndWait();
        }

        public static void main(String[] args) {
                launch(args);
        }
}
